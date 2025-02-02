package main.domain.controllers;

import main.domain.classes.Producte;
import main.domain.classes.Relacio;
import main.domain.exceptions.*;
import main.domain.libs.Pair;
import main.domain.classes.Cataleg;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Aquesta classe és un controlador que gestiona el cataleg de productes i les relacions entre ells.
 * Aquesta classe permet afegir, eliminar i modificar productes i relacions entre ells.
 * Aquesta classe permet obtenir informació del cataleg i de les relacions entre els productes.
 * @author MarcosMartinezMartinez
 * @author keinthdc
 */
public class CtrlCataleg {
    /**
     * Atributs de la classe CtrlCataleg
     * cataleg: Cataleg de productes.
     * relacions: Relacions entre els productes.
     */
    private Relacio relacions;
    private Cataleg cataleg;

    /**
     * Constructor de la classe CtrlCataleg.
     * Inicialitza el cataleg i les relacions.
     */
    //Constructora
    public CtrlCataleg() {
        cataleg = new Cataleg();
        relacions = new Relacio();

    }

    //Consultores
    /**
     * Consultora del cataleg de productes.
     * @return Retorna el cataleg de productes.
     * @throws ExcepcioCatalegBuit
     */
    public Map<Integer, Producte> getCataleg() throws ExcepcioCatalegBuit {
        return cataleg.getCataleg();
    }

    /**
     * Consultora del nombre de productes del cataleg.
     * @return Retorna el nombre de productes del cataleg.
     */
    public Integer getNumProductes() {
        return cataleg.getNumProductes();
    }

    /**
     * Consultora del producte amb identificador id.
     * @param id Identificador del producte.
     * @return Retorna el producte amb identificador id.
     * @throws ExcepcioProducteNoExisteix
     */
    public Producte getProducte(int id) throws ExcepcioProducteNoExisteix {
        return cataleg.getProducte(id);
    }

    /**
     * Consultora de l'identificador del producte amb identificador id.
     * @param id Identificador del producte.
     * @return Retorna l'identificador del producte amb identificador id.
     * @throws ExcepcioProducteNoExisteix
     */
    public Integer getProducteId(int id) throws ExcepcioProducteNoExisteix {
        return cataleg.getProducteId(id);
    }

    /**
     * Consultora del nom del producte amb identificador id.
     * @param id Identificador del producte.
     * @return Retorna el nom del producte amb identificador id.
     * @throws ExcepcioProducteNoExisteix
     */
    public String getProducteNom(int id) throws ExcepcioProducteNoExisteix {
        return cataleg.getProducteNom(id);
    }

    /**
     * Consultora del preu del producte amb identificador id.
     * @param id Identificador del producte.
     * @return Retorna el preu del producte amb identificador id.
     * @throws ExcepcioProducteNoExisteix
     */
    public Integer getProductePreu(int id) throws ExcepcioProducteNoExisteix {
        return cataleg.getProductePreu(id);
    }

    /**
     * Consultora de si existeix el producte amb identificador id.
     * @param id Identificador del producte.
     * @return Retorna cert si existeix el producte amb identificador id, fals altrament.
     */
    public boolean existProducte(int id) {
        return cataleg.existProducte(id);
    }

    /**
     * Consultora de les relacions del cataleg.
     * @return Retorna les relacions del cataleg.
     * @throws ExcepcioCatalegBuit
     */
    public Map<Pair<Integer, Integer>, Integer> getRelacions() throws ExcepcioCatalegBuit {
        if (cataleg.getNumProductes() == 0) throw new ExcepcioCatalegBuit();
        return relacions.getAllRelacio();
    }

    /**
     * Consultora de les relacions inverses del cataleg.
     * @return Retorna les relacions inverses del cataleg (relacio inversa = 100 - relacio).
     * @throws ExcepcioCatalegBuit
     */
    public Map<Pair<Integer, Integer>, Integer> getRelacionsInvers() throws ExcepcioCatalegBuit {
        if (cataleg.getNumProductes() == 0) throw new ExcepcioCatalegBuit();
        return relacions.getAllRelacioInversa();
    }

