/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.distributeur.services;

import fr.miage.rois.distributeur.entities.Abonnement;
import javax.ejb.Stateless;
import javax.ejb.LocalBean;

/**
 *
 * @author sagab
 */
@Stateless
@LocalBean
public class DistributeurBean {

    public void envoyerAbonnement(Abonnement abo) {
        Sender sender = new Sender();
        sender.setAbonnement(abo);
        sender.start();
    }
    
}
