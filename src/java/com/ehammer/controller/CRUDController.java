/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ehammer.controller;

import java.io.Serializable;

/**
 *
 * @author byorn
 */
public interface  CRUDController extends Serializable{
    
    
     public void prepareForEdit(Integer flyerID);
    
     public void prepareForNew();
     
     public void search();
     
     public void delete(Integer id);
     
     public void save();
     
}

