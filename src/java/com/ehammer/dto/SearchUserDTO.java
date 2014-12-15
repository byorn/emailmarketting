/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ehammer.dto;

import java.io.Serializable;

/**
 *
 * @author byorn
 */
public class SearchUserDTO implements Serializable{

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    private String username;
}
