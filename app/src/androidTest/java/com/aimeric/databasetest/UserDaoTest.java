package com.aimeric.databasetest;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.aimeric.databasetest.database.dao.UserDatabase;
import com.aimeric.databasetest.models.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.ExecutionException;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4.class)
public class UserDaoTest {

    // 1 - Déclare notre DataBase
    private UserDatabase mDatabase;

    // 5 - Data pour les tests
    private static User USER_DEMO = new User("Aimeric");

    // 2 - Règle
    @Rule
    public InstantTaskExecutorRule mInstantTaskExecutorRule = new InstantTaskExecutorRule();

    // 3 - Avant le test on creer la database
    @Before
    public void initDb() throws Exception {
        this.mDatabase = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().getContext(),
                UserDatabase.class)
                .allowMainThreadQueries()
                .build();
    }

    @After
    // 4 - Après le test, fermer la databse
    public void closeDb() throws Exception {
        mDatabase.close();
    }

    @Test
    public void insertUser() throws InterruptedException {

        // 6 - Je verifie que la database est vide
        assertEquals(0, LiveDataTestUtil.getValue(this.mDatabase.mUserDao().getAllUser()).size());

        // 7 - J'ajoute un nouvel user
        this.mDatabase.mUserDao().insertUser(USER_DEMO);

        // 8 - Je verifie que la database est vide
        assertEquals(1, LiveDataTestUtil.getValue(this.mDatabase.mUserDao().getAllUser()).size());

    }

    @Test
    public void deleteUser() throws InterruptedException {

        // 6 - Je verifie que la database est vide
        assertEquals(0, LiveDataTestUtil.getValue(this.mDatabase.mUserDao().getAllUser()).size());

        // 7 - J'ajoute un nouvel user
        this.mDatabase.mUserDao().insertUser(USER_DEMO);

        // 8 - Je verifie que la database est vide
        assertEquals(1, LiveDataTestUtil.getValue(this.mDatabase.mUserDao().getAllUser()).size());

        User user = LiveDataTestUtil.getValue(this.mDatabase.mUserDao().getAllUser()).get(0);
        this.mDatabase.mUserDao().deleteUser(user.getId());

        // 8 - Je verifie que la database est vide
        assertEquals(0, LiveDataTestUtil.getValue(this.mDatabase.mUserDao().getAllUser()).size());

    }

}
