/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.gestiondistributeurs.services;

import fr.miage.rois.gestiondistributeurs.entities.Abonner;
import fr.miage.rois.gestiondistributeurs.entities.AbonnerPK;
import fr.miage.rois.gestiondistributeurs.entities.Distributeur;
import fr.miage.rois.gestiondistributeurs.entities.Titre;
import fr.miage.rois.gestiondistributeurs.entities.Volume;
import fr.miage.rois.gestiondistributeurs.metier.AbonnerFacadeLocal;
import fr.miage.rois.gestiondistributeurs.metier.DistributeurFacadeLocal;
import fr.miage.rois.gestiondistributeurs.metier.TitreFacadeLocal;
import fr.miage.rois.gestiondistributeurs.metier.VolumeFacadeLocal;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author sagab
 */
@Stateless
public class GestionDistributeurs implements GestionDistributeursLocal {
    
    @EJB
    private DistributeurFacadeLocal distributeurFacadeLocal;
    
    @EJB
    private AbonnerFacadeLocal abonnerFacadeLocal;
    
    @EJB
    private TitreFacadeLocal titreFacadeLocal;
    
    @EJB
    private VolumeFacadeLocal volumeFacadeLocal;

    @Override
    public void creerCompte(Distributeur distributeur) {
        this.distributeurFacadeLocal.create(distributeur);
    }

    @Override
    public void abonnerDistribTitre(Distributeur distributeur, Titre titre, int nbCopies, int cout, int duree) {
        Abonner abonner = new Abonner(new AbonnerPK(distributeur.getIddistributeur(), titre.getIdtitre()));
        abonner.setNbcopies(nbCopies);
        abonner.setCout(cout);
        abonner.setDuree(duree);
        abonner.setValide(false);
        abonner.setDistributeur(distributeur);
        abonner.setTitre(titre);
        this.abonnerFacadeLocal.create(abonner);
    }

    @Override
    public void validerAbonnement(Abonner abonner) {
        abonner.setValide(true);
        this.abonnerFacadeLocal.edit(abonner);
    }

    @Override
    public Titre findTitreById(int idtitre) {
        return this.titreFacadeLocal.find(idtitre);
    }

    @Override
    public void creerTitre(Titre titre) {
        this.titreFacadeLocal.create(titre);
    }

    @Override
    public Volume findVolumeById(int idvolume) {
        return this.volumeFacadeLocal.find(idvolume);
    }

    @Override
    public void creerVolume(Volume volume) {
        this.volumeFacadeLocal.create(volume);
        this.envoyerVolumeAuxDistributeurs(volume.getIdtitre().getIdtitre());
    }

    @Override
    public Distributeur findDistribById(int idDistrib) {
        return this.distributeurFacadeLocal.find(idDistrib);
    }

    private void envoyerVolumeAuxDistributeurs(int idTitre) {
        Titre titre = this.titreFacadeLocal.find(idTitre);
        
        if (titre != null) {
            ArrayList<Volume> volumes = new ArrayList(this.volumeFacadeLocal.findVolumesByTitre(titre));
            
            for (Volume volume : volumes) {
                DistributionVolumeJMSSender.envoyerVolumeAuxDistributeurs(volume);
            }
        }
    }

}
