
package com.training.javaee.jsf.viewbeans.film;

import com.training.javaee.jsf.domain.Film;
import com.training.javaee.jsf.services.ServicioFilm;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author training
 */
@ViewScoped
@ManagedBean
public class BeanListaPeliculas implements Serializable{
    
    @Inject
    private ServicioFilm servicioFilm;
    
    private List<Film> listaPeliculas;
    
    
    public BeanListaPeliculas(){
        this.inicializar();
    }
    
    private void inicializar(){
        this.listaPeliculas = new ArrayList();
    }
    
    @PostConstruct
    public void inicializarPC(){
        this.cargarListaActores();
    }
    
    private void cargarListaActores(){
        this.listaPeliculas = this.servicioFilm.consultar();
    }

    public List<Film> getListaPeliculas() {
        return listaPeliculas;
    }

    public void setListaPeliculas(List<Film> listaPeliculas) {
        this.listaPeliculas = listaPeliculas;
    }
    
}
