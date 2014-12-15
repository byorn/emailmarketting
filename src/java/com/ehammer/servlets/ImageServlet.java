/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.ehammer.servlets;

import com.ehammer.dao.ImageDAO;
import com.ehammer.entities.Image;
import com.ehammer.util.FrameworkUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author byorn
 */
@WebServlet(name = "ImageServlet", urlPatterns = {"/ImageServlet"})
public class ImageServlet extends HttpServlet {


    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
                FrameworkUtil.debug("Entered ImageServlet");
               
                String imageId = request.getParameter("image_id");
               
                 System.out.println("Entered ImageServlet" + imageId);
                
                Integer imageid = null;
                
                try{
                    imageid = Integer.valueOf(imageId);
                }catch(NumberFormatException nfe){
                    FrameworkUtil.log("Image Id was null, probably was NEW operation");
                    return;
                }
                
                Image image = ImageDAO.instance().findImage(imageid);
        
                response.reset();
                response.setContentType("image/jpg");
                response.setHeader("Cache-Control", "no-store");
                response.setDateHeader("Expires", 0);
                response.getOutputStream().write(image.getImage(), 0, image.getImage().length);
                response.getOutputStream().flush();

                response.getOutputStream().close();
                FrameworkUtil.debug("Exiting ImageServlet");
        
    }
    
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    
    
    
}
