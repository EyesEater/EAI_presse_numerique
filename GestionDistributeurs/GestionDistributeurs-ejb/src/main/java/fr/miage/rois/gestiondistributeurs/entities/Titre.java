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
import javax.validation.constraints.Size;
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
    , @NamedQuery(name = "Titre.findByIdtitre", query = "SELECT t FROM Titre t WHERE t.idtitre = :idtitre")
    , @NamedQuery(name = "Titre.findByNom", query = "SELECT t FROM Titre t WHERE t.nom = :nom")
    , @NamedQuery(name = "Titre.findByMotscles", query = "SELECT t FROM Titre t WHERE t.motscles = :motscles")})
public class Titre implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDTITRE")
    private Integer idtitre;
    @Size(max = 100)
    @Column(name = "NOM")
    private String nom;
    @Size(max = 100)
    @Column(name = "MOTSCLES")
    private String motscles;
    @OneToMany(mappedBy = "idtitre")
    private Collection<Volume> volumeCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "titre")
    private Collection<Abonner> abonnerCollection;

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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getMotscles() {
        return motscles;
    }

    public void setMotscles(String motscles) {
        this.motscles = motscles;
    }

    @XmlTransient
    public Collection<Volume> getVolumeCollection() {
        return volumeCollection;
    }

    public void setVolumeCollection(Collection<Volume> volumeCollection) {
        this.volumeCollection = volumeCollection;
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
        StringBuilder st = new StringBuilder("{");
        String[] motscles = this.motscles.split(",");
        st.append("idtitre:").append(this.idtitre).append(",motscles:[");
        for (String s : motscles)
                st.append("\"").append(s).append("\",");
        if (st.charAt(st.length()-1) == ',')
            st.deleteCharAt(st.length()-1);
        st.append("],nom:\"").append(this.nom).append("\",volumes:[");
        for (Volume v : this.volumeCollection) {
            st.append(v.getIdvolume()).append(",");
        }
        st.deleteCharAt(st.length()-1).append("]}");
        return st.toString();
    }
    
}
