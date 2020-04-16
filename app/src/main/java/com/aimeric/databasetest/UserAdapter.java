package com.aimeric.databasetest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aimeric.databasetest.models.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    private List<User> mUserList;

    public UserAdapter(List<User> userList) {
        mUserList = userList;
    }

    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
       User currentUser = mUserList.get(position);
       holder.updateNameOfUser(currentUser);
    }

    @Override
    public int getItemCount() {
        return mUserList.size();
    }

    public void updateData(List<User> users){
        this.mUserList = users;
        this.notifyDataSetChanged();
    }

}
