
package com.training.javaee.jaxrs.viewbeans.actor;

import com.training.javaee.jaxrs.common.util.JSFUtil;
import com.training.javaee.jaxrs.model.ModeloActor;
import com.training.javaee.jaxrs.restclient.RestClientActor;
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
    private RestClientActor restClientActor;
    
    private List<ModeloActor> listaActores;
    
    private ModeloActor actorSeleccionado;
    
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
        this.listaActores = this.restClientActor.consultar();
    }
    
    public String ver(){
        if(actorSeleccionado == null){
            JSFUtil.addErrorMessage("Tiene que seleccionar un actor para continuar");
            return null;
        }else{
            return "/pages/actor/view";
        }
    }
    

    public List<ModeloActor> getListaActores() {
        return listaActores;
    }

    public void setListaActores(List<ModeloActor> listaActores) {
        this.listaActores = listaActores;
    }

    public ModeloActor getActorSeleccionado() {
        return actorSeleccionado;
    }

    public void setActorSeleccionado(ModeloActor actorSeleccionado) {
        this.actorSeleccionado = actorSeleccionado;
    }
    
    
    
    
    
}
