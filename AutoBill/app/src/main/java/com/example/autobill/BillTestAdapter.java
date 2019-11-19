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

public class BillTestAdapter extends RecyclerView.Adapter<BillTestAdapter.ViewHolder> {

    public List<Map<String, Object>> list=new ArrayList<>();
    public Context con;
    public LayoutInflater inflater;

    public BillTestAdapter(List<Map<String, Object>> list, Context con) {
        this.con = con;
        this.list = list;
        inflater = LayoutInflater.from(con);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.bill_test_item, null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.item_name.setText(list.get(position).get("Name").toString());
        holder.item_money.setText(list.get(position).get("Money").toString());
        holder.item_date.setText(list.get(position).get("Time").toString());
        holder.item_user.setText(list.get(position).get("List").toString());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView item_name, item_money, item_date, item_user;
        public ViewHolder(View itemView) {
            super(itemView);
            item_name = itemView.findViewById(R.id.item_name);
            item_money = itemView.findViewById(R.id.item_money);
            item_date = itemView.findViewById(R.id.item_date);
            item_user = itemView.findViewById(R.id.item_user);
        }
    }
}
