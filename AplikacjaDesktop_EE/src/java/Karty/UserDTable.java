/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Karty;

import DTO.UserDTO;
import EE_ejb.FasadaUserD_ejbRemote;
import Menadzery.Def;
import Obiekty.PanelStron;
import Obiekty.TabelaDanych;
import Utils.Pagination;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Darek Xperia
 * @classdesc Karta służy do wyszukiwania i edycji listy uzytkowników z tabeli
 * UserD
 */
public class UserDTable extends JPanel implements Karta {

    private TabelaDanych tabela;
    private JScrollPane scrollPane;
    private ButtonsPanel przyciski;
    private DefaultTableModel model;
    private DefaultTableModel modelB;
    private PanelStron panelStron;
    private Pagination<UserDTO> pag;
    private boolean inicjacja = false;

    @Override
    public void init(Object[] args) {
        panelStron = (PanelStron) args[0];
        this.setLayout(new BorderLayout());
        wypelnij();

    }

    private void wypelnij() {
        this.removeAll();
        model = new DefaultTableModel(getData(0, null), 0);
        modelB = new DefaultTableModel(getData(0, null), 0);
        List<UserDTO> tmp = lookupFasadaUserD_ejbRemote().listaUzytkownikow();
        pag = new Pagination(5, tmp);
        tmp = pag.generateDataArray();
        formatujDane(tmp);
        tabela = new TabelaDanych(model, modelB, (byte) 0b00100101, this);
        tabela.getTableHeader().setReorderingAllowed(false); // wyłączenie przenoszenia kolumn
        tabela.setPreferredScrollableViewportSize(new Dimension(500, 70));
        tabela.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(tabela);
        this.add(scrollPane, BorderLayout.CENTER);
        przyciski = new ButtonsPanel((byte) 0b00001111, (byte) 0b00000111, this);
        this.add(przyciski, BorderLayout.SOUTH);
    }

    private void wypelnij(List<UserDTO> dane) {
        this.removeAll();
        model = new DefaultTableModel(getData(0, null), 0);
        modelB = new DefaultTableModel(getData(0, null), 0);
        formatujDane(dane);
        tabela = new TabelaDanych(model, modelB, (byte) 0b00110101, this);
        tabela.getTableHeader().setReorderingAllowed(false); // wyłączenie przenoszenia kolumn
        tabela.setPreferredScrollableViewportSize(new Dimension(500, 70));
        tabela.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(tabela);
        this.add(scrollPane, BorderLayout.CENTER);
        this.add(przyciski, BorderLayout.SOUTH);
    }

    private void formatujDane(List<UserDTO> in) {
        // najpierw List<UserDTO> to List<String>
        for (UserDTO e : in) {
            model.addRow(e.toArray());
            modelB.addRow(e.toArray());
        }
    }

    private List<UserDTO> odczytajDane(boolean onlyMod) {
        // najpierw List<UserDTO> to List<String>
        List<UserDTO> out = new ArrayList<>();
        for (int i = 0; i < model.getRowCount(); i++) {
            UserDTO el = new UserDTO((Long) model.getValueAt(i, 0),
                    (String) model.getValueAt(i, 1),
                    (String) model.getValueAt(i, 2),
                    (String) model.getValueAt(i, 3),
                    (Date) model.getValueAt(i, 4),
                    (Boolean) model.getValueAt(i, 5),
                    (Short) model.getValueAt(i, 6));
            if (onlyMod) { // tylko zmodyfikowane
                UserDTO el2 = new UserDTO((Long) modelB.getValueAt(i, 0),
                        (String) modelB.getValueAt(i, 1),
                        (String) modelB.getValueAt(i, 2),
                        (String) modelB.getValueAt(i, 3),
                        (Date) modelB.getValueAt(i, 4),
                        (Boolean) modelB.getValueAt(i, 5),
                        (Short) modelB.getValueAt(i, 6));
                if (!el.equals(el2)) { // zmodyfikowany ?
                    out.add(el);
                }
            } else {
                out.add(el);
            }
        }
        return out;
    }

    @Override
    public void logout() {

    }

