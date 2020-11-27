/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.redacchef.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.miage.rois.redacchef.entities.Article;
import fr.miage.rois.redacchef.entities.Titre;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;

/**
 *
 * @author sagab
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "QueueJ")
    ,
    @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class RedacChefJMSListener implements MessageListener {

    @EJB
    private AppliRedacChefLocal appliRedacChefLocal;

    @Resource
    private MessageDrivenContext mdc;

    private final Gson gson;

    public RedacChefJMSListener() {
        this.gson = new Gson();
    }

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                
                TextMessage tm = (TextMessage) message;
                String articleJson = tm.getText();

                JsonObject json = new JsonParser().parse(articleJson).getAsJsonObject();

                Integer idtitre = json.get("idtitre").getAsInt();
                Titre titre = appliRedacChefLocal.findTitre(idtitre);
                
                Integer idarticle = json.get("idarticle").getAsInt();
                Article tmp = appliRedacChefLocal.findArticle(idarticle);

                if (titre != null && tmp == null) {

                    Article article = new Article(json.get("idarticle").getAsInt());
                    article.setAuteur(json.get("auteur").getAsInt());
                    article.setContenu(json.get("contenu").getAsString());
                    article.setIdtitre(titre);
                    article.setMotscles(json.get("motscles").getAsString());
                    article.setTitre(json.get("titre").getAsString());
                    article.setValide(json.get("valide").getAsBoolean());

                    appliRedacChefLocal.ajouterArticle(article);
                    
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
            mdc.setRollbackOnly();
        }
    }

}
