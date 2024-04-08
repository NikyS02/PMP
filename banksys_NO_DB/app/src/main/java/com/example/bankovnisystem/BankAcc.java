package com.example.bankovnisystem;

import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class BankAcc implements Serializable {
    private final int accNumber;
    private String login;
    private String passwd;
    private String name;
    private Double balance;

    BankAcc(){
        accNumber = 111111;
        login = "login";
        passwd = "1234";
        name = "Test Account";
        balance = 500.0;
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
        return accNumber;
    }

    private void setAccNumber(int accNumber) {
        accNumber = accNumber;
    }

    public void setData(BankAcc bankAcc, TextView name, TextView accNum, TextView balance) {
        name.setText(this.getName());
        accNum.setText(String.valueOf(this.getAccNumber()));
        balance.setText("Zůstatek: "+this.getBalance().toString()+" Kč");
    }

    protected void pay(Payment payment) {
        if(this.balance < payment.getAmmout()) throw new RuntimeException();
        this.balance = this.balance - payment.getAmmout();

    }
}
