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
public class UserDFacade extends AbstractFacade<UserD> implements UserDFacadeLocal {

    @PersistenceContext(unitName = "Serwis_EE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserDFacade() {
        super(UserD.class);
    }

    @Override
    public UserD findByName(String userName) {
        try {
        Query query = em.createQuery("SELECT c FROM UserD as c WHERE c.username = :userName",
                UserD.class);

        return (UserD) query
                .setParameter("userName", userName)
                .getSingleResult();
        } catch (javax.persistence.NoResultException e) {
            return null;
        }
    }
}
