package com.example.testapp.model;

import android.annotation.SuppressLint;
import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.testapp.utils.InjectionUtils;
import com.example.testapp.viewmodel.LoginViewModel;
import com.example.testapp.viewmodel.StatementsListViewModel;

public class UserViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    @SuppressLint("StaticFieldLeak")
    private static volatile UserViewModelFactory INSTANCE;

    private final Application mApplication;

    private final UserRepository mUserRepository;

    public static UserViewModelFactory getInstance(Application application) {

        if (INSTANCE == null) {
            synchronized (UserViewModelFactory.class) {
                if (INSTANCE == null) {
                    Log.e(UserViewModelFactory.class.getName(),"instance found null");
                    INSTANCE = new UserViewModelFactory(application,
                            InjectionUtils.provideUserDetailsRepository(application.getApplicationContext()));
                }
            }
        }
        return INSTANCE;
    }

    private UserViewModelFactory(Application application, UserRepository repository) {
        mApplication = application;
        mUserRepository = repository;
    }

    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            //noinspection unchecked
            return (T) new LoginViewModel(mApplication, mUserRepository);
        } else if(modelClass.isAssignableFrom(StatementsListViewModel.class)){
            return (T) new StatementsListViewModel(mApplication,mUserRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
