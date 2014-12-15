/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ehammer.controller;

import com.ehammer.dao.FlyerDAO;
import com.ehammer.dao.ImageDAO;
import com.ehammer.dto.ImageBean;
import com.ehammer.dto.SearchFlyerDTO;
import com.ehammer.entities.Flyer;
import com.ehammer.entities.Image;
import com.ehammer.util.FrameworkUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author byorn
 */
@ManagedBean
@ViewScoped
public class FlyerController implements CRUDController{

    private Collection<Flyer> flyers = new ArrayList<Flyer>();

    private Flyer flyer = null;

    private SearchFlyerDTO searchFlyer =  new SearchFlyerDTO();;
    
    private Flyer[] selectedFlyers;
    
    private Map<String,String> listBoxflyers;

    
    
    private String selectedFlyerInListBox;
    
    private ImageBean imageBean = new ImageBean();

   
   

    
   

    

    public FlyerController() {
        
        search();
        
        flyer = new Flyer();
        
        if(listBoxflyers == null){
            
            listBoxflyers = new HashMap<String,String>();
            
            List<Flyer> listFlyers = FlyerDAO.instance().findFlyerEntities();
            
            for(Flyer flyer: listFlyers){
               listBoxflyers.put(flyer.getName(),flyer.getId().toString());
            }
        }
        
        

    }
    
    

    public Collection<Flyer> getFlyers() {
        return flyers;
    }

    public void setFlyers(Collection<Flyer> flyers) {
        this.flyers = flyers;
    }

    public Flyer getFlyer() {
        return flyer;
    }

    public void setFlyer(Flyer flyer) {
        this.flyer = flyer;
    }

    public SearchFlyerDTO getSearchFlyer() {
        return searchFlyer;
    }

    public void setSearchFlyer(SearchFlyerDTO searchFlyer) {
        this.searchFlyer = searchFlyer;
    }

   
    
    @Override
    public void save() {
        if(flyer!=null && flyer.getId()==null){
          
            FlyerDAO.instance().create(flyer);
        }else{
            FlyerDAO.instance().edit(flyer);
        }
     
        search();
        flyer = new Flyer();
    
    }
    
    
    
    @Override
    public void prepareForEdit(Integer flyerID){
        
        this.flyer = FlyerDAO.instance().findFlyer(flyerID);
        System.out.println("prepareForEditCalled" + flyerID);
       
    }
    
    
    @Override
    public void prepareForNew(){
        flyer = new Flyer();
        System.out.println("prepareForNewCalled");
    }

  

    @Override
    public void search() {
       
       flyers.clear();
       flyers.addAll(FlyerDAO.instance().findFlyerEntities(searchFlyer));
       System.out.println("Search called: " + flyers.size());
    }

  
    @Override
    public void delete(Integer id) {
      FlyerDAO.instance().destroy(id);
      search();
    }
    
    
    
    
    public Flyer[] getSelectedFlyers() {
        return selectedFlyers;
    }

    public void setSelectedFlyers(Flyer[] selectedFlyers) {
        this.selectedFlyers = selectedFlyers;
    }
    
    public Map<String, String> getListBoxflyers() {
        return listBoxflyers;
    }

    public void setListBoxflyers(Map<String, String> listBoxflyers) {
        this.listBoxflyers = listBoxflyers;
    }

    public String getSelectedFlyerInListBox() {
        return selectedFlyerInListBox;
    }

    public void setSelectedFlyerInListBox(String selectedFlyerInListBox) {
        this.selectedFlyerInListBox = selectedFlyerInListBox;
    }
    
    
    public void handleFileUpload(FileUploadEvent event) {  
        FacesMessage msg = new FacesMessage("Succesful", event.getFile().getFileName() + " is uploaded.");  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
        boolean succesfullyUploaded = true;
        String fileNameForSystem = FrameworkUtil.prependedFileName(event.getFile().getFileName());
        try {
            
            File targetFolder = new File(FrameworkUtil.getAttachementStoragePath());
            InputStream inputStream = event.getFile().getInputstream();
            OutputStream out = new FileOutputStream(new File(targetFolder,
                    fileNameForSystem));
            int read = 0;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            inputStream.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            succesfullyUploaded = false;
            e.printStackTrace();
        }
        
        if(succesfullyUploaded){
            this.flyer.setFlyerAttachement(fileNameForSystem);
            save();
        }
        
    }
    
    public ImageBean getImageBean() {
        return imageBean;
    }

    public void setImageBean(ImageBean imageBean) {
        this.imageBean = imageBean;
    }

    public void addImageToContentArea(){
       
        String html = "<a href=\"" + imageBean.getTheLinkWhenImageIsClicked() + "\"><img style=\"width=\""+imageBean.getWidth()+"\" height=\"" + imageBean.getHeight() + "\"\" src=" + "\"" + FrameworkUtil.getContextPath() + "/ImageServlet?image_id=" + imageBean.getId() + "\" /></a>";

        flyer.setHtmlContent(flyer.getHtmlContent() + html);

    }
    
    public void prepareImageForAdding(Integer imageID){
        
        this.flyer.setHtmlContent(this.flyer.getHtmlContent());
        
        imageBean.setId(imageID);
        
    }
    
    
}