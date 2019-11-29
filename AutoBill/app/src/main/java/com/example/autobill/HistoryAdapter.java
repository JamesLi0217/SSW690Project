package com.example.autobill;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.LinearViewHolder> {
    private Context mContext;

    public HistoryAdapter(Context context) {
        this.mContext = context;
    }

    @NonNull
    @Override
    public HistoryAdapter.LinearViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LinearViewHolder(LayoutInflater.from(mContext).inflate(R.layout.history_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.LinearViewHolder holder, int position) {
        holder.textView.setText("History " + position);

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
//            imageView = itemView.findViewById(R.id.friends_iv);
            textView = itemView.findViewById(R.id.history_name);
        }
    }
}
