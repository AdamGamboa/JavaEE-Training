package com.training.javaee.servletcdi.managedbeans;

import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author training
 */
@ApplicationScoped
public class BeanContador {
    private int contador;
    
    public int getContador(){
        return contador;
    }
    
    public void aumentarContador(){
        contador++;
    }
}
