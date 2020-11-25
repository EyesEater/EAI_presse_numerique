/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.recherchearchive.expo.rest;

import com.google.gson.Gson;
import fr.miage.rois.recherchearchive.services.GestionVolumeLocal;
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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author sagab
 */
@Path("volumeSearch")
@RequestScoped
public class VolumeRechercheResource {

    private final GestionVolumeLocal gestionVolume;

    @Context
    private UriInfo context;
    
    private final Gson gson;

    /**
     * Creates a new instance of VolumeRechercheResource
     */
    public VolumeRechercheResource() {
        this.gson = new Gson();
        this.gestionVolume = lookupGestionVolumeLocal();
    }

    /**
     * Retrouver un volume par l'id du titre
     * @param titre l'id d'un titre
     * @return un volume en json
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getVolumeByTitreId(@QueryParam("titre") String titre) {
        return gestionVolume.findByTitreId(titre).toString();
    }

    private GestionVolumeLocal lookupGestionVolumeLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (GestionVolumeLocal) c.lookup("java:global/RechercheArchive-ear/RechercheArchive-ejb-1.0-SNAPSHOT/GestionVolume");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
