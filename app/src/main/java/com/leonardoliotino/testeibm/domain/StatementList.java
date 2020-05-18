package com.leonardoliotino.testeibm.domain;

import com.google.gson.annotations.SerializedName;

public class StatementList {

   @SerializedName("title")
   private String title;

   @SerializedName("desc")
   private String desc;

   @SerializedName("date")
   private String date;

   @SerializedName("value")
   private Double value;


    public String getTitle() {
        return title;
    }



    public void setTitle(String title) {
        this.title = title;
    }



    public String getDesc() {
        return desc;
    }



    public void setDesc(String desc) {
        this.desc = desc;
    }



    public String getDate() {
        return date;
    }



    public void setDate(String date) {
        this.date = date;
    }



    public Double getValue() {
        return value;
    }



    public void setValue(Double value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return "StatementList{" +
                "title='" + title + '\'' +
                ", desc='" + desc + '\'' +
                ", date='" + date + '\'' +
                ", value=" + value +
                '}';
    }


}
