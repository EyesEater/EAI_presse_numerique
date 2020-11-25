/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.misesouspresse.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sagab
 */
@Entity
@Table(name = "VOLUME")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Volume.findAll", query = "SELECT v FROM Volume v")
    , @NamedQuery(name = "Volume.findByIdvolume", query = "SELECT v FROM Volume v WHERE v.idvolume = :idvolume")
    , @NamedQuery(name = "Volume.findByTermine", query = "SELECT v FROM Volume v WHERE v.termine = :termine")
    , @NamedQuery(name = "Volume.findByNom", query = "SELECT v FROM Volume v WHERE v.nom = :nom")
    , @NamedQuery(name = "Volume.findByNumero", query = "SELECT v FROM Volume v WHERE v.numero = :numero")})
public class Volume implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDVOLUME")
    private Integer idvolume;
    @Column(name = "TERMINE")
    private Boolean termine;
    @Size(max = 100)
    @Column(name = "NOM")
    private String nom;
    @Column(name = "NUMERO")
    private Integer numero;
    @JoinColumn(name = "IDTITRE", referencedColumnName = "IDTITRE")
    @ManyToOne
    private Titre idtitre;
    @ManyToMany(mappedBy = "volumeCollection")
    private Collection<Publicite> publiciteCollection;

    public Volume() {
    }

    public Volume(Integer idvolume) {
        this.idvolume = idvolume;
    }

    public Integer getIdvolume() {
        return idvolume;
    }

    public void setIdvolume(Integer idvolume) {
        this.idvolume = idvolume;
    }

    public Boolean getTermine() {
        return termine;
    }

    public void setTermine(Boolean termine) {
        this.termine = termine;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    @XmlTransient
    public Collection<Publicite> getPubliciteCollection() {
        return publiciteCollection;
    }

    public void setPubliciteCollection(Collection<Publicite> publiciteCollection) {
        this.publiciteCollection = publiciteCollection;
    }

    public Titre getIdtitre() {
        return idtitre;
    }

    public void setIdtitre(Titre idtitre) {
        this.idtitre = idtitre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvolume != null ? idvolume.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Volume)) {
            return false;
        }
        Volume other = (Volume) object;
        if ((this.idvolume == null && other.idvolume != null) || (this.idvolume != null && !this.idvolume.equals(other.idvolume))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.miage.rois.misesouspresse.Volume[ idvolume=" + idvolume + " ]";
    }
    
}
