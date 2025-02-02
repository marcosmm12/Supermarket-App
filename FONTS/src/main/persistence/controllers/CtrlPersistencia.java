package main.persistence.controllers;

import main.persistence.classes.*;
import main.domain.classes.Producte;
import main.domain.libs.*;

import java.io.IOException;
import java.util.Map;
import java.util.Vector;

/**
 * Aquesta classe és el controlador de persistència.
 * Aquesta classe és l'encarregada de gestionar la persistència de les dades.
 * Aquesta classe ens permet guardar i carregar les dades del sistema.
 * @author MarcosMartinezMartinez
 */
public class CtrlPersistencia {

    /**
     * Atributs de la classe:
     * factoriaAd: Factoria d'adaptadors de la capa de persistencia
     */
    private FactoriaAd factoriaAd ;

    /**
     * Constructora per defecte.
     * S'encarrega d'inicialitzar la factoria d'adaptadors.
     */
    public CtrlPersistencia() {
        factoriaAd = FactoriaAd.getInstance();
    }

    /**
     * Mètode que carrega al programa el cataleg guardat al sistema amb l'identificador "id".
     * @param id Identificador del cataleg
     * @return Cataleg guardat al sistema amb identificador "id"
     */
    public Map<Integer, Producte> getCataleg(String id) {
        Map<Integer, Producte> cataleg = factoriaAd.getCatalegAd().getCataleg(id);
        return cataleg;
    }

    /**
     * Mètode que carrega al programa les relacions guardades al sistema amb l'identificador "id".
     * @param id Identificador de les relacions
     * @return Relacions guardades al sistema amb identificador "id"
     */
    public Map<Pair<Integer, Integer>, Integer> getRelacio(String id) {
        Map<Pair<Integer, Integer>, Integer> relacions = factoriaAd.getRelacioAd().getRelacio(id);
        return relacions;
    }

    /**
     * Mètode que carrega al programa l'altura guardada al sistema amb l'identificador "id".
     * @param id Identificador de l'altura
     * @return Altura guardada al sistema amb identificador "id"
     */
    public int getAltura(String id) {
        int altura = factoriaAd.getPrestatgeriaAd().getAltura(id);
        return altura;
    }

    /**
     * Mètode que carrega al programa la distribució guardada al sistema amb l'identificador "id".
     * @param id Identificador de la distribució
     * @return Distribució guardada al sistema amb identificador "id"
     */
    public Vector<Integer> getDistribucio(String id) {
        Vector<Integer> distribucio = factoriaAd.getPrestatgeriaAd().getDistribucio(id);
        return distribucio;
    }

    /**
     * Mètode que retorna si existeix un estat del programa guardat al sistema amb l'identificador "id".
     * @param id Identificador de les dades
     * @return Cert si existeix l'estat del programa amb identificador "id" al sistema, fals altrament
     */
    public boolean existId(String id) {
        boolean res = false;
        res = factoriaAd.getCatalegAd().existCataleg(id);
        res = res && factoriaAd.getRelacioAd().existRelacio(id);
        res = res && factoriaAd.getPrestatgeriaAd().existAltura(id);
        res = res && factoriaAd.getPrestatgeriaAd().existDistribucio(id);
        return res;
    }

    /**
     * Mètode que guarda a un fitxer tots els estats guardats al programa.
     * @param nomFitxer Nom del fitxer on es guardaran les dades
     * @throws IOException Llança una excepció si no es pot guardar el fitxer
     */
    public void guardarSistema(String nomFitxer) throws IOException {
        factoriaAd.getCatalegAd().guardarCatalegs(nomFitxer);
        factoriaAd.getRelacioAd().guardarRelacions(nomFitxer);
        factoriaAd.getPrestatgeriaAd().guardarAltures(nomFitxer);
        factoriaAd.getPrestatgeriaAd().guardarDistribucions(nomFitxer);
    }

    /**
     * Mètode que carrega del fitxer amb nom "nomFitxer" tots els estats del programa que conte.
     * @param nomFitxer Nom del fitxer on es guarden les dades
     * @throws IOException Llança una excepció si no es poden carregar les dades
     */
    public void carregarSistema(String nomFitxer) throws IOException {
        factoriaAd.getCatalegAd().carregarCatalegs(nomFitxer);
        factoriaAd.getRelacioAd().carregarRelacions(nomFitxer);
        factoriaAd.getPrestatgeriaAd().carregarAltures(nomFitxer);
        factoriaAd.getPrestatgeriaAd().carregarDistribucions(nomFitxer);
    }

