package com.leonardoliotino.testeibm.domain;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

  @SerializedName("userAccount")
  public UserAccount userAcount;

  @SerializedName("error")
  public Error error;


}
