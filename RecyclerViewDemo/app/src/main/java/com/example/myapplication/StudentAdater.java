package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StudentAdater extends RecyclerView.Adapter<StudentAdater.ViewHolder> {
    List<Student> list;
    IOnClickStudent iOnClickStudent;

    public void setOnClick(IOnClickStudent onClick) {
        this.iOnClickStudent = onClick;
    }

    public StudentAdater(List<Student> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public StudentAdater.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentAdater.ViewHolder holder, int position) {
        Student s = list.get(position);

        holder.imgAvatar.setImageResource(s.getAvatar());
        holder.tvName.setText(s.getName());
        holder.tvCode.setText(s.getCode());

        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnClickStudent.onClickName(s);
            }
        });

        holder.imgAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iOnClickStudent.onClickImage(s);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAvatar;
        TextView tvName, tvCode;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgAvatar = itemView.findViewById(R.id.imgAvatar);
            tvName = itemView.findViewById(R.id.tvName);
            tvCode = itemView.findViewById(R.id.tvCode);
        }
    }
}
