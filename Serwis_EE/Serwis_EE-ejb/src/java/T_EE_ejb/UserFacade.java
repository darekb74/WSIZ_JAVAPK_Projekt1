/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package T_EE_ejb;

import Tabele.UserD;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Darek Xperia
 */
@Stateless
public class UserFacade extends AbstractFacade<UserD> implements UserFacadeLocal {

    @PersistenceContext(unitName = "Serwis_EE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(UserD.class);
    }
    
    @Override
    public UserD findByName(String userName) {
        Query query = em.createQuery("SELECT c FROM UserD as c WHERE c.username = :userName",
                UserD.class);
        
        return (UserD)query
                .setParameter("userName", userName)
                .getSingleResult();
    }
    
    @Override
    public List<UserD> generateUserList() {
        Query query = em.createQuery("SELECT e FROM UserD e", UserD.class);
        
        return (List<UserD>)query
                .getResultList();
    }
    
}
