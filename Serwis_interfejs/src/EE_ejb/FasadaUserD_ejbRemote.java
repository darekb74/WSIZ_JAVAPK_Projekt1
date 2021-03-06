/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EE_ejb;

import DTO.UserDTO;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author Darek Xperia
 */
@Remote
public interface FasadaUserD_ejbRemote {

    public UserDTO znajdzUzytkownika(String username);
    
    public List<UserDTO> listaUzytkownikow();

    public Boolean uzytkownikIstnieje(String username);
    
    public void aktualizujDane(UserDTO user);
    
    public void aktualizujListe(List<UserDTO> listaDTO);
    
    public void usunUzytkownika(UserDTO user);
    
    public void dodajUzytkownika(UserDTO userDTO);
    
    public List<UserDTO> pobierzZakresRekordow(int start, int limit);
    
    public List<UserDTO> wyszukiwanie(String kolumna, String operator, String tekst);
    
    public Long znajdzNastepneID();
}
