package com.viorsan.mergeadapterdemo;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.commonsware.cwac.merge.MergeAdapter;
import com.viorsan.mergeadapterdemo.Adapters.VisitorListAdapter;
import com.viorsan.mergeadapterdemo.Models.DATE_TYPE;
import com.viorsan.mergeadapterdemo.Models.Visitors;

public class MainActivity extends ListActivity {
    public static final String TAG = MainActivity.class.getName();

    private MergeAdapter adapter=null;

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
            //получаем списки (там проходы по списку)
            today=buildListForDateType(DATE_TYPE.TODAY);
            yesterday=buildListForDateType(DATE_TYPE.YESTERDAY);
            twodaysago=buildListForDateType(DATE_TYPE.TWO_DAYS_AGO);
            threedaysago=buildListForDateType(DATE_TYPE.THREE_DAYS_AGO);
            before=buildListForDateType(DATE_TYPE.BEFORE);

            return null;
        };

        @Override
        protected void onPostExecute(Void result) {
            //обновляем GUI
            //используем merge adapter от CommonsWare
            adapter=new MergeAdapter();
            adapter.addView(buildLabel(R.string.today));
            adapter.addAdapter(today);
            adapter.addView(buildLabel(R.string.yesterday));
            adapter.addAdapter(yesterday);
            adapter.addView(buildLabel(R.string.twodaysago));
            adapter.addAdapter(twodaysago);
            adapter.addView(buildLabel(R.string.threedaysago));
            adapter.addAdapter(threedaysago);
            adapter.addView(buildLabel(R.string.before));
            adapter.addAdapter(before);


            setListAdapter(adapter);
            //разделитель нам не нужен
            //getResources().getColor() deprecated на Android 6.0
            getListView().setDivider(new ColorDrawable(ContextCompat.getColor(MainActivity.this,android.R.color.transparent)));
            getListView().setDividerHeight(0);
            //все, убираем Progress Bar
            progress.dismiss();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Адаптер для списка для посещенных в заданную дату
     * @param dateType
     * @return
     */
    private VisitorListAdapter buildListForDateType(DATE_TYPE dateType) {
        return new VisitorListAdapter(
                this,
                Visitors.get().getPeopleVisitedAt(dateType)
    );
        //в принципе конечно с одним элементом в данном случае можно было и simple_list_item_1
        /*
        return new ArrayAdapter<String>(
                this,
                //R.layout.sublist,
                android.R.layout.simple_list_item_1,
                result
        );*/

    }


    /**
     * Создаем TextView с заданным текстом (для использования в качестве разделителя)
     * @param resId ресурс с текстом
     * @return
     */
    private View buildLabel(int resId) {
        TextView result=new TextView(this);
        result.setText(resId);
        //задаем выделение
        result.setTypeface(result.getTypeface(), Typeface.BOLD);
        return(result);
    }


}
