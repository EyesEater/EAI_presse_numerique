/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.redacchef.service;

import fr.miage.rois.redacchef.entities.Article;
import fr.miage.rois.redacchef.entities.Titre;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.naming.NamingException;

/**
 *
 * @author sagab
 */
@Stateless
public class AppliRedacChef implements AppliRedacChefLocal {
    
    @EJB
    private GestionArticleLocal gestionArticleLocal;
    
    @EJB
    private GestionTitreLocal gestionTitreLocal;

    @Override
    public void affecterTitre(Titre titre) {
        gestionTitreLocal.affecterTitre(titre);
    }

    @Override
    public List<Article> listerArticlesByTitre(Titre titre) {
        return gestionArticleLocal.listerArticlesByTitre(titre);
    }

    @Override
    public void validerArticle(Article article) {
        gestionArticleLocal.validerArticle(article);
        RedacChefJMSSender.envoyerArticlesAMiseSousPresse(article);
    }

    @Override
    public void refusArticle(Article article) {
        gestionArticleLocal.refusArticle(article);
    }

    @Override
    public List<Article> listerArticlesValideByTitre(Titre titre) {
        return gestionArticleLocal.listerArticlesValideByTitre(titre);
    }

    @Override
    public void ajouterArticle(Article article) {
        gestionArticleLocal.ajouterArticle(article);
    }

    @Override
    public Titre findTitre(Integer idTitre) {
        return gestionTitreLocal.findTitre(idTitre);
    }

    @Override
    public Article findArticle(Integer idArticle) {
        return gestionArticleLocal.findArticle(idArticle);
    }

}
