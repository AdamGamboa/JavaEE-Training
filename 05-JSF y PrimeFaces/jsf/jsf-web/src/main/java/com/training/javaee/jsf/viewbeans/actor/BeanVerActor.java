/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.training.javaee.jsf.viewbeans.actor;

import com.training.javaee.jsf.common.util.JSFUtil;
import com.training.javaee.jsf.domain.Actor;
import com.training.javaee.jsf.services.ServicioActor;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author training
 */
@ManagedBean
@ViewScoped
public class BeanVerActor implements Serializable{
    
    private Actor actor;
    
    @Inject
    private ServicioActor servicioActor;
    
    
    public BeanVerActor(){}
    
    @PostConstruct
    public void inicializarPC(){
        String actorId = JSFUtil.getRequestParameter("actorId");
        if(actorId == null){
            JSFUtil.addErrorMessage("NO se ha recibido el parametro del actor seleccionado");
        }
        Integer id = Integer.parseInt(actorId);
        actor = this.servicioActor.buscar(id);
    }

    public Actor getActor() {
        return actor;
    }

    public void setActor(Actor actor) {
        this.actor = actor;
    }
    
    
}
