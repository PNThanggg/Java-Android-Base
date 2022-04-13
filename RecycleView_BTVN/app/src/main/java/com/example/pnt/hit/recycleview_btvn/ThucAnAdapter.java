package com.example.pnt.hit.recycleview_btvn;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ThucAnAdapter extends RecyclerView.Adapter<ThucAnAdapter.ViewHolder> {

    List<ThucAn> list;
    Context context;

    public ThucAnAdapter(List<ThucAn> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View itemView = layoutInflater.inflate(R.layout.item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.hinh.setImageResource(list.get(position).getImgHinh());
        holder.txtTen.setText(list.get(position).getTen());
        holder.txtMota.setText(list.get(position).getMoTa());
        holder.txtGia.setText(list.get(position).getGia());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView hinh;
        TextView txtTen, txtMota, txtGia;

        public ViewHolder(View itemView){
            super(itemView);
            final int[] i = {0};

            hinh = itemView.findViewById(R.id.imgHinh);
            txtTen = itemView.findViewById(R.id.txtTen);
            txtMota = itemView.findViewById(R.id.txtMoTa);
            txtGia = itemView.findViewById(R.id.txtGia);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    i[0] += 1;
                    if(i[0] % 2 == 1) itemView.setBackgroundColor(Color.GRAY);
                    else itemView.setBackgroundColor(Color.BLACK);
                }
            });
        }
    }
}
