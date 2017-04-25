/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import javax.persistence.EntityManager;

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
}
