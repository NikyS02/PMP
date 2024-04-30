package com.example.bankovnisystem;

import java.io.Serializable;
import java.util.Date;

public class Payment implements Serializable {
    // údaje: číslo účtu, kód banky (seznam název banky a kód banky), VS, SS, KS, zpráva pro příjemce, zpráve pro odesílatele,
    // funkce zkopírovat zprávu příjemce do zprávy pro odesítale
    // datum splatnosti
    private int id;
    private int accNumber;
    private int bankCode;
    private double ammout;
    private int VS;
    private int SS;
    private int KS;
    private String messageForReciever;
    private String messageForSender;
    private String date;

    private int accNumberFrom;
    private int bankCodeFrom;
    Payment(
            int id,
            int accNumber,
            int bankCode,
            double ammout,
            int VS,
            int SS,
            int KS,
            String messageForReciever,
            String messageForSender,
            String date,
            int accNumberFrom,
            int bankCodeFrom){
        this.id = id;
        setAccNumber(accNumber);
        setBankCode(bankCode);
        setAmmout(ammout);
        setVS(VS);
        setSS(SS);
        setKS(KS);
        setMessageForReciever(messageForReciever);
        setMessageForSender(messageForSender);
        setDate(date);
        setAccNumberFrom(accNumberFrom);
        setBankCodeFrom(bankCodeFrom);
    }

    public int getAccNumber() {
        return accNumber;
    }

    private void setAccNumber(int accNumber) {
        this.accNumber = accNumber;
    }

    public int getBankCode() {
        return bankCode;
    }

    private void setBankCode(int bankCode) {
        this.bankCode = bankCode;
    }

    public double getAmmout() {
        return ammout;
    }

    public void setAmmout(double ammout) {
        this.ammout = ammout;
    }

    public int getVS() {
        return VS;
    }

    private void setVS(int VS) {
        this.VS = VS;
    }

    public int getSS() {
        return SS;
    }

    private void setSS(int SS) {
        this.SS = SS;
    }

    public int getKS() {
        return KS;
    }

    private void setKS(int KS) {
        this.KS = KS;
    }

    public String getMessageForReciever() {
        return messageForReciever;
    }

    private void setMessageForReciever(String messageForReciever) {
        this.messageForReciever = messageForReciever;
    }

    public String getMessageForSender() {
        return messageForSender;
    }

    private void setMessageForSender(String messageForSender) {
        this.messageForSender = messageForSender;
    }

    public String getDate() {
        return date;
    }

    private void setDate(String date) {
        this.date = date;
    }

    public int getAccNumberFrom() {
        return accNumberFrom;
    }

    public void setAccNumberFrom(int accNumberFrom) {
        this.accNumberFrom = accNumberFrom;
    }

    public int getBankCodeFrom() {
        return bankCodeFrom;
    }

    public void setBankCodeFrom(int bankCodeFrom) {
        this.bankCodeFrom = bankCodeFrom;
    }
}


