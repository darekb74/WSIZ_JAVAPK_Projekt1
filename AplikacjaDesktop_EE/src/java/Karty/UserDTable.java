/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Karty;

import DTO.UserDTO;
import EE_ejb.Fasada_EE_ejbRemote;
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
    private ButtonsPanel przyciski;
    
    @Override
    public void init(Object[] args) {
        this.setLayout(new BorderLayout());
        String[] nazwyKolumn = {"Id",
            "Nazwa użytkownika",
            "Hasło",
            "e-Mail",
            "Ostatni login",
            "Online",
            "Poziom dostępu"};

        DefaultTableModel model = new DefaultTableModel(nazwyKolumn, 0);
        DefaultTableModel modelb = new DefaultTableModel(nazwyKolumn, 0);
        List<UserDTO> tmp = lookupFasada_EE_ejbRemote().listaUzytkownikow();
        formatujDane(tmp, model, modelb);
        tabela = new TabelaDanych(model, modelb, (byte) 0b00110101, this);
        tabela.getTableHeader().setReorderingAllowed(false); // wyłączenie przenoszenia kolumn
        tabela.setPreferredScrollableViewportSize(new Dimension(500, 70));
        tabela.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(tabela);
        this.add(scrollPane, BorderLayout.CENTER);
        przyciski = new ButtonsPanel(true, true);
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
        
    }

    private Fasada_EE_ejbRemote lookupFasada_EE_ejbRemote() {
        try {
            Context c = new InitialContext();
            return (Fasada_EE_ejbRemote) c.lookup("java:comp/env/Fasada_EE_ejb");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
