/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ehammer.controller;

import com.ehammer.dao.SettingsDAO;
import com.ehammer.dto.SearchSettingsDTO;
import com.ehammer.entities.Settings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author byorn
 */
@ManagedBean
@ViewScoped
public class SettingsController implements CRUDController{

    private Collection<Settings> settings = new ArrayList<Settings>();

    private Settings setting = null;

    private SearchSettingsDTO searchSettings =  new SearchSettingsDTO();;
    
    private Settings[] selectedSettingss;
    
    private Map<String,String> listBoxsettings;

    
    
    private String selectedSettingsInListBox;

    

   
    boolean editMode = false;

    
   

    

    public SettingsController() {
        
        search();

        
        if(listBoxsettings == null){
            
            listBoxsettings = new HashMap<String,String>();
            
            List<Settings> listSettingss = SettingsDAO.instance().findSettingsEntities();
            
            for(Settings setting: listSettingss){
               listBoxsettings.put(setting.getName(),setting.getId().toString());
            }
        }

    }
    
    

    public Collection<Settings> getSettingss() {
        return settings;
    }

    public void setSettingss(Collection<Settings> settings) {
        this.settings = settings;
    }

    public Settings getSettings() {
        return setting;
    }

    public void setSettings(Settings setting) {
        this.setting = setting;
    }

    public SearchSettingsDTO getSearchSettings() {
        return searchSettings;
    }

    public void setSearchSettings(SearchSettingsDTO searchSettings) {
        this.searchSettings = searchSettings;
    }

    public void create() {
       
        SettingsDAO.instance().create(setting);
        search();
        setting = new Settings();
    }
    
    @Override
    public void save() {
        if(setting!=null && setting.getId()==null){
          
            SettingsDAO.instance().create(setting);
        }else{
            SettingsDAO.instance().edit(setting);
        }
     
        search();
        setting = new Settings();
    
    }
    
    
    
    @Override
    public void prepareForEdit(Integer settingID){
        
        this.setting = SettingsDAO.instance().findSettings(settingID);
        this.editMode = true;
        search();
    }
    
    
    @Override
    public void prepareForNew(){
        this.editMode = false;
        setting = new Settings();
        System.out.println("prepareForNewCalled");
    }

    public void edit() {
        
        SettingsDAO.instance().edit(setting);
        search();
        this.setting = null;
        
    }

    @Override
    public void search() {
       
       settings.clear();
       settings.addAll(SettingsDAO.instance().findSettingsEntities(searchSettings));
       System.out.println("Search called: " + settings.size());
    }

    public String gotoEdit() {
        if (setting == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select a setting to edit", ""));
            return null;
        }
        return "edit";
    }

    @Override
    public void delete(Integer id) {
      SettingsDAO.instance().destroy(id);
      search();
    }
    
    public Settings[] getSelectedSettingss() {
        return selectedSettingss;
    }

    public void setSelectedSettingss(Settings[] selectedSettingss) {
        this.selectedSettingss = selectedSettingss;
    }
    
    public Map<String, String> getListBoxsettings() {
        return listBoxsettings;
    }

    public void setListBoxsettings(Map<String, String> listBoxsettings) {
        this.listBoxsettings = listBoxsettings;
    }

    public String getSelectedSettingsInListBox() {
        return selectedSettingsInListBox;
    }

    public void setSelectedSettingsInListBox(String selectedSettingsInListBox) {
        this.selectedSettingsInListBox = selectedSettingsInListBox;
    }
    
    public boolean isEditMode() {
        return editMode;
    }

    public void setEditMode(boolean editMode) {
        this.editMode = editMode;
    }
    
    
    
}