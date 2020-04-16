package com.aimeric.databasetest;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.aimeric.databasetest.models.User;
import com.aimeric.databasetest.repositories.UserDataRepository;

import java.util.List;
import java.util.concurrent.Executor;

public class UserViewModel extends ViewModel {

    // 1 - Declare les repository
    private final UserDataRepository userDataSource;

    // 2 - Declare un executor
    private final Executor executor;

    // 3 - Création du constructeur
    public UserViewModel(UserDataRepository userDataSource, Executor executor) {
        this.userDataSource = userDataSource;
        this.executor = executor;
    }

    // 4 - Utilisation des repository
    public LiveData<List<User>> getAllUsers() {
        return userDataSource.getAllUsers();
    }
    public void insertUser(User user) {
        executor.execute(() -> {
            userDataSource.insertUser(user);
        });
    }
    public void deleteUser(long id) {
        executor.execute(() -> {
            userDataSource.deleteUser(id);
        });
    }

}
