package com.training.javaee.servletcdi.managedbeans;

import java.io.InputStream;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author training
 */
@RequestScoped
public class ControlImagen {
    
    public InputStream obtenerImagen(int i){
        String imagenName;
        switch (i) {
            case 1 :
                imagenName = "/imagenes/duke.png";
                break;
            case 2 : 
                imagenName = "/imagenes/glassfish.png";
                break;
            case 3 : 
                imagenName = "/imagenes/java.png";
                break;
            default :
                return null;
        }
        InputStream is = this.getClass().getResourceAsStream(imagenName);
        return is;
    }
    
}
