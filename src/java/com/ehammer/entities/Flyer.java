/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ehammer.entities;

import com.ehammer.util.FrameworkUtil;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author byorn
 */
@Entity
@Table(name = "flyer")
@XmlRootElement
public class Flyer implements Serializable {
    @Basic(optional = false)
    @Column(name = "version")
    private int version;
    
  
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "status")
    private String status;
  
    @Column(name = "html_content")
    private String htmlContent;
    
    @Column(name = "flyer_attachement")
    private String flyerAttachement;
    
    @Transient
    private StreamedContent fileAttachementDownload; 

   

    public Flyer() {
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

 

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Flyer)) {
            return false;
        }
        Flyer other = (Flyer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.eblaster.entities.Flyer[ id=" + id + " ]";
    }
    
    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public String getFlyerAttachement() {
        return flyerAttachement;
    }

    public void setFlyerAttachement(String flyerAttachement) {
        this.flyerAttachement = flyerAttachement;
    }

    
    public StreamedContent getFileAttachementDownload() {
        
        File  f = new File(FrameworkUtil.getAttachementStoragePath() + "" + getFlyerAttachement());
        
        FileInputStream fin;
        
        try {
            fin = new FileInputStream(f);
            
            fileAttachementDownload = new DefaultStreamedContent(fin, "application/octet-stream", getFlyerAttachement());
          } catch (FileNotFoundException ex) {
            Logger.getLogger(Flyer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return fileAttachementDownload;
       
    }

    public void setFileAttachementDownload(StreamedContent fileAttachementDownload) {
        this.fileAttachementDownload = fileAttachementDownload;
    }

  
    public String getFlyerContentForDesigner(){
      String con = "";
      if(this.htmlContent!=null){
          con = this.htmlContent.replace("container", "");
      }
      return con;
    }
    
    
}
