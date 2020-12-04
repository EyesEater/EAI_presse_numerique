/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.redacchef.metier;

import fr.miage.rois.redacchef.entities.Article;
import fr.miage.rois.redacchef.entities.Titre;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author sagab
 */
@Stateless
public class ArticleFacade extends AbstractFacade<Article> implements ArticleFacadeLocal {

    @PersistenceContext(unitName = "fr.miage.rois_RedacChef-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ArticleFacade() {
        super(Article.class);
    }

    @Override
    public List<Article> listeArticlesByTitre(Titre titre) {
        return getEntityManager().createQuery("SELECT a FROM Article a WHERE a.idtitre = :titre")
                .setParameter("titre", titre)
                .getResultList();
    }

    @Override
    public List<Article> listeArticlesValide() {
        return getEntityManager().createQuery("SELECT a FROM Article a WHERE a.valide = true")
                .getResultList();
    }

    @Override
    public void validerArticle(Article article) {
        getEntityManager().createQuery("UPDATE Article a SET a.valide = true WHERE a.idarticle = :article")
                .setParameter("article", article.getIdarticle())
                .executeUpdate();
    }
    
}
