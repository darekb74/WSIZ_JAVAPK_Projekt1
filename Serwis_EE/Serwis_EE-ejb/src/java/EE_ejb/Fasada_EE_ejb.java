/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EE_ejb;

import DTO.UserDTO;
import T_EE_ejb.UserFacadeLocal;
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
    
    //private MgrUzytkownikow mgrU = new MgrUzytkownikow();
    
    @Override
    public UserDTO znajdzUzytkownika(String userName) {
        return bazaUzytkownikow.find(userName).getUserDTO();
    }
    /*public Boolean uzytkownikIstnieje(String username) {
        return false;
    }*/
}
