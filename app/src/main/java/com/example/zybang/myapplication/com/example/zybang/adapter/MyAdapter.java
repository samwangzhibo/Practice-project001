package com.example.zybang.myapplication.com.example.zybang.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.zybang.myapplication.R;
import com.example.zybang.myapplication.ui.activity.WordsActivity;

import java.util.ArrayList;

/**
 * Created by zybang on 2016/3/3.
 */
public class MyAdapter extends BaseAdapter {
    private ArrayList<String> datas;
    Context ctx;

    public MyAdapter(ArrayList<String> datas, Context ctx) {
        this.datas = datas;
        this.ctx = ctx;
    }

    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas == null ? null : datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder = null;
        if (view == null) {
            view = LayoutInflater.from(ctx).inflate(R.layout.item_style, null);
            viewHolder = new ViewHolder();
            viewHolder.tv = (TextView) view.findViewById(R.id.item_tv);
            viewHolder.tv.setText(datas.get(position));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(ctx.getApplicationContext(), WordsActivity.class);
                    if (datas.get(position) != null)
                        it.putExtra("word", datas.get(position));
                    ctx.startActivity(it);
                }
            });
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
            viewHolder.tv.setText(datas.get(position));
        }
        return view;
    }

    public static class ViewHolder {
        private TextView tv;
    }
}
