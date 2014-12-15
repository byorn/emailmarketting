/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ehammer.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 260514b
 */
@Entity
@Table(name = "accessright")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Accessright.findAll", query = "SELECT a FROM Accessright a"),
    @NamedQuery(name = "Accessright.findById", query = "SELECT a FROM Accessright a WHERE a.id = :id"),
    @NamedQuery(name = "Accessright.findByName", query = "SELECT a FROM Accessright a WHERE a.name = :name"),
    @NamedQuery(name = "Accessright.findByStatus", query = "SELECT a FROM Accessright a WHERE a.status = :status"),
    @NamedQuery(name = "Accessright.findByVersion", query = "SELECT a FROM Accessright a WHERE a.version = :version"),
    @NamedQuery(name = "Accessright.findByDescription", query = "SELECT a FROM Accessright a WHERE a.description = :description")})
public class Accessright implements Serializable {
   
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @Version
    private int version;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;

    public Accessright() {
    }

    public Accessright(Integer id) {
        this.id = id;
    }

    public Accessright(Integer id, String name, String status, int version, String description) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.version = version;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        if (!(object instanceof Accessright)) {
            return false;
        }
        Accessright other = (Accessright) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "softwareperson.framework.entities.Accessright[ id=" + id + " ]";
    }

    
    
}
