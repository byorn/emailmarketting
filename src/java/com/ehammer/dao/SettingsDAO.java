/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ehammer.dao;

import com.ehammer.dto.SearchSettingsDTO;
import com.ehammer.entities.Settings;
import static com.ehammer.util.FrameworkUtil.getEntityManager;
import com.ehammer.util.RecordStatus;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


/**
 *
 * @author 260514b
 */
public class SettingsDAO {

    private static SettingsDAO instance = new SettingsDAO();

    private SettingsDAO() {
    }

    ;
    
    public static SettingsDAO instance() {
        return instance;
    }

    public void create(Settings settings) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            settings.setStatus(RecordStatus.ACT.toString());
            em.persist(settings);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Settings settings) {
        EntityManager em = null;
        try {
            em = getEntityManager();

            em.getTransaction().begin();
            settings.setStatus(RecordStatus.ACT.toString());
            em.merge(settings);
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
            Settings settings;

            settings = em.getReference(Settings.class, id);
            settings.getId();

            em.remove(settings);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Settings> findeEmailSettings() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select o from Settings as o where o.code like 'EMAIL%'");
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Settings> findSettingsEntities() {
        return findSettingsEntities(true, -1, -1);
    }

    public List<Settings> findSettingsEntities(int maxResults, int firstResult) {
        return findSettingsEntities(false, maxResults, firstResult);
    }

    private List<Settings> findSettingsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select o from Settings as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Settings findSettings(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Settings.class, id);
        } finally {
            em.close();
        }
    }

    public int getSettingsCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Settings as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Settings> findSettingsEntities(SearchSettingsDTO searchDTO) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select o from Settings as o where o.name like :name");

            return q.setParameter("name", "%" + searchDTO.getName() + "%").getResultList();
        } finally {
            em.close();
        }
    }

}
