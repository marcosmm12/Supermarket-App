package main.domain.controllers;

import main.domain.classes.Algoritme;
import main.domain.classes.AlgoritmeFBruta;
import main.domain.classes.algoritmeAproximacio;
import main.domain.classes.Prestatgeria;
import main.domain.classes.Producte;
import main.domain.exceptions.ExcepcioCatalegBuit;
import main.domain.exceptions.ExcepcioJaExisteixDistribucio;
import main.domain.exceptions.ExcepcioNoExisteixDistribucio;
import main.domain.exceptions.ExcepcioProducteNoExisteix;
import main.domain.libs.Pair;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.Vector;

/**
 * Aquesta classe és el controlador de la distribució dels productes a la prestatgeria.
 * Aquesta classe permet distribuir els productes a la prestatgeria, modificar la distribució i canviar l'algoritme
 * Aquesta classe fa ús de les classes CtrlCataleg, Algoritme i Prestatgeria.
 */
public class CtrlDistribucio {
    /**
     * Atributs de la classe CtrlDistribucio
     * cataleg: Controlador del cataleg de productes
     * algoritme: Algoritme per distribuir els productes
     * prestatgeria: Prestatgeria on es guarden els productes
     */
    // Atributs
    private CtrlCataleg cataleg;
    private Algoritme algoritme;
    private Prestatgeria prestatgeria;

    // Constructora
    /**
     * Constructora de la classe CtrlDistribucio
     * @param c Controlador del cataleg de productes
     * @param a Algoritme per distribuir els productes
     * @param p Prestatgeria on es guarden els productes
     */
    public CtrlDistribucio(CtrlCataleg c, Algoritme a, Prestatgeria p) {
        cataleg = c;
        algoritme = a;
        prestatgeria = p;
    }

    // Funcions
    // Funcio per calcular la distribucio adient
    /**
     * Aquesta funció calcula la distribució adient dels productes a la prestatgeria.
     * @throws ExcepcioCatalegBuit Es genera si el cataleg de productes està buit
     */
    public void distribuirProductes() throws ExcepcioCatalegBuit {
        Vector<Integer> nova_distribucio = new Vector<Integer>();
        int num_productes = cataleg.getNumProductes();
        if (num_productes > 0) {
            Map<Pair<Integer, Integer>, Integer> relacions = cataleg.getRelacionsInvers();
            if (!relacions.isEmpty())
                nova_distribucio = new Vector<Integer>(algoritme.aplicarAlgoritme(relacions, num_productes));
            else nova_distribucio.add(cataleg.getCataleg().keySet().iterator().next());
        }
        prestatgeria.actualitzaDistribucio(nova_distribucio);
    }

    // Modificar la distribucio
    /**
     * Aquesta funció modifica la distribució de dos productes a la prestatgeria.
     * @param id1 Identificador del primer producte
     * @param id2 Identificador del segon producte
     * @throws ExcepcioProducteNoExisteix Es genera si algun dels dos productes no existeix al cataleg
     * @throws ExcepcioCatalegBuit Es genera si el cataleg de productes està buit
     */
    public void modificarDistribucio(int id1, int id2) throws ExcepcioProducteNoExisteix, ExcepcioCatalegBuit {
        if (!cataleg.existProducte(id1)) throw new ExcepcioProducteNoExisteix(id1);
        if (!cataleg.existProducte(id2)) throw new ExcepcioProducteNoExisteix(id2);
        prestatgeria.modificarDistribucio(id1, id2);
    }

    // Funcio per canviar el tipus d'algoritme aplicat
    /**
     * Aquesta funció canvia l'algoritme aplicat per distribuir els productes a la prestatgeria.
     * @param a Algoritme per distribuir els productes
     */
    public void setAlgoritme(Algoritme a) {
        algoritme = a;
    }

    //Escollir algoritme de força bruta
    /**
     * Aquesta funció escull l'algoritme de força bruta per distribuir els productes a la prestatgeria.
     * @throws ExcepcioCatalegBuit Es genera si el cataleg de productes està buit
     */
    public void escollirAlgoritmeForcaBruta() throws ExcepcioCatalegBuit {
        algoritme = new AlgoritmeFBruta();
        distribuirProductes();
    }

    //Escollir algoritme d'aproximacio
    /**
     * Aquesta funció escull l'algoritme d'aproximació per distribuir els productes a la prestatgeria.
     * @throws ExcepcioCatalegBuit Es genera si el cataleg de productes està buit
     */
    public void escollirAlgoritmeAproximacio() throws ExcepcioCatalegBuit {
        algoritme = new algoritmeAproximacio();
        distribuirProductes();
    }

    // Getters
    /**
     * Aquesta funció retorna l'alçada de la prestatgeria.
     * @return Alçada de la prestatgeria
     */
    public int getAltura() {
        return prestatgeria.getAltura();
    }

    /**
     * Aquesta funció retorna la distribució de la prestatgeria.
     * @return Distribució de la prestatgeria
     * @throws ExcepcioCatalegBuit Es genera si el cataleg de productes està buit
     */
    public Vector<Integer> getDistribucio() throws ExcepcioCatalegBuit {
        return prestatgeria.getDistribucio();
    }

    // Setters
    /**
     * Aquesta funció carrega una distribució de la prestatgeria.
     * @param d Distribució de la prestatgeria
     */
    public void setDistribucio(Vector<Integer> d) {
        prestatgeria.setDistribucio(d);
    }

    //Funcio per carregar una distribucio de prestatgeria
    /**
     * Aquesta funció carrega una distribució de la prestatgeria.
     * @param distribucio Distribució de la prestatgeria
     * @param altura Alçada de la prestatgeria
     */
    public void carregarDistribucio (Vector<Integer> distribucio, int altura) {
        prestatgeria.carregarDistribucio(distribucio);
        prestatgeria.carregarAltura(altura);
    }
}
