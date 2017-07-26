
package com.training.javaee.jsf.services;

import java.util.concurrent.Future;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

/**
 *
 * @author training
 */
@Stateless
public class ServicioConAsincronos {
    
    @Asynchronous
    public void longExecuteMethod(){
        for(int i=0; i < 100000; i++){
            System.out.println(i);
        }
    }
    
    @Asynchronous
    public Future<String> longExecuteMethodWithReturn(){
        for(int i=0; i < 1000; i++){
            System.out.println(i);
        }
        return new AsyncResult<String>("Se ejecutó el asincrono en segundo plano");
    }
}
