package com.leonardoliotino.testeibm.api;

import com.leonardoliotino.testeibm.domain.LoginResponse;
import com.leonardoliotino.testeibm.domain.StatementResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface ApiMethodRetrofit {

    @FormUrlEncoded
    @POST("/api/login")
    Call<LoginResponse> login(@Field("user") String user, @Field("password") String password);


    @GET("statements/{userId}")
    Call<StatementResponse> listContaSelecionada(@Path("userId") int userId);

}
