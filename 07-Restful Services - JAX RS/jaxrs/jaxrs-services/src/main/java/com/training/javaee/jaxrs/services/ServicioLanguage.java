package com.training.javaee.jaxrs.services;


import com.training.javaee.jaxrs.domain.Language;
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
public class ServicioLanguage {
    
    @PersistenceContext(unitName = "SakilaPU")
    private EntityManager em;
    
    public List<Language> consultar(){
        try {
            Query query = em.createNativeQuery("select * from language", Language.class);
            List<Language> resultado = query.getResultList();
            return resultado;
        } catch (Exception ex) {
            throw new RuntimeException("Error consultando los lenguages", ex);
        }
    }
    
}
