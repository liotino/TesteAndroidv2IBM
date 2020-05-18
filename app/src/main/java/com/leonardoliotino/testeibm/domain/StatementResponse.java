package com.leonardoliotino.testeibm.domain;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StatementResponse {

 @SerializedName("statementList")
 public List<StatementList> statementListList;

 @SerializedName("error")
 public Error error;


}
