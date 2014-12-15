/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ehammer.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author byorn
 */
@Entity
@Table(name = "email_status")
public class EmailStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
   
    @Column(name = "description", nullable = true)
    private String description;
    
    @JoinColumn(name = "flyer_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Flyer flyerId;
    
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Customer customerId;

   
  
    @Column(name = "email_status", nullable = false)
    private String emailStatus;
    
    @Column(name = "last_modified", nullable = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date lastModified;

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
   
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

   
    public Flyer getFlyerId() {
        return flyerId;
    }

    public void setFlyerId(Flyer flyerId) {
        this.flyerId = flyerId;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmailStatus)) {
            return false;
        }
        EmailStatus other = (EmailStatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eblaster.entities.EmailsSent[ id=" + id + " ]";
    }
    
     public String getEmailStatus() {
        return emailStatus;
    }

    public void setEmailStatus(String emailStatus) {
        this.emailStatus = emailStatus;
    }
    
    
    
    
   
    
}
