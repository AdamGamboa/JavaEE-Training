package com.training.java8.j8ejemplo.modelo;

/**
 *
 * @author training
 */
public class Camisa {
    
    private String marca;
    private String color;
    private String tamano;
    private double precio;

    public Camisa() {
    }

    public Camisa(String marca, String color, String tamano, double precio) {
        this.marca = marca;
        this.color = color;
        this.tamano = tamano;
        this.precio = precio;
    }
 

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    
}
