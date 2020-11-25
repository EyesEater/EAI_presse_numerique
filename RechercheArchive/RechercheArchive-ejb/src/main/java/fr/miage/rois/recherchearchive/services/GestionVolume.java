/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.recherchearchive.services;

import fr.miage.rois.recherchearchive.entities.Titre;
import fr.miage.rois.recherchearchive.entities.Volume;
import fr.miage.rois.recherchearchive.metier.TitreFacadeLocal;
import fr.miage.rois.recherchearchive.metier.VolumeFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author sagab
 */
@Stateless
public class GestionVolume implements GestionVolumeLocal {
    
    @EJB
    private VolumeFacadeLocal volumeFacadeLocal;
    
    @EJB
    private TitreFacadeLocal titreFacadeLocal;

    @Override
    public Volume findByNom(String nom) {
        return volumeFacadeLocal.findByNom(nom);
    }

    @Override
    public void ajouterVolume(Volume volume) {
        volumeFacadeLocal.create(volume);
    }

    @Override
    public List<Volume> findByTitreId(String titre) {
        Titre t = titreFacadeLocal.findById(titre);
        return volumeFacadeLocal.findByTitreId(t);
    }

}
