/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.redacchef.service;

import fr.miage.rois.redacchef.entities.Article;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.StreamMessage;

/**
 *
 * @author sagab
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "QueueJ"),
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class RedacChefJMSListener implements MessageListener {
    
    @EJB
    private AppliRedacChefLocal appliRedacChefLocal;

    @Override
    public void onMessage(Message message) {
        try {
        if (message instanceof StreamMessage) {
            StreamMessage sm = (StreamMessage) message;
            if ((sm.readObject()) instanceof Article) {
                Article article = (Article) sm.readObject();
                appliRedacChefLocal.ajouterArticle(article);
            }
        }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
    
}
