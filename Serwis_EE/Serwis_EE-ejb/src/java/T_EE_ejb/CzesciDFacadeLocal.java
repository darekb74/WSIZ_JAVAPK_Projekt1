/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package T_EE_ejb;

import Tabele.CzesciD;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Darek Xperia
 */
@Local
public interface CzesciDFacadeLocal {

    void create(CzesciD czesciD);

    void edit(CzesciD czesciD);

    void remove(CzesciD czesciD);

    CzesciD find(Object id);
    
    CzesciD findByName(String nazwa);

    List<CzesciD> findAll();

    List<CzesciD> findRange(int[] range);

    int count();
    
}
