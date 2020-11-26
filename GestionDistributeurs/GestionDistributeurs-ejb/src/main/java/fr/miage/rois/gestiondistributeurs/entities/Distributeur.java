/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.gestiondistributeurs.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
@Table(name = "DISTRIBUTEUR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Distributeur.findAll", query = "SELECT d FROM Distributeur d")
    , @NamedQuery(name = "Distributeur.findByIddistributeur", query = "SELECT d FROM Distributeur d WHERE d.iddistributeur = :iddistributeur")})
public class Distributeur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDDISTRIBUTEUR")
    private Integer iddistributeur;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "distributeur")
    private Collection<Abonner> abonnerCollection;

    public Distributeur() {
    }

    public Distributeur(Integer iddistributeur) {
        this.iddistributeur = iddistributeur;
    }

    public Integer getIddistributeur() {
        return iddistributeur;
    }

    public void setIddistributeur(Integer iddistributeur) {
        this.iddistributeur = iddistributeur;
    }

    @XmlTransient
    public Collection<Abonner> getAbonnerCollection() {
        return abonnerCollection;
    }

    public void setAbonnerCollection(Collection<Abonner> abonnerCollection) {
        this.abonnerCollection = abonnerCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iddistributeur != null ? iddistributeur.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Distributeur)) {
            return false;
        }
        Distributeur other = (Distributeur) object;
        if ((this.iddistributeur == null && other.iddistributeur != null) || (this.iddistributeur != null && !this.iddistributeur.equals(other.iddistributeur))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.miage.rois.gestiondistributeurs.entities.Distributeur[ iddistributeur=" + iddistributeur + " ]";
    }
    
}
