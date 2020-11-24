/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.recherchearchive.expo.rest;

import com.google.gson.Gson;
import fr.miage.rois.recherchearchive.entities.Titre;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import fr.miage.rois.recherchearchive.services.GestionTitreLocal;
import java.util.List;
import javax.ws.rs.PathParam;

/**
 * REST Web Service pour l'ensemble des comptes
 *
 * @author Cédric Teyssié
 */
@Path("rechercheTitre")
public class RechercheTitreResource {

    // Accès BackOffice
    private final GestionTitreLocal gestionTitre;

    @Context
    private UriInfo context;

    // Convertisseur JSON
    private final Gson gson;

    /**
     * Constructeur Ressource
     */
    public RechercheTitreResource() {
        this.gson = new Gson();
        this.gestionTitre = lookupGestionTitreLocal();
    }
    
    /**
     * Récupérer un Titre par le nom
     * @param keywords les parametres de recherches par mots clés
     * @return une réponse HTTP avec le Titre en JSON
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getTitreByMotsCles(@PathParam("keywordSearch") String keywords) {
        List<Titre> titres = gestionTitre.findByMotsCles(keywords);
        
        StringBuilder st = new StringBuilder("{");
        for (Titre t : titres) {
            st.append("{id:").append(t.getIdtitre()).append(",")
                    .append("nom:").append(t.getNom()).append(",")
                    .append("numero:").append(t.getMotscles()).append("}");
        }
        st.deleteCharAt(st.length()-1).append("}");
        
        return this.gson.toJson(st.toString());
    }

    /**
     * Recherche JNDI BackOffice
     * @return la référence vers le BackOffice
     */
    private GestionTitreLocal lookupGestionTitreLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (GestionTitreLocal) c.lookup("java:global/RechercheArchive-ear/RechercheArchive-ejb/ApplicationRechercheArchive!fr.miage.rois.recherchearchive.services.GestionTitreLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
