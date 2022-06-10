package com.example.hit.pnt.searchviewfilter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> implements Filterable {

    private List<User> userList;
    private final List<User> userListOld;

    public UserAdapter(List<User> userList) {
        this.userList = userList;
        this.userListOld = userList;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);

        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = userList.get(position);

        if(user == null) return;

        holder.imgUser.setImageResource(user.getImage());
        holder.txtName.setText(user.getName());
        holder.txtAddress.setText(user.getAddress());
    }

    @Override
    public int getItemCount() {
        return userList == null ? 0 : userList.size();
    }

    public static class UserViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView imgUser;
        private TextView txtName, txtAddress;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);

            imgUser = itemView.findViewById(R.id.image);
            txtName = itemView.findViewById(R.id.txtName);
            txtAddress = itemView.findViewById(R.id.txtAddress);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();

                if(strSearch.isEmpty()) {
                    userList = userListOld;
                } else {
                    List<User> list = new ArrayList<>();

                    for(User user : userListOld) {
                        if(user.getName().toLowerCase().contains(strSearch.toLowerCase())) {
                            list.add(user);
                        }
                    }

                    userList = list;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = userList;

                return  filterResults;
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                userList = (List<User>) results.values;

                notifyDataSetChanged();
            }
        };
    }
}
