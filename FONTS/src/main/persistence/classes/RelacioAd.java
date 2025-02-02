package main.persistence.classes;

import main.domain.libs.Pair;

import java.io.IOException;
import java.util.*;

/**
 * Aquesta classe és una interfície que permet gestionar la persistència de les dades de la prestatgeria.
 * Aquesta classe permet guardar i carregar les dades de la prestatgeria.
 * @author erikGauchia
 */

 public interface RelacioAd {

    /**
     * Retorna la relacio amb identificador id.
     * @param id Identificador de la relacio.
     * @return Retorna la relacio amb identificador id.
     */

    public Map<Pair<Integer, Integer>, Integer> getRelacio(String id);

    /**
     * Retorna si existeix la relacio amb identificador id.
     * @param id Identificador de la relacio.
     * @return Retorna si existeix la relacio amb identificador id.
     */

    public  boolean existRelacio(String id);

    /**
     * Guarda una relacio en la esctructura de relacions amb el id passat per parametre
     * @param id Identificador de la relacio a guardar.
     * @param relacio Relacio a guardar
     */

    public void guardarRelacio(String id, Map<Pair<Integer, Integer>, Integer> relacio);

    /**
     * Carrega les relacions que hi ha en un fitxer amb el nom de nomFitxer dins el programa
     * @param nomFitxer Nom del fitxer del que volem carregar les relacions.
     * @throws IOException
     */

    public void carregarRelacions(String nomFitxer) throws IOException;

    /**
     * Guarda les relacions que hi ha actualment al programa en un fitxer amb el nom de nomFitxer
     * @param nomFitxer Nom del fitxer en el que volem guardar les relacions.
     * @throws IOException
     */

    public void guardarRelacions(String nomFitxer) throws IOException;

    /**
     * Retorna una relacio de un fitxer amb el nom de nomFitxer
     * @param nomFitxer Nom del fitxer del que volem carregar la relacio.
     * @return Relacio d'un fitxer amb el nom de nomFitxer.
     * @throws IOException
     */

    public Map<Pair<Integer, Integer>, Integer> getRelacioFitxer(String nomFitxer) throws IOException;

    /**
     * Guarda la relacio passada per parametre en un fitxer amb el nom de nomFitxer
     * @param nomFitxer Nom del fitxer on volem carregar la relacio.
     * @param relacio Relacio a carregar en el fitxer.
     * @throws IOException
     */

    public void guardarRelacioFitxer(String nomFitxer, Map<Pair<Integer, Integer>, Integer> relacio) throws IOException;
}