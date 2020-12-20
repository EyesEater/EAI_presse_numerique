/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.expo;

import com.google.gson.Gson;
import fr.miage.rois.distributeur.entities.Abonnement;
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
@Path("distrib")
@RequestScoped
public class DistribResource {

    fr.miage.rois.distributeur.services.DistributeurBean distributeurBean = lookupDistributeurBeanBean();
    
    Gson gson = new Gson();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of DistribResource
     */
    public DistribResource() {
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void envoyerAbonnement(@QueryParam("abonnement") String abonnement) {
        Abonnement abo = this.gson.fromJson(abonnement, Abonnement.class);
        this.distributeurBean.envoyerAbonnement(abo);
    }

    private fr.miage.rois.distributeur.services.DistributeurBean lookupDistributeurBeanBean() {
        try {
            javax.naming.Context c = new InitialContext();
            return (fr.miage.rois.distributeur.services.DistributeurBean) c.lookup("java:global/AppDistributeur-ear/AppDistributeur-ejb-1.0-SNAPSHOT/DistributeurBean");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
