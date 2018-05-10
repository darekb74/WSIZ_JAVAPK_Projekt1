/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Obiekty;

/**
 *
 * @author Darek Xperia
 */
public class Def {

    public final static boolean DEBUG = true;

    public final static byte LVL0 = (byte) 0x0;    // 00000000 - podstawowe menu
    public final static byte LVL1 = (byte) 0x1;    // 00000001 - menu pracownika magazynu
    public final static byte LVL2 = (byte) 0x2;    // 00000010 - menu serwisanta 
    public final static byte LVL3 = (byte) 0x4;    // 00000100 - 
    public final static byte LVL4 = (byte) 0x8;    // 00001000 - 
    public final static byte LVL5 = (byte) 0x10;   // 00010000 - dodawanie przesuniec magazynowych
    public final static byte LVL6 = (byte) 0x20;   // 00100000 - dodawanie danych osobowych
    public final static byte LVL7 = (byte) 0x40;   // 01000000 - 
    public final static byte LVL9 = (byte) 0x80;   // 10000000 -                                              
    public final static byte ADM = (byte) 0xFF;    // 11111111 - wszystkie (administrator)

    public final static String O_ZAKONCZ = "Zakończ";
    public final static String O_SPRAWDZ_STANY = "Sprawdź stany magazynowe";
    public final static String O_SZUKAJ_CZESCI = "Szukaj części";
}
