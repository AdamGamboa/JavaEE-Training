package com.training.javaee.jpa.common;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author training
 */
public class EntityManagerHelper {

    private static EntityManagerFactory emf;
    private static final ThreadLocal<EntityManager> threadLocal = new ThreadLocal<>();

    /**
     * Get EntityManager
     *
     * @return EntityManager
     */
    public static EntityManager getEntityManager() {
        if(emf == null){
            emf= Persistence.createEntityManagerFactory("SakilaPU");
        }
        EntityManager manager = threadLocal.get();
        if (manager == null || manager.isOpen() == false) {
            manager = emf.createEntityManager();
            threadLocal.set(manager);
        }
        return manager;
    }

    /**
     * Close EntityManager
     */
    public static void closeEntityManager() {
        final EntityManager em = threadLocal.get();
        threadLocal.set(null);
        closeEm(em);
    }

    /**
     * Close EntityManager
     *
     * @param em EntityManager
     */
    private static void closeEm(final EntityManager em) {
        try {
            if (em != null) {
                em.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
