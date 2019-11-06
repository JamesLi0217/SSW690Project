package com.example.autobill;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import static android.view.LayoutInflater.from;

public class ListAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public ListAdapter(Context context){
        this.mContext = context;
        mLayoutInflater = from(context);

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    static class ViewHolder{
        public ImageView imageView;
        public TextView tvTextView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if(convertView == null){
            convertView = mLayoutInflater.inflate(R.layout.list_items,null);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.iv);
            holder.tvTextView = convertView.findViewById(R.id.tv);
        }
        return null;
        //列表中每行的样式
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}
