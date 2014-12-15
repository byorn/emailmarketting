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
public class ImageBean implements Serializable {
    
    private String width;
    private String height;
    private String url;
    private String theLinkWhenImageIsClicked;


    private Integer id;

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTheLinkWhenImageIsClicked() {
        if(theLinkWhenImageIsClicked==null){
            theLinkWhenImageIsClicked = "";
        }
        return theLinkWhenImageIsClicked;
    }

    public void setTheLinkWhenImageIsClicked(String theLinkWhenImageIsClicked) {
        this.theLinkWhenImageIsClicked = theLinkWhenImageIsClicked;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
  
    
}
