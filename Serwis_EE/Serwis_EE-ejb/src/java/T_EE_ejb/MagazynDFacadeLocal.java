/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package T_EE_ejb;

import Tabele.MagazynD;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Darek Xperia
 */
@Local
public interface MagazynDFacadeLocal {

    void create(MagazynD magazynD);

    void edit(MagazynD magazynD);

    void remove(MagazynD magazynD);

    MagazynD find(Object id);
    
    MagazynD findByName(String nazwa);

    List<MagazynD> findAll();

    List<MagazynD> findRange(int[] range);

    int count();
    
}
