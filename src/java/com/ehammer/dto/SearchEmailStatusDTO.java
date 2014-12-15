/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ehammer.dto;

import com.ehammer.util.EmailState;
import java.io.Serializable;

/**
 *
 * @author byorn
 */
public class SearchEmailStatusDTO implements Serializable{

    private String name;
    private EmailState status;
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    

    public EmailState getStatus() {
        return status;
    }

    public void setStatus(EmailState status) {
        this.status = status;
    }
 
}