    /**
     * Mètode que guarda al programa el cataleg, les relacions, l'altura i la distribució amb identificador "id".
     * @param id Identificador de les dades
     * @param altura Altura de la prestatgeria
     * @param distribucio Distribució de la prestatgeria
     * @param cataleg Cataleg de productes
     * @param relacio Relacions entre productes
     */
    public void guardarTot(String id, int altura, Vector<Integer> distribucio, Map<Integer, Producte> cataleg, Map<Pair<Integer, Integer>, Integer> relacio) {
        factoriaAd.getCatalegAd().guardarCataleg(id, cataleg);
        factoriaAd.getRelacioAd().guardarRelacio(id, relacio);
        factoriaAd.getPrestatgeriaAd().guardarAltura(id, altura);
        factoriaAd.getPrestatgeriaAd().guardarDistribucio(id, distribucio);
    }

    /**
     * Mètode que guarda a un fitxer amb nom "nomFitxer" el cataleg, les relacions, l'altura i la distribució.
     * @param nomFitxer Nom del fitxer on es guardaran les dades
     * @param altura Altura de la prestatgeria
     * @param distribucio Distribució de la prestatgeria
     * @param cataleg Cataleg de productes
     * @param relacio Relacions entre productes
     * @throws IOException Llança una excepció si no es poden guardar les dades
     */
    public void guardarTotFitxer(String nomFitxer, int altura, Vector<Integer> distribucio, Map<Integer, Producte> cataleg, Map<Pair<Integer, Integer>, Integer> relacio) throws IOException {
        factoriaAd.getCatalegAd().guardarCatalegFitxer(nomFitxer, cataleg);
        factoriaAd.getRelacioAd().guardarRelacioFitxer(nomFitxer, relacio);
        factoriaAd.getPrestatgeriaAd().guardarAlturaFitxer(nomFitxer, altura);
        factoriaAd.getPrestatgeriaAd().guardarDistribucioFitxer(nomFitxer, distribucio);
    }

    /**
     * Mètode que carrega del fitxer amb nom "nomFitxer" el cataleg de productes.
     * @param nomFitxer Nom del fitxer on es guarden les dades
     * @return Retorna el cataleg de productes guardat al fitxer
     * @throws IOException Llança una excepció si no es poden carregar les dades
     */
    public Map<Integer, Producte> getCatalegFitxer(String nomFitxer) throws IOException {
        Map<Integer, Producte> cataleg = factoriaAd.getCatalegAd().getCatalegFitxer(nomFitxer);
        return cataleg;
    }

    /**
     * Mètode que carrega del fitxer amb nom "nomFitxer" les relacions entre productes.
     * @param nomFitxer Nom del fitxer on es guarden les dades
     * @return Retorna les relacions entre productes guardades al fitxer
     * @throws IOException Llança una excepció si no es poden carregar les dades
     */
    public Map<Pair<Integer, Integer>, Integer> getRelacioFitxer(String nomFitxer) throws IOException {
        Map<Pair<Integer, Integer>, Integer> relacio = factoriaAd.getRelacioAd().getRelacioFitxer(nomFitxer);
        return relacio;
    }

    /**
     * Mètode que carrega del fitxer amb nom "nomFitxer" l'altura de la prestatgeria.
     * @param nomFitxer Nom del fitxer on es guarden les dades
     * @return Retorna l'altura de la prestatgeria guardada al fitxer
     * @throws IOException Llança una excepció si no es poden carregar les dades
     */
    public int getAlturaFitxer(String nomFitxer) throws IOException {
        int altura = factoriaAd.getPrestatgeriaAd().getAlturaFitxer(nomFitxer);
        return altura;
    }

    /**
     * Mètode que carrega del fitxer amb nom "nomFitxer" la distribució de la prestatgeria.
     * @param nomFitxer Nom del fitxer on es guarden les dades
     * @return Retorna la distribució de la prestatgeria guardada al fitxer
     * @throws IOException Llança una excepció si no es poden carregar les dades
     */
    public Vector<Integer> getDistribucioFitxer(String nomFitxer) throws IOException {
        Vector<Integer> distribucio = factoriaAd.getPrestatgeriaAd().getDistribucioFitxer(nomFitxer);
        return distribucio;
    }
}
