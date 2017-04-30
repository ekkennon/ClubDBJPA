/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

/**
 *
 * @author raefo
 */
public class MemberDB {
    
    public static Member getMemberByID(String memid) {
        EntityManager manager = DBUtil.getEmFactory().createEntityManager();
        
        try {
            Member m = manager.find(Member.class, memid);
            return m;
        //} catch (Exception e) {
            
        } finally {
            manager.close();
        }
    }
    
    public static String updateMember(Member m) {
        String msg = "";
        EntityManager manager = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = manager.getTransaction();
        try {
            trans.begin();
            manager.merge(m);
            trans.commit();
            msg = "Member " + m.getMemID() + " updated.<br/>";
        } catch (Exception e) {
            msg = "Error on Member Update: " + e.getMessage() + "<br/>";
            trans.rollback();
        } finally {
            manager.close();
        }
        return msg;
    }
}
