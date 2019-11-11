package com.example.testapp.utils;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.testapp.model.UserRepository;
import com.example.testapp.network.RetrofitFactory;

import static androidx.core.util.Preconditions.checkNotNull;

public class InjectionUtils {

    public static UserRepository provideUserDetailsRepository(@NonNull Context context) {
        checkNotNull(context);
        // create a retrfot instance and send as parameter to the repostiory
        return UserRepository.getInstance(RetrofitFactory.getRetrofitInstance());
    }
}
