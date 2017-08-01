package com.training.javaee.jaxrs.rest;

import com.training.javaee.jaxrs.domain.Actor;
import com.training.javaee.jaxrs.model.ModeloActor;
import com.training.javaee.jaxrs.services.ServicioActor;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author training
 */
@Path("actor")
@RequestScoped
public class RestActor {
    
    @Inject
    private ServicioActor servicioActor;
    
    @GET
    @Path("{actorId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public ModeloActor getActor(@PathParam("actorId")int actorId){
        Actor actor = this.servicioActor.buscar(actorId);
        if(actor != null){
            ModeloActor modeloActor = new ModeloActor();
            modeloActor.setActorId(actor.getActorId());
            modeloActor.setLastName(actor.getLastName());
            modeloActor.setFirstName(actor.getFirstName());
            return modeloActor;
        }
        return null;
    }
    
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response getActors(){
        List<Actor> listaActores = this.servicioActor.consultar();
        List<ModeloActor> resultado = new ArrayList<>();
        for(Actor actor : listaActores){
            ModeloActor modeloActor = new ModeloActor();
            modeloActor.setActorId(actor.getActorId());
            modeloActor.setLastName(actor.getLastName());
            modeloActor.setFirstName(actor.getFirstName());
            resultado.add(modeloActor);
        }
        GenericEntity<List<ModeloActor>> list = 
		new GenericEntity<List<ModeloActor>>(resultado) {};
        
        return Response.ok(list).build();
    }
    
    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response guardar(ModeloActor modeloActor){
        Actor actor = this.servicioActor.guardar(modeloActor.getFirstName(), 
                modeloActor.getLastName());
        ModeloActor nuevoModelo = new ModeloActor();
        nuevoModelo.setActorId(actor.getActorId());
        nuevoModelo.setFirstName(actor.getFirstName());
        nuevoModelo.setLastName(actor.getLastName());
        return Response.status(Response.Status.CREATED)
                .entity(nuevoModelo)
                .build();
    }
    
    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response modificar(ModeloActor modeloActor){
        Actor actorModificar = new Actor();
        actorModificar.setActorId(modeloActor.getActorId());
        actorModificar.setLastName(modeloActor.getLastName());
        actorModificar.setFirstName(modeloActor.getFirstName());
        this.servicioActor.modificar(actorModificar);
        return Response.ok().build();
    }
    
    @DELETE
    public Response eliminar(@PathParam("actorId") int actorId){
        this.servicioActor.eliminar(actorId);
        return Response.ok().build();
    }
}
