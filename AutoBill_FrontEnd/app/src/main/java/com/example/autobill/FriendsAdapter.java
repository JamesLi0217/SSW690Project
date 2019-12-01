package com.example.autobill;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder> {

    public List<Map<String, Object>> list=new ArrayList<>();
    public Context con;
    public LayoutInflater inflater;
    public FriendsAdapter(List<Map<String, Object>> list, Context con) {
        this.con = con;
        this.list = list;
        inflater = LayoutInflater.from(con);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.friends_list_items, null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.friends_iv.setText(String.valueOf(position +1));
        holder.friend_name.setText(list.get(position).get("Name").toString());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView friend_name, friends_iv;
        public ViewHolder(View itemView) {
            super(itemView);
            friend_name = itemView.findViewById(R.id.friends_name);
            friends_iv = itemView.findViewById(R.id.friends_iv);
        }
    }
}
