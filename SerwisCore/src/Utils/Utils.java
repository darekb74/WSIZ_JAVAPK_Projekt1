/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Darek Xperia
 * @classdesc
 * Klasa zawiera różne metody pomocnicze
 */
public class Utils {

    public static String md5(String pass) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(pass.getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            return null; // błąd
        }
    }

    public static String foramtujDate(Date data) {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdfDate.format(data);
    }
    
    public static boolean sprawdzTekst(String t) {
        String[] listaZ = new String[] {";","\\"};
        for (String s : listaZ) {
            if (t.contains(s)) return false;
        }
        return true;
    }
}
