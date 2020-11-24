/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.redacchef.service;

import fr.miage.rois.redacchef.entities.Article;
import fr.miage.rois.redacchef.entities.Titre;
import fr.miage.rois.redacchef.metier.TitreFacadeLocal;
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
    public void affecterTitre(Titre titre) {
        this.titreFacadeLocal.create(titre);
    }

    @Override
    public Titre findTitre(Integer idTitre) {
        return this.titreFacadeLocal.find(idTitre);
    }
    
}
