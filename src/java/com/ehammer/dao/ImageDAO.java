/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ehammer.dao;
import com.ehammer.dto.SearchImageDTO;
import com.ehammer.entities.Image;
import static com.ehammer.util.FrameworkUtil.*;
import com.ehammer.util.RecordStatus;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


/**
 *
 * @author 260514b
 */
public class ImageDAO  {

    private static ImageDAO instance = new ImageDAO();

    private ImageDAO() {
    }

    public static ImageDAO instance() {
        return instance;
    }

    public void create(Image image) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            image.setStatus(RecordStatus.ACT.toString());
            em.persist(image);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Image image) {
        EntityManager em = null;
        try {
            em = getEntityManager();

            em.getTransaction().begin();
            em.merge(image);
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
            Image image;
            image = em.getReference(Image.class, id);
            em.remove(image);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Image> findImageEntities() {
        return findImageEntities(true, -1, -1);
    }

    public List<Image> findImageEntities(int maxResults, int firstResult) {
        return findImageEntities(false, maxResults, firstResult);
    }

    private List<Image> findImageEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select o from Image as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    
    public List<Image> findImageEntities(List<Integer> imageIds) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select o from Image as o where o.id in (:imageids)");
            q.setParameter("imageids",imageIds);
           
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    

    public Image findImage(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Image.class, id);
        } finally {
            em.close();
        }
    }

    public int getImageCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Image as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Image> findImageEntities(SearchImageDTO searchDTO) {
        EntityManager em = getEntityManager();
        try {
            Query q;
            
            if(searchDTO==null || searchDTO.getName()==null || "".equals(searchDTO.getName().trim())){
                q = em.createQuery("select o from Image as o ");
            }else{
                q = em.createQuery("select o from Image as o where o.name like :name");
                q.setParameter("name", "%" + searchDTO.getName() + "%");
            }
            
            
            return q.getResultList();

        
        } finally {
            em.close();
        }
    }
    
    

}
