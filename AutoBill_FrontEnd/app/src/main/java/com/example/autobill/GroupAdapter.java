package com.example.autobill;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {


    public List<Map<String, Object>> list=new ArrayList<>();
    public Context con;
    public LayoutInflater inflater;
    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public GroupAdapter(List<Map<String, Object>> list, Context con) {
        this.con = con;
        this.list = list;
        inflater = LayoutInflater.from(con);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_items, null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.group_iv.setText(String.valueOf(position + 1));
        holder.group_name.setText(list.get(position).get("Name").toString());
//        holder.group_user_list.setText(list.get(position).get("List").toString());

        if(mOnItemClickListener != null) {
            holder.itemLayout.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onItemClick(view, position);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView group_name, group_user_list, group_iv;
        RelativeLayout itemLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            group_name = itemView.findViewById(R.id.group_name);
            group_user_list = itemView.findViewById(R.id.group_user_list);
            itemLayout = itemView.findViewById(R.id.list_items);
            group_iv = itemView.findViewById(R.id.group_iv);
        }
    }
}
