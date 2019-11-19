package com.example.autobill;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestpageAdapter extends RecyclerView.Adapter<TestpageAdapter.ViewHolder> {

    public List<Map<String, Object>> list=new ArrayList<>();
    public Context con;
    public LayoutInflater inflater;
    public TestpageAdapter(List<Map<String, Object>> list, Context con) {
        this.con = con;
        this.list = list;
        inflater = LayoutInflater.from(con);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.testpage_item, null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.testpage_id.setText(list.get(position).get("ID").toString());
        holder.testpage_name.setText(list.get(position).get("Name").toString());
        holder.testpage_list.setText(list.get(position).get("List").toString());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView testpage_id, testpage_name, testpage_list;
        public ViewHolder(View itemView) {
            super(itemView);
            testpage_id = itemView.findViewById(R.id.tp_group_id);
            testpage_name = itemView.findViewById(R.id.tp_group_name);
            testpage_list = itemView.findViewById(R.id.tp_user_list);
        }
    }
}
