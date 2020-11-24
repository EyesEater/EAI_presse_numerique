/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.recherchearchive.services;

import fr.miage.rois.recherchearchive.entities.Titre;
import fr.miage.rois.recherchearchive.entities.Volume;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author sagab
 */
@Stateless
public class AppliRechercheArchive implements AppliRechercheArchiveLocal {
    
    @EJB
    private GestionTitreLocal gestionTitreLocal;
    
    @EJB
    private GestionVolumeLocal gestionVolumeLocal;

    @Override
    public Volume findVolumeByNom(String nom) {
        return gestionVolumeLocal.findByNom(nom);
    }
    
    @Override
    public void ajouterVolume(Volume volume) {
        gestionVolumeLocal.ajouterVolume(volume);
    }
    
    @Override
    public Titre findTitreByNom(String nom) {
        return gestionTitreLocal.findByNom(nom);
    }
    
    @Override
    public List<Titre> findTitreByMotsCles(String motsCles) {
        return gestionTitreLocal.findByMotsCles(motsCles);
    }
    
    @Override
    public void ajouterTitre(Titre titre) {
        gestionTitreLocal.ajouterTitre(titre);
    }
}
