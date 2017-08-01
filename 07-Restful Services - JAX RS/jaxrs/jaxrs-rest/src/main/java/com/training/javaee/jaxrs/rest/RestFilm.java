/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.training.javaee.jaxrs.rest;

import com.training.javaee.jaxrs.domain.Film;
import com.training.javaee.jaxrs.model.ModeloFilm;
import com.training.javaee.jaxrs.services.ServicioFilm;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author training
 */
@Path("film")
@RequestScoped
public class RestFilm {
    
    @Inject
    private ServicioFilm servicioFilm;
    
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Response consultarFilms(){
        List<Film> listaPeliculas = this.servicioFilm.consultar();
        List<ModeloFilm> resultado = new ArrayList<>();
        for(Film film : listaPeliculas){
            ModeloFilm modeloFilm = new ModeloFilm();
            modeloFilm.setDescription(film.getDescription());
            modeloFilm.setFilmId(film.getFilmId());
            modeloFilm.setLastUpdate(film.getLastUpdate());
            modeloFilm.setLength(film.getLength());
            modeloFilm.setRating(film.getRating());
            modeloFilm.setReleaseYear(film.getReleaseYear());
            modeloFilm.setRentalDuration(film.getRentalDuration());
            modeloFilm.setRentalRate(film.getRentalRate());
            modeloFilm.setReplacementCost(film.getReplacementCost());
            modeloFilm.setSpecialFeatures(film.getSpecialFeatures());
            modeloFilm.setTitle(film.getTitle());
            resultado.add(modeloFilm);
        }
        GenericEntity<List<ModeloFilm>> list = 
		new GenericEntity<List<ModeloFilm>>(resultado) {};
        
        return Response.ok(list).build();
    }
}
