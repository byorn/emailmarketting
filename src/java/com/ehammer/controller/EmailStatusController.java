/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ehammer.controller;

import com.ehammer.dao.EmailStatusDAO;
import com.ehammer.entities.EmailStatus;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;


/**
 *
 * @author byorn
 */
@ManagedBean
@ViewScoped
public class EmailStatusController implements Serializable {


    private List<EmailStatus> emailStatusLIst = new ArrayList<EmailStatus>();

    
    public EmailStatusController(){
        
    }
    
    public List<EmailStatus> getEmailStatusLIst() {
        return emailStatusLIst;
    }

    public void setEmailStatusLIst(List<EmailStatus> emailStatusLIst) {
        this.emailStatusLIst = emailStatusLIst;
    }

    
    public void checkQueue(){
        emailStatusLIst.clear();
        emailStatusLIst.addAll(EmailStatusDAO.instance().getQueueItems());
    }
    
   

}
