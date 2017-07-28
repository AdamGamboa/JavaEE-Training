
package com.training.javaee.jaxws.model;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author training
 */
@XmlRootElement(name="actor")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "actor")
public class ModeloActor {
    
    @XmlElement(name="actor_id")
    private Integer actorId;
    
    private String nombre;
    
    private String apellido;

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
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

    @Override
    public String toString() {
        return "ModeloActor{" + "actorId=" + actorId + ", nombre=" + nombre + ", apellido=" + apellido + '}';
    }
    
    
}
