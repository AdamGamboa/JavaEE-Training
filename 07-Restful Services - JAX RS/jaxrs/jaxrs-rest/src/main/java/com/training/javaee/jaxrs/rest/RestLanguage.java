package com.training.javaee.jaxrs.rest;

import com.training.javaee.jaxrs.domain.Film;
import com.training.javaee.jaxrs.domain.Language;
import com.training.javaee.jaxrs.model.ModeloFilm;
import com.training.javaee.jaxrs.model.ModeloLanguage;
import com.training.javaee.jaxrs.services.ServicioFilm;
import com.training.javaee.jaxrs.services.ServicioLanguage;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author training
 */
@Path("language")
@RequestScoped
public class RestLanguage {
    
    @Inject
    private ServicioLanguage servicioLanguage;
    
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response consultarIdiomas(){
        List<Language> listaIdiomas = this.servicioLanguage.consultar();
        List<ModeloLanguage> resultado = new ArrayList<>();
        for(Language idioma : listaIdiomas){
            ModeloLanguage modeloLanguage = new ModeloLanguage();
            modeloLanguage.setLanguageId(idioma.getLanguageId());
            modeloLanguage.setName(idioma.getName());
            modeloLanguage.setLastUpdate(idioma.getLastUpdate());
            resultado.add(modeloLanguage);
        }
        GenericEntity<List<ModeloLanguage>> list = 
		new GenericEntity<List<ModeloLanguage>>(resultado) {};
        
        return Response.ok(list).build();
    }
}
