package com.leonardoliotino.testeibm.domain;

import com.google.gson.annotations.SerializedName;

/*
  TALVEZ NAO PRECISAVA COLOCAR SERIALIZED
  MAS PRECISAVA TESTAR! PORQUE O NOME DO ATRIBUTO
  E O MESMO QUE O NOME JSON QUE VEM DA API
 */


public class Error {

    @SerializedName("code")
    private Integer code;

    @SerializedName("message")
    private String message;

}
