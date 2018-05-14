/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package T_EE_ejb;

import Tabele.UserD;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Darek Xperia
 */
@Local
public interface UserDFacadeLocal {

    void create(UserD user);

    void edit(UserD user);
    
    void editList(List<UserD> user);

    void remove(UserD user);

    UserD find(Object id);
    
    UserD findByName(String username);

    List<UserD> findAll();

    List<UserD> findRange(int[] range);

    int count();
    
}
