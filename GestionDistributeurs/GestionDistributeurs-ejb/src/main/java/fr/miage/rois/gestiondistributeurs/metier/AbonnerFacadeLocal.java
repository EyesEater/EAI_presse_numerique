/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.gestiondistributeurs.metier;

import fr.miage.rois.gestiondistributeurs.entities.Abonner;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sagab
 */
@Local
public interface AbonnerFacadeLocal {

    void create(Abonner abonner);

    void edit(Abonner abonner);

    void remove(Abonner abonner);

    Abonner find(Object id);

    List<Abonner> findAll();

    List<Abonner> findRange(int[] range);

    int count();
    
}
