package com.example.testapp.network;

import com.example.testapp.network.response.LoginResponse;
import com.example.testapp.network.response.StatementResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface RetrofitServices {

    @FormUrlEncoded
    @POST("/api/login")
    Call<LoginResponse> isValidUser(
            @Field("user") String user,
            @Field("password") String password);

  /*  @GET("/api/statements/")
    Call<StatementResponse> doGetUserStatementList(@Path(value = "id", encoded = true) String userId);*/

    @GET
    Call<StatementResponse> doGetUserStatementList(@Url String url);

}
