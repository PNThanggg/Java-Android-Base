package com.example.hit.pnt.retrofitbase.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.hit.pnt.retrofitbase.R;
import com.example.hit.pnt.retrofitbase.moedl.Item;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder>{
    private final List<Item> items;

    public ItemAdapter(List<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);

        return new ItemViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        Item item = items.get(position);

        holder.userId.setText(Integer.valueOf(item.getUserId()).toString());
        holder.id.setText(Integer.valueOf(item.getId()).toString());
        holder.title.setText(item.getTitle());
        holder.body.setText(item.getBody());
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView id, userId, title, body;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            userId = itemView.findViewById(R.id.txtUserId);
            id = itemView.findViewById(R.id.txtId);
            title = itemView.findViewById(R.id.txtTitle);
            body = itemView.findViewById(R.id.txtBody);
        }
    }
}
