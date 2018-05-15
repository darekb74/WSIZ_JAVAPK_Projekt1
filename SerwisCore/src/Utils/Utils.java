/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.awt.Container;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Darek Xperia
 * @classdesc Klasa zawiera różne metody pomocnicze
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
        String[] listaZ = new String[]{";", "\\"};
        for (String s : listaZ) {
            if (t.contains(s)) {
                return false;
            }
        }
        return true;
    }

    public static void msgBox(String tekst, String tytul, int opcje, Container rodzic) {
        JOptionPane.showMessageDialog(rodzic, tekst, tytul, opcje);
    }
    
    public static boolean sprawdzPoprawnoscDanych(int typDanych, String s) {
        boolean ret = false;
        switch (typDanych) {
            case 5: //Date
                // yyyy-MM-dd, yyyy/MM/dd, yyyy.MM.dd
                ret = s.matches("^(19[0-9]{2}|[2-9][0-9]{3})(\\/|-|\\.)((0(1|3|5|7|8)|10|12)(\\/|-|\\.)(0[1-9]|1[0-9]|2[0-9]|3[0-1])|(0(4|6|9)|11)(\\/|-|\\.)(0[1-9]|1[0-9]|2[0-9]|30)|(02)(\\/|-|\\.)(0[1-9]|1[0-9]|2[0-9]))$");
                break;
            case 6: //Date
                // yyyy-MM-dd HH:mm:ss, yyyy/MM/dd HH:mm:ss, yyyy.MM.dd HH:mm:ss
                ret = s.matches("^(19[0-9]{2}|[2-9][0-9]{3})(\\/|-|\\.)((0(1|3|5|7|8)|10|12)(\\/|-|\\.)(0[1-9]|1[0-9]|2[0-9]|3[0-1])|(0(4|6|9)|11)(\\/|-|\\.)(0[1-9]|1[0-9]|2[0-9]|30)|(02)(\\/|-|\\.)(0[1-9]|1[0-9]|2[0-9]))\\x20(0[0-9]|1[0-9]|2[0-3])(:[0-5][0-9]){2}$");
                break;
            default:
                ret = true;
                break;
        }
        return ret;
    }
}
