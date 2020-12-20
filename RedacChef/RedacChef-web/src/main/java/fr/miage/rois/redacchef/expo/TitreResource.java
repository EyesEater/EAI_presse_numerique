/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.redacchef.expo;

import com.google.gson.Gson;
import fr.miage.rois.redacchef.entities.Titre;
import fr.miage.rois.redacchef.service.AppliRedacChefLocal;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.enterprise.context.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author sagab
 */
@Path("titre")
@RequestScoped
public class TitreResource {

    AppliRedacChefLocal appliRedacChef = lookupAppliRedacChefLocal();

    @Context
    private UriInfo context;
    
    private final Gson gson;

    /**
     * Creates a new instance of TitreResource
     */
    public TitreResource() {
        this.gson = new Gson();
    }

    /**
     * Retrieves a Titre from it's id
     * @param idTitre id of a titre
     * @return an instance of java.lang.String
     */
    @Path("{idTitre}")
    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String getTitre(@PathParam("idTitre") String idTitre) {
        Titre titre = this.appliRedacChef.findTitre(Integer.parseInt(idTitre));
        return titre.toString();
    }
    
    /**
     * POST to create a titre
     * @param titre titre in json
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void affecterTitre(@QueryParam("titre") String titre) {
        Titre t = gson.fromJson(titre, Titre.class);
        this.appliRedacChef.affecterTitre(t);
    }

    private AppliRedacChefLocal lookupAppliRedacChefLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (AppliRedacChefLocal) c.lookup("java:global/RedacChef-ear/RedacChef-ejb-1.0-SNAPSHOT/AppliRedacChef");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
