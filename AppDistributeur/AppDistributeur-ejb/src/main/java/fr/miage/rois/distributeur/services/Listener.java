/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.distributeur.services;

import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
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
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "TopicD")
    ,
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic")
})
public class Listener implements MessageListener {

    @Resource
    private MessageDrivenContext mdc;
    
    private final Logger logger;

    public Listener() {
        logger = Logger.getLogger(Listener.class);
    }

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                
                TextMessage tm = (TextMessage) message;
                
                String volumesJson = tm.getText();
                
                System.out.println("Un Distributeur récupère le volume :");
                System.out.println(volumesJson);
               
            }
        } catch (JMSException e) {
            logger.error("Erreur lors de l'intégration de la liste des Volumes en JMS.");
            mdc.setRollbackOnly();
        }
    }

}