    /**
     * Consultora de si existeix la relacio entre els productes amb identificadors id1 i id2.
     * @param id1 Identificador del primer producte.
     * @param id2 Identificador del segon producte.
     * @return Retorna cert si existeix la relacio entre els productes amb identificadors id1 i id2, fals altrament.
     * @throws ExcepcioProducteNoExisteix
     */
    public boolean existRelacio(int id1, int id2) throws ExcepcioProducteNoExisteix {
        if (!existProducte(id1)) throw new ExcepcioProducteNoExisteix(id1);
        if (!existProducte(id2)) throw new ExcepcioProducteNoExisteix(id2);
        return relacions.existRelacio(id1, id2);
    }

    /**
     * Consultora de la relacio entre els productes amb identificadors id1 i id2.
     * @param id1 Identificador del primer producte.
     * @param id2 Identificador del segon producte.
     * @return Retorna la relacio entre els productes amb identificadors id1 i id2.
     * @throws RelacioNotExistException
     * @throws ExcepcioCatalegBuit
     */
    public Integer getRelacio(int id1, int id2) throws RelacioNotExistException, ExcepcioCatalegBuit {
        if (cataleg.getNumProductes() == 0) throw new ExcepcioCatalegBuit();
        return relacions.getValRelacio(id1, id2);
    }

    //Modificadores
    /**
     * Modificadora del cataleg de productes.
     * @param cataleg Cataleg de productes.
     */
    public void setCataleg(Map<Integer, Producte> cataleg) {
        this.cataleg.setCataleg(cataleg);
    }

    /**
     * Modificadora de les relacions del cataleg.
     * @param relacions Relacions del cataleg.
     */
    public void setRelacions(Map<Pair<Integer, Integer>, Integer> relacions) {
        this.relacions = new Relacio(relacions);
    }

    /**
     * Afegeix un producte al cataleg amb identificador id, nom, preu i relacions amb altres productes.
     * @param id Identificador del producte.
     * @param nom Nom del producte.
     * @param preu Preu del producte.
     * @param relacions Relacions del producte amb altres productes.
     * @throws ExcepcioProducteJaExisteix
     * @throws ExcepcioProducteNoExisteix
     * @throws ExcepcioCatalegBuit
     */
    public void afegeixProducte(Integer id, String nom, Integer preu, List<Pair<Integer, Integer>> relacions) throws ExcepcioProducteJaExisteix, ExcepcioProducteNoExisteix, ExcepcioCatalegBuit {
        if (existProducte(id)) throw new ExcepcioProducteJaExisteix(id);
        cataleg.afegeixProducte(id, nom, preu);
        //Insercio de les relacions especificades
        for (Pair<Integer, Integer> r : relacions) {
            if (!existProducte(r.getFirstVal())) throw new ExcepcioProducteNoExisteix(r.getFirstVal());
            this.relacions.addRelacio(id, r.getFirstVal(), r.getSecondVal());
        }
        //Establiment valor per defecte de les relacions no especificades
        Map<Integer, Producte> cataleg = this.cataleg.getCataleg();
        for (Map.Entry<Integer, Producte> entry : cataleg.entrySet()) {
            if (!existRelacio(id, entry.getKey()) && !id.equals(entry.getKey())) {
                this.relacions.addRelacio(id, entry.getKey(),50); //Valor per defecte
            }
        }
    }

    /**
     * Elimina el producte amb identificador id del cataleg.
     * @param id Identificador del producte.
     * @throws ExcepcioProducteNoExisteix
     * @throws ExcepcioCatalegBuit
     */
    public void eliminarProducte(Integer id) throws ExcepcioProducteNoExisteix, ExcepcioCatalegBuit {
        if (cataleg.getNumProductes() == 0) throw new ExcepcioCatalegBuit();
        if (!existProducte(id)) throw new ExcepcioProducteNoExisteix(id);
        cataleg.eliminarProducte(id);
        relacions.eliminaRelacio(id);
    }

