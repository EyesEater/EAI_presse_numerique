/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.misesouspresse.metier;

import fr.miage.rois.misesouspresse.entities.Publicite;
import fr.miage.rois.misesouspresse.entities.Volume;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sagab
 */
@Local
public interface VolumeFacadeLocal {

    void create(Volume volume);

    void edit(Volume volume);

    void remove(Volume volume);

    Volume find(Object id);

    List<Volume> findAll();

    List<Volume> findRange(int[] range);

    int count();

    public Volume findById(Integer idvolume);

    public void affecterNumeroAUnVolume(int numero, int volume);

    public void affecterListPub(Volume volume, List<Publicite> pubs);
    
}
