/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package T_EE_ejb;

import Tabele.PersonalInfo;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Darek Xperia
 */
@Local
public interface PersonalInfoFacadeLocal {

    void create(PersonalInfo personalInfo);

    void edit(PersonalInfo personalInfo);

    void remove(PersonalInfo personalInfo);

    PersonalInfo find(Object id);

    List<PersonalInfo> findAll();

    List<PersonalInfo> findRange(int[] range);

    int count();
    
}
