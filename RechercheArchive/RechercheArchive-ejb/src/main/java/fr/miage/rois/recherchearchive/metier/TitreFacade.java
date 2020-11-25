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
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sagab
 */
@Stateless
public class TitreFacade extends AbstractFacade<Titre> implements TitreFacadeLocal {

    @PersistenceContext(unitName = "fr.miage.rois_RechercheArchive-ejb_ejb_1.0-SNAPSHOTPU")
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
        Titre titre = null;
        try {
        titre = (Titre) em.createQuery("SELECT t FROM Titre t WHERE t.nom LIKE :nom")
                .setParameter("nom", nom)
                .getSingleResult();
        } catch (NoResultException e) {
            return titre;
        } catch (NonUniqueResultException e) {
            titre = (Titre) em.createQuery("SELECT t FROM Titre t WHERE t.nom LIKE :nom")
                .setParameter("nom", nom)
                .getResultList().get(0);
        }
        return titre;
    }

    @Override
    public List<Titre> findByMotsCles(String motsCles) {
        motsCles = "%" + motsCles + "%";
        motsCles = motsCles.replace(",", "%,%");
        System.out.println(motsCles);
        return em.createQuery("SELECT t FROM Titre t WHERE t.motscles LIKE :motscles")
                .setParameter("motscles", motsCles)
                .getResultList();
    }

    @Override
    public Titre findById(String idtitre) {
        Titre titre = null;
        try {
            return (Titre) getEntityManager().createQuery("SELECT t FROM Titre t WHERE t.idtitre = :idtitre")
                .setParameter("idtitre", Integer.parseInt(idtitre))
                .getSingleResult();
        } catch (NoResultException e) {
            return titre;
        }
    }
    
}
