/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ehammer.dao;
import com.ehammer.dto.SearchCustomerDTO;
import com.ehammer.entities.Customer;
import static com.ehammer.util.FrameworkUtil.*;
import com.ehammer.util.RecordStatus;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


/**
 *
 * @author 260514b
 */
public class CustomerDAO  {

    private static CustomerDAO instance = new CustomerDAO();

    private CustomerDAO() {
    }

    public static CustomerDAO instance() {
        return instance;
    }

    public void create(Customer customer) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            customer.setStatus(RecordStatus.ACT.toString());
            em.persist(customer);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Customer customer) {
        EntityManager em = null;
        try {
            em = getEntityManager();

            em.getTransaction().begin();
            customer.setStatus(RecordStatus.ACT.toString());
            em.merge(customer);
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
            Customer customer;
            customer = em.getReference(Customer.class, id);
            em.remove(customer);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Customer> findCustomerEntities() {
        return findCustomerEntities(true, -1, -1);
    }

    public List<Customer> findCustomerEntities(int maxResults, int firstResult) {
        return findCustomerEntities(false, maxResults, firstResult);
    }

    private List<Customer> findCustomerEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select o from Customer as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    
    
    public List<Customer> findCustomerEntities(List<Integer> customerIds) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select o from Customer as o where o.id in (:customerids)");
            q.setParameter("customerids",customerIds);
           
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    

    public Customer findCustomer(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Customer.class, id);
        } finally {
            em.close();
        }
    }

    public int getCustomerCount() {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from Customer as o");
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Customer> findCustomerEntities(SearchCustomerDTO searchDTO) {
        EntityManager em = getEntityManager();
        try {
            Query q;
            
            if(searchDTO==null || searchDTO.getName()==null || "".equals(searchDTO.getName().trim())){
                q = em.createQuery("select o from Customer as o ");
            }else{
                q = em.createQuery("select o from Customer as o where o.name like :name");
                q.setParameter("name", "%" + searchDTO.getName() + "%");
            }
            
            
            return q.getResultList();

        
        } finally {
            em.close();
        }
    }
    
    

}
