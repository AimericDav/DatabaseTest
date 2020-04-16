package com.aimeric.databasetest.repositories;

import androidx.lifecycle.LiveData;

import com.aimeric.databasetest.database.dao.UserDao;
import com.aimeric.databasetest.models.User;

import java.util.List;

public class UserDataRepository {

    // 1 - Je déclare mon DAO
    private final UserDao mUserDao;

    // 2 - J'add le constructeur
    public UserDataRepository(UserDao userDao) { this.mUserDao = userDao; }

    // 3 - J'utilise mes methode de mon DAO
    public LiveData<List<User>> getAllUsers() { return this.mUserDao.getAllUser(); }
    public void  insertUser(User user) { this.mUserDao.insertUser(user); }
    public void  deleteUser(long id) { this.mUserDao.deleteUser(id); }

}
