/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.gestiondistributeurs.metier;

import fr.miage.rois.gestiondistributeurs.entities.Volume;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sagab
 */
@Stateless
public class VolumeFacade extends AbstractFacade<Volume> implements VolumeFacadeLocal {

    @PersistenceContext(unitName = "fr.miage.rois_GestionDistributeurs-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VolumeFacade() {
        super(Volume.class);
    }

    @Override
    public List<Volume> findVolumesByTitre(Integer idtitre) {
        return getEntityManager().createQuery("SELECT v FROM Volume v WHERE v.idtitre = :titre")
                .setParameter("titre", idtitre)
                .getResultList();
    }
    
}
