/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.rois.redacchefejb;

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
    , @NamedQuery(name = "Article.findByContenu", query = "SELECT a FROM Article a WHERE a.contenu = :contenu")})
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

    @Override
    public String toString() {
        return "fr.miage.rois.redacchefejb.Article[ idarticle=" + idarticle + " ]";
    }
    
}
