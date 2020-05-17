package com.leonardoliotino.testeibm.domain;

import com.google.gson.annotations.SerializedName;

public class UserAccount {

    @SerializedName("userId")
    private Integer userId;

    @SerializedName("name")
    private String name;

    @SerializedName("agency")
    private String agency;

    @SerializedName("bankAccount")
    private String bankAccount;

    @SerializedName("balance")
    private Double balance;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(String bankAccount) {
        this.bankAccount = bankAccount;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", bankAccount='" + bankAccount + '\'' +
                ", agency='" + agency + '\'' +
                ", balance=" + balance +
                '}';
    }
}
