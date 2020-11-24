/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.recherchearchive.expo.rest;

import com.google.gson.Gson;
import fr.miage.rois.recherchearchive.entities.Volume;
import fr.miage.rois.recherchearchive.entities.Volume;
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
import fr.miage.rois.recherchearchive.services.GestionVolumeLocal;
import javax.ws.rs.PathParam;

/**
 * REST Web Service pour l'ensemble des comptes
 *
 * @author Cédric Teyssié
 */
@Path("volume")
public class GestionVolumeResource {

    // Accès BackOffice
    private final GestionVolumeLocal gestionVolume;

    @Context
    private UriInfo context;

    // Convertisseur JSON
    private final Gson gson;

    /**
     * Constructeur Ressource
     */
    public GestionVolumeResource() {
        this.gson = new Gson();
        this.gestionVolume = lookupGestionVolumeLocal();
    }
    
    /**
     * Récupérer un Volume par le nom
     * @param nom le nom d'un volume
     * @return une réponse HTTP avec le Volume en JSON
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getVolumeByNom(@PathParam("nameSearch") String nom) {
        Volume v = gestionVolume.findByNom(nom);
        
        if (v != null) {
            StringBuilder st = new StringBuilder();
            st.append(v.getIdvolume())
                    .append(":{id:").append(v.getIdvolume()).append(",")
                    .append("nom:").append(v.getNom()).append(",")
                    .append("numero:").append(v.getNumero()).append(",")
                    .append("titre:").append(v.getIdtitre()).append(",")
                    .append("termine:").append(v.getTermine()).append("}");
            
            return this.gson.toJson(st.toString());
        }
        return this.gson.toJson("Aucun volume trouvé");
    }
    
    /**
     * Ajouter un volume a la base des volumes archivé
     * @param parameters un objet JSON représentant un volume
     * @return 201 si créé, 400 si les paramètres sont incorrectes et 409 si le volume existe déjà
     */
    @POST
    public Response ajouterVolume(@QueryParam("volume") String parameters) {
        Volume volume = this.gson.fromJson(parameters, Volume.class);
        
        if (volume != null) {
            if(gestionVolume.findByNom(volume.getNom()).getIdtitre().equals(volume.getIdtitre()))
                return Response.status(Response.Status.CONFLICT).build();
            
            gestionVolume.ajouterVolume(volume);
            return Response.status(Response.Status.CREATED).build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }

    /**
     * Recherche JNDI BackOffice
     * @return la référence vers le BackOffice
     */
    private GestionVolumeLocal lookupGestionVolumeLocal() {
        try {
            javax.naming.Context c = new InitialContext();
            return (GestionVolumeLocal) c.lookup("java:global/RechercheArchive-ear/RechercheArchive-ejb/ApplicationRechercheArchive!fr.miage.rois.recherchearchive.services.GestionVolumeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
