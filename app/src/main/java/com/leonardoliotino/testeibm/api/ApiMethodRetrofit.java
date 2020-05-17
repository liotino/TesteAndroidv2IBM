package com.leonardoliotino.testeibm.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiMethodRetrofit {

    @FormUrlEncoded
    @POST("/api/login")
    Call<ResponseBody> login(@Field("user") String user, @Field("password") String password);

}
