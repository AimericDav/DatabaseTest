package com.aimeric.databasetest.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.aimeric.databasetest.models.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    LiveData<List<User>> getAllUser();

    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM user WHERE id = :id")
    long deleteUser(long id);

}
