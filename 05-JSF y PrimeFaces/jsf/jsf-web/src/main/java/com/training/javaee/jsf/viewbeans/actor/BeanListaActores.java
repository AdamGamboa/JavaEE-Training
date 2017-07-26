
package com.training.javaee.jsf.viewbeans.actor;

import com.training.javaee.jsf.common.util.JSFUtil;
import com.training.javaee.jsf.domain.Actor;
import com.training.javaee.jsf.services.ServicioActor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author training
 */
@ViewScoped
@ManagedBean
public class BeanListaActores implements Serializable{
    
    @Inject
    private ServicioActor servicioActor;
    
    private List<Actor> listaActores;
    
    private Actor actorSeleccionado;
    
    public BeanListaActores(){
        this.inicializar();
    }
    
    private void inicializar(){
        this.listaActores = new ArrayList();
    }
    
    @PostConstruct
    public void inicializarPC(){
        this.cargarListaActores();
    }
    
    private void cargarListaActores(){
        this.listaActores = this.servicioActor.consultar();
    }
    
    public String ver(){
        if(actorSeleccionado == null){
            JSFUtil.addErrorMessage("Tiene que seleccionar un actor para continuar");
            return null;
        }else{
            return "/pages/actor/view";
        }
    }
    

    public List<Actor> getListaActores() {
        return listaActores;
    }

    public void setListaActores(List<Actor> listaActores) {
        this.listaActores = listaActores;
    }

    public Actor getActorSeleccionado() {
        return actorSeleccionado;
    }

    public void setActorSeleccionado(Actor actorSeleccionado) {
        this.actorSeleccionado = actorSeleccionado;
    }
    
    
    
    
    
}
