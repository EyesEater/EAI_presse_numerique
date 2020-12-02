/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.gestiondistributeurs.services;

import fr.miage.rois.gestiondistributeurs.entities.Abonner;
import fr.miage.rois.gestiondistributeurs.entities.Distributeur;
import fr.miage.rois.gestiondistributeurs.entities.Titre;
import fr.miage.rois.gestiondistributeurs.entities.Volume;
import javax.ejb.Local;

/**
 *
 * @author sagab
 */
@Local
public interface GestionDistributeursLocal {
    
    public void creerCompte(Distributeur distributeur);
    
    public void abonnerDistribTitre(Distributeur distributeur, Titre titre, int nbCopies, int cout, int duree);
    
    public void validerAbonnement(Abonner abonner);

    public Titre findTitreById(int idtitre);

    public void creerTitre(Titre titre);

    public Volume findVolumeById(int idvolume);

    public void creerVolume(Volume volume);

    public Distributeur findDistribById(int idDistrib);
}
