/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.redacchefejb;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sagab
 */
@Entity
@Table(name = "TITRE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Titre.findAll", query = "SELECT t FROM Titre t")
    , @NamedQuery(name = "Titre.findByIdtitre", query = "SELECT t FROM Titre t WHERE t.idtitre = :idtitre")})
public class Titre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDTITRE")
    private Integer idtitre;
    @OneToMany(mappedBy = "idtitre")
    private Collection<Article> articleCollection;

    public Titre() {
    }

    public Titre(Integer idtitre) {
        this.idtitre = idtitre;
    }

    public Integer getIdtitre() {
        return idtitre;
    }

    public void setIdtitre(Integer idtitre) {
        this.idtitre = idtitre;
    }

    @XmlTransient
    public Collection<Article> getArticleCollection() {
        return articleCollection;
    }

    public void setArticleCollection(Collection<Article> articleCollection) {
        this.articleCollection = articleCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idtitre != null ? idtitre.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Titre)) {
            return false;
        }
        Titre other = (Titre) object;
        if ((this.idtitre == null && other.idtitre != null) || (this.idtitre != null && !this.idtitre.equals(other.idtitre))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.miage.rois.redacchefejb.Titre[ idtitre=" + idtitre + " ]";
    }
    
}
