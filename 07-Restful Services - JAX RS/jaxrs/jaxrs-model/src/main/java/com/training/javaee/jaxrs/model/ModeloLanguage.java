package com.training.javaee.jaxrs.model;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 *
 * @author training
 */
@XmlRootElement(name="language")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "language")
public class ModeloLanguage {
    
    @XmlElement(name = "language_id")
    private Integer languageId;
    
    private String name;
    
    @XmlElement(name = "last_update")
    private Date lastUpdate;

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
    
    
    
}
