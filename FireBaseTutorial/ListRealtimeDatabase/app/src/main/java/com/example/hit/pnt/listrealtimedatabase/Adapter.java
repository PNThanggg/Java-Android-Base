package com.example.hit.pnt.listrealtimedatabase;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.UserViewHolder> {

    private List<User> list;
    private IClickListener mIClickListener;

    public interface IClickListener {
        void onClickUpdateItem(User user);
    }

    public Adapter(List<User> list, IClickListener iClickListener) {
        this.list = list;
        this.mIClickListener = iClickListener;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new UserViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = list.get(position);
        if(user == null) {
            return;
        }

        holder.id.setText("Id: " + user.getId());
        holder.name.setText("Name: " + user.getName());
        holder.button.setOnClickListener(v -> mIClickListener.onClickUpdateItem(user));
    }


    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView id, name;
        private Button button;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.name);
            button = itemView.findViewById(R.id.button2);
        }
    }
}
