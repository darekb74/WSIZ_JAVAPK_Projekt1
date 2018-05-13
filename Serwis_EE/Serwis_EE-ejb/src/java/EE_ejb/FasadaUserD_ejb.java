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
import Tabele.UserD;
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
        return bazaUzytkownikow.findByName(userName).getUserDTO();
    }

    @Override
    public List<UserDTO> listaUzytkownikow() {
        return this.MapToDTO(bazaUzytkownikow.generateUserList());
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
        return (bazaUzytkownikow.find(userName).getUserDTO().getId() > 0 );
    }
    
    @Override
    public void aktualizujDane(UserDTO userDTO) {
        UserD u = mgr.map(userDTO);
        bazaUzytkownikow.edit(u);
    }
    
    @Override
    public void usunUzytkownika(UserDTO userDTO) {
        UserD u = mgr.map(userDTO);
        bazaUzytkownikow.remove(u);
    }
}
