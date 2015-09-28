package com.udea.controller;


import com.udea.logica.CustomerManager;
import com.udea.logica.DiscountManager;
import com.udea.logica.MicroMarketManager;
import com.udea.modelo.Customer;
import java.io.Serializable;
import java.time.zone.ZoneRulesProvider;
import java.util.List;
import javax.ejb.EJB;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jalejandro.berrio
 */
public class CustomerBean implements Serializable{
    @EJB
    private MicroMarketManager microMarketManager;
    @EJB
    private DiscountManager discountManager;
    @EJB
    private CustomerManager customerManager;

    /**
     * Creates a new instance of CustomerBean
     */
    public CustomerBean() {
    }
    //propiedades del modelo
    //para desplegar / actualizar / inserar en el formulario
    private Customer customer;
    //Para mostar en la tabla de datos
    private List<Customer> customers;
    
    //Retorna la lista de clientes para mostrar en la tabla de datos
    public List<Customer> getCustomers(){
        //Si no hay datos en la lista llame a la funcion refresh para
        //la tabla de datos de la vista
        if((customers==null) || (customers.isEmpty())) refresh();
        return customers;
    }
    //Retorna los detalle del cliente Usado para desplegar en el
    //formulario customer details
    public Customer getDetails(){
        return customer;
    }
    //Action handler - llamado cuando se hace click sobre cada ID
    public String showDetails(Customer customer){
        this.customer=customer;
        return "DETAILS"; //Este es el primer caso en la navegacion
    }
    //Action Handler - Actualizo el modelo del cliente en la BD
    //Llamado cuando se presiona el boton Update en el formulario
    public String update(){
        customer=customerManager.update(customer);
        return "SAVED"; // Este es el segundo caso en la navegación
    }
    
    //Action Handler - Retorna la lista de clientes de la tabla
    public String list(){
        return "LIST"; //Este es el tercer caso en la navegacion
    }
    
    public void refresh(){
        customers=customerManager.getAllCustomers();
    }
}
