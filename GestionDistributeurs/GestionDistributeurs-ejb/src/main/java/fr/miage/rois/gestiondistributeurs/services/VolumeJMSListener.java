/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.gestiondistributeurs.services;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.miage.rois.gestiondistributeurs.entities.Titre;
import fr.miage.rois.gestiondistributeurs.entities.Volume;
import java.util.Iterator;
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
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "QueueMSP")
    ,
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class VolumeJMSListener implements MessageListener {

    @EJB
    private GestionDistributeursLocal gestionDistributeursLocal;

    @Resource
    private MessageDrivenContext mdc;
    
    private final Logger logger;

    public VolumeJMSListener() {
        logger = Logger.getLogger(VolumeJMSListener.class);
    }

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                TextMessage tm = (TextMessage) message;
                String volumesJson = tm.getText();
                System.out.println("APPLICATION DISTRIBUTEUR");
                System.out.println(volumesJson);
                JsonObject volumeJson = new JsonParser().parse(volumesJson).getAsJsonObject();
                JsonObject titreJson = volumeJson.get("idtitre").getAsJsonObject();
                
                Titre titre = this.gestionDistributeursLocal.findTitreById(titreJson.get("idtitre").getAsInt());

                if (titre == null) {
                    titre = new Titre(titreJson.get("idtitre").getAsInt());
                    StringBuilder motscle = new StringBuilder();
                    for (JsonElement e : titreJson.get("motscles").getAsJsonArray()) {
                        motscle.append(e.getAsString()).append(",");
                    }
                    motscle.deleteCharAt(motscle.length()-1);
                    titre.setMotscles(motscle.toString());
                    titre.setNom(titreJson.get("nom").getAsString());

                    this.gestionDistributeursLocal.creerTitre(titre);
                }
                    
                Volume volume = this.gestionDistributeursLocal.findVolumeById(volumeJson.get("idvolume").getAsInt());

                if (volume == null) {
                    volume = new Volume(volumeJson.get("idvolume").getAsInt());
                    volume.setNom(volumeJson.get("nom").getAsString());
                    volume.setNumero(volumeJson.get("numero").getAsInt());
                    volume.setTermine(volumeJson.get("termine").getAsBoolean());
                    volume.setIdtitre(titre);

                    this.gestionDistributeursLocal.creerVolume(volume);
                }

                VolumeRESTSender.envoyerTitreAAppliRecherche(titre);
                VolumeRESTSender.envoyerVolumeAAppliRecherche(volume);
                
                DistributionVolumeJMSSender.envoyerVolumeAuxDistributeurs(volume);
               
            }
        } catch (JMSException e) {
            logger.error("Erreur lors de l'intégration de la liste des Volumes en JMS.");
            mdc.setRollbackOnly();
        }
    }

}
