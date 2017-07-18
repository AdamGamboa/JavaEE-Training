package com.training.javaee.jpa.services;

import com.training.javaee.jpa.common.EntityManagerHelper;
import com.training.javaee.jpa.domain.Actor;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author training
 */
@RequestScoped
public class ServicioActor {

    public List<Actor> consultar() {
        EntityManager em = null;
        try {
            em = EntityManagerHelper.getEntityManager();
            Query query = em.createQuery("select a from Actor a");
            List<Actor> resultado = query.getResultList();
            return resultado;
        } catch (Exception ex) {
            throw new RuntimeException("Error consultando los actores", ex);
        } finally {
            if (em != null) {
                EntityManagerHelper.closeEntityManager();
            }
        }
    }


    public Actor guardar(String nombre, String apellido) {
        Actor actor = new Actor();
        actor.setFirstName(nombre);
        actor.setLastName(apellido);
        return this.guardar(actor);
    }
    
    public Actor guardar(Actor actor) {
        EntityManager em = null;
        try {
            em = EntityManagerHelper.getEntityManager();
            em.getTransaction().begin();
            em.persist(actor);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw new RuntimeException("Ha ocurrido un problema al crear un actor", ex);
        } finally {
            if (em != null) {
                EntityManagerHelper.closeEntityManager();
            }
        }
        return actor;
    }
    
    public void modificar(Actor actor) {
        EntityManager em = null;
        try {
            em = EntityManagerHelper.getEntityManager();
            em.getTransaction().begin();
            em.merge(actor);
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw new RuntimeException("Ha ocurrido un problema al modificar un actor", ex);
        } finally {
            if (em != null) {
                EntityManagerHelper.closeEntityManager();
            }
        }
    }
    
    public void eliminar(Actor actor) {
        EntityManager em = null;
        try {
            em = EntityManagerHelper.getEntityManager();
            em.getTransaction().begin();
            em.remove(em.getReference(Actor.class, actor.getActorId()));
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw new RuntimeException("Ha ocurrido un problema al eliminar un actor", ex);
        } finally {
            if (em != null) {
                EntityManagerHelper.closeEntityManager();
            }
        }
    }
    
    public void eliminar(int actorId) {
        EntityManager em = null;
        try {
            em = EntityManagerHelper.getEntityManager();
            em.getTransaction().begin();
            
            String sql = "delete FilmActor fa where fa.actor.actorId = :actorId";
            em.createQuery(sql).setParameter("actorId", actorId).executeUpdate();
            
            em.remove(em.getReference(Actor.class, actorId));
            
            em.getTransaction().commit();
        } catch (Exception ex) {
            throw new RuntimeException("Ha ocurrido un problema al eliminar un actor", ex);
        } finally {
            if (em != null) {
                EntityManagerHelper.closeEntityManager();
            }
        }
    }
    
    public Actor buscar(int actorId){
        EntityManager em = null;
        try {
            em = EntityManagerHelper.getEntityManager();
            return em.find(Actor.class, actorId);
        } catch (Exception ex) {
            throw new RuntimeException("Ha ocurrido un problema al buscar un actor", ex);
        } finally {
            if (em != null) {
                EntityManagerHelper.closeEntityManager();
            }
        }
    }
}
