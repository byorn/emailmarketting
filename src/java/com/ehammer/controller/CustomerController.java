/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ehammer.controller;

import com.ehammer.dao.CustomerDAO;
import com.ehammer.dao.FlyerDAO;
import com.ehammer.dto.SearchCustomerDTO;
import com.ehammer.entities.Customer;
import com.ehammer.entities.Flyer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import com.ehammer.util.EmailBatchScheduler;

/**
 *
 * @author byorn
 */
@ManagedBean
@ViewScoped
public class CustomerController implements CRUDController{

    private Collection<Customer> customers = new ArrayList<Customer>();

    private Customer customer = null;

    private SearchCustomerDTO searchCustomer =  new SearchCustomerDTO();;
    
    private Customer[] selectedCustomers;
    
    private Map<String,String> listBoxflyers;

    
    
    private String selectedFlyerInListBox;

   

    
   

    

    public CustomerController() {
        
        search();

        
        if(listBoxflyers == null){
            
            listBoxflyers = new HashMap<String,String>();
            
            List<Flyer> listFlyers = FlyerDAO.instance().findFlyerEntities();
            
            for(Flyer flyer: listFlyers){
               listBoxflyers.put(flyer.getName(),flyer.getId().toString());
            }
        }

    }
    
    

    public Collection<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Collection<Customer> customers) {
        this.customers = customers;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public SearchCustomerDTO getSearchCustomer() {
        return searchCustomer;
    }

    public void setSearchCustomer(SearchCustomerDTO searchCustomer) {
        this.searchCustomer = searchCustomer;
    }

   
    
    @Override
    public void save() {
        if(customer!=null && customer.getId()==null){
            CustomerDAO.instance().create(customer);
        }else{
            CustomerDAO.instance().edit(customer);
        }
     
        search();
        customer = new Customer();
    
    }
    
    
    
    @Override
    public void prepareForEdit(Integer customerID){
        
        this.customer = CustomerDAO.instance().findCustomer(customerID);
        System.out.println("prepareForEditCalled");
        search();
    }
    
    
    @Override
    public void prepareForNew(){
        customer = new Customer();
        System.out.println("prepareForNewCalled");
    }

   
    @Override
    public void search() {
       
       customers.clear();
       customers.addAll(CustomerDAO.instance().findCustomerEntities(searchCustomer));
       System.out.println("Search called: " + customers.size());
    }


    @Override
    public void delete(Integer id) {
      CustomerDAO.instance().destroy(id);
      search();
    }
    
    public Customer[] getSelectedCustomers() {
        return selectedCustomers;
    }

    public void setSelectedCustomers(Customer[] selectedCustomers) {
        this.selectedCustomers = selectedCustomers;
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
    
    public void email(){
        
        if (selectedCustomers == null || selectedCustomers.length<1) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select atleast one customer", ""));
            return;
        }
        
        if (selectedFlyerInListBox == null || selectedFlyerInListBox.trim().equals("")) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Please select a flyer", ""));
            return;
        }
        
        
        
        Flyer flyerToEmail = FlyerDAO.instance().findFlyer(Integer.valueOf(selectedFlyerInListBox));
       
        
        EmailBatchScheduler.scheduleDaMailMan(flyerToEmail, selectedCustomers);
        
  
    
    }
    
    
    
   
    
    
    
}