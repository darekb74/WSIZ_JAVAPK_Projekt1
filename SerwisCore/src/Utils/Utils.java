/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.awt.Container;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Darek Xperia
 * @classdesc Klasa zawiera różne metody pomocnicze
 */
public class Utils {

    private static final String CHK_DATE = "^(19[0-9]{2}|[2-9][0-9]{3})(\\/|-|\\.)((0(1|3|5|7|8)|10|12)(\\/|-|\\.)(0[1-9]|1[0-9]|2[0-9]|3[0-1])|(0(4|6|9)|11)(\\/|-|\\.)(0[1-9]|1[0-9]|2[0-9]|30)|(02)(\\/|-|\\.)(0[1-9]|1[0-9]|2[0-9]))(\\x20(0[0-9]|1[0-9]|2[0-3])(:[0-5][0-9]){2})?$";
    private static final String CHK_BETWEEN_DATE = "^(19[0-9]{2}|[2-9][0-9]{3})(\\/|-|\\.)((0(1|3|5|7|8)|10|12)(\\/|-|\\.)(0[1-9]|1[0-9]|2[0-9]|3[0-1])|(0(4|6|9)|11)(\\/|-|\\.)(0[1-9]|1[0-9]|2[0-9]|30)|(02)(\\/|-|\\.)(0[1-9]|1[0-9]|2[0-9]))(\\x20(0[0-9]|1[0-9]|2[0-3])(:[0-5][0-9]){2})?(\\x20)?(\\x20((?i)and(?-i))\\x20|,)(\\x20)?(19[0-9]{2}|[2-9][0-9]{3})(\\/|-|\\.)((0(1|3|5|7|8)|10|12)(\\/|-|\\.)(0[1-9]|1[0-9]|2[0-9]|3[0-1])|(0(4|6|9)|11)(\\/|-|\\.)(0[1-9]|1[0-9]|2[0-9]|30)|(02)(\\/|-|\\.)(0[1-9]|1[0-9]|2[0-9]))(\\x20(0[0-9]|1[0-9]|2[0-3])(:[0-5][0-9]){2})?$";
    private static final String CHK_USERNAME = "^(?=.{3,32}$)[(?i)a-z(?-i)0-9]+$";
    private static final String CHK_PASSWORD = "^(?=.{5,32}$)(?=.*\\d+.*)(?=.*[a-z]+.*)(?=.*[A-Z]+.*)[(?i)a-z(?-i)0-9]+$";
    //private static final String CHK_PASSWORD = "^(?=.{5,32}$)[(?i)a-z(?-i)0-9]+$";
    private static final String CHK_EMAIL = "^[(?i)a-z(?-i)0-9._%+-]+@[(?i)a-z(?-i)0-9.-]+\\.[(?i)a-z(?-i)]{2,4}$";
    
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

    public static Date zamienNaDate(String text) {
        SimpleDateFormat format;
        if (sprawdzPoprawnoscDanych(5, text)) {
            text = text.replace("/", "-");
            text = text.replace(".", "-");
            if (text.length() == 10) {
                text += " 00:00:00";
            }
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        } else {
            text = "1970-01-01 00:00:00";
            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
        try {
            return format.parse(text);
        } catch (ParseException e) {
            return usunMS(new Date());
        }
            
    }

    public static Date usunMS(Date data) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                    .parse(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                            .format(data));
        } catch (ParseException e) {
            return null;
        }
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

    public static boolean sprawdzPoprawnoscDanych(String c, String s) {
        int cls;
        switch (c) {
            case "java.lang.Long":
                cls = 0;
                break;
            case "java.lang.Integer":
                cls = 1;
                break;
            case "java.lang.Byte":
                cls = 2;
                break;
            case "java.lang.Short":
                cls = 6;
                break;
            case "java.lang.Boolean":
                cls = 4;
                break;
            case "java.util.Date":
                cls = 5;
                break;
            case "java.lang.String":
            default:
                cls = 3;
                break;
        }
        return sprawdzPoprawnoscDanych(cls, s);
    }

    public static boolean sprawdzString(int minDlugosc, int maxDlugosc, String tekst) {
        return (tekst.length() >= minDlugosc && tekst.length() <= maxDlugosc );
    }

    public static boolean sprawdzPoprawnoscDanych(int typDanych, String s) {
        boolean ret;
        switch (typDanych) {
            case 0: //Long
                try {
                    Long tmp = Long.parseLong(s);
                } catch (NumberFormatException e) {
                    return false;
                }
                return true;
            case 1://Integer
                try {
                    Integer tmp = Integer.parseInt(s);
                } catch (NumberFormatException e) {
                    return false;
                }
                return true;
            case 2://Byte
                try {
                    Byte tmp = Byte.parseByte(s);
                } catch (NumberFormatException e) {
                    return false;
                }
                return true;
            case 4://Boolean
                try {
                    Boolean tmp = Boolean.parseBoolean(s);
                } catch (NumberFormatException e) {
                    return false;
                }
                return true;
            case 5: //Date
                // yyyy-MM-dd, yyyy/MM/dd, yyyy.MM.dd lub
                // yyyy-MM-dd HH:mm:ss, yyyy/MM/dd HH:mm:ss, yyyy.MM.dd HH:mm:ss
                return s.matches(CHK_DATE);
            case 6://Short
                try {
                    Short tmp = Short.parseShort(s);
                } catch (NumberFormatException e) {
                    return false;
                }
                return true;
            // 50 + wstępna poprawność (NOT) BETWEEN
            case 55: // DATE
                return s.matches(CHK_BETWEEN_DATE);
            case 100: // username
                return s.matches(CHK_USERNAME);
            case 101: // password
                return s.matches(CHK_PASSWORD);
            case 102: // e-mail
                return s.matches(CHK_EMAIL);
            default:
                return true;
        }
    }
}
