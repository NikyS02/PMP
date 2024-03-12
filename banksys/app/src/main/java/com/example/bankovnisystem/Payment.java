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

}
