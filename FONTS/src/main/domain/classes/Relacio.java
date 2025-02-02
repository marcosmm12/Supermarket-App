package main.domain.classes;

import java.util.TreeMap;
import java.util.Iterator;
import java.util.Map;

import main.domain.libs.Pair;
import main.domain.exceptions.*;

/**
 * Aquesta classe representa la relacio entre els productes de la botiga.
 * La relacio es un diccionari on la clau es un parell de identificadors de productes i el valor es la relacio entre ells.
 * Aquesta classe conte els metodes per gestionar la relacio entre productes.
 * Aquesta classe permet consultar, afegir, eliminar i modificar relacions entre productes.
 * @author keinthdc
 */
public class Relacio {
    /**
     * Atributs de la classe Relacio:
     * coeficient: Diccionari on la clau es un parell de identificadors de productes i el valor es la relacio entre ells.
     */
    private Map<Pair<Integer, Integer>, Integer> coeficient;

    /**
     * Constructora de la classe Relacio:
     */
    //Constructor
    public Relacio() {
        this.coeficient = new TreeMap<>();
    }

    /**
     * Constructora de la classe Relacio:
     */
    //nueva constructora
    public Relacio(Map<Pair<Integer, Integer>, Integer> relacions) {
        this.coeficient = new TreeMap<Pair<Integer, Integer>, Integer>();
        this.coeficient = new TreeMap<Pair<Integer, Integer>, Integer>(relacions);
    }

    /**
     * Metode per obtenir la relacio entre dos productes.
     * @param id1: Identificador del primer producte.
     * @param id2: Identificador del segon producte.
     * @return coeficient.get(productPair): Relacio entre els productes amb identificadors id1 i id2.
     * @throws RelacioNotExistException: Es llença si la relacio entre els productes amb identificadors id1 i id2 no existeix.
     */
    //Returns the relation value
    public Integer getValRelacio(Integer id1, Integer id2) throws RelacioNotExistException {
        Pair<Integer, Integer> productPair = new Pair<>(id1, id2);
        Pair<Integer, Integer> productPairInverse = new Pair<>(id2, id1);
        if (this.coeficient.containsKey(productPair)) {
            return this.coeficient.get(productPair);
        } else if (this.coeficient.containsKey(productPairInverse)) {
            return this.coeficient.get(productPairInverse);
        } else {
            throw new RelacioNotExistException("Error: Relacio of id1:" + id1.toString() + ", id2:" + id2.toString() + " does not exist");
        }
    }

    /**
     * Metode per comprovar si existeix una relacio entre dos productes.
     * @param id1: Identificador del primer producte.
     * @param id2: Identificador del segon producte.
     * @return true: Si existeix una relacio entre els productes amb identificadors id1 i id2, fals altrament
     */
    //Returns true if exist relation
    public boolean existRelacio(Integer id1, Integer id2) {
        Pair<Integer, Integer> productPair = new Pair<>(id1, id2);
        Pair<Integer, Integer> productPairInvers = new Pair<>(id2, id1);
        return this.coeficient.containsKey(productPair) || this.coeficient.containsKey(productPairInvers);
    }

    /**
     * Metode per eliminar una relacio entre dos productes.
     * @param id1: Identificador del primer producte.
     * @param id2: Identificador del segon producte.
     * @return coeficient.remove(productPair): Relacio eliminada entre els productes amb identificadors id1 i id2.
     */
    //Deletes the relation
    public void eraseRelacio(Integer id1, Integer id2) throws RelacioNotExistException {
        if (existRelacio(id1, id2)) {
            Pair<Integer, Integer> productPair = new Pair<>(id1, id2);
            this.coeficient.remove(productPair);
        } else if(existRelacio(id2, id1)) {
            Pair<Integer, Integer> productPair = new Pair<>(id2, id1);
            this.coeficient.remove(productPair);
        }
        else {
            //tratar en caso de que no exista
            throw new RelacioNotExistException("Error: Relacio of id1:" + id1.toString() + ", id2:" + id2.toString() + " does not exist");
        }
    }

