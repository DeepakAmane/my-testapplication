package com.example.testapp.model;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.testapp.network.RetrofitServices;
import com.example.testapp.network.response.LoginResponse;
import com.example.testapp.network.response.StatementResponse;
import com.example.testapp.network.response.UserAccount;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/***
 * The UserRepository acts as repository for the user authentication
 * and fetching the User statement List and send back the required data back to Activityies via Viewmodel
 */
public class UserRepository {

    private MutableLiveData<LoginResponse> mUserLoginResponse;
    private MutableLiveData<StatementResponse> mStatementListResponse;

    private static UserRepository INSTANCE = null;
    private Retrofit mRetrofit;
    private RetrofitServices retrofitServices;

    // Prevent direct instantiation.
    private UserRepository(Retrofit retrofit) {
        Log.e(UserRepository.class.getName(), "Const");
        mUserLoginResponse = new MutableLiveData<>();
        mStatementListResponse = new MutableLiveData<>();
        this.mRetrofit = retrofit;
        retrofitServices = mRetrofit.create(RetrofitServices.class);
    }

    /**
     * Returns the single instance of this class, creating it if necessary.
     *
     * @param retrofit
     */
    public static UserRepository getInstance(Retrofit retrofit) {
        if (INSTANCE == null) {
            INSTANCE = new UserRepository(retrofit);
        }
        return INSTANCE;
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
