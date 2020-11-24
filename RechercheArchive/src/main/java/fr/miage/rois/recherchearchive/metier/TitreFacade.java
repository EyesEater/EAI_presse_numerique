/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.recherchearchive.metier;

import fr.miage.rois.recherchearchive.entities.Titre;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sagab
 */
@Stateless
public class TitreFacade extends AbstractFacade<Titre> implements TitreFacadeLocal {

    @PersistenceContext(unitName = "fr.miage.rois_RechercheArchive_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TitreFacade() {
        super(Titre.class);
    }
    
}
