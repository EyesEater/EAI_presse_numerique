/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.recherchearchive.services;

import fr.miage.rois.recherchearchive.entities.Titre;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sagab
 */
@Local
public interface GestionTitreLocal {
    
    Titre findByNom(String nom);
    
    List<Titre> findByMotsCles(String motsCles);
    
    void ajouterTitre(Titre titre);

    public Titre findById(String idtitre);
    
}
