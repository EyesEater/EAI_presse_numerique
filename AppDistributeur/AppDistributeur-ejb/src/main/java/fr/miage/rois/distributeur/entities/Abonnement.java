/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.distributeur.entities;

/**
 *
 * @author sagab
 */
public class Abonnement {
    
    private int idDistributeur;
    private int idTitre;
    private int nbCopies;
    private int cout;
    private int duree;

    public Abonnement(int idDistributeur, int idTitre, int nbCopies, int cout, int duree) {
        this.idDistributeur = idDistributeur;
        this.idTitre = idTitre;
        this.nbCopies = nbCopies;
        this.cout = cout;
        this.duree = duree;
    }

    public int getIdDistributeur() {
        return idDistributeur;
    }

    public void setIdDistributeur(int idDistributeur) {
        this.idDistributeur = idDistributeur;
    }

    public int getIdTitre() {
        return idTitre;
    }

    public void setIdTitre(int idTitre) {
        this.idTitre = idTitre;
    }

    public int getNbCopies() {
        return nbCopies;
    }

    public void setNbCopies(int nbCopies) {
        this.nbCopies = nbCopies;
    }

    public int getCout() {
        return cout;
    }

    public void setCout(int cout) {
        this.cout = cout;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }
    
    
    
}
