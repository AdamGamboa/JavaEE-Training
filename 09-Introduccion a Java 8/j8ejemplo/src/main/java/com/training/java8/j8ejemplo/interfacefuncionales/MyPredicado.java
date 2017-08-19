/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.training.java8.j8ejemplo.interfacefuncionales;

/**
 *
 * @author training
 */
@FunctionalInterface
public interface MyPredicado<T> {
    
    boolean evaluar(T objet);
}
