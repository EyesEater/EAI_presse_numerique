/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.redacchef.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sagab
 */
@Entity
@Table(name = "ARTICLE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Article.findAll", query = "SELECT a FROM Article a")
    , @NamedQuery(name = "Article.findByIdarticle", query = "SELECT a FROM Article a WHERE a.idarticle = :idarticle")
    , @NamedQuery(name = "Article.findByTitre", query = "SELECT a FROM Article a WHERE a.titre = :titre")
    , @NamedQuery(name = "Article.findByMotscles", query = "SELECT a FROM Article a WHERE a.motscles = :motscles")
    , @NamedQuery(name = "Article.findByAuteur", query = "SELECT a FROM Article a WHERE a.auteur = :auteur")
    , @NamedQuery(name = "Article.findByContenu", query = "SELECT a FROM Article a WHERE a.contenu = :contenu")
    , @NamedQuery(name = "Article.findByValide", query = "SELECT a FROM Article a WHERE a.valide = :valide")})
public class Article implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "IDARTICLE")
    private Integer idarticle;
    @Size(max = 100)
    @Column(name = "TITRE")
    private String titre;
    @Size(max = 100)
    @Column(name = "MOTSCLES")
    private String motscles;
    @Column(name = "AUTEUR")
    private Integer auteur;
    @Size(max = 1000)
    @Column(name = "CONTENU")
    private String contenu;
    @Column(name = "VALIDE")
    private Boolean valide;
    @JoinColumn(name = "IDTITRE", referencedColumnName = "IDTITRE")
    @ManyToOne
    private Titre idtitre;

    public Article() {
    }

    public Article(Integer idarticle) {
        this.idarticle = idarticle;
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

    public Titre getIdtitre() {
        return idtitre;
    }

    public void setIdtitre(Titre idtitre) {
        this.idtitre = idtitre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idarticle != null ? idarticle.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Article)) {
            return false;
        }
        Article other = (Article) object;
        if ((this.idarticle == null && other.idarticle != null) || (this.idarticle != null && !this.idarticle.equals(other.idarticle))) {
            return false;
        }
        return true;
    }

    /**
     * private Integer idarticle;
    private String titre;
    private String motscles;
    private Integer auteur;
    private String contenu;
    private Boolean valide;
    private Titre idtitre;
     * @return 
     */
    @Override
    public String toString() {
        String[] motscles = this.motscles.split(",");
        StringBuilder st = new StringBuilder("{");
        st.append("idarticle:").append(this.idarticle).append(",")
                .append("titre:\"").append(this.titre).append("\",")
                .append("contenu:\"").append(this.contenu).append("\",")
                .append("auteur:").append(this.auteur).append(",")
                .append("valide:").append(this.valide).append(",")
                .append("idtitre:").append(this.idtitre).append(",")
                .append("motscles:[");
        
        for (String mot : motscles) {
            st.append("\"").append(mot).append("\",");
        }
        st.deleteCharAt(st.length()-1).append("]}");
        return st.toString();
    }
    
}
