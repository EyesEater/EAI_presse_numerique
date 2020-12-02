/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.gestiondistributeurs.metier;

import fr.miage.rois.gestiondistributeurs.entities.Abonner;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sagab
 */
@Stateless
public class AbonnerFacade extends AbstractFacade<Abonner> implements AbonnerFacadeLocal {

    @PersistenceContext(unitName = "fr.miage.rois_GestionDistributeurs-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AbonnerFacade() {
        super(Abonner.class);
    }

    @Override
    public List<Abonner> findAbonnerByTitre(Integer idtitre) {
        return getEntityManager().createQuery("SELECT a FROM Abonner a WHERE a.abonnerPK.idtitre = :titre")
                .setParameter("titre", idtitre)
                .getResultList();
    }
    
}
