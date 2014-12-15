/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ehammer.util;

import com.ehammer.controller.CustomerController;
import com.ehammer.dao.EmailStatusDAO;
import com.ehammer.entities.Customer;
import com.ehammer.entities.EmailStatus;
import com.ehammer.entities.Flyer;
import com.ehammer.jobs.DaMailMan;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import static org.quartz.JobBuilder.newJob;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import static org.quartz.TriggerBuilder.newTrigger;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author byorn
 */
public class EmailBatchScheduler {
    
    
    public static void scheduleDaMailMan(Flyer flyer,Customer[] customers){
     
        List<Integer> emailStatusIds = null;
        
        String key = "org.quartz.impl.StdSchedulerFactory.KEY";

        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();

        StdSchedulerFactory factory = (StdSchedulerFactory) servletContext.getAttribute(key);


        Scheduler quartzScheduler;
       
        try {
            quartzScheduler = factory.getScheduler("MyQuartzScheduler");
       

            JobDetail job1 = newJob(DaMailMan.class)
                    .withIdentity("job1", "group1")
                    //.usingJobData("flyerId", flyer.getId().toString())
                    //.usingJobData("customerList", strCustomers)
                    .build();

            // Define a Trigger that will fire "now", and not repeat
            Trigger trigger = newTrigger()
                    .withIdentity("trigger1", "group1")
                    .startNow()
                    .build();
       
            
           emailStatusIds = createNewEmailStateRecords(customers, flyer);
            
           quartzScheduler.scheduleJob(job1, trigger);
            
        
        } catch (SchedulerException ex) {
            Logger.getLogger(CustomerController.class.getName()).log(Level.SEVERE, null, ex);
            //rollback
            EmailStatusDAO.instance().deleteAll(emailStatusIds);
        } 
        
        
        
    }

    private static List<Integer> createNewEmailStateRecords(Customer[] customers, Flyer flyer) {
        List<EmailStatus> emailsToBeSent = new ArrayList<EmailStatus>();
        
        for(Customer customer : customers){
            EmailStatus emailsSent = new EmailStatus();
            emailsSent.setCustomerId(customer);
            emailsSent.setFlyerId(flyer);
            emailsSent.setEmailStatus(EmailState.NEW.toString());
            emailsToBeSent.add(emailsSent);
        }
        
        return EmailStatusDAO.instance().createNew(emailsToBeSent);
    }
    
   
}
