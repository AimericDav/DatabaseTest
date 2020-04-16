package com.aimeric.databasetest.injections;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.aimeric.databasetest.UserViewModel;
import com.aimeric.databasetest.repositories.UserDataRepository;

import java.util.concurrent.Executor;

public class ViewModelFactory implements ViewModelProvider.Factory {

    // 1 - Récuperation des repository et de l'executor
    private final UserDataRepository mUserDataRepository;
    private final Executor mExecutor;

    // 2 - Mise en place du constructeur
    public ViewModelFactory(UserDataRepository userDataRepository, Executor executor) {
        this.mUserDataRepository = userDataRepository;
        this.mExecutor = executor;
    }

    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(UserViewModel.class)) {
            return (T) new UserViewModel(mUserDataRepository, mExecutor);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
