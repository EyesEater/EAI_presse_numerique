/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.gestiondistributeurs.metier;

import fr.miage.rois.gestiondistributeurs.entities.Distributeur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sagab
 */
@Local
public interface DistributeurFacadeLocal {

    void create(Distributeur distributeur);

    void edit(Distributeur distributeur);

    void remove(Distributeur distributeur);

    Distributeur find(Object id);

    List<Distributeur> findAll();

    List<Distributeur> findRange(int[] range);

    int count();
    
}
