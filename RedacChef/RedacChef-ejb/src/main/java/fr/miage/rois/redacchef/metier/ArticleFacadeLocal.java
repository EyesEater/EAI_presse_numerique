/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.redacchef.metier;

import fr.miage.rois.redacchef.entities.Article;
import fr.miage.rois.redacchef.entities.Titre;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author sagab
 */
@Local
public interface ArticleFacadeLocal {

    void create(Article article);

    void edit(Article article);

    void remove(Article article);

    Article find(Object id);

    List<Article> findAll();

    List<Article> findRange(int[] range);

    int count();

    public List<Article> listeArticlesByTitre(Titre titre);

    public List<Article> listeArticlesValide();

    public void validerArticle(Article article);
    
}
