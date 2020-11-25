/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.recherchearchive.expo.rest;

import com.google.gson.Gson;
import fr.miage.rois.recherchearchive.entities.Titre;
import fr.miage.rois.recherchearchive.services.GestionTitreLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author sagab
 */
@Path("titre")
@RequestScoped
public class GestionTitreResource {

    private final GestionTitreLocal gestionTitre;

    @Context
    private UriInfo context;
    
    private final Gson gson;

    /**
     * Creates a new instance of GestionTitreResource
     */
    public GestionTitreResource() {
        this.gson = new Gson();
        this.gestionTitre = lookupGestionTitreLocal();
    }

    /**
     * Retrieves representation of an instance of fr.miage.rois.recherchearchive.expo.rest.GestionTitreResource
     * @param nom le nom d'un titre
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getTitreByNom(@QueryParam("nom") String nom) {
        Titre titre = this.gestionTitre.findByNom(nom);
        
        if (titre != null)
            return this.gestionTitre.findByNom(nom).toString();
        return "{error:\"Aucun titre trouvé\", code:404}";
    }

    /**
     * Ajouter un titre a la base des titres archivé
     * @param parameters un objet JSON représentant un titre
     * @return 201 si créé, 400 si les paramètres sont incorrectes et 409 si le titre existe déjà
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String ajouterTitre(@QueryParam("titre") String parameters) {
        Titre titre = this.gson.fromJson(parameters, Titre.class);
        
        if (titre != null) {
            Titre tmp = gestionTitre.findByNom(titre.getNom());
            if(tmp != null && tmp.getIdtitre().equals(titre.getIdtitre()))
                return "{code:409}";
            
            gestionTitre.ajouterTitre(titre);
            return "{code:201}";
        }
        return "{code:400," + parameters + "}";
    }

    private GestionTitreLocal lookupGestionTitreLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            //java:global/miage.tp_tpbourse-ear_ear_1.0-SNAPSHOT/miage.tp_tpbourse-ejb_ejb_1.0-SNAPSHOT/Services!bourse.miage.tp.core.sessions.ServicesLocal
            //java:global/tpbourse-ear/tpbourse-ejb-1.0-SNAPSHOT/Services
            return (GestionTitreLocal) c.lookup("java:global/RechercheArchive-ear/RechercheArchive-ejb-1.0-SNAPSHOT/GestionTitre");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
