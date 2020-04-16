package com.aimeric.databasetest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.aimeric.databasetest.injections.Injection;
import com.aimeric.databasetest.injections.ViewModelFactory;
import com.aimeric.databasetest.models.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private List<User> mUserList;
    private UserAdapter mUserAdapter;

    private Button mButton;

    // 1 - Declare le view model
    UserViewModel mUserViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configureRecyclerView();
        configureViewModel();
          configureButtonAdd();
    }

    @Override
    public void onClick(View v) {

        if(v == mButton) {
            this.insertUser();
            this.mUserAdapter.notifyDataSetChanged();
        }

    }

    private void configureRecyclerView() {
        this.mUserList = new ArrayList<>();
        this.mRecyclerView = findViewById(R.id.activity_main_recycler_view);
        this.mUserAdapter = new UserAdapter(this.mUserList);
        this.mRecyclerView.setAdapter(this.mUserAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void configureViewModel() {
        ViewModelFactory mViewModelFactory = Injection.provideViewModelFactory(this);
        this.mUserViewModel = new ViewModelProvider(this, mViewModelFactory).get(UserViewModel.class);
    }

    private void getUsers() {
        this.mUserViewModel.getAllUsers().observe(this, this::updateUserList);
    }

    public void updateUserList(List<User> users) {
        this.mUserAdapter.updateData(users);
    }

    private void configureButtonAdd() {
        this.mButton = findViewById(R.id.activity_main_add_but);
        this.mButton.setOnClickListener(this);
    }

    public void insertUser() {
        User user = new User("Username");
        this.mUserViewModel.insertUser(user);
    }

}
