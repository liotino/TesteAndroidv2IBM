package com.leonardoliotino.testeibm.api;

import com.leonardoliotino.testeibm.domain.userAccount;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiMethodRetrofit {

    @FormUrlEncoded
    @POST("/app/login")
    userAccount login(@Field("email") String email, @Field("password") String password);

}
