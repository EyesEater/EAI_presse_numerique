/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.gestiondistributeurs.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author sagab
 */
@Embeddable
public class AbonnerPK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "IDDISTRIBUTEUR")
    private int iddistributeur;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDTITRE")
    private int idtitre;

    public AbonnerPK() {
    }

    public AbonnerPK(int iddistributeur, int idtitre) {
        this.iddistributeur = iddistributeur;
        this.idtitre = idtitre;
    }

    public int getIddistributeur() {
        return iddistributeur;
    }

    public void setIddistributeur(int iddistributeur) {
        this.iddistributeur = iddistributeur;
    }

    public int getIdtitre() {
        return idtitre;
    }

    public void setIdtitre(int idtitre) {
        this.idtitre = idtitre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) iddistributeur;
        hash += (int) idtitre;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AbonnerPK)) {
            return false;
        }
        AbonnerPK other = (AbonnerPK) object;
        if (this.iddistributeur != other.iddistributeur) {
            return false;
        }
        if (this.idtitre != other.idtitre) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.miage.rois.gestiondistributeurs.entities.AbonnerPK[ iddistributeur=" + iddistributeur + ", idtitre=" + idtitre + " ]";
    }
    
}
