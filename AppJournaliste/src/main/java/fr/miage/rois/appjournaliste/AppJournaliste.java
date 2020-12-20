/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.appjournaliste;

import fr.miage.rois.appjournaliste.entities.Article;
import fr.miage.rois.appjournaliste.service.Sender;

/**
 *
 * @author sagab
 */
public class AppJournaliste {

    public static void main(String[] args) {
        Sender sender = new Sender();
        
        Article article = new Article(2, "Titre mignon", "mignon,titre", 1, "Contenu", false, 1);
        sender.setArticle(article);
        
        sender.start();
    }

}
