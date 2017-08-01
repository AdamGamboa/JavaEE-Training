package com.training.javaee.jaxrs.services;


import com.training.javaee.jaxrs.domain.Film;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author training
 */
@Stateless
public class ServicioFilm {
    
    @PersistenceContext(unitName = "SakilaPU")
    private EntityManager em;
    
    public List<Film> consultar(){
        try {
            Query query = em.createNamedQuery("Film.findAll");
            List<Film> resultado = query.getResultList();
            return resultado;
        } catch (Exception ex) {
            throw new RuntimeException("Error consultando las peliculas", ex);
        }
    }
    
    public Film buscar(short filmId){
        try {
            return em.find(Film.class, filmId);
        } catch (Exception ex) {
            throw new RuntimeException("Ha ocurrido un problema al buscar una Pelicula", ex);
        }
    }
    
}
