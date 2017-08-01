
package com.training.javaee.jaxrs.viewbeans.language;


import com.training.javaee.jaxrs.model.ModeloLanguage;
import com.training.javaee.jaxrs.restclient.RestClientLanguage;
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
public class BeanListaIdiomas implements Serializable{
    
    @Inject
    private RestClientLanguage restClientLanguage;
    
    private List<ModeloLanguage> listaIdiomas;
    
    
    public BeanListaIdiomas(){
        this.inicializar();
    }
    
    private void inicializar(){
        this.listaIdiomas = new ArrayList();
    }
    
    @PostConstruct
    public void inicializarPC(){
        this.cargarListaActores();
    }
    
    private void cargarListaActores(){
        this.listaIdiomas = this.restClientLanguage.consultar();
    }

    public List<ModeloLanguage> getListaIdiomas() {
        return listaIdiomas;
    }

    public void setListaIdiomas(List<ModeloLanguage> listaIdiomas) {
        this.listaIdiomas = listaIdiomas;
    }
    
    
}
