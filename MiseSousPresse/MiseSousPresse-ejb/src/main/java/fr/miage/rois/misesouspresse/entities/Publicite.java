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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "PUBLICITE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Publicite.findAll", query = "SELECT p FROM Publicite p")
    , @NamedQuery(name = "Publicite.findByIdpub", query = "SELECT p FROM Publicite p WHERE p.idpub = :idpub")
    , @NamedQuery(name = "Publicite.findByContenu", query = "SELECT p FROM Publicite p WHERE p.contenu = :contenu")
    , @NamedQuery(name = "Publicite.findByImage", query = "SELECT p FROM Publicite p WHERE p.image = :image")})
public class Publicite implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDPUB")
    private Integer idpub;
    @Size(max = 1000)
    @Column(name = "CONTENU")
    private String contenu;
    @Size(max = 100)
    @Column(name = "IMAGE")
    private String image;
    @JoinTable(name = "INTEGRER", joinColumns = {
        @JoinColumn(name = "IDPUB", referencedColumnName = "IDPUB")}, inverseJoinColumns = {
        @JoinColumn(name = "IDVOLUME", referencedColumnName = "IDVOLUME")})
    @ManyToMany
    private Collection<Volume> volumeCollection;

    public Publicite() {
    }

    public Publicite(Integer idpub) {
        this.idpub = idpub;
    }

    public Integer getIdpub() {
        return idpub;
    }

    public void setIdpub(Integer idpub) {
        this.idpub = idpub;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @XmlTransient
    public Collection<Volume> getVolumeCollection() {
        return volumeCollection;
    }

    public void setVolumeCollection(Collection<Volume> volumeCollection) {
        this.volumeCollection = volumeCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idpub != null ? idpub.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Publicite)) {
            return false;
        }
        Publicite other = (Publicite) object;
        if ((this.idpub == null && other.idpub != null) || (this.idpub != null && !this.idpub.equals(other.idpub))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder st = new StringBuilder("{");
        st.append("idpub:").append(this.idpub).append(",contenu:").append(this.contenu).append(",image:").append(this.image).append(",volumes:[");
        for (Volume v : this.volumeCollection) {
            st.append("idvolume:").append(v.getIdvolume()).append(",");
        }
        st.deleteCharAt(st.length()-1).append("]}");
        return st.toString();
    }
    
}
