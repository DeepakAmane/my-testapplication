package com.example.testapp.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.testapp.network.RetrofitFactory;
import com.example.testapp.network.RetrofitServices;
import com.example.testapp.network.response.LoginResponse;
import com.example.testapp.network.response.StatementResponse;
import com.example.testapp.network.response.UserAccount;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Repository for StatementsList
 **/
public class StatementsRepository {

    private MutableLiveData<StatementResponse> mStatementListResponse;

    private Retrofit retrofit;
    private RetrofitServices retrofitServices;

    public StatementsRepository() {
        mStatementListResponse = new MutableLiveData<>();
        Log.e(UserDetailsRepository.class.getName(), "Cont");
        initRetrofit();
    }

    private void initRetrofit() {
        retrofit = RetrofitFactory.getRetrofit();
        retrofitServices = retrofit.create(RetrofitServices.class);
    }


    /***
     * API call for fethcing the Statements List for the Respective USer ID
     * @param userAccount
     */
    public void fetchStatementList(UserAccount userAccount) {
        Call<StatementResponse> call2 = retrofitServices.doGetUserStatementList("https://bank-app-test.herokuapp.com/api/statements/" + userAccount.getUserId());

        //  Call<StatementResponse> call2 = retrofitServices.doGetUserStatementList(String.valueOf( response.body().getUserAccount().getUserId()));

        call2.enqueue(new Callback<StatementResponse>() {
            @Override
            public void onResponse(Call<StatementResponse> call, Response<StatementResponse> response) {
                StatementResponse result = response.body();
                if (result != null) {
                    Log.e("Succcess", "Success");
                    mStatementListResponse.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<StatementResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public LiveData<StatementResponse> getStatementList() {
        return mStatementListResponse;
    }
}
