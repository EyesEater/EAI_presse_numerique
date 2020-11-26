/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.appjournaliste.entities;

/**
 *
 * @author sagab
 */
public class Article {
    
    private Integer idarticle;
    private String titre;
    private String motscles;
    private Integer auteur;
    private String contenu;
    private Boolean valide;
    private Integer idtitre;

    public Article(Integer idarticle, String titre, String motscles, Integer auteur, String contenu, Boolean valide, Integer idtitre) {
        this.idarticle = idarticle;
        this.titre = titre;
        this.motscles = motscles;
        this.auteur = auteur;
        this.contenu = contenu;
        this.valide = valide;
        this.idtitre = idtitre;
    }

    public Integer getIdarticle() {
        return idarticle;
    }

    public void setIdarticle(Integer idarticle) {
        this.idarticle = idarticle;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getMotscles() {
        return motscles;
    }

    public void setMotscles(String motscles) {
        this.motscles = motscles;
    }

    public Integer getAuteur() {
        return auteur;
    }

    public void setAuteur(Integer auteur) {
        this.auteur = auteur;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Boolean getValide() {
        return valide;
    }

    public void setValide(Boolean valide) {
        this.valide = valide;
    }

    public Integer getIdtitre() {
        return idtitre;
    }

    public void setIdtitre(Integer idtitre) {
        this.idtitre = idtitre;
    }
}
