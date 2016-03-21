package com.blenmaterial.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.blenmaterial.R;

import java.util.ArrayList;

/**
 * 一个简单的只展示名字的ReciclerViewAdapter,使用代码创建用来展示的条目的TextView
 * 目前textview没能居中
 *
 * Created by Blensmile on 2016/3/20.
 */
public class SimpleRcContactsAdapter extends RecyclerView.Adapter<SimpleRcContactsAdapter.Holder> {

    private Context context;
    private ArrayList<String> list;

    public SimpleRcContactsAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        TextView view = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.textView.setText(list.get(position));
        //            holder.textView.setBackgroundColor(0x80ff0000);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        public TextView textView;


        public Holder(TextView view) {
            super(view);
            this.textView =  view;

        }
    }

}
