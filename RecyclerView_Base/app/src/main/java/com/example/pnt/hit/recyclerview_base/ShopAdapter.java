package com.example.pnt.hit.recyclerview_base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ViewHolder> {

    List<DataShop> list;
    Context context;

    public ShopAdapter(List<DataShop> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View itemView = layoutInflater.inflate(R.layout.item_row, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName.setText(list.get(position).getName());
        holder.img.setImageResource(list.get(position).getImg());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void RemoveItem(int i){
        list.remove(i);
        notifyItemRemoved(i);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtName;
        ImageView img;

        public ViewHolder(View itemView){
            super(itemView);

            txtName = itemView.findViewById(R.id.textView);
            img = itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    RemoveItem(getAdapterPosition());
                    Toast.makeText(itemView.getContext(), "Remove", Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
