
package com.training.javaee.jsf.viewbeans.language;


import com.training.javaee.jsf.domain.Language;
import com.training.javaee.jsf.services.ServicioLanguage;
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
    private ServicioLanguage servicioLanguage;
    
    private List<Language> listaIdiomas;
    
    
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
        this.listaIdiomas = this.servicioLanguage.consultar();
    }

    public List<Language> getListaIdiomas() {
        return listaIdiomas;
    }

    public void setListaIdiomas(List<Language> listaIdiomas) {
        this.listaIdiomas = listaIdiomas;
    }
    
    
}
