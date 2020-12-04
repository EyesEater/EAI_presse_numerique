/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.redacchef.expo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.miage.rois.redacchef.entities.Article;
import fr.miage.rois.redacchef.entities.Titre;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author sagab
 */
@Path("article")
@RequestScoped
public class ArticleResource {

    fr.miage.rois.redacchef.service.AppliRedacChefLocal appliRedacChef = lookupAppliRedacChefLocal();

    @Context
    private UriInfo context;
    
    private final Gson gson;

    /**
     * Creates a new instance of ArticleResource
     */
    public ArticleResource() {
        this.gson = new Gson();
    }

    /**
     * Retrieves representation of an instance of fr.miage.rois.redacchef.expo.ArticleResource
     * @param idTitre l'id d'un titre pour en récuperer la list des Articles
     * @return an instance of java.lang.String
     */
    @Path("titre/{idTitre}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getArticlesByTitre(@PathParam("idTitre") String idTitre) {
        Titre titre = this.appliRedacChef.findTitre(Integer.parseInt(idTitre));
        
        StringBuilder st = new StringBuilder("{");
        int i=0;
        for (Article a : this.appliRedacChef.listerArticlesByTitre(titre)) {
            st.append(i).append(":").append(a).append(",");
        }
        st.deleteCharAt(st.length()-1).append("}");
        return st.toString();
    }

    /**
     * Retrieves representation of an instance of fr.miage.rois.redacchef.expo.ArticleResource
     * @param idArticle l'id d'un article a retrouver
     * @return an instance of java.lang.String
     */
    @Path("{idArticle}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getArticle(@PathParam("idArticle") String idArticle) {
        Article article = this.appliRedacChef.findArticle(Integer.parseInt(idArticle));
        return article.toString();
    }

    /**
     * Retrieves representation of an instance of fr.miage.rois.redacchef.expo.ArticleResource
     * @param idTitre l'id du titre
     * @return an instance of java.lang.String
     */
    @Path("valide/{idTitre}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getArticlesValideByTitre(@PathParam("idTitre") String idTitre) {
        Titre titre = this.appliRedacChef.findTitre(Integer.parseInt(idTitre));

        StringBuilder st = new StringBuilder("{ ");
        int i=0;
        for (Article a : this.appliRedacChef.listerArticlesValideByTitre(titre)) {
            st.append(i).append(":").append(a).append(",");
        }
        st.deleteCharAt(st.length()-1).append("}");
        return st.toString();
    }

    /**
     * PUT method for validating article
     * @param idArticle the id of the validated article
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void validerArticle(@QueryParam("article") String idArticle) {
        Article article = this.appliRedacChef.findArticle(Integer.parseInt(idArticle));

        this.appliRedacChef.validerArticle(article);
    }
    
    /**
     * 
     * @param article
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public void ajouterArticle(@QueryParam("article") String article) {
        JsonObject articleJson = new JsonParser().parse(article).getAsJsonObject();
        Titre titre = this.appliRedacChef.findTitre(articleJson.get("idtitre").getAsInt());
        Article a = new Article(articleJson.get("idarticle").getAsInt());
        a.setAuteur(articleJson.get("auteur").getAsInt());
        a.setContenu(articleJson.get("contenu").getAsString());
        a.setIdtitre(titre);
        a.setMotscles(articleJson.get("motscles").getAsString());
        a.setTitre(articleJson.get("titre").getAsString());
        a.setValide(false);
        
        this.appliRedacChef.ajouterArticle(a);
    }
    
    /**
     * DELETE methode to remove an article by refusing it
     * @param idArticle the id of the refused article
     */
    @Path("{idArticle}")
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    public void refuserArticle(@PathParam("idArticle") String idArticle) {
        Article article = this.appliRedacChef.findArticle(Integer.parseInt(idArticle));
        
        this.appliRedacChef.refusArticle(article);
    }

    private fr.miage.rois.redacchef.service.AppliRedacChefLocal lookupAppliRedacChefLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (fr.miage.rois.redacchef.service.AppliRedacChefLocal) c.lookup("java:global/RedacChef-ear/RedacChef-ejb-1.0-SNAPSHOT/AppliRedacChef");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
