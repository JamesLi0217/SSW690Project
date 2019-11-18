package com.example.autobill;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class GroupLinearAdapter extends RecyclerView.Adapter<GroupLinearAdapter.LinearViewHolder> {

    private Context mContext;

    public GroupLinearAdapter(Context context) {
        this.mContext = context;
    }
    @NonNull
    @Override
    public GroupLinearAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.list_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GroupLinearAdapter.LinearViewHolder holder, int position) {
        holder.textView.setText("Group Name");

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    class LinearViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ImageView imageView;
        public LinearViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.group_iv);
            textView = itemView.findViewById(R.id.group_name);
        }
    }
}
