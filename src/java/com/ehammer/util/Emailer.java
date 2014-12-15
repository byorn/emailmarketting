/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ehammer.util;

import com.ehammer.entities.Settings;
import java.util.Date;
import java.util.Map;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;

/**
 *
 * @author byorn
 */
public class Emailer {
    
    
    
    private static Emailer emailer = null;
    
    public static Emailer init(){
        if(emailer==null){
            return emailer = new Emailer();
        }
        return emailer;
    }
    
   
    
    
    public String SendEmail(Map<String, Settings> map, String content, String subject, String toEmail, byte[] attachement, String attachementFileName){
        
        String result = "Successfully emailed to" + toEmail;
        
        String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
        String host = map.get(Settings.CODE.EMAIL_HOST.toString()).getValue();
        String from = map.get(Settings.CODE.EMAIL_FROM_ADDRESS.toString()).getValue();
        String port = map.get(Settings.CODE.EMAIL_PORT.toString()).getValue();
        final String username =  map.get(Settings.CODE.EMAIL_USERNAME.toString()).getValue();
        final String password =  map.get(Settings.CODE.EMAIL_PASSWORD.toString()).getValue();
        String to   = toEmail;
        
        String messageText = content;
        boolean sessionDebug = false;

        // Create some properties and get the default Session.
        Properties props = System.getProperties();
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.socketFactory.class", SSL_FACTORY);

        Session mailSession = Session.getInstance(props,
                                new javax.mail.Authenticator() {
                                    protected PasswordAuthentication 
                                          getPasswordAuthentication() {
                                        return new PasswordAuthentication
                                          (username,password);
                                    }
                                });
 
 
        mailSession.setDebug(sessionDebug);
 
        // Instantiate a new MimeMessage and fill it with the 
        // required information.
        Message msg = new MimeMessage(mailSession);
        try {
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject);
            msg.setSentDate(new Date());
        
        
        // Create a multipar message
        Multipart multipart = new MimeMultipart();
    
        if(attachement !=null && attachementFileName!=null){
            
            MimeBodyPart attachmentBodyPart = new MimeBodyPart();

            DataSource source = new ByteArrayDataSource(attachement, "application/x-any");
            attachmentBodyPart.setDataHandler(new DataHandler(source));
            attachmentBodyPart.setFileName(attachementFileName);
            multipart.addBodyPart(attachmentBodyPart);
            attachmentBodyPart.setContentID("<some_image_id>");
            attachmentBodyPart.setDisposition(MimeBodyPart.INLINE);
        }

        
         MimeBodyPart htmlBodyPart = new MimeBodyPart();
         htmlBodyPart.setContent(messageText, "text/html; charset=utf-8");
         //htmlBodyPart.setText(messageText, "US-ASCII", "html");
         multipart.addBodyPart(htmlBodyPart);

        // Send the complete message parts
         msg.setContent(multipart );
          
            
         Transport.send(msg);

        } catch (MessagingException ex) {
            result = "Error !" + ex.getMessage();
            FrameworkUtil.log(ex);
        }catch (Throwable ex) {
            result = "Error !" + ex.getMessage();
            FrameworkUtil.log(ex);
        }
 
        
        
        return result;
    }
    
}
