/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ehammer.dao;

import static com.ehammer.util.FrameworkUtil.getEntityManager;
import javax.persistence.Query;


/**
 *
 * @author 260514b
 */
public class LoginDAO {

    private static LoginDAO instance = new LoginDAO();

    private LoginDAO() {
    }

    ;
    
    public static LoginDAO instance() {
        return instance;
    }

    public boolean authorize(String userName, String password) {
        Query q = getEntityManager().createQuery("select count(o) from User as o where o.username = :username and o.password = :password");
        int count = ((Long) q.setParameter("username", userName).setParameter("password", password).getSingleResult()).intValue();
        if (count > 0) {
            return true;
        }
        return false;
    }

}
