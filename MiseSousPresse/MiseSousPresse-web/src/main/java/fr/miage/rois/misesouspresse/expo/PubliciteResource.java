/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.misesouspresse.expo;

import com.google.gson.Gson;
import fr.miage.rois.misesouspresse.entities.Publicite;
import fr.miage.rois.misesouspresse.services.GestionVolumeLocal;
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
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author sagab
 */
@Path("publicite")
@RequestScoped
public class PubliciteResource {

    GestionVolumeLocal gestionVolume = lookupGestionVolumeLocal();

    @Context
    private UriInfo context;
    
    private final Gson gson;

    /**
     * Creates a new instance of PubliciteResource
     */
    public PubliciteResource() {
        this.gson = new Gson();
    }

    /**
     * Retrieves representation of an instance of fr.miage.rois.misesouspresse.expo.PubliciteResource
     * @param publicite
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void creerPublicite(@QueryParam("publicite") String publicite) {
        Publicite pub = gson.fromJson(publicite, Publicite.class);
        
        this.gestionVolume.creerPublicite(pub);
    }

    private GestionVolumeLocal lookupGestionVolumeLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (GestionVolumeLocal) c.lookup("java:global/MiseSousPresse-ear/MiseSousPresse-ejb-1.0-SNAPSHOT/GestionVolume");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
