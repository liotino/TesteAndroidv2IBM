package com.leonardoliotino.testeibm.api;

import com.leonardoliotino.testeibm.domain.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiMethodRetrofit {

    @FormUrlEncoded
    @POST("/api/login")
    Call<LoginResponse> login(@Field("user") String user, @Field("password") String password);

}
