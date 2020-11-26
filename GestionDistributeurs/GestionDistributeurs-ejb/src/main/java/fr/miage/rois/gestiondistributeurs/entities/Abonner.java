/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.gestiondistributeurs.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sagab
 */
@Entity
@Table(name = "ABONNER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Abonner.findAll", query = "SELECT a FROM Abonner a")
    , @NamedQuery(name = "Abonner.findByIddistributeur", query = "SELECT a FROM Abonner a WHERE a.abonnerPK.iddistributeur = :iddistributeur")
    , @NamedQuery(name = "Abonner.findByIdtitre", query = "SELECT a FROM Abonner a WHERE a.abonnerPK.idtitre = :idtitre")
    , @NamedQuery(name = "Abonner.findByNbcopies", query = "SELECT a FROM Abonner a WHERE a.nbcopies = :nbcopies")
    , @NamedQuery(name = "Abonner.findByCout", query = "SELECT a FROM Abonner a WHERE a.cout = :cout")
    , @NamedQuery(name = "Abonner.findByDuree", query = "SELECT a FROM Abonner a WHERE a.duree = :duree")
    , @NamedQuery(name = "Abonner.findByValide", query = "SELECT a FROM Abonner a WHERE a.valide = :valide")})
public class Abonner implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AbonnerPK abonnerPK;
    @Column(name = "NBCOPIES")
    private Integer nbcopies;
    @Column(name = "COUT")
    private Integer cout;
    @Column(name = "DUREE")
    private Integer duree;
    @Column(name = "VALIDE")
    private Boolean valide;
    @JoinColumn(name = "IDDISTRIBUTEUR", referencedColumnName = "IDDISTRIBUTEUR", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Distributeur distributeur;
    @JoinColumn(name = "IDTITRE", referencedColumnName = "IDTITRE", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Titre titre;

    public Abonner() {
    }

    public Abonner(AbonnerPK abonnerPK) {
        this.abonnerPK = abonnerPK;
    }

    public Abonner(int iddistributeur, int idtitre) {
        this.abonnerPK = new AbonnerPK(iddistributeur, idtitre);
    }

    public AbonnerPK getAbonnerPK() {
        return abonnerPK;
    }

    public void setAbonnerPK(AbonnerPK abonnerPK) {
        this.abonnerPK = abonnerPK;
    }

    public Integer getNbcopies() {
        return nbcopies;
    }

    public void setNbcopies(Integer nbcopies) {
        this.nbcopies = nbcopies;
    }

    public Integer getCout() {
        return cout;
    }

    public void setCout(Integer cout) {
        this.cout = cout;
    }

    public Integer getDuree() {
        return duree;
    }

    public void setDuree(Integer duree) {
        this.duree = duree;
    }

    public Boolean getValide() {
        return valide;
    }

    public void setValide(Boolean valide) {
        this.valide = valide;
    }

    public Distributeur getDistributeur() {
        return distributeur;
    }

    public void setDistributeur(Distributeur distributeur) {
        this.distributeur = distributeur;
    }

    public Titre getTitre() {
        return titre;
    }

    public void setTitre(Titre titre) {
        this.titre = titre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (abonnerPK != null ? abonnerPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Abonner)) {
            return false;
        }
        Abonner other = (Abonner) object;
        if ((this.abonnerPK == null && other.abonnerPK != null) || (this.abonnerPK != null && !this.abonnerPK.equals(other.abonnerPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.miage.rois.gestiondistributeurs.entities.Abonner[ abonnerPK=" + abonnerPK + " ]";
    }
    
}
