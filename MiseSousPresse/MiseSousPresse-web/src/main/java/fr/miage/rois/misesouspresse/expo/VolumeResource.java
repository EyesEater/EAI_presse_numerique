/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.misesouspresse.expo;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.miage.rois.misesouspresse.entities.Titre;
import fr.miage.rois.misesouspresse.entities.Volume;
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
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author sagab
 */
@Path("volume")
@RequestScoped
public class VolumeResource {

    fr.miage.rois.misesouspresse.services.GestionVolumeLocal gestionVolume = lookupGestionVolumeLocal();

    @Context
    private UriInfo context;
    
    private final Gson gson;

    /**
     * Creates a new instance of VolumeResource
     */
    public VolumeResource() {
        this.gson = new Gson();
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void creerVolume(@QueryParam("volume") String volume) {
        JsonObject volumeJson = new JsonParser().parse(volume).getAsJsonObject();
        
        
        Volume v = new Volume(volumeJson.get("idvolume").getAsInt());
        if (volumeJson.get("idTitre") != null) { 
            Titre titre = this.gestionVolume.findTitreById(volumeJson.get("idtitre").getAsInt());
            v.setIdtitre(titre);
        }
        v.setNom(volumeJson.get("nom").getAsString());
        v.setTermine(false);
        
        this.gestionVolume.creerVolume(v);
    }

    /**
     * PUT method for updating or creating an instance of VolumeResource
     * @param data
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void affecterNumeroAUnVolume(@QueryParam("data") String data) {
        JsonObject json = new JsonParser().parse(data).getAsJsonObject();
        int numero = json.get("numero").getAsInt();
        Volume volume = this.gestionVolume.findVolume(json.get("volume").getAsInt());
        
        if (volume != null) {
            this.gestionVolume.affecterNumeroAUnVolume(numero, volume.getIdvolume());
        }
    }
    
    /**
     * 
     * @param idVolume 
     */
    @Path("{idVolume}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void affecterEncartsPublicitaires(@PathParam("idVolume") String idVolume) {
        Volume volume = this.gestionVolume.findVolume(Integer.parseInt(idVolume));
        
        if (volume != null) {
            this.gestionVolume.affecterEncartsPublicitaires(volume);
        }
    }
    
    @Path("miseenpage/{idVolume}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void miseEnPage(@PathParam("idVolume") String idVolume) {
        Volume volume = this.gestionVolume.findVolume(Integer.parseInt(idVolume));
        
        if (volume != null) {
            this.gestionVolume.miseEnPage(volume);
        }
    }

    private fr.miage.rois.misesouspresse.services.GestionVolumeLocal lookupGestionVolumeLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (fr.miage.rois.misesouspresse.services.GestionVolumeLocal) c.lookup("java:global/MiseSousPresse-ear/MiseSousPresse-ejb-1.0-SNAPSHOT/GestionVolume");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
