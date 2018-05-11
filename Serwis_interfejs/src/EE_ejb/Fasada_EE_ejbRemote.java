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
public interface Fasada_EE_ejbRemote {

    public UserDTO znajdzUzytkownika(String username);
    
    public List<UserDTO> listaUzytkownikow();

    public Boolean uzytkownikIstnieje(String username);
}