    @Override
    public void takeAction(int type, Object[] args) {
        switch (type) {
            default:
                break;
            case 1: // update
                List<UserDTO> listaDTO = odczytajDane(true);
                for (UserDTO el : listaDTO) {
                    if (Def.DEBUG) {
                        System.out.println("Lista elementów do aktualizacji:");
                        System.out.println(el.toString());
                    }
                }
                lookupFasadaUserD_ejbRemote().aktualizujListe(listaDTO);
                wypelnij();
                validate();
                break;
            case 3: // refresh;
                wypelnij();
                validate();
                break;
            case 4:
                if (args[2] != null) {
                    if (!Utils.Utils.sprawdzTekst((String) args[2])) {
                        Utils.Utils.msgBox("Wyszukiwany tekst zawiera niedozwolone znaki!", "Błąd danych.", JOptionPane.ERROR_MESSAGE, this);
                        if (Def.DEBUG) {
                            System.out.println("[BLĄD] Wyszukiwany tekst zawiera niedozwolone znaki!");
                        }
                        break;
                    } else if (((String) args[2]).isEmpty() && !(((String) args[1]).contains("NULL") || ((String) args[1]).contains("EMPTY"))) {
                        Utils.Utils.msgBox("Pole wyszukiwania puste!", "Błąd danych.", JOptionPane.ERROR_MESSAGE, this);
                        if (Def.DEBUG) {
                            System.out.println("[BLĄD] Pole wyszukiwania puste!");
                        }
                        break;
                    }
                    //sprawdzanie poprawności
                    boolean ok;
                    int t = (int) getData(2, null)[(int) args[0]];
                    switch (t) {
                        case 0: //long
                            break;
                    }
                    switch ((String) args[1]) {
                        case "BETWEEN":
                        case "NOT BETWEEN":
                            t += 50; // specjalne testery
                            ok = Utils.Utils.sprawdzPoprawnoscDanych(t, (String) args[2]);
                            break;
                        default:
                            ok = Utils.Utils.sprawdzPoprawnoscDanych(t, (String) args[2]);
                    }
                    if (ok) {
                        List<UserDTO> wynik = lookupFasadaUserD_ejbRemote()
                                .wyszukiwanie((String) getData(1, null)[(int) args[0]],
                                        (String) args[1], (String) args[2]);

                        pag = new Pagination(2, wynik);
                        panelStron.wygenerujPanel(pag.generateControlArray(), pag.getPage());
                        wypelnij(pag.generateDataArray());
                        //validate();
                    } else {
                        Utils.Utils.msgBox("Warunek wyszukiwania jest nieprawidłowy!\n"
                                + (String) args[2], "Błąd danych wyszukiania.", JOptionPane.ERROR_MESSAGE, this);
                        if (Def.DEBUG) {
                            System.out.println("[BLĄD] Warunek wyszukiwania jest nieprawidłowy!");
                        }
                    }
                }
                break;
            case 5: // show panel (init)
                panelStron.wygenerujPanel(pag.generateControlArray(), pag.getPage());
                wypelnij(pag.generateDataArray());
                break;
            case 6: // zmiana strony
                switch ((String)args[0]) {
                    case "<<":
                        pag.setPage(1);
                        break;
                    case "<":
                        pag.setPage(pag.getPage()-1);
                        break;
                    case ">>":
                        pag.setPage(pag.getPagesCount());
                        break;
                    case ">":
                        pag.setPage(pag.getPage()+1);
                        break;
                    default:
                        try {
                            pag.setPage(Integer.parseInt((String)args[0]));
                        } catch (NumberFormatException e) {
                            
                        }
                        break;
                }
                panelStron.wygenerujPanel(pag.generateControlArray(), pag.getPage());
                wypelnij(pag.generateDataArray());
                break;
        }
    }

    @Override
    public Object[] getData(int type, Object[] args) {
        switch (type) {

            default: // lista nazw kolumn 
                return new Object[]{"Id",
                    "Nazwa użytkownika",
                    "Hasło",
                    "e-Mail",
                    "Ostatni login",
                    "Online",
                    "Poziom dostępu"};
            case 1: // lista kolumn w bazie
                return new Object[]{"id",
                    "username",
                    "password_hash",
                    "eMail",
                    "last_login",
                    "isOnline",
                    "rmask"};
            case 2: // lista typów danych poszczególnych kolumn
                // 0 - Long, 1 - Integer, 2 - Byte, 3 - String, 4 - Boolean
                // 5 - Date, 6 - Short
                return new Object[]{0, 3, 3, 3, 5, 4, 6};

        }
    }

    private FasadaUserD_ejbRemote lookupFasadaUserD_ejbRemote() {
        try {
            Context c = new InitialContext();
            return (FasadaUserD_ejbRemote) c.lookup("ejb/FasadaUserD_ejb");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

}
