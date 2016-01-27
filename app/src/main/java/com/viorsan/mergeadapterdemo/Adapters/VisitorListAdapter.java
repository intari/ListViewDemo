package com.viorsan.mergeadapterdemo.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.viorsan.mergeadapterdemo.Models.Visitor;
import com.viorsan.mergeadapterdemo.R;

import java.util.ArrayList;

/**
 * Created by Dmitriy Kazimirov, e-mail:dmitriy.kazimirov@viorsan.com on 26.01.16.
 */
public class VisitorListAdapter extends ArrayAdapter<Visitor> {
    public static final String TAG = VisitorListAdapter.class.getName();
    private ArrayList<Visitor> items;
    private Context context;

    //viewHolder для кэширования
    static class ViewHolder {
        TextView nameTextView;
        TextView detailsTextView;
    }
    public VisitorListAdapter(Context context, ArrayList<Visitor> items) {
        super(context,R.layout.sublist,items);
        this.items=items;
        this.context=context;

    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView;
        ViewHolder holder;
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
        holder.nameTextView.setText(items.get(position).getName());
        holder.detailsTextView.setText(items.get(position).getDate().toString());
        return rowView;
    }

}


