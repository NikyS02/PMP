package com.example.bankovnisystem;

import android.widget.TextView;

public class BankAcc {
    private String login;
    private String passwd;
    private String name;
    private Double balance;
    private int AccNumber;

    BankAcc(){
        login = "login";
        passwd = "1234";
        name = "Test Account";
        balance = 0.0;
        AccNumber = 111111;
    }

    public String getLogin() {
        return login;
    }

    private void setLogin(String login) {
        this.login = login;
    }

    public String getPasswd() {
        return passwd;
    }

    private void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public Double getBalance() {
        return balance;
    }

    private void setBalance(Double balance) {
        this.balance = balance;
    }

    public int getAccNumber() {
        return AccNumber;
    }

    private void setAccNumber(int accNumber) {
        AccNumber = accNumber;
    }

    public void setData(BankAcc bankAcc, TextView name, TextView accNum, TextView balance) {
        name.setText(this.getName());
        accNum.setText(String.valueOf(this.getAccNumber()));
        balance.setText("Zůstatek: "+this.getBalance().toString());
    }
}
