/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Karty;

import DTO.UserDTO;
import EE_ejb.FasadaUserD_ejbRemote;
import Obiekty.TabelaDanych;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Darek Xperia
 * Karta służy do wyszukiwania i edycji listy uzytkowników
 * z tabeli UserD
 */
public class UserDTable extends JPanel implements Karta{
    
    private TabelaDanych tabela;
    private JScrollPane scrollPane;
    private ButtonsPanel przyciski;
    
    @Override
    public void init(Object[] args) {
        this.setLayout(new BorderLayout());
        wypelnij();
        
        
    }
    
    private void wypelnij() {
        this.removeAll();
        String[] nazwyKolumn = {"Id",
            "Nazwa użytkownika",
            "Hasło",
            "e-Mail",
            "Ostatni login",
            "Online",
            "Poziom dostępu"};
        DefaultTableModel model = new DefaultTableModel(nazwyKolumn, 0);
        DefaultTableModel modelb = new DefaultTableModel(nazwyKolumn, 0);
        List<UserDTO> tmp = lookupFasadaUserD_ejbRemote().listaUzytkownikow();
        formatujDane(tmp, model, modelb);
        tabela = new TabelaDanych(model, modelb, (byte) 0b00110101, this);
        tabela.getTableHeader().setReorderingAllowed(false); // wyłączenie przenoszenia kolumn
        tabela.setPreferredScrollableViewportSize(new Dimension(500, 70));
        tabela.setFillsViewportHeight(true);
        scrollPane = new JScrollPane(tabela);
        this.add(scrollPane, BorderLayout.CENTER);
        przyciski = new ButtonsPanel(true, true, false, this);
        this.add(przyciski, BorderLayout.SOUTH);
    }
    
    private void formatujDane(List<UserDTO> in, DefaultTableModel model, DefaultTableModel modelb) {
        // najpierw List<UserDTO> to List<String>
        for (UserDTO e : in) {
            model.addRow(e.toArray());
            modelb.addRow(e.toArray());
        }
    }
    
    @Override
    public void logout() {
        
    }
    
    @Override
    public void takeAction(int type) {
        switch (type) {
            default:
                break;
            case 3: // refresh;
                wypelnij();
                validate();
                break;
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
