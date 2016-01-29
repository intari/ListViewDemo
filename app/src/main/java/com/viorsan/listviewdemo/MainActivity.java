package com.viorsan.listviewdemo;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.viorsan.listviewdemo.Adapters.VisitorListAdapter;
import com.viorsan.listviewdemo.Models.DATE_TYPE;
import com.viorsan.listviewdemo.Models.Visitor;
import com.viorsan.listviewdemo.Models.VisitorGroup;
import com.viorsan.listviewdemo.Models.Visitors;

import java.util.ArrayList;

public class MainActivity extends ListActivity {
    public static final String TAG = MainActivity.class.getName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //подготовим данные, в фоне
        SortAndPrepareTask task=new SortAndPrepareTask();
        task.execute();
    }



    private class SortAndPrepareTask extends AsyncTask<Void,Void,Void> {
        private ProgressDialog progress=new ProgressDialog(MainActivity.this);

        private VisitorListAdapter today;
        private VisitorListAdapter yesterday;
        private VisitorListAdapter twodaysago;
        private VisitorListAdapter threedaysago;
        private VisitorListAdapter before;
        private VisitorGroup todayGroup;
        private VisitorGroup yesterdayGroup;
        private VisitorGroup twoDaysAgoGroup;
        private VisitorGroup threeDaysAgoGroup;
        private VisitorGroup beforeGroup;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //покажим что мы работает
            progress.setMessage(getString(R.string.loadingData));
            progress.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progress.setIndeterminate(true);
            progress.show();

        }
        @Override
        protected Void doInBackground(Void...params) {
            //сортируем хотя на самом деле это и НЕ НУЖНО (учитывая как выполняется генерация)
            Visitors.get().sort();
            //делаем выборки
            todayGroup=new VisitorGroup(
                    DATE_TYPE.TODAY,
                    Visitors.get().getPeopleVisitedAt(DATE_TYPE.TODAY)
            );
            yesterdayGroup=new VisitorGroup(
                    DATE_TYPE.YESTERDAY,
                    Visitors.get().getPeopleVisitedAt(DATE_TYPE.YESTERDAY)
            );
            twoDaysAgoGroup=new VisitorGroup(
                    DATE_TYPE.TWO_DAYS_AGO,
                    Visitors.get().getPeopleVisitedAt(DATE_TYPE.TWO_DAYS_AGO)
            );
            threeDaysAgoGroup=new VisitorGroup(
                    DATE_TYPE.THREE_DAYS_AGO,
                    Visitors.get().getPeopleVisitedAt(DATE_TYPE.THREE_DAYS_AGO)
            );
            beforeGroup=new VisitorGroup(
                    DATE_TYPE.BEFORE,
                    Visitors.get().getPeopleVisitedAt(DATE_TYPE.BEFORE)
            );

            for (Visitor v:Visitors.get().getPeople()) {
                Log.d(TAG,v.getDateType().getStringValue(MainActivity.this)+ " "+ v.getName());
            }

            return null;
        };

        @Override
        protected void onPostExecute(Void result) {
            //обновляем GUI


            ArrayList<VisitorGroup> groups=new ArrayList<>();
            groups.add(todayGroup);
            groups.add(yesterdayGroup);
            groups.add(twoDaysAgoGroup);
            groups.add(threeDaysAgoGroup);
            groups.add(beforeGroup);


            VisitorListAdapter visitorListAdapter=new VisitorListAdapter(
                    MainActivity.this,groups);

            setListAdapter(visitorListAdapter);
            //разделитель нам не нужен
            //getResources().getColor() deprecated на Android 6.0
            getListView().setDivider(new ColorDrawable(ContextCompat.getColor(MainActivity.this,android.R.color.transparent)));
            getListView().setDividerHeight(0);
            //все, убираем Progress Bar
            progress.dismiss();
        }
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
*/

}
