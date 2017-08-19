package com.training.java8.j8ejemplo.comparadores;

import com.training.java8.j8ejemplo.modelo.Persona;
import java.util.Comparator;

/**
 *
 * @author training
 */
public class PersonaNombreComparador implements Comparator<Persona>{

    @Override
    public int compare(Persona o1, Persona o2) {
        if(o1 != null && o2 != null){
            return o1.getNombre().compareTo(o2.getNombre());
        }
        return 1;
    }
    
}
