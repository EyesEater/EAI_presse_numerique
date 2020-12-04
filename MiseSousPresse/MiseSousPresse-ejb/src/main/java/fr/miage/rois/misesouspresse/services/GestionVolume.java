/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.misesouspresse.services;

import fr.miage.rois.misesouspresse.entities.Publicite;
import fr.miage.rois.misesouspresse.entities.Titre;
import fr.miage.rois.misesouspresse.entities.Volume;
import fr.miage.rois.misesouspresse.metier.PubliciteFacadeLocal;
import fr.miage.rois.misesouspresse.metier.TitreFacadeLocal;
import fr.miage.rois.misesouspresse.metier.VolumeFacadeLocal;
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
    private PubliciteFacadeLocal publiciteFacadeLocal;
    
    @EJB
    private TitreFacadeLocal titreFacadeLocal;

    @Override
    public boolean creerVolume(Volume volume) {
        this.volumeFacadeLocal.create(volume);
        
        Volume v = this.volumeFacadeLocal.findById(volume.getIdvolume());
        if (v != null) {
            return v.equals(volume);
        }
        
        return false;
    }

    @Override
    public void affecterNumeroAUnVolume(int numero, Volume volume) {
        this.volumeFacadeLocal.affecterNumeroAUnVolume(numero, volume);
    }

    @Override
    public void affecterEncartsPublicitaires(Volume volume) {
        List<Publicite> pubs = this.publiciteFacadeLocal.getPublicites();
        
        this.volumeFacadeLocal.affecterListPub(volume, pubs);
    }

    @Override
    public void creerPublicite(Publicite publicite) {
        this.publiciteFacadeLocal.create(publicite);
    }

    @Override
    public Titre findTitreById(int idTitre) {
        return this.titreFacadeLocal.find(idTitre);
    }
    
}
