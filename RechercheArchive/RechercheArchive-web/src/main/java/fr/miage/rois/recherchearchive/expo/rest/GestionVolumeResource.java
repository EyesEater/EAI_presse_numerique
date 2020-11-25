/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.recherchearchive.expo.rest;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.miage.rois.recherchearchive.entities.Titre;
import fr.miage.rois.recherchearchive.entities.Volume;
import fr.miage.rois.recherchearchive.services.GestionTitreLocal;
import fr.miage.rois.recherchearchive.services.GestionVolumeLocal;
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
@Path("volume")
@RequestScoped
public class GestionVolumeResource {

    private final GestionTitreLocal gestionTitre;

    private final GestionVolumeLocal gestionVolume;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GestionVolumeResource
     */
    public GestionVolumeResource() {
        this.gestionVolume = lookupGestionVolumeLocal();
        this.gestionTitre = lookupGestionTitreLocal();
    }

    /**
     * Retrouver un volume par le nom
     * @param nom Le nom d'un volume
     * @return un volume en json
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getVolumeByNom(@QueryParam("nom") String nom) {
        return gestionVolume.findByNom(nom).toString();
    }

    

    /**
     * Ajouter un volume a la base de donnée des archives
     * @param parameters un json représentant un volume
     * @return 201 si créé, 400 si les paramètres sont incorrectes et 409 si le volume existe déjà
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String ajouterVolume(@QueryParam("volume") String parameters) {
        JsonObject json = new JsonParser().parse(parameters).getAsJsonObject();
        String idtitre = json.get("idtitre").getAsString();
        Titre titre = gestionTitre.findById(idtitre);
        
        if(titre != null) {
            Volume volume = new Volume(json.get("idvolume").getAsInt());
            volume.setIdtitre(titre);
            volume.setNom(json.get("nom").getAsString());
            volume.setNumero(json.get("numero").getAsInt());
            volume.setTermine(json.get("termine").getAsBoolean());
        
            Volume tmp = gestionVolume.findByNom(volume.getNom());
            if(tmp != null && tmp.getIdvolume().equals(volume.getIdvolume()))
                return "{code:409}";

            gestionVolume.ajouterVolume(volume);
            return "{code:201}";
        }
        
        return "{code:400," + parameters + "}";
    }

    private GestionVolumeLocal lookupGestionVolumeLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            //java:global/RechercheArchive-ear/RechercheArchive-ejb-1.0-SNAPSHOT/GestionTitre
            return (GestionVolumeLocal) c.lookup("java:global/RechercheArchive-ear/RechercheArchive-ejb-1.0-SNAPSHOT/GestionVolume");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
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
