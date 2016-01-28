package com.viorsan.listviewdemo.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.viorsan.listviewdemo.Models.DATE_TYPE;
import com.viorsan.listviewdemo.Models.Visitor;
import com.viorsan.listviewdemo.Models.VisitorGroup;
import com.viorsan.listviewdemo.R;

import java.util.ArrayList;

/**
 * Created by Dmitriy Kazimirov, e-mail:dmitriy.kazimirov@viorsan.com on 26.01.16.
 */
public class VisitorListAdapter extends BaseAdapter {
    public static final String TAG = VisitorListAdapter.class.getName();
    private ArrayList<VisitorGroup> items;
    private Context context;

    //viewHolder для кэширования строк с данными
    static class ViewHolder {
        TextView nameTextView;
        TextView detailsTextView;
    }
    public VisitorListAdapter(Context context, ArrayList<VisitorGroup> items) {
        super();
        this.items=items;
        this.context=context;

    }

    /**
     * Общее количество рядов в списке
     * @return Общее количество рядов в списке
     */
    @Override
    public int getCount() {
        int count=0;
        for (VisitorGroup group:items) {
            count=count+1;//за заголовок группы
            count=count+group.getVisitors().size();//за содержимое группы
        }
        return count;
    }

    /***
     * Количество разных вариантов элементов ListView
     * @return Количество разных вариантов элементов ListView
     */
    @Override
    public int getViewTypeCount() {
        return 2;
    }

    /**
     *
     * Возвращает модель для заданныой позиции
     * Мы можем вернуть либо String с заголовком секции
     * либо Visitor с данные для показа строки посещения
     * а если все равно не доходит - читаем Busy Coder's Guide for Android p.715 (глава про Advanced List Views)
     *
     * @param position позиция
     * @return модель для заданной позиции
     */
    @Override
    public Object getItem(int position) {
        int offset=position;
        int index=0;
        for (VisitorGroup group:items) {
            if (offset==0) {
                //заголовок группы
                return new String(group.getDateType().getStringValue(context));
            }
            offset--;
            Visitor[] array=new Visitor[group.getVisitors().size()];
            array=(Visitor[]) group.getVisitors().toArray(array);

            if (offset<array.length) {
                //есть в этой группе
                return array[offset];
            }
            offset-=array.length;
            index++;
        }
        throw new IllegalArgumentException("Invalid position:"+String.valueOf(position));
    }
    /**
     * Идентификатор позиции (если бы мы с базой данных работале - ID-поле можно)
     * пока просто саму позицию
     * @param position позиция
     * @return идентификатор позиции
     */
    @Override
    public long getItemId(int position) {
        return position;
    }
    /***
     * Тип обьекта для заданной позиции
     * @param position позиция
     * @return тип обьекта
     */
    @Override
    public int getItemViewType(int position) {
        if (getItem(position) instanceof String) {
            return 0;
        }
        return 1;
    }

    private View getHeaderView(int position, View convertView, ViewGroup parent) {
        View rowView;
        rowView=convertView;
        if (rowView==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView=inflater.inflate(R.layout.header,parent,false);
        }
        String header=(String)getItem(position);
        TextView label=(TextView)rowView.findViewById(R.id.label);
        label.setTypeface(label.getTypeface(), Typeface.BOLD);
        label.setText(header);
        return  rowView;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView;
        ViewHolder holder;
        if (getItemViewType(position)==0) {
            return getHeaderView(position,convertView,parent);
        }
        ///есть ли Viewholder в кеше
        if (convertView!=null) {
            ///есть - просто прочитаем
            rowView=convertView;
            holder=(ViewHolder)rowView.getTag();
        } else {
            //нет - inflate'им им
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.sublist, parent, false);
            //настроим viewHolder
            holder=new ViewHolder();
            holder.nameTextView=(TextView) rowView.findViewById(R.id.name);
            holder.detailsTextView=(TextView) rowView.findViewById(R.id.details);
            rowView.setTag(holder);
        }
        Visitor visitor=(Visitor)getItem(position);
        holder.nameTextView.setText(visitor.getName());
        holder.detailsTextView.setText(visitor.getDate().toString());

        return rowView;
    }

}


