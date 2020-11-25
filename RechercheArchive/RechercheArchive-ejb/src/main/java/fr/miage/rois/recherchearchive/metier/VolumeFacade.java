/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.recherchearchive.metier;

import fr.miage.rois.recherchearchive.entities.Titre;
import fr.miage.rois.recherchearchive.entities.Volume;
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
public class VolumeFacade extends AbstractFacade<Volume> implements VolumeFacadeLocal {

    @PersistenceContext(unitName = "fr.miage.rois_RechercheArchive-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VolumeFacade() {
        super(Volume.class);
    }

    @Override
    public Volume findByNom(String nom) {
        Volume volume = null;
        try {
            volume = (Volume) em.createQuery("SELECT v FROM Volume v WHERE v.nom LIKE :nom")
                .setParameter("nom", nom)
                .getSingleResult();
        } catch (NoResultException e) {
            return volume;
        } catch (NonUniqueResultException e) {
            volume = (Volume) em.createQuery("SELECT v FROM Volume v WHERE v.nom LIKE :nom")
                .setParameter("nom", nom)
                .getResultList().get(0);
        }
        return volume;
    }

    @Override
    public List<Volume> findByTitreId(Titre titre) {
        return em.createQuery("SELECT v FROM Volume v WHERE v.idtitre = :titre")
                .setParameter("titre", titre)
                .getResultList();
    }
    
}
