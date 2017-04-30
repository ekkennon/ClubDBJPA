/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author raefo
 */
public class PurchaseDB {
    public static List<Purchase> getPurchases(String memid) {
        EntityManager manager = DBUtil.getEmFactory().createEntityManager();
        String qs = "SELECT p FROM Purchase p WHERE p.memid = :memid ORDER BY p.purchaseDate";
        TypedQuery<Purchase> q = manager.createQuery(qs, Purchase.class);
        q.setParameter("memid", memid);
        
        List<Purchase> p = null;
        try {
            p = q.getResultList();
            if (p == null || p.isEmpty()) {
                p = null;
            }
        } catch (NoResultException e) {
            p = null;
        } finally {
            manager.close();
        }
        return p;
    }
}
