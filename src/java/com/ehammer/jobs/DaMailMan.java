/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ehammer.jobs;

import com.ehammer.dao.EmailStatusDAO;
import com.ehammer.entities.Customer;
import com.ehammer.entities.EmailStatus;
import com.ehammer.entities.Flyer;
import com.ehammer.util.EmailState;
import com.ehammer.util.Emailer;
import com.ehammer.util.FrameworkUtil;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author byorn
 */
public class DaMailMan implements Job {
    
    

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {

        FrameworkUtil.debug("Entered execute method of Email Job");
        
        List<EmailStatus> newRecordsForEmailing = EmailStatusDAO.instance().findEmailStatusEntities(EmailState.NEW);
        
        
        
        sendEmailAndUpdateState(newRecordsForEmailing); 
        
    }

    
    private void sendEmailAndUpdateState(List<EmailStatus> newRecordsForEmailing) {
        for (EmailStatus emailStatus : newRecordsForEmailing) {
            
            Flyer flyer = emailStatus.getFlyerId();
            Customer customer = emailStatus.getCustomerId();
            
            byte[] attachment = null;
            
            if(flyer.getFlyerAttachement()!=null){
                File file = new File(FrameworkUtil.getAttachementStoragePath() + "" + flyer.getFlyerAttachement());
                try {
                    attachment = FileUtils.readFileToByteArray(file);
                } catch (IOException ex) {
                        Logger.getLogger(DaMailMan.class.getName()).log(Level.SEVERE, null, ex);
                }
            
            }
            
            
            

            String result = Emailer.init().SendEmail(FrameworkUtil.loadSettings(), replaceAttributes(flyer.getHtmlContent(), customer), flyer.getName(),
                    customer.getEmail(), attachment, flyer.getFlyerAttachement());

            if (result.startsWith("Error")) {
                emailStatus.setEmailStatus(EmailState.FAI.toString());
                
            } else {
                emailStatus.setEmailStatus(EmailState.SUC.toString());
                
            }

            emailStatus.setDescription(result);

        }
        EmailStatusDAO.instance().updateAll(newRecordsForEmailing);
    }

    

    private String replaceAttributes(String content, Customer customer) {

        String replaced = content.replace("{customer}", customer.getName());

        return replaced;
    }

}
