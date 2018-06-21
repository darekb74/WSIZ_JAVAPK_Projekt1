/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package T_EE_ejb;

import Tabele.CzesciD;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Darek Xperia
 */
@Stateless
public class CzesciDFacade extends AbstractFacade<CzesciD> implements CzesciDFacadeLocal {

    @PersistenceContext(unitName = "Serwis_EE-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CzesciDFacade() {
        super(CzesciD.class);
    }
    
    @Override
    public CzesciD findByName(String nazwa) {
        try {
            Query query = em.createQuery("SELECT c FROM CzesciD as c WHERE c.nazwa = :nazwa",
                    CzesciD.class);

            return (CzesciD) query
                    .setParameter("nazwa", nazwa)
                    .getSingleResult();
        } catch (javax.persistence.NoResultException e) {
            return null;
        }
    }

    @Override
    public Long findNextId() {
        try {
            Query query = em.createQuery("SELECT i.id FROM CzesciD i ORDER BY i.id DESC");

            return (Long) query
                    .setMaxResults(1)
                    .getSingleResult() + 1;
        } catch (javax.persistence.NoResultException e) {
            return null;
        }
    }
}
