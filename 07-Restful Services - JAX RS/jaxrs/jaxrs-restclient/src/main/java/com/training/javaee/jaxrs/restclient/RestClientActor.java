
package com.training.javaee.jaxrs.restclient;

import com.training.javaee.jaxrs.model.ModeloActor;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author training
 */
@ApplicationScoped
public class RestClientActor {
    
    private Client client = ClientBuilder.newClient();
    
    public List<ModeloActor> consultar(){
        List<ModeloActor> actores =
            client.target("http://localhost:8080/jaxrs-rest/rest/actor")
            .request(MediaType.APPLICATION_XML)
            .get(new GenericType<List<ModeloActor>>() {
        });
        return actores;
    }
    
    public ModeloActor buscar(int actorId){
        ModeloActor actor = client
                .target("http://localhost:8080/jaxrs-rest/rest/actor/{actorId}")
                .resolveTemplate("actorId", actorId)
                .request(MediaType.APPLICATION_XML)
                .get(ModeloActor.class);
        return actor;
    }
    
    public ModeloActor guardar(ModeloActor modeloActor){
        ModeloActor actor = client
                .target("http://localhost:8080/jaxrs-rest/rest/actor")
                .request(MediaType.APPLICATION_XML)
                .post(Entity.entity(modeloActor, MediaType.APPLICATION_XML), 
                        ModeloActor.class);
        return actor;
    }
    
    public void modificar(ModeloActor modeloActor){
        client.target("http://localhost:8080/jaxrs-rest/rest/actor")
                .request(MediaType.APPLICATION_XML)
                .put(Entity.entity(modeloActor, MediaType.APPLICATION_XML));
    }
    
    public void eliminar(int actorId){
        client.target("http://localhost:8080/jaxrs-rest/rest/actor/{actorId}")
                .resolveTemplate("actorId", actorId)
                .request(MediaType.APPLICATION_XML)
                .delete();
    }
}
