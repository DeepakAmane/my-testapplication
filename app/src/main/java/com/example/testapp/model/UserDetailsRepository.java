package com.example.testapp.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.testapp.network.RetrofitFactory;
import com.example.testapp.network.RetrofitServices;
import com.example.testapp.network.response.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/***
 * The UserDetailsRepository acts as repository
 */
public class UserDetailsRepository {

    private MutableLiveData<LoginResponse> mUserLoginResponse;

    private Retrofit retrofit;
    private RetrofitServices retrofitServices;

    public UserDetailsRepository() {
        mUserLoginResponse = new MutableLiveData<>();
        Log.e(UserDetailsRepository.class.getName(), "Cont");
        initRetrofit();
    }

    private void initRetrofit() {
        retrofit = RetrofitFactory.getRetrofit();
        retrofitServices = retrofit.create(RetrofitServices.class);
    }

    /**
     * Perform User Login autentication via API
     **/
    public void loginUser(String email, String password) {

        Call<LoginResponse> call = retrofitServices.isValidUser(email, password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if (response != null) {
                    Log.e("Succcess", "Success");
                    mUserLoginResponse.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public LiveData<LoginResponse> getLoginUser() {
        return mUserLoginResponse;
    }
}
