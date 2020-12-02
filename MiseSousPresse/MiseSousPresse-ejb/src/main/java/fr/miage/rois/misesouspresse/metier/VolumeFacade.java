/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.misesouspresse.metier;

import fr.miage.rois.misesouspresse.entities.Publicite;
import fr.miage.rois.misesouspresse.entities.Volume;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sagab
 */
@Stateless
public class VolumeFacade extends AbstractFacade<Volume> implements VolumeFacadeLocal {

    @PersistenceContext(unitName = "fr.miage.rois_MiseSousPresse-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VolumeFacade() {
        super(Volume.class);
    }

    @Override
    public Volume findById(Integer idvolume) {
        Volume volume = null;
        try {
            volume = (Volume) getEntityManager().createQuery("SELECT v FROM Volume v WHERE v.idvolume = :volume")
                .setParameter("volume", idvolume)
                .getSingleResult();
        } catch (NoResultException e) {
            return volume;
        }
        return volume;
    }

    @Override
    public void affecterNumeroAUnVolume(int numero, Volume volume) {
        getEntityManager().createQuery("UPDATE Volume v SET v.numero = :numero WHERE v.idvolume = :volume")
                .setParameter("numero", numero)
                .setParameter("volume", volume)
                .executeUpdate();
    }

    @Override
    public void affecterListPub(Volume volume, List<Publicite> pubs) {
        volume.setPubliciteCollection(pubs);
        this.edit(volume);
    }
    
}
