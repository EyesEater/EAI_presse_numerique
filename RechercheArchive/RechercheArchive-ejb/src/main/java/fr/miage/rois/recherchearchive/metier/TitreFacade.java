/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.recherchearchive.metier;

import fr.miage.rois.recherchearchive.entities.Titre;
import java.util.List;
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

    @Override
    public Titre findByNom(String nom) {
        return (Titre) em.createQuery("SELECT * FROM Titre WHERE nom LIKE :nom")
                .setParameter("nom", nom)
                .getSingleResult();
    }

    @Override
    public List<Titre> findByMotsCles(String motsCles) {
        return em.createQuery("SELECT * FROM Titre WHERE motscles IN (:motscles)")
                .setParameter("motscles", motsCles)
                .getResultList();
    }
    
}
