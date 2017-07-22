
package com.training.javaee.ejb.services;

import java.io.Serializable;
import java.util.Date;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Startup;

/**
 *
 * @author training
 */
@Startup
@Singleton
public class ServicioFecha implements Serializable{
    
    @Schedule(hour = "*", minute = "*", second = "*/15")
    public void imprimaHora(){
        System.out.println(new Date());
    }
    
}
