package main.domain.classes;

import java.util.*;
import java.io.*;
import main.domain.libs.Pair;
import java.util.Vector;
import java.util.Map;
import main.domain.exceptions.*;

/**
 * Aquesta classe representa una prestatgeria de la botiga.
 * La prestatgeria es un vector on cada posicio representa una posicio de la prestatgeria i el valor es l'identificador del producte.
 * Aquesta classe conte els metodes per gestionar la prestatgeria de la botiga.
 * Aquesta classe permet consultar, calcular i modificar la distribucio dels productes a la prestatgeria.
 * @author erikGauchia
 */
public class Prestatgeria {
    /** Atributs de la classe Prestatgeria:
     * distribucio: Vector on cada posicio representa una posicio de la prestatgeria i el valor es l'identificador del producte.
     * altura: Altura de la prestatgeria.
     */
    private Vector<Integer> distribucio;
    private int altura;

    /**
     * Constructora de la classe Prestatgeria:
     */
    //Constructora
    public Prestatgeria() {
        distribucio = new Vector<>();
        altura = 3;
    }
    //Getters i setters

    /**
     * Metode per obtenir l'altura de la prestatgeria.
     * @return altura: Altura de la prestatgeria.
     */
    // Retorna la altura de la distribucio
    public int getAltura() {
        return altura;
    }

    /**
     * Metode per obtenir la distribucio actual de la prestatgeria.
     * @return distribucio: Distribucio actual de la prestatgeria.
     */
    // Retorna la distribucio actual
    public Vector<Integer> getDistribucio() {
        return distribucio;
    }

    /**
     * Metode per modificar la distribucio actual de la prestatgeria.
     * @param d: Nova distribucio de la prestatgeria.
     */
    // Modifica la distribucio actual
    public void setDistribucio(Vector<Integer> d) {
        distribucio = new Vector<Integer> (d);
    }

    /**
     * Metode per modificar la distribucio actual de la prestatgeria.
     * @param distribucio: Nova distribucio de la prestatgeria.
     */
    //Retorna el orde dels productes de la distribucio amb l'identificador "id"
    public void carregarDistribucio(Vector<Integer> distribucio) {
        setDistribucio(distribucio);
    }

    /**
     * Metode per modificar l'altura de la prestatgeria.
     * @param altura: Nova altura de la prestatgeria.
     */
    //Retorna l'altura de la distribucio de la
    public void carregarAltura(int altura) {
        this.altura = altura;
    }

    /**
     * Metode per actualitzar la distribucio de la prestatgeria.
     * @param nova_distribucio: Nova distribucio de la prestatgeria.
     */
    public void actualitzaDistribucio(Vector<Integer> nova_distribucio) {
        //Funcio per guardar la distribucio actual a prestatgeria
        distribucio = new Vector<Integer>();
        distribucio = new Vector<Integer>(nova_distribucio);
    }

    /**
     * Metode per canviar la posicio de dos productes a la prestatgeria.
     * @param id1 Identificador del primer producte.
     * @param id2 Identificador del segon producte.
     */
    //Funcio per modificar la distribucio
    public void modificarDistribucio(int id1, int id2) {
        for (int i = 0; i < distribucio.size(); ++i) {
            if (distribucio.get(i) == id1) {
                distribucio.set(i, id2);
            }
            else if (distribucio.get(i) == id2) {
                distribucio.set(i, id1);
            }
        }
    }
}