package com.example.ukol04;

import android.database.Cursor;

import androidx.annotation.NonNull;

public class Hra {

    private String nazev = "", zanr = "";

    private boolean dohrano = false;

    private int id = 0, odehrano = 0, postup = 0, hodnoceni = 0;

    public Hra(int id, String nazev, String zanr, boolean dohrano, int odehrano, int postup, int hodnoceni) {
        setId(id);
        setNazev(nazev);
        setZanr(zanr);
        setDohrano(dohrano);
        setOdehrano(odehrano);
        setPostup(postup);
        setHodnoceni(hodnoceni);
    }

    public Hra(Cursor cursor) {
        cursor.moveToFirst();
        setId(cursor.getInt(0));
        setNazev(cursor.getString(1));
        setZanr(cursor.getString(2));
        setDohrano(cursor.getString(3).equals("1"));
        setOdehrano(cursor.getInt(4));
        setPostup(cursor.getInt(5));
        setHodnoceni(cursor.getInt(6));

        cursor.close();
    }

    @NonNull
    @Override
    public String toString() {
        return getId() + ";" + getNazev() + ";" + getZanr() + ";" + isDohrano() + ";" + getOdehrano() + ";" + getPostup() + ";" + getHodnoceni();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazev() {
        return nazev;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }

    public String getZanr() {
        return zanr;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    public boolean isDohrano() {
        return dohrano;
    }

    public void setDohrano(boolean dohrano) {
        this.dohrano = dohrano;
    }

    public int getOdehrano() {
        return odehrano;
    }

    public void setOdehrano(int odehrano) {
        this.odehrano = odehrano;
    }

    public int getPostup() {
        return postup;
    }

    public void setPostup(int postup) {
        this.postup = postup;
    }

    public int getHodnoceni() {
        return hodnoceni;
    }

    public void setHodnoceni(int hodnoceni) {
        this.hodnoceni = hodnoceni;
    }
}
