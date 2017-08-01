package com.training.javaee.jaxrs.services;

import com.training.javaee.jaxrs.domain.Actor;
import java.util.List;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author training
 */
@Stateless
public class ServicioActor {

    @PersistenceContext(unitName = "SakilaPU")
    private EntityManager em;

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Actor> consultar() {
        try {
            return em.createQuery("SELECT a FROM Actor a").getResultList();
        } catch (Exception ex) {
            throw new RuntimeException("Error consultando los actores", ex);
        }
    }

    public Actor guardar(String nombre, String apellido) {
        Actor actor = new Actor();
        actor.setFirstName(nombre);
        actor.setLastName(apellido);
        return this.guardar(actor);
    }
    
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public Actor guardar(Actor actor) {
        try {
            em.persist(actor);
            return actor;
        } catch (Exception ex) {
            throw new RuntimeException("Ha ocurrido un problema al crear un actor", ex);
        } 
    }

    public void modificar(Actor actor) {
        try {
            em.merge(actor);
        } catch (Exception ex) {
            throw new RuntimeException("Ha ocurrido un problema al modificar un actor", ex);
        }
    }

    public void eliminar(Actor actor) {
        try {
            em.remove(em.getReference(Actor.class, actor.getActorId()));
        } catch (Exception ex) {
            throw new RuntimeException("Ha ocurrido un problema al eliminar un actor", ex);
        }
    }

    public void eliminar(int actorId) {
        try {
            String sql = "delete FilmActor fa where fa.actor.actorId = :actorId";
            em.createQuery(sql).setParameter("actorId", actorId).executeUpdate();
            em.remove(em.getReference(Actor.class, actorId));
        } catch (Exception ex) {
            throw new RuntimeException("Ha ocurrido un problema al eliminar un actor", ex);
        }
    }

    public Actor buscar(int actorId) {
        try {
            return em.find(Actor.class, actorId);
        } catch (Exception ex) {
            throw new RuntimeException("Ha ocurrido un problema al buscar un actor", ex);
        }
    }
}
