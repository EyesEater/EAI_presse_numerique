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
import javax.ws.rs.PathParam;

/**
 * REST Web Service pour l'ensemble des comptes
 *
 * @author Cédric Teyssié
 */
@Path("titre")
public class GestionTitreResource {

    // Accès BackOffice
    private final GestionTitreLocal gestionTitre;

    @Context
    private UriInfo context;

    // Convertisseur JSON
    private final Gson gson;

    /**
     * Constructeur Ressource
     */
    public GestionTitreResource() {
        this.gson = new Gson();
        this.gestionTitre = lookupGestionTitreLocal();
    }
    
    /**
     * Récupérer un Titre par le nom
     * @param nom le nom d'un Titre
     * @return une réponse HTTP avec le Titre en JSON
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getTitreByNom(@PathParam("titleSearch") String nom) {
        Titre t = gestionTitre.findByNom(nom);
        
        if (t != null) {
            StringBuilder st = new StringBuilder();
            st.append("{id:").append(t.getIdtitre()).append(",")
                    .append("nom:").append(t.getNom()).append(",")
                    .append("numero:").append(t.getMotscles()).append("}");
            
            return this.gson.toJson(st.toString());
        }
        return this.gson.toJson("Aucun titre trouvé");
    }
    
    /**
     * Ajouter un titre a la base des titres archivé
     * @param parameters un objet JSON représentant un titre
     * @return 201 si créé, 400 si les paramètres sont incorrectes et 409 si le titre existe déjà
     */
    @POST
    public Response ajouterTitre(@QueryParam("titre") String parameters) {
        Titre titre = this.gson.fromJson(parameters, Titre.class);
        
        if (titre != null) {
            if(gestionTitre.findByNom(titre.getNom()).getIdtitre().equals(titre.getIdtitre()))
                return Response.status(Response.Status.CONFLICT).build();
            
            gestionTitre.ajouterTitre(titre);
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
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
