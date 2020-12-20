/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.gestiondistributeurs.services;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.miage.rois.gestiondistributeurs.entities.Distributeur;
import fr.miage.rois.gestiondistributeurs.entities.Titre;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import org.apache.log4j.Logger;

/**
 *
 * @author sagab
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "QueueD")
    ,
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class SAbonnerJMSListener implements MessageListener {

    @EJB
    private GestionDistributeursLocal gestionDistributeursLocal;

    @Resource
    private MessageDrivenContext mdc;
    
    private final Logger logger;

    public SAbonnerJMSListener() {
        logger = Logger.getLogger(SAbonnerJMSListener.class);
    }

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                
                TextMessage tm = (TextMessage) message;
                String abonnementJson = tm.getText();

                JsonObject json = new JsonParser().parse(abonnementJson).getAsJsonObject();

                int idDistrib = json.get("idDistributeur").getAsInt();
                int idTitre = json.get("idTitre").getAsInt();
                int nbCopies = json.get("nbCopies").getAsInt();
                int cout = json.get("cout").getAsInt();
                int duree = json.get("duree").getAsInt();
                
                Distributeur distributeur = this.gestionDistributeursLocal.findDistribById(idDistrib);
                Titre titre = this.gestionDistributeursLocal.findTitreById(idTitre);
                
                if (distributeur != null && titre != null) {
                    this.gestionDistributeursLocal.abonnerDistribTitre(distributeur, titre, nbCopies, cout, duree);
                } else {
                    this.gestionDistributeursLocal.creerCompte(new Distributeur(idDistrib));
                    distributeur = this.gestionDistributeursLocal.findDistribById(idDistrib);
                
                    this.gestionDistributeursLocal.abonnerDistribTitre(distributeur, titre, nbCopies, cout, duree);
                }
               
            }
        } catch (JMSException e) {
            logger.error("Erreur lors de l'intégration du message pour abonner un distributeur en JMS.");
            mdc.setRollbackOnly();
        }
    }

}
