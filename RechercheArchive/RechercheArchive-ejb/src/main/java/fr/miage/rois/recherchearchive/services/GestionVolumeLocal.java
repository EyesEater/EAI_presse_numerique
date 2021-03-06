/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.recherchearchive.services;

import fr.miage.rois.recherchearchive.entities.Volume;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sagab
 */
@Local
public interface GestionVolumeLocal {
    
    public Volume findByNom(String nom);
    
    public void ajouterVolume(Volume volume);

    public List<Volume> findByTitreId(String titre);
    
}
