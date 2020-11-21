/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.redacchef.service;

import fr.miage.rois.redacchef.entities.Article;
import fr.miage.rois.redacchef.entities.Titre;
import fr.miage.rois.redacchef.metier.ArticleFacadeLocal;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author sagab
 */
@Stateless
public class GestionArticle implements GestionArticleLocal {
    
    @EJB
    private ArticleFacadeLocal articleFacadeLocal;

    @Override
    public List<Article> listerArticlesByTitre(Titre titre) {
        return articleFacadeLocal.listeArticlesByTitre(titre);
    }

    @Override
    public void validerArticle(Article article) {
        articleFacadeLocal.valideArticle(article);
    }

    @Override
    public void refusArticle(Article article) {
        articleFacadeLocal.remove(article);
    }

    @Override
    public List<Article> listerArticlesValideByTitre(Titre Titre) {
        return articleFacadeLocal.listeArticlesValide();
    }

    @Override
    public void ajouterArticle(Article article) {
        articleFacadeLocal.create(article);
    }

}
