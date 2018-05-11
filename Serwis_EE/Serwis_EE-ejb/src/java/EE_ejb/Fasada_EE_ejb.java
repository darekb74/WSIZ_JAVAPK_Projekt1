/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EE_ejb;

import DTO.UserDTO;
import T_EE_ejb.UserFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Darek Xperia
 */
@Stateless
public class Fasada_EE_ejb implements Fasada_EE_ejbRemote {

    @EJB
    private UserFacadeLocal bazaUzytkownikow;

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
}
