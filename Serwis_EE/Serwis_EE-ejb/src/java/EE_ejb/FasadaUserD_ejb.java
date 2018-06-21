/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EE_ejb;

import DTO.UserDTO;
import Menadzery.MgrUzytkownikow;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import T_EE_ejb.UserDFacadeLocal;
import javax.inject.Named;

/**
 *
 * @author Darek Xperia
 */
@Stateless(mappedName="ejb/FasadaUserD_ejb")
@Named
public class FasadaUserD_ejb implements FasadaUserD_ejbRemote {

    @EJB
    private UserDFacadeLocal bazaUzytkownikow;

    private MgrUzytkownikow mgr = new MgrUzytkownikow();

    @Override
    public UserDTO znajdzUzytkownika(String userName) {
        try {
            return bazaUzytkownikow.findByName(userName).getUserDTO();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<UserDTO> listaUzytkownikow() {
        return this.MapToDTO(bazaUzytkownikow.findAll());
    }

    private List<UserDTO> MapToDTO(List<Tabele.UserD> lista) {
        List<UserDTO> result = new ArrayList<>();

        lista.forEach((e) -> {
            result.add(e.getUserDTO());
        });
        return result;
    }

    @Override
    public Boolean uzytkownikIstnieje(String userName) {
        return (bazaUzytkownikow.find(userName).getUserDTO().getId() > 0);
    }

    @Override
    public void aktualizujDane(UserDTO userDTO) {
        bazaUzytkownikow.edit(mgr.map(userDTO));
    }

    @Override
    public void usunUzytkownika(UserDTO userDTO) {
        bazaUzytkownikow.remove(mgr.map(userDTO));
    }

    @Override
    public List<UserDTO> pobierzZakresRekordow(int start, int limit) {
        return this.MapToDTO(bazaUzytkownikow.findRange(new int[]{start, start + limit - 1}));
    }

    @Override
    public void aktualizujListe(List<UserDTO> listaDTO) {
        bazaUzytkownikow.editList(mgr.mapList(listaDTO));
    }
    
    @Override
    public List<UserDTO> wyszukiwanie(String kolumna, String operator, String tekst) {
        return this.MapToDTO(bazaUzytkownikow.customQuery(kolumna, operator, tekst));
    }
}
