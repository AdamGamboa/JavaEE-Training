
package com.training.javaee.jaxws.webservices;

import com.training.javaee.jaxws.domain.Actor;
import com.training.javaee.jaxws.model.ModeloActor;
import com.training.javaee.jaxws.services.ServicioActor;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

/**
 *
 * @author training
 */
@WebService(serviceName = "ActorWS")
public class WebserviceActor {
    
    @Inject
    private ServicioActor servicioActor;
    
    @WebMethod(operationName = "getActor")
    @WebResult(name = "actor")
    public ModeloActor obtenerActor(@WebParam(name="actorId") int id){
        Actor actor = this.servicioActor.buscar(id);
        if(actor != null){
            ModeloActor modeloActor = new ModeloActor();
            modeloActor.setActorId(actor.getActorId());
            modeloActor.setApellido(actor.getLastName());
            modeloActor.setNombre(actor.getFirstName());
            return modeloActor;
        }
        return null;
    }
    
    @WebMethod(operationName = "consultar")
    @WebResult(name = "actor")
    public List<ModeloActor> consultarActores(){
        List<Actor> lista = this.servicioActor.consultar();
        List<ModeloActor> resultado = new ArrayList<>();
        for(Actor actor : lista){
            ModeloActor modeloActor = new ModeloActor();
            modeloActor.setActorId(actor.getActorId());
            modeloActor.setApellido(actor.getLastName());
            modeloActor.setNombre(actor.getFirstName());
            resultado.add(modeloActor);
        }
        return resultado;
    }
    
    @WebMethod
    @WebResult(name = "actor")
    public ModeloActor crearActor(String nombre, String apellido){
        Actor actor = this.servicioActor.guardar(nombre, apellido);
        if(actor!=null){
            ModeloActor modeloActor = new ModeloActor();
            modeloActor.setActorId(actor.getActorId());
            modeloActor.setApellido(actor.getLastName());
            modeloActor.setNombre(actor.getFirstName());
            return modeloActor;
        }
        return null;
    }
    
    @WebMethod(operationName = "guardarActor")
    @WebResult(name = "actor")
    public ModeloActor guardarActor(ModeloActor modeloActor){
        Actor temp = new Actor();
        temp.setFirstName(modeloActor.getNombre());
        temp.setLastName(modeloActor.getApellido());
        
        Actor actor = this.servicioActor.guardar(temp);
        if(actor!=null){
            ModeloActor nuevoActor = new ModeloActor();
            nuevoActor.setActorId(actor.getActorId());
            nuevoActor.setApellido(actor.getLastName());
            nuevoActor.setNombre(actor.getFirstName());
            return nuevoActor;
        }
        return null;
    }
}
