/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ehammer.util;


import com.ehammer.dao.SettingsDAO;
import com.ehammer.entities.Settings;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



/**
 *
 * @author 260514b
 */
public class FrameworkUtil {
    
    private static EntityManagerFactory factory = null;

    
    
    
   public static EntityManager getEntityManager() {
    
       if(factory ==null || !factory.isOpen()){
       
            factory = Persistence.createEntityManagerFactory("test1");
            
            
       }
        
       EntityManager manager = factory.createEntityManager();
       
       return manager;
       
    }
   
   
   public static void log(String str){
        Logger logger = LogManager.getLogger("logManager");
        logger.info(str);
        
   }
   
   public static void debug(String str){
        Logger logger = LogManager.getLogger("logManager");
        logger.debug(str);
        
   }
   
    public static void log(Throwable e){
        Logger logger = LogManager.getLogger("logManager");
        logger.error("Error Occurred", e);
        
   }
    
    public static String getContextValue(String contextKey){
        
        FacesContext ctx = FacesContext.getCurrentInstance();
        return ctx.getExternalContext().getInitParameter(contextKey);
        
    }
    
    public static String getContextPath(){
       HttpServletRequest request = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }

    
    public static Map getRequestMap(){
    
        FacesContext context = FacesContext.getCurrentInstance();
        Map map = context.getExternalContext().getRequestParameterMap();
        
        return map;
    
    }
    
    
    private static Map<String, Settings> settingsMap = null;

    
    public static Map<String, Settings> loadSettings() {
        
        if(settingsMap==null){
            settingsMap = new HashMap<String, Settings>();
        }

        List<Settings> settings = SettingsDAO.instance().findSettingsEntities();

        for (Settings setting : settings) {
            settingsMap.put(setting.getCode(), setting);
        }

        return settingsMap;

    }
    
    private static String getSettingValueForCode(String code){
        return loadSettings().get(code).getValue();
    }
    
    public static String prependedFileName(String fileName){
        
        Calendar c = Calendar.getInstance();
        int d = c.get(Calendar.DATE);
        int m = c.get(Calendar.MONTH);
        int y = c.get(Calendar.YEAR);
        long t = c.getTimeInMillis();
        return String.valueOf(d + "-" + m + "-" + y + "-"+t) + fileName;
    }

    
    public static String getImageStoragePath(){
        
        return getSettingValueForCode(Settings.CODE.IMG_PTH.toString());
        
    }
    
       public static String getAttachementStoragePath(){
        
        return getSettingValueForCode(Settings.CODE.ATT_PATH.toString());
        
    }
    
    
  
}
