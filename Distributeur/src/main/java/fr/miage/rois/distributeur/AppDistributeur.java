/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.distributeur;

import fr.miage.rois.distributeur.entities.Abonnement;
import fr.miage.rois.distributeur.services.Sender;

/**
 *
 * @author sagab
 */
public class AppDistributeur {
   
    public static void main(String[] args) {
        Abonnement abonnement = new Abonnement(2, 1, 20, 10, 12);
        Sender sender = new Sender();
        sender.setAbonnement(abonnement);
        sender.start();
    }
    
}
