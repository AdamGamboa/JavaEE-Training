/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.training.java8.j8ejemplo.modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;

/**
 *
 * @author Adam
 */
public class Automovil {
    
    private String marca;
    private Year modelo;
    private String color;
    private LocalDateTime fechaProduccion;
    private LocalDate anoVenta;
    private double precio;

    public Automovil() {
    }

    public Automovil(String marca, Year modelo, String color, double precio) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.precio = precio;
    }
    
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Year getModelo() {
        return modelo;
    }

    public void setModelo(Year modelo) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public LocalDateTime getFechaProduccion() {
        return fechaProduccion;
    }

    public void setFechaProduccion(LocalDateTime fechaProduccion) {
        this.fechaProduccion = fechaProduccion;
    }

    public LocalDate getAnoVenta() {
        return anoVenta;
    }

    public void setAnoVenta(LocalDate anoVenta) {
        this.anoVenta = anoVenta;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    
    
}
