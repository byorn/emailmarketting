/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ehammer.dao;

import com.ehammer.dto.SearchFlyerDTO;
import com.ehammer.entities.Flyer;
import static com.ehammer.util.FrameworkUtil.getEntityManager;
import com.ehammer.util.RecordStatus;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author 260514b
 */
public class FlyerDAO {

    private static FlyerDAO instance = new FlyerDAO();

    private FlyerDAO() {
    }

    
    
    public static FlyerDAO instance() {
        return instance;
    }

    public void create(Flyer flyer) {
        
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            flyer.setStatus(RecordStatus.ACT.toString());
            em.persist(flyer);
            em.getTransaction().commit();
            
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Flyer flyer) {
        EntityManager em = null;
        try {
            em = getEntityManager();

            em.getTransaction().begin();
            flyer.setStatus(RecordStatus.ACT.toString());
            em.merge(flyer);
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
            Flyer flyer;

            flyer = em.getReference(Flyer.class, id);
            flyer.getId();

            em.remove(flyer);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void updateFlyerStatus(Integer id, String emailStatus) {
        EntityManager em = null;
        try {

            em = getEntityManager();
            em.getTransaction().begin();

            em.createQuery("update Flyer  set emailingStatus = :emailingStatus where id = :flyerid")
                    .setParameter("flyerid", id)
                    .setParameter("emailingStatus", emailStatus)
                    .executeUpdate();

            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Flyer> findFlyerEntities() {
        return findFlyerEntities(true, -1, -1);
    }

    public List<Flyer> findFlyerEntities(int maxResults, int firstResult) {
        return findFlyerEntities(false, maxResults, firstResult);
    }

    private List<Flyer> findFlyerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select o from Flyer as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Flyer findFlyer(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Flyer.class, id);
        } finally {
            em.close();
        }
    }

    public int getFlyerCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Flyer as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Flyer> findFlyerEntities(SearchFlyerDTO searchDTO) {
       EntityManager em = getEntityManager();
        try {
            Query q;
            
            if(searchDTO==null || searchDTO.getName()==null || "".equals(searchDTO.getName().trim())){
                q = em.createQuery("select o from Flyer as o ");
            }else{
                q = em.createQuery("select o from Flyer as o where o.name like :name");
                q.setParameter("name", "%" + searchDTO.getName() + "%");
            }
            
            
            return q.getResultList();

        
        } finally {
            em.close();
        }
    }

}
