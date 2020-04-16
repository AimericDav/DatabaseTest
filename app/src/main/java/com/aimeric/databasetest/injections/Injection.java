package com.aimeric.databasetest.injections;

import android.content.Context;

import com.aimeric.databasetest.database.dao.UserDatabase;
import com.aimeric.databasetest.repositories.UserDataRepository;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Injection {

    // 1 - On retourne un repository avec ma databse
    public static UserDataRepository provideUserDataSource(Context context) {
        UserDatabase database = UserDatabase.getInstance(context);
        return new UserDataRepository(database.mUserDao());
    }

    // 2 - On return un executor
    public static Executor provideExecutor() { return Executors.newSingleThreadExecutor(); }

    // 3 - On return notre factory avec le tout
    public static ViewModelFactory provideViewModelFactory(Context context) {
        UserDataRepository dataSourceUser = provideUserDataSource(context);
        Executor executor = provideExecutor();
        return new ViewModelFactory(dataSourceUser, executor);
    }

}
