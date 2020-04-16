package com.aimeric.databasetest;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aimeric.databasetest.models.User;

public class UserViewHolder extends RecyclerView.ViewHolder {

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void updateNameOfUser(User user) {
        TextView textView = itemView.findViewById(R.id.name);
        textView.setText(user.getUsername());
    }

}
