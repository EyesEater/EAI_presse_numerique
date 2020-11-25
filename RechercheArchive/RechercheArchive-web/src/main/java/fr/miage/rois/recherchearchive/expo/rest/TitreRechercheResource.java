/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.recherchearchive.expo.rest;

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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author sagab
 */
@Path("titleSearch")
@RequestScoped
public class TitreRechercheResource {

    private final GestionTitreLocal gestionTitre;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TitreRechercheResource
     */
    public TitreRechercheResource() {
        this.gestionTitre = lookupGestionTitreLocal();
    }

    /**
     * Récupérer un titre par des mots clés
     * @param motscles la liste des mots clés (ex: system,with,banane)
     * @return un volume en json
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getTitreByMotsCles(@QueryParam("keywords") String motscles) {
        return gestionTitre.findByMotsCles(motscles).toString();
    }

    private GestionTitreLocal lookupGestionTitreLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (GestionTitreLocal) c.lookup("java:global/RechercheArchive-ear/RechercheArchive-ejb-1.0-SNAPSHOT/GestionTitre");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
