package com.aimeric.databasetest.database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.telecom.Call;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.OnConflictStrategy;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.aimeric.databasetest.models.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

    // 1 - Déclare mon singleton (instance de ma database ici présente)
    private static volatile UserDatabase INSTANCE;

    // 2 - Déclarer DAO methode en abstract
    public abstract UserDao mUserDao();

    // 3 - Je prépare ma méthode getInstance
    public static UserDatabase getInstance(Context context) {

        if(INSTANCE == null) {
            synchronized (UserDatabase.class) {
                if(INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, UserDatabase.class, "MyDatabase.db").addCallback(prepopulateDatabase()).build();
                }
            }
        }
        return INSTANCE;
    }

    // 4 - Je réalise un callback si besoin pour remplir ma database à sa création
    private static Callback prepopulateDatabase() {
        return new Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);

                ContentValues contentValues = new ContentValues();
                contentValues.put("username", "Aimeric");
                db.insert("User", OnConflictStrategy.IGNORE, contentValues);
            }
        };
    }

}
