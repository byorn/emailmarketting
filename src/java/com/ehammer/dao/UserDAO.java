/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ehammer.dao;

import com.ehammer.dto.SearchUserDTO;
import com.ehammer.entities.Accessright;
import com.ehammer.entities.User;
import com.ehammer.entities.UserAccessright;
import static com.ehammer.util.FrameworkUtil.getEntityManager;
import com.ehammer.util.RecordStatus;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


/**
 *
 * @author 260514b
 */
public class UserDAO {

    private static final UserDAO instance = new UserDAO();

    private UserDAO() {
    }

    ;
    
    public static UserDAO instance() {
        return instance;
    }

    public void create(User user) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            user.setStatus(RecordStatus.ACT.toString());
            em.persist(user);

            if (user.getUserAccessrightCollection() != null && user.getUserAccessrightCollection().size() > 0) {
                for (UserAccessright uar : user.getUserAccessrightCollection()) {
                    uar.setUserId(user);
                    em.persist(uar);
                }
            }

            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(User user, List<Integer> accessRightsSelected) {
        EntityManager em = null;
        try {

            em = getEntityManager();

            em.getTransaction().begin();

            user.setStatus(RecordStatus.ACT.toString());

            if (user.getUserAccessrightCollection() != null) {
                user.getUserAccessrightCollection().clear();
            } else {
                List<UserAccessright> emptyUserAccessRights = new ArrayList<UserAccessright>();
                user.setUserAccessrightCollection(emptyUserAccessRights);
            }

            if (accessRightsSelected != null && accessRightsSelected.size() > 0) {

                List<Accessright> selectedAccessRights = AccessrightDAO.instance().findAccessrightEntities(accessRightsSelected);

                Collection<UserAccessright> userAccessRights = new ArrayList<UserAccessright>();

                //add the new ones
                for (Accessright ar : selectedAccessRights) {
                    UserAccessright uar = new UserAccessright();
                    uar.setUserId(user);
                    uar.setAccessrightId(ar);
                    userAccessRights.add(uar);
                }
                user.setUserAccessrightCollection(userAccessRights);
            }

            em.merge(user);

            em.getTransaction().commit();

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            User user;

            user = em.getReference(User.class, id);
            user.getId();

            em.remove(user);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<User> findUserEntities() {
        return findUserEntities(true, -1, -1);
    }

    public List<User> findUserEntities(int maxResults, int firstResult) {
        return findUserEntities(false, maxResults, firstResult);
    }

    private List<User> findUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from User as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public User findUser(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }

    public int getUserCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from User as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<User> findUserEntities(SearchUserDTO searchDTO) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from User as o where o.username like :username");

            return q.setParameter("username", "%" + searchDTO.getUsername() + "%").getResultList();
        } finally {
            em.close();
        }
    }

    public User findUser(String userName) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from User as o where o.username = :username");

            return (User) q.setParameter("username", userName).getSingleResult();
        } finally {
            em.close();
        }
    }

    public int getUserCount(String userName) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o)  from User as o where o.username = :username");

            return ((Long) q.setParameter("username", userName).getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
