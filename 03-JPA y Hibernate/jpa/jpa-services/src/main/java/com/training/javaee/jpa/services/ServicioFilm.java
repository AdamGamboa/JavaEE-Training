package com.training.javaee.jpa.services;

import com.training.javaee.jpa.common.EntityManagerHelper;
import com.training.javaee.jpa.domain.Actor;
import com.training.javaee.jpa.domain.Film;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author training
 */
@RequestScoped
public class ServicioFilm {
    
    public List<Film> consultar(){
        EntityManager em = null;
        try {
            em = EntityManagerHelper.getEntityManager();
            Query query = em.createNamedQuery("Film.findAll");
            List<Film> resultado = query.getResultList();
            return resultado;
        } catch (Exception ex) {
            throw new RuntimeException("Error consultando las peliculas", ex);
        } finally {
            if (em != null) {
                EntityManagerHelper.closeEntityManager();
            }
        }
    }
    
    public Film buscar(short filmId){
        EntityManager em = null;
        try {
            em = EntityManagerHelper.getEntityManager();
            return em.find(Film.class, filmId);
        } catch (Exception ex) {
            throw new RuntimeException("Ha ocurrido un problema al buscar una Pelicula", ex);
        } finally {
            if (em != null) {
                EntityManagerHelper.closeEntityManager();
            }
        }
    }
    
}