    /**
     * Modifica l'identificador del producte amb identificador id per id2.
     * @param id Identificador del producte.
     * @param id2 Nou identificador del producte.
     * @throws ExcepcioProducteNoExisteix
     * @throws ExcepcioIdJaEstaEnUs
     * @throws RelacioNotExistException
     * @throws ExcepcioCatalegBuit
     */
    public void modificarIdProducte(Integer id, Integer id2) throws ExcepcioProducteNoExisteix, ExcepcioIdJaEstaEnUs, RelacioNotExistException, ExcepcioCatalegBuit {
        if (cataleg.getNumProductes() == 0) throw new ExcepcioCatalegBuit();
        if (!existProducte(id)) throw new ExcepcioProducteNoExisteix(id);
        if (existProducte(id2)) throw new ExcepcioIdJaEstaEnUs(id2);
        cataleg.modificarIdProducte(id, id2);
        //Canvi de les relacions del producte
        Map<Pair<Integer, Integer>, Integer> relacionsActualitzades = new TreeMap<Pair<Integer, Integer>, Integer>();
        for (Map.Entry<Pair<Integer, Integer>, Integer> entry : relacions.getAllRelacio().entrySet()) {
            Pair<Integer, Integer> key = entry.getKey();
            Integer value = entry.getValue();
            if (key.getFirstVal().equals(id)) {
                relacionsActualitzades.put(new Pair<Integer, Integer>(id2, key.getSecondVal()), value);
            } else if (key.getSecondVal().equals(id)) {
                relacionsActualitzades.put(new Pair<Integer, Integer>(key.getFirstVal(), id2), value);
            } else {
                relacionsActualitzades.put(key, value);
            }
        }
        relacions = new Relacio(relacionsActualitzades);
    }

    /**
     * Modifica el nom del producte amb identificador id per nom.
     * @param id Identificador del producte.
     * @param nom Nou nom del producte.
     * @throws ExcepcioProducteNoExisteix
     * @throws ExcepcioCatalegBuit
     */
    public void modificarNomProducte(Integer id, String nom) throws ExcepcioProducteNoExisteix, ExcepcioCatalegBuit {
        if (cataleg.getNumProductes() == 0) throw new ExcepcioCatalegBuit();
        if (!existProducte(id)) throw new ExcepcioProducteNoExisteix(id);
        cataleg.modificarNomProducte(id, nom);
    }

    /**
     * Modifica el preu del producte amb identificador id per preu.
     * @param id Identificador del producte.
     * @param preu Nou preu del producte.
     * @throws ExcepcioProducteNoExisteix
     * @throws ExcepcioCatalegBuit
     */
    public void modificarPreuProducte(Integer id, Integer preu) throws ExcepcioProducteNoExisteix, ExcepcioCatalegBuit {
        if (cataleg.getNumProductes() == 0) throw new ExcepcioCatalegBuit();
        if (!existProducte(id)) throw new ExcepcioProducteNoExisteix(id);
        cataleg.modificarPreuProducte(id, preu);
    }

    /**
     * Modifica la relacio entre els productes amb identificadors id i id2 per value.
     * @param id Identificador del primer producte.
     * @param id2 Identificador del segon producte.
     * @param value Nou valor de la relacio.
     * @throws ExcepcioProducteNoExisteix
     * @throws RelacioNotExistException
     * @throws ExcepcioCatalegBuit
     */
    public void modificarRelacio(Integer id, Integer id2, Integer value) throws ExcepcioProducteNoExisteix, RelacioNotExistException, ExcepcioCatalegBuit {
        if (cataleg.getNumProductes() == 0) throw new ExcepcioCatalegBuit();
        if (!existProducte(id)) throw new ExcepcioProducteNoExisteix(id);
        if (!existProducte(id2)) throw new ExcepcioProducteNoExisteix(id2);
        //ChangeValRelacio pot provocar una excepcio si no existeix la relacio
        relacions.changeValRelacio(id, id2, value);
    }
}
