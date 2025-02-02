package main.domain.classes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;

/**
 * Aquesta classe representa un producte de la botiga.
 * Aquesta classe permet consultar, afegir, eliminar i modificar productes.
 * @author MarcosMartinezMartinez
 */
@JsonIgnoreProperties({"producte"})
public class Producte implements Serializable {
    /**
     * Atributs de la classe Producte:
     * id: Identificador del producte.
     * nom: Nom del producte.
     * preu: Preu del producte.
     */
    private int id;

    private String nom;

    private int preu;

    /**
     * Constructora de la classe Producte:
     */
    //Constructora
    public  Producte() {
    }

    /**
     * Constructora de la classe Producte:
     * @param id: Identificador del producte.
     * @param nom: Nom del producte.
     * @param preu: Preu del producte.
     */
    public Producte(Integer id, String nom, Integer preu)  {
        this.id = id;
        this.nom = nom;
        this.preu = preu;
    }

    //Getters i Setters
    /**
     * Metode per obtenir el producte.
     * @return this: Producte.
     */
    public Producte getProducte() {
        return this;
    }

    /**
     * Metode per obtenir l'identificador del producte.
     * @return id: Identificador del producte.
     */
    public Integer getId() {
        return id;
    }

    /**
     * Metode per obtenir el nom del producte.
     * @return nom: Nom del producte.
     */
    public String getNom() {
        return nom;
    }

    /**
     * Metode per obtenir el preu del producte.
     * @return preu: Preu del producte.
     */
    public Integer getPreu() {
        return preu;
    }

    /**
     * Metode per assignar l'identificador del producte.
     * @param id: Identificador del producte.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Metode per assignar el nom del producte.
     * @param nom: Nom del producte.
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * Metode per assignar el preu del producte.
     * @param preu: Preu del producte.
     */
    public void setPreu(Integer preu) {
        this.preu = preu;
    }

    /**
     * Metode per pasar a String un producte.
     * @return String: Representacio en String del producte.
     */
    @Override
    public String toString() {
        return "Producte{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", preu=" + preu +
                '}';
    }
}