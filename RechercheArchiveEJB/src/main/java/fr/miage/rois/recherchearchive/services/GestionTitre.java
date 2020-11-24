/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.recherchearchive.services;

import fr.miage.rois.recherchearchive.entities.Titre;
import fr.miage.rois.recherchearchive.metier.TitreFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author sagab
 */
@Stateless
public class GestionTitre implements GestionTitreLocal {
    
    @EJB
    private TitreFacadeLocal titreFacadeLocal;

    @Override
    public List<Titre> findByNom(String nom) {
        return titreFacadeLocal.findByNom(nom);
    }

    @Override
    public List<Titre> findByMotsCles(String motsCles) {
        return titreFacadeLocal.findByMotsCles(motsCles);
    }

    @Override
    public void ajouterTitre(Titre titre) {
        titreFacadeLocal.create(titre);
    }

}
