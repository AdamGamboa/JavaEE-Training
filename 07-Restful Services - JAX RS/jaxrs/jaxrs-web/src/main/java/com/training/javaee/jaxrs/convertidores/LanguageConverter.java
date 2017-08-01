package com.training.javaee.jaxrs.convertidores;

import com.training.javaee.jaxrs.model.ModeloLanguage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author training
 */
@FacesConverter(value = "LanguageConverter")
public class LanguageConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null && !value.trim().isEmpty()){
            String [] languageFields = value.split("%");
            ModeloLanguage language = new ModeloLanguage();
            if(languageFields.length >= 2){
                language.setLanguageId(Integer.parseInt(languageFields[0]));
                language.setName(languageFields[1]);
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value instanceof ModeloLanguage){
            ModeloLanguage language = (ModeloLanguage) value;
            return language.getLanguageId()+"%"+language.getName();
        }
        return "";
    }
    
}
