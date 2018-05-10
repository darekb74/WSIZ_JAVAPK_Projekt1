/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package T_EE_ejb;

import Tabele.UserD;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.client.Client;

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
    
    public UserD find(String userName) {
        Query query = em.createQuery("select u FROM User WHERE u.userName = :userName",
                UserD.class);
        
        return (UserD)query
                .setParameter("userName", userName)
                .getSingleResult();
    }
    
}
