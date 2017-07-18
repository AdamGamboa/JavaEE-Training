package com.training.javaee.jpa.services;

import com.training.javaee.jpa.common.EntityManagerHelper;
import com.training.javaee.jpa.domain.Film;
import com.training.javaee.jpa.domain.Language;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author training
 */
@RequestScoped
public class ServicioLanguage {
    
    
    public List<Language> consultar(){
        EntityManager em = null;
        try {
            em = EntityManagerHelper.getEntityManager();
            Query query = em.createNativeQuery("select * from language", Language.class);
            List<Language> resultado = query.getResultList();
            return resultado;
        } catch (Exception ex) {
            throw new RuntimeException("Error consultando los lenguages", ex);
        } finally {
            if (em != null) {
                EntityManagerHelper.closeEntityManager();
            }
        }
    }
    
}
