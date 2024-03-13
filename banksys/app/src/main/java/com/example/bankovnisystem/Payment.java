package com.example.bankovnisystem;

import java.util.Date;

public class Payment {
    // údaje: číslo účtu, kód banky (seznam název banky a kód banky), VS, SS, KS, zpráva pro příjemce, zpráve pro odesílatele,
    // funkce zkopírovat zprávu příjemce do zprávy pro odesítale
    // datum splatnosti

    private int accNumber;
    private int bankCode;
    private int VS;
    private int SS;
    private int KS;
    private String messageForReciever;
    private String messageForSender;
    private Date date;

    Payment(int accNumber, int bankCode, Date date){
        setAccNumber(accNumber);
        setBankCode(bankCode);
        setDate(date);

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

    public Date getDate() {
        return date;
    }

    private void setDate(Date date) {
        this.date = date;
    }
}



