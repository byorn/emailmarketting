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
public class SearchSettingsDTO implements Serializable{

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    private String name;
}