    /**
     * Metode per afegir una relacio entre dos productes.
     * @param id1: Identificador del primer producte.
     * @param id2: Identificador del segon producte.
     * @param value: Relacio entre els productes amb identificadors id1 i id2.
     * @throws RelacioExistException: Es llença si la relacio entre els productes amb identificadors id1 i id2 ja existeix.
     */
    //Adds the relation with the value
    public void addRelacio(Integer id1, Integer id2, Integer value) throws RelacioExistException {
        if (!existRelacio(id1, id2) && !existRelacio(id2, id1)) {
            Pair<Integer, Integer> productPair = new Pair<>(id1, id2);
            this.coeficient.put(productPair, value);
        } else {
            //tratar en caso de que ya exista
            throw new RelacioExistException("Error: Relacio of id1:" + id1.toString() + ", id2:" + id2.toString() + " already exist");
        }
    }

    /**
     * Metode per modificar la relacio entre dos productes.
     * @param id1: Identificador del primer producte.
     * @param id2: Identificador del segon producte.
     * @param value: Nova relacio entre els productes amb identificadors id1 i id2.
     * @throws RelacioNotExistException: Es llença si la relacio entre els productes amb identificadors id1 i id2 no existeix.
     */
    //Changes the value of the relation
    public void changeValRelacio(Integer id1, Integer id2, Integer value) throws RelacioNotExistException {
        Pair<Integer, Integer> productPair = new Pair<>(id1, id2);
        Pair<Integer, Integer> productPairInvers = new Pair<>(id2, id1);
        if (this.coeficient.containsKey(productPair)) {
            this.coeficient.replace(productPair, value);
        } else if(this.coeficient.containsKey(productPairInvers)) {
            this.coeficient.replace(productPairInvers, value);
        } else {
            //tratar en caso de que no exista
            throw new RelacioNotExistException("Error: Relacio of id1:" + id1.toString() + ", id2:" + id2.toString() + " does not exist");
        }
    }

    /**
     * Metode per eliminar totes les relacions associades a un producte.
     * @param id Identificador del producte.
     */
    //Erases all the relation
    public void eliminaRelacio(Integer id) {
        Iterator<Map.Entry<Pair<Integer, Integer>, Integer>> iterator = coeficient.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Pair<Integer, Integer>, Integer> entry = iterator.next();
            // Comprobamos si la clave contiene el id y eliminamos de manera segura
            if (entry.getKey().getFirstVal().equals(id) || entry.getKey().getSecondVal().equals(id))
                iterator.remove();
        }
    }

    /**
     * Metode per obtenir totes les relacions.
     * @return coeficient: Diccionari amb totes les relacions.
     */
    //Returns all relations in a map form
    public Map<Pair<Integer, Integer>, Integer> getAllRelacio()
    {
        return new TreeMap<>(this.coeficient);
    }

    /**
     * Metode per obtenir totes les relacions inverses.
     * @return inversa: Diccionari amb totes les relacions inverses.
     */
    //Returns all inverse relations in a map form.
    //Esto es porque la clase algoritmo utiliza busqueda de combinaciones minimas porque resuelve TSP,
    //con esto conseguimos que las relaciones maximas se conviertan en valores minimos y las relaciones minimas en valores maximos
    public Map<Pair<Integer, Integer>, Integer> getAllRelacioInversa()
    {
        Map<Pair<Integer, Integer>, Integer> inversa = new TreeMap<>();
        for (Map.Entry<Pair<Integer, Integer>, Integer> entry : this.coeficient.entrySet()) {
            Pair<Integer, Integer> key = entry.getKey();
            Integer invertedValue = 100 - entry.getValue(); // Invert the value by subtracting it from 100
            inversa.put(key, invertedValue);
        }
        return inversa;
    }
}
