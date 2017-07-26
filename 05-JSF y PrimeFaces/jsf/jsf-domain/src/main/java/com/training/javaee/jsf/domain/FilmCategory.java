package com.training.javaee.jsf.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author training
 */
@Entity
@Table(name = "film_category")
@NamedQueries({
    @NamedQuery(name = "FilmCategory.findAll", query = "SELECT f FROM FilmCategory f")})
public class FilmCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @EmbeddedId
    protected FilmCategoryPK filmCategoryPK;
    
    @Column(name = "last_update")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdate;
    
    @JoinColumn(name = "category_id", referencedColumnName = "category_id", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Category category;

    public FilmCategory() {
    }

    public FilmCategory(FilmCategoryPK filmCategoryPK) {
        this.filmCategoryPK = filmCategoryPK;
    }

    public FilmCategory(FilmCategoryPK filmCategoryPK, Date lastUpdate) {
        this.filmCategoryPK = filmCategoryPK;
        this.lastUpdate = lastUpdate;
    }

    public FilmCategory(short filmId, short categoryId) {
        this.filmCategoryPK = new FilmCategoryPK(filmId, categoryId);
    }

    public FilmCategoryPK getFilmCategoryPK() {
        return filmCategoryPK;
    }

    public void setFilmCategoryPK(FilmCategoryPK filmCategoryPK) {
        this.filmCategoryPK = filmCategoryPK;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (filmCategoryPK != null ? filmCategoryPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FilmCategory)) {
            return false;
        }
        FilmCategory other = (FilmCategory) object;
        if ((this.filmCategoryPK == null && other.filmCategoryPK != null) || (this.filmCategoryPK != null && !this.filmCategoryPK.equals(other.filmCategoryPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.training.javaee.ejb.domain.FilmCategory[ filmCategoryPK=" + filmCategoryPK + " ]";
    }
    
}
