/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.gestiondistributeurs.services;

import com.google.gson.Gson;
import fr.miage.rois.gestiondistributeurs.entities.Titre;
import fr.miage.rois.gestiondistributeurs.entities.Volume;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import org.apache.log4j.Logger;

/**
 *
 * @author sagab
 */
public class VolumeRESTSender {

    public static void envoyerVolumeAAppliRecherche(Volume volume) {
        Logger logger = Logger.getLogger(VolumeRESTSender.class);
        Gson gson = new Gson();
        try {
            URL url = new URL("http://localhost:8080/RechercheArchive-web/webresources/titre");
            URLConnection con = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) con;
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            
            byte[] out = volume.toString().getBytes(StandardCharsets.UTF_8);
            int length = out.length;
            
            http.setFixedLengthStreamingMode(length);
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();
            
            try (OutputStream os = http.getOutputStream()) {
                os.write(out);
            }
            System.out.println(http.getResponseMessage());
        } catch (MalformedURLException e) {
            logger.error("Envoie du Volume :" + volume.toString() + " en POST impossible avec l'URL donnée");
        } catch (IOException e) {
            logger.error("Problème lors de l'envoie en POST du volume : " + volume.toString());
        }
    }
    
    public static void envoyerTitreAAppliRecherche(Titre titre) {
        Logger logger = Logger.getLogger(VolumeRESTSender.class);
        Gson gson = new Gson();
        try {
            URL url = new URL("http://localhost:8080/RechercheArchive-web/webresources/titre");
            URLConnection con = url.openConnection();
            HttpURLConnection http = (HttpURLConnection) con;
            http.setRequestMethod("POST");
            http.setDoOutput(true);
            
            byte[] out = titre.toString().getBytes(StandardCharsets.UTF_8);
            int length = out.length;

            http.setFixedLengthStreamingMode(length);
            http.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            http.connect();
            
            try (OutputStream os = http.getOutputStream()) {
                os.write(out);
            }
            
        } catch (MalformedURLException e) {
            logger.error("Envoie du Titre : " + titre.toString() + " en POST impossible avec l'URL donnée");
        } catch (IOException e) {
            logger.error("Problème lors de l'envoie en POST du Titre : " + titre.toString());
        }
    }

}
