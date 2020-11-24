/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.recherchearchive.expo.rest;

import com.google.gson.Gson;
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
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.PathParam;

/**
 * REST Web Service pour l'ensemble des comptes
 *
 * @author Cédric Teyssié
 */
@Path("rechercheVolume")
public class RechercheVolumeResource {

    // Accès BackOffice
    private final GestionVolumeLocal gestionVolume;

    @Context
    private UriInfo context;

    // Convertisseur JSON
    private final Gson gson;

    /**
     * Constructeur Ressource
     */
    public RechercheVolumeResource() {
        this.gson = new Gson();
        this.gestionVolume = lookupGestionVolumeLocal();
    }
    
    /**
     * Récupérer un Volume par le nom
     * @param titre l'id d'un titre 
     * @return une réponse HTTP avec le/les Volume/s en JSON
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getVolumeByMotsCles(@PathParam("titleIdResearch") String titre) {
        List<Volume> volumes = gestionVolume.findByTitreId(titre);
        
        StringBuilder st = new StringBuilder();
        for (Volume v : volumes) {
            st.append("{").append(v.getIdvolume())
                    .append(":{id:").append(v.getIdvolume()).append(",")
                    .append("nom:").append(v.getNom()).append(",")
                    .append("numero:").append(v.getNumero()).append(",")
                    .append("titre:").append(v.getIdtitre()).append(",")
                    .append("termine:").append(v.getTermine()).append("},");
        }
        st.deleteCharAt(st.length()-1).append("}");
        
        return this.gson.toJson(st.toString());
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
