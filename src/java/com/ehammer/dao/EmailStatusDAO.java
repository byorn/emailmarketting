/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ehammer.dao;


import com.ehammer.dto.SearchEmailStatusDTO;
import com.ehammer.entities.EmailStatus;
import com.ehammer.util.EmailState;
import static com.ehammer.util.FrameworkUtil.getEntityManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author 260514b
 */
public class EmailStatusDAO {

    private static EmailStatusDAO instance = new EmailStatusDAO();

    private EmailStatusDAO() {
    }

    ;
    
    public static EmailStatusDAO instance() {
        return instance;
    }

    public void deleteAll() {

        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            em.createQuery("DELETE EmailStatus").executeUpdate();

            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }

    }

    public  List<Integer> createNew(Collection<EmailStatus> emailsSent) {

        List<Integer> ids = new ArrayList<Integer>();
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            for (EmailStatus emailSent : emailsSent) {
               emailSent.setLastModified(new Date());
                em.persist(emailSent);
                ids.add(emailSent.getId());
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return ids;

    }
    
    
    public void deleteAll(List<Integer> ids){
        EntityManager em = null; 
        Query q = em.createQuery("select o from EmailSatatus as o where o.id in (:ids)");
        q.setParameter("ids", ids);
        q.executeUpdate();
        
    }
    
    
    public void updateAll(Collection<EmailStatus> emailsSent) {

        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();

            for (EmailStatus emailSent : emailsSent) {
                emailSent.setLastModified(new Date());
                em.merge(emailSent);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }

    }
    

    public List<EmailStatus> findEmailStatusEntities() {
        return findEmailStatusEntities(true, -1, -1);
    }

    public List<EmailStatus> findEmailStatusEntities(int maxResults, int firstResult) {
        return findEmailStatusEntities(false, maxResults, firstResult);
    }

    private List<EmailStatus> findEmailStatusEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select o from EmailStatus as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public EmailStatus findEmailStatus(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EmailStatus.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmailStatusCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from EmailStatus as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<EmailStatus> findEmailStatusEntities(SearchEmailStatusDTO searchDTO) {
        EntityManager em = getEntityManager();
        try {
            Query q;
            StringBuilder jpql = new StringBuilder("select o from EmailStatus as o");
          
            if(searchDTO != null && searchDTO.getName()!=null && "".equals(searchDTO.getName().trim())){
                jpql.append(" where o.customerId.email like :email");
                q = em.createQuery(jpql.toString()).setParameter("email", "%" + searchDTO.getName() + "%");
            }else{
                q = em.createQuery(jpql.toString());
            }
            
            return q.getResultList();
        
        } finally {
            em.close();
        }
    }
    
    
    public List<EmailStatus> findEmailStatusEntities(EmailState emailState) {
        EntityManager em = getEntityManager();
        try {
            
            
            String jpql = "select o from EmailStatus as o where o.emailStatus = :state";
          
            Query q = em.createQuery(jpql).setParameter("state", emailState.toString());
            
           
            return q.getResultList();
        
        } finally {
            em.close();
        }
    }
    
    
    public List<EmailStatus> getQueueItems() {
        EntityManager em = getEntityManager();
        try {
            
            
            String jpql = "select o from EmailStatus as o where o.emailStatus = :state";
          
            Query q = em.createQuery(jpql).setParameter("state", EmailState.NEW.toString());
            
            
            return q.getResultList();
        
        } finally {
            em.close();
        }
    }
    
    

}
