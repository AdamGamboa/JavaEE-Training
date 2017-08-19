package com.training.java8.j8ejemplo;

import com.training.java8.j8ejemplo.interfacefuncionales.MyComparador;
import com.training.java8.j8ejemplo.interfacefuncionales.MyPredicado;
import com.training.java8.j8ejemplo.modelo.Persona;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author training
 */
public class LambdaApiEjemplos {
    
    private static List<Persona> personas;
    
    public static void main (String [] args){
        LambdaApiEjemplos instancia = new LambdaApiEjemplos();
        instancia.personas = new ArrayList<Persona>();
        personas.add(new Persona("Juan", "Perez","Perez", 30, "San Jose", 'M'));
        personas.add(new Persona("Adam", "Gamboa","Gonzalez", 29, "San Jose", 'M'));
        personas.add(new Persona("Luis", "Salas","Solis", 20, "Cartago", 'M'));
        personas.add(new Persona("Maria", "Gonzalez","Ramirez", 34, "Heredia", 'F'));
        personas.add(new Persona("Estaba", "Perez","Solis", 25, "Alajuela", 'M'));
        personas.add(new Persona("Carla", "Perez","Ortega", 24, "San Jose", 'F'));
        
        System.out.println("----- Antes de : -------");
        for(Persona p : personas){
            System.out.println(p);
        }
        System.out.println("----- Antes de : -------");
        
        List<Persona> filtrado = instancia.filtrarPersonas(personas, (Persona p) -> p.getEdad()>=30);
        
        System.out.println("----- Despues de : -------");
        filtrado.stream().forEach(p->System.out.println(p));
        System.out.println("----- Despues de : -------");
    }
    
    public List<Persona> filtrarPersonas(List<Persona> personas, MyPredicado<Persona> myPredicado){
        List<Persona> resultado = new ArrayList<>();
        for(Persona p : personas){
            if(myPredicado.evaluar(p)){
                resultado.add(p);
            }
        }
        return resultado;
    }
    
    
    public void ejecutarHilo(){
        Thread t = new Thread(
            new Runnable() {
                @Override
                public void run() {
                    System.out.println("Logica del hilo");
                }
            });
        t.start();
        
        //---
        Thread t2 = new Thread(() -> System.out.println("Logica del hilo"));
    }
    
}
