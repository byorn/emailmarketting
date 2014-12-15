/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ehammer.controller;

import com.ehammer.dao.ImageDAO;
import com.ehammer.dto.SearchImageDTO;
import com.ehammer.entities.Image;
import com.ehammer.util.FrameworkUtil;
import com.ehammer.util.JsfUtil;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author byorn
 */
@ManagedBean
@ViewScoped
public class ImageController implements CRUDController{

    private Collection<Image> images = new ArrayList<Image>();

    private Image image = null;

    private SearchImageDTO searchImage =  new SearchImageDTO();;
    
    private Image[] selectedImages;
    
    private String selectedImageInListBox;

   

    
   

    

    public ImageController() {
        
        search();

        
        

    }
    
    

    public Collection<Image> getImages() {
        return images;
    }

    public void setImages(Collection<Image> images) {
        this.images = images;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public SearchImageDTO getSearchImage() {
        return searchImage;
    }

    public void setSearchImage(SearchImageDTO searchImage) {
        this.searchImage = searchImage;
    }

    
    
    @Override
    public void save() {
        if(image!=null && image.getId()==null){
          
            ImageDAO.instance().create(image);
        }else{
            ImageDAO.instance().edit(image);
        }
     
        search();
        image = new Image();
    
    }
    
    
    
    @Override
    public void prepareForEdit(Integer imageID){
        
        this.image = ImageDAO.instance().findImage(imageID);
        System.out.println("prepareForEditCalled");
        search();
    }
    
    
    @Override
    public void prepareForNew(){
        image = new Image();
        System.out.println("prepareForNewCalled");
    }

   
    @Override
    public void search() {
       
       images.clear();
       images.addAll(ImageDAO.instance().findImageEntities(searchImage));
       System.out.println("Search called: " + images.size());
    }

    

    @Override
    public void delete(Integer id) {
      ImageDAO.instance().destroy(id);
      search();
    }
    
    public Image[] getSelectedImages() {
        return selectedImages;
    }

    public void setSelectedImages(Image[] selectedImages) {
        this.selectedImages = selectedImages;
    }
    
   

    public String getSelectedImageInListBox() {
        return selectedImageInListBox;
    }

    public void setSelectedImageInListBox(String selectedImageInListBox) {
        this.selectedImageInListBox = selectedImageInListBox;
    }
    
    
    public void handleFileUpload(FileUploadEvent event) {  
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        
        try {

            byte[] fileContents = IOUtils.toByteArray(event.getFile().getInputstream());
            if (fileContents.length <= 0) {
                JsfUtil.addErrorMessage("Please upload a file first");
               return;
            }

            //validation
            if (Integer.valueOf(FrameworkUtil.getContextValue("maxUploadSize")) < fileContents.length) {
                JsfUtil.addErrorMessage("Uploaded File Exceeds 10MB");
                return; 
            }
            image.setName(event.getFile().getFileName());
            image.setImage(fileContents);
        } catch (IOException ex) {
            FrameworkUtil.log(ex);
        }
            
        save();
       
        
    }
    
    
}