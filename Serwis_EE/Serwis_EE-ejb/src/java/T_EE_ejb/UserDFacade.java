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

    private Query setParameter(Query q, String column, String param, String keyword) {
        switch (column) {
            case "id":
                q.setParameter(param, Long.parseLong(keyword));
                break;
            case "rmask":
                q.setParameter(param, Byte.parseByte(keyword));
                break;
            case "isOnline":
                q.setParameter(param, Boolean.parseBoolean(keyword));
                break;
            default:
                q.setParameter(param, keyword);
                break;
        }
        return q;
    }

    @Override
    public List<UserD> customQuery(String column, String operator, String keyword) {
        try {
            String q1 = "";
            Query query;
            switch (operator) {
                default:
                    q1 = "c." + column + " " + operator + " :keyword";
                    query = em.createQuery("SELECT c FROM UserD as c WHERE " + q1,
                            UserD.class);

                    query = setParameter(query, column, "keyword", keyword);
                    break;
                case "LIKE":
                case "NOT LIKE":
                    q1 = "c." + column + " " + operator + " :keyword";
                    query = em.createQuery("SELECT c FROM UserD as c WHERE " + q1,
                            UserD.class);
                    query.setParameter("keyword", "%" + keyword + "%");
                    break;
                case "BETWEEN":
                case "NOT BETWEEN":
                    String[] k = keyword.split(",");
                    q1 = "c." + column + " " + operator + " :min AND :max";
                    query = em.createQuery("SELECT c FROM UserD as c WHERE " + q1,
                            UserD.class);
                    query = setParameter(query, column, "min", k[0]);
                    query = setParameter(query, column, "max", k[1]);
                    break;
                case "IS NULL":
                case "IS NOT NULL":
                case "IS EMPTY":
                case "IS NOT EMPTY":
                    q1 = "c." + column + " " + operator;
                    query = em.createQuery("SELECT c FROM UserD as c WHERE " + q1,
                            UserD.class);
                    break;
            }

            return (List<UserD>) query
                    .getResultList();
        } catch (javax.persistence.NoResultException e) {
            return null;
        }
    }

}
