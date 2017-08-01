
package com.training.javaee.jaxrs.model;

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
    
    @XmlElement(name="first_name")
    private String firstName;
    
    @XmlElement(name="last_name")
    private String lastName;

    public Integer getActorId() {
        return actorId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    
    
}
