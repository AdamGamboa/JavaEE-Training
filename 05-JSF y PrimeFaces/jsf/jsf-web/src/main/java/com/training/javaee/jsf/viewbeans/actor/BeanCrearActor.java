package com.training.javaee.jsf.viewbeans.actor;

import com.training.javaee.jsf.common.util.JSFUtil;
import com.training.javaee.jsf.services.ServicioActor;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author training
 */
@ManagedBean
@ViewScoped
public class BeanCrearActor implements Serializable{
    
    private String nombre;
    
    private String apellido;
    
    @Inject 
    private ServicioActor servicioActor;
    
    
    public BeanCrearActor(){
        
    }
    
    public String guardar(){
        try{
            servicioActor.guardar(nombre, apellido);
            JSFUtil.addSuccessMessage("El usuario ha sido guardado");
            return "/pages/actor/list";
        }catch(Exception ex){
           JSFUtil.addErrorMessage("Error guardando el usuario"); 
           return null;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
}
