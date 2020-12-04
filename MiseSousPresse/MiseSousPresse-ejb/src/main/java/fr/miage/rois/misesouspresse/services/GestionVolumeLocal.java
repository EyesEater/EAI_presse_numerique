/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.misesouspresse.services;

import fr.miage.rois.misesouspresse.entities.Publicite;
import fr.miage.rois.misesouspresse.entities.Titre;
import fr.miage.rois.misesouspresse.entities.Volume;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sagab
 */
@Local
public interface GestionVolumeLocal {
    
    public boolean creerVolume(Volume volume);
    
    public void affecterNumeroAUnVolume(int numero, Volume volume);
    
    public void affecterEncartsPublicitaires(Volume volume);

    public void creerPublicite(Publicite publicite);

    public Titre findTitreById(int idTitre);
}
