/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.misesouspresse.services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.miage.rois.misesouspresse.entities.Publicite;
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
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "QueuePub")
    ,
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class PubliciteJMSListener implements MessageListener {
    
    @EJB
    private GestionVolumeLocal gestionVolumeLocal;
    
    @Resource
    private MessageDrivenContext context;
    
    private final Gson gson;
    
    private final Logger logger;

    public PubliciteJMSListener() {
        this.gson = new Gson();
        this.logger = Logger.getLogger(this.getClass());
    }

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                TextMessage tm = (TextMessage) message;
                String pubJson = tm.getText();

                JsonObject json = new JsonParser().parse(pubJson).getAsJsonObject();
                
                Publicite publicite = new Publicite(json.get("idpub").getAsInt());
                publicite.setContenu(json.get("contenu").getAsString());
                publicite.setImage(json.get("image").getAsString());
                
                this.gestionVolumeLocal.creerPublicite(publicite);
            }
        } catch (JMSException e) {
            logger.error("Erreur lors de l'intégration d'un nouvel Article en JMS.");
            context.setRollbackOnly();
        }
    }

}
