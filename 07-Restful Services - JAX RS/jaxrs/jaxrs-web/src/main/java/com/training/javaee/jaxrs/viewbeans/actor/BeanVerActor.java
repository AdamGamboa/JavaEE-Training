package com.training.javaee.jaxrs.viewbeans.actor;

import com.training.javaee.jaxrs.common.util.JSFUtil;
import com.training.javaee.jaxrs.model.ModeloActor;
import com.training.javaee.jaxrs.restclient.RestClientActor;
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
    
    private ModeloActor actor;
    
    @Inject
    private RestClientActor restClientActor;
    
    
    public BeanVerActor(){}
    
    @PostConstruct
    public void inicializarPC(){
        String actorId = JSFUtil.getRequestParameter("actorId");
        if(actorId == null){
            JSFUtil.addErrorMessage("NO se ha recibido el parametro del actor seleccionado");
        }
        Integer id = Integer.parseInt(actorId);
        actor = this.restClientActor.buscar(id);
    }

    public ModeloActor getActor() {
        return actor;
    }

    public void setActor(ModeloActor actor) {
        this.actor = actor;
    }
    
    
}
