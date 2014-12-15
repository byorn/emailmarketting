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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author byorn
 */
@Entity
@Table(name = "settings")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Settings.findAll", query = "SELECT s FROM Settings s"),
    @NamedQuery(name = "Settings.findById", query = "SELECT s FROM Settings s WHERE s.id = :id"),
    @NamedQuery(name = "Settings.findByName", query = "SELECT s FROM Settings s WHERE s.name = :name"),
    @NamedQuery(name = "Settings.findByStatus", query = "SELECT s FROM Settings s WHERE s.status = :status"),
    @NamedQuery(name = "Settings.findByVersion", query = "SELECT s FROM Settings s WHERE s.version = :version"),
    @NamedQuery(name = "Settings.findByValue", query = "SELECT s FROM Settings s WHERE s.value = :value"),
    @NamedQuery(name = "Settings.findByCode", query = "SELECT s FROM Settings s WHERE s.code = :code")})
public class Settings implements Serializable {
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
    @Column(name = "version")
    private int version;
    @Column(name = "value")
    private String value;
    @Basic(optional = false)
    @Column(name = "code")
    private String code;

    public Settings() {
    }

    public Settings(Integer id) {
        this.id = id;
    }

    public Settings(Integer id, String name, String status, int version, String code) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.version = version;
        this.code = code;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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
        if (!(object instanceof Settings)) {
            return false;
        }
        Settings other = (Settings) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eblaster.entities.Settings[ id=" + id + " ]";
    }
 
    public static enum CODE {
       EMAIL_FROM_ADDRESS,
EMAIL_HOST,
EMAIL_PASSWORD,
EMAIL_PORT,
EMAIL_USERNAME,
IMG_PTH,
ATT_PATH
    }
    
}
