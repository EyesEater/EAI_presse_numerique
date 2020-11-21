/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.redacchef.service;

import fr.miage.rois.redacchef.entities.Article;
import fr.miage.rois.redacchef.entities.Titre;
import java.util.List;
import javax.ejb.Local;
import javax.jms.JMSException;
import javax.naming.NamingException;

/**
 *
 * @author sagab
 */
@Local
public interface AppliRedacChefLocal {
    
    public void envoyerMiseSousPresse(Titre titre) throws JMSException, NamingException;
    
    public void affecterTitre(Titre titre);
    
    public List<Article> listerArticlesByTitre(Titre titre);
    
    public void validerArticle(Article article);
    
    public void refusArticle(Article article);

    public List<Article> listerArticlesValideByTitre(Titre titre);
    
    public void ajouterArticle(Article article);
    
}
