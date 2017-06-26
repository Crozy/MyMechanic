package com.mycompany.myapp.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Demande.
 */
@Entity
@Table(name = "demande")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Demande implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "utilisateur")
    private String utilisateur;

    @Column(name = "demande")
    private String demande;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUtilisateur() {
        return utilisateur;
    }

    public Demande utilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
        return this;
    }

    public void setUtilisateur(String utilisateur) {
        this.utilisateur = utilisateur;
    }

    public String getDemande() {
        return demande;
    }

    public Demande demande(String demande) {
        this.demande = demande;
        return this;
    }

    public void setDemande(String demande) {
        this.demande = demande;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Demande demande = (Demande) o;
        if (demande.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), demande.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Demande{" +
            "id=" + getId() +
            ", utilisateur='" + getUtilisateur() + "'" +
            ", demande='" + getDemande() + "'" +
            "}";
    }
}
