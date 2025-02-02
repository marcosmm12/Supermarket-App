package main.domain.classes;


import main.domain.exceptions.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Aquesta classe representa el cataleg de productes de la botiga.
 * El cataleg es un diccionari on la clau es l'identificador del producte i el valor es el producte.
 * Aquesta classe conte els metodes per gestionar el cataleg de productes.
 * Aquesta classe permet consultar, afegir, eliminar i modificar productes del cataleg.
 * @author MarcosMartinezMartinez
 */
public class Cataleg {
    /**
     * Atributs de la classe Cataleg:
     * cataleg: Diccionari on la clau es l'identificador del producte i el valor es el producte.
     */
    private Map<Integer, Producte> cataleg;

    /**
     * Constructora de la classe Cataleg:
     */
    //Creadora
    public Cataleg() {
        cataleg = new HashMap<Integer, Producte>();
    }

    //Getters
    /**
     * Metode per obtenir el cataleg de productes.
     * @return cataleg: Cataleg de productes.
     * @throws ExcepcioCatalegBuit: Es llença si el cataleg esta buit.
     */
    public Map<Integer, Producte> getCataleg() throws ExcepcioCatalegBuit {
        if (cataleg.size() == 0) throw new ExcepcioCatalegBuit();
        return cataleg;
    }

    /**
     * Metode per obtenir el nombre de productes del cataleg.
     * @return cataleg.size(): Nombre de productes del cataleg.
     */
    public Integer getNumProductes() {
        return cataleg.size();
    }

    /**
     * Metode per obtenir un producte del cataleg.
     * @param id: Identificador del producte.
     * @return cataleg.get(id): Producte amb identificador id.
     * @throws ExcepcioProducteNoExisteix: Es llença si el producte amb identificador id no existeix.
     */
    public Producte getProducte(int id) throws ExcepcioProducteNoExisteix {
        if (!cataleg.containsKey(id)) throw new ExcepcioProducteNoExisteix(id);
        return cataleg.get(id);
    }

    /**
     * Metode per obtenir l'identificador d'un producte del cataleg.
     * @param id: Identificador del producte.
     * @return cataleg.get(id).getId(): Identificador del producte amb identificador id.
     * @throws ExcepcioProducteNoExisteix: Es llença si el producte amb identificador id no existeix.
     */
    public Integer getProducteId(int id) throws ExcepcioProducteNoExisteix {
        if (!cataleg.containsKey(id)) throw new ExcepcioProducteNoExisteix(id);
        return cataleg.get(id).getId();
    }

    /**
     * Metode per obtenir el nom d'un producte del cataleg.
     * @param id: Identificador del producte.
     * @return cataleg.get(id).getNom(): Nom del producte amb identificador id.
     * @throws ExcepcioProducteNoExisteix: Es llença si el producte amb identificador id no existeix.
     */
    public String getProducteNom(int id) throws ExcepcioProducteNoExisteix {
        if (!cataleg.containsKey(id)) throw new ExcepcioProducteNoExisteix(id);
        return cataleg.get(id).getNom();
    }

    /**
     * Metode per obtenir el preu d'un producte del cataleg.
     * @param id: Identificador del producte.
     * @return cataleg.get(id).getPreu(): Preu del producte amb identificador id.
     * @throws ExcepcioProducteNoExisteix: Es llença si el producte amb identificador id no existeix.
     */
    public Integer getProductePreu(int id) throws ExcepcioProducteNoExisteix {
        if (!cataleg.containsKey(id)) throw new ExcepcioProducteNoExisteix(id);
        return cataleg.get(id).getPreu();
    }

    /**
     * Metode per comprovar si un producte existeix al cataleg.
     * @param id: Identificador del producte.
     * @return cataleg.containsKey(id): True si el producte amb identificador id existeix al cataleg, false altrament.
     */
    public boolean existProducte(int id) {
        return cataleg.containsKey(id);
    }

    //Setters
    /**
     * Metode per assignar un cataleg de productes.
     * @param cataleg: Cataleg de productes.
     */
    public void setCataleg(Map<Integer, Producte> cataleg) {
        this.cataleg = new HashMap<Integer, Producte>();
        this.cataleg = new HashMap<Integer, Producte> (cataleg);
    }

    /**
     * Metode per afegir un producte al cataleg.
     * @param id Identificador del producte.
     * @param nom Nom del producte.
     * @param preu Preu del producte.
     * @throws ExcepcioProducteJaExisteix Es llença si el producte ja existeix al cataleg.
     */
    public void afegeixProducte(Integer id, String nom, Integer preu) throws ExcepcioProducteJaExisteix {
        Producte p = new Producte(id, nom, preu);
        cataleg.put(id, p);
    }

    /**
     * Metode per eliminar un producte del cataleg.
     * @param id Identificador del producte.
     * @throws ExcepcioProducteNoExisteix Es llença si el producte no existeix al cataleg.
     * @throws ExcepcioCatalegBuit Es llença si el cataleg esta buit.
     */
    public void eliminarProducte(Integer id) throws ExcepcioProducteNoExisteix, ExcepcioCatalegBuit {
        cataleg.remove(id);
    }

    /**
     * Metode per modificar l'identificador d'un producte del cataleg.
     * @param id Identificador del producte.
     * @param id2 Nou identificador del producte.
     * @throws ExcepcioProducteNoExisteix Es llença si el producte no existeix al cataleg.
     * @throws ExcepcioIdJaEstaEnUs Es llença si el nou identificador ja esta en us.
     * @throws RelacioNotExistException Es llença si el producte no existeix al cataleg.
     * @throws ExcepcioCatalegBuit Es llença si el cataleg esta buit.
     */
    public void modificarIdProducte(Integer id, Integer id2) throws ExcepcioProducteNoExisteix, ExcepcioIdJaEstaEnUs, RelacioNotExistException, ExcepcioCatalegBuit {
        Producte p = getProducte(id);
        p.setId(id2);
        //Canvi del producte a cataleg
        cataleg.remove(id);
        cataleg.put(id2, p);
    }

    /**
     * Metode per modificar el nom d'un producte del cataleg.
     * @param id Identificador del producte.
     * @param nom Nou nom del producte.
     * @throws ExcepcioProducteNoExisteix Es llença si el producte no existeix al cataleg.
     * @throws ExcepcioCatalegBuit Es llença si el cataleg esta buit.
     */
    public void modificarNomProducte(Integer id, String nom) throws ExcepcioProducteNoExisteix, ExcepcioCatalegBuit {
        Producte p = getProducte(id);
        p.setNom(nom);
        cataleg.replace(id, p);
    }

    /**
     * Metode per modificar el preu d'un producte del cataleg.
     * @param id Identificador del producte.
     * @param preu Nou preu del producte.
     * @throws ExcepcioProducteNoExisteix Es llença si el producte no existeix al cataleg.
     * @throws ExcepcioCatalegBuit Es llença si el cataleg esta buit.
     */
    public void modificarPreuProducte(Integer id, Integer preu) throws ExcepcioProducteNoExisteix, ExcepcioCatalegBuit {
        Producte p = getProducte(id);
        p.setPreu(preu);
        cataleg.replace(id, p);
    }
}
