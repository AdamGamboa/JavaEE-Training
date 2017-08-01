
package com.training.javaee.jaxrs.viewbeans.film;

import com.training.javaee.jaxrs.model.ModeloFilm;
import com.training.javaee.jaxrs.restclient.RestClientFilm;
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
    private RestClientFilm restClientFilm;
    
    private List<ModeloFilm> listaPeliculas;
    
    
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
        this.listaPeliculas = this.restClientFilm.consultar();
    }

    public List<ModeloFilm> getListaPeliculas() {
        return listaPeliculas;
    }

    public void setListaPeliculas(List<ModeloFilm> listaPeliculas) {
        this.listaPeliculas = listaPeliculas;
    }
    
}
