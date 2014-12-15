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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author 260514b
 */
@Entity
@Table(name = "user_accessright")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UserAccessright.findAll", query = "SELECT u FROM UserAccessright u"),
    @NamedQuery(name = "UserAccessright.findById", query = "SELECT u FROM UserAccessright u WHERE u.id = :id")})
public class UserAccessright implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private User userId;
    @JoinColumn(name = "accessright_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Accessright accessrightId;

    public UserAccessright() {
    }

    public UserAccessright(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
    }

    public Accessright getAccessrightId() {
        return accessrightId;
    }

    public void setAccessrightId(Accessright accessrightId) {
        this.accessrightId = accessrightId;
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
        if (!(object instanceof UserAccessright)) {
            return false;
        }
        UserAccessright other = (UserAccessright) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "softwareperson.framework.entities.UserAccessright[ id=" + id + " ]";
    }
    
}
