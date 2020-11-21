/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.redacchef.service;

import fr.miage.rois.redacchef.entities.Titre;
import javax.ejb.Local;

/**
 *
 * @author sagab
 */
@Local
public interface GestionTitreLocal {
    
    public void affecterTitre(Titre titre);
        
}
