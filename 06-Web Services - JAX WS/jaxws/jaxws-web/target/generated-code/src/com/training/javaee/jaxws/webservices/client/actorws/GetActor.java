
package com.training.javaee.jaxws.webservices.client.actorws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para getActor complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="getActor">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="actorId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getActor", propOrder = {
    "actorId"
})
public class GetActor {

    protected int actorId;

    /**
     * Obtiene el valor de la propiedad actorId.
     * 
     */
    public int getActorId() {
        return actorId;
    }

    /**
     * Define el valor de la propiedad actorId.
     * 
     */
    public void setActorId(int value) {
        this.actorId = value;
    }

}
