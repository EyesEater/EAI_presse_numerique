/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.misesouspresse.metier;

import fr.miage.rois.misesouspresse.entities.Titre;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sagab
 */
@Stateless
public class TitreFacade extends AbstractFacade<Titre> implements TitreFacadeLocal {

    @PersistenceContext(unitName = "fr.miage.rois_MiseSousPresse-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TitreFacade() {
        super(Titre.class);
    }
    
}
