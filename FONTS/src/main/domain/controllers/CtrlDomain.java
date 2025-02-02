package main.domain.controllers;

import main.domain.classes.Prestatgeria;
import main.domain.classes.Producte;
import main.domain.classes.algoritmeAproximacio;
import main.domain.exceptions.*;
import main.domain.libs.Pair;

import main.persistence.controllers.CtrlPersistencia;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Vector;

/**
 * Aquesta classe es un controlador de domini que gestiona tot el domini del programa.
 * Aquesta classe permet gestionar el cataleg de productes, la distribucio dels productes i la persistencia de les dades.
 * @author MarcosMartinezMartinez
 */
public class CtrlDomain {
    /**
     * Atributs de la classe CtrlDomain
     * distribucio: Controlador de la distribucio dels productes
     * cataleg: Controlador del cataleg de productes
     * ctrlPersistencia: Controlador de la persistencia de les dades
     * */
    //Atributs
    private CtrlDistribucio distribucio;
    private CtrlCataleg cataleg;
    private CtrlPersistencia ctrlPersistencia;

    /**
     * Constructora de la classe CtrlDomain
     */
    //Constructora
    public CtrlDomain() {
        this.cataleg = new CtrlCataleg();
        this.distribucio = new CtrlDistribucio(cataleg, new algoritmeAproximacio(), new Prestatgeria());
        this.ctrlPersistencia = new CtrlPersistencia();
    }

    /**
     * Consultora del cataleg de productes
     * @return Retorna el cataleg de productes
     * @throws ExcepcioCatalegBuit Es genera si el cataleg de productes esta buit
     */
    //Consultar del cataleg
    public Map<Integer, Producte> getCataleg() throws ExcepcioCatalegBuit {
        return cataleg.getCataleg();
    }

    /**
     * Consultora del producte del cataleg amb id "id"
     * @param id Identificador del producte
     * @return El producte amb id "id"
     * @throws ExcepcioProducteNoExisteix
     * @throws ExcepcioCatalegBuit
     */
    //Consultar un producte del cataleg
    public Producte getProducte(int id) throws ExcepcioProducteNoExisteix, ExcepcioCatalegBuit {
        return cataleg.getProducte(id);
    }

    /**
     * Consultora del id del producte amb id "id"
     * @param id Identificador del producte
     * @return L'id del producte amb id "id"
     * @throws ExcepcioProducteNoExisteix
     */
    public Integer getProducteId(int id) throws ExcepcioProducteNoExisteix {
        return cataleg.getProducteId(id);
    }

    /**
     * Consultora del nom del producte amb id "id"
     * @param id Identificador del producte
     * @return El nom del producte amb id "id"
     * @throws ExcepcioProducteNoExisteix
     */
    public String getProducteNom(int id) throws ExcepcioProducteNoExisteix {
        return cataleg.getProducteNom(id);
    }

    /**
     * Consultora del preu del producte amb id "id"
     * @param id Identificador del producte
     * @return El preu del producte amb id "id"
     * @throws ExcepcioProducteNoExisteix
     */
    public Integer getProductePreu(int id) throws ExcepcioProducteNoExisteix {
        return cataleg.getProductePreu(id);
    }

    /**
     * Consultora de si el producte amb id "id" existeix al cataleg
     * @param id Identificador del producte
     * @return Cert si el producte amb id "id" existeix al cataleg, fals altrament
     */
    //Consultar si existeix un producte al cataleg
    public boolean existProducte(int id) {
        return cataleg.existProducte(id);
    }

    /**
     * Consultora de les relacions del cataleg
     * @return Les relacions del cataleg
     * @throws ExcepcioCatalegBuit
     */
    //Consultar les relacions del cataleg
    public Map<Pair<Integer, Integer>, Integer> getRelacions() throws ExcepcioCatalegBuit {
        return cataleg.getRelacions();
    }

    /**
     * Consultora de la relacio entre els productes amb id "id1" i "id2"
     * @param id1 Identificador del primer producte
     * @param id2 Identificador del segon producte
     * @return La relacio entre els productes amb id "id1" i "id2"
     * @throws RelacioNotExistException
     * @throws ExcepcioCatalegBuit
     */
    //Consultar una relacio del cataleg
    public Integer getRelacio(int id1, int id2) throws RelacioNotExistException, ExcepcioCatalegBuit {
        return cataleg.getRelacio(id1, id2);
    }

    /**
     * Consultora de la distribucio actual
     * @return La distribucio actual
     * @throws ExcepcioCatalegBuit
     */
    //Consultar la distribucio actual
    public Vector<Integer> getDistribucio() throws ExcepcioCatalegBuit{
        return distribucio.getDistribucio();
    }

    /**
     * Consultora de l'altura de la distribucio actual
     * @return L'altura de la distribucio actual
     */
    //Consultar l'altura de la distribucio actual
    public int getAltura() {
        return distribucio.getAltura();
    }

    /**
     * Afegir un producte al cataleg
     * @param id Identificador del producte
     * @param nom Nom del producte
     * @param preu Preu del producte
     * @param relacions Relacions del producte amb altres productes
     * @throws ExcepcioProducteJaExisteix
     * @throws ExcepcioProducteNoExisteix
     * @throws ExcepcioCatalegBuit
     */
    //Afegir un producte al cataleg
    public void afegirProducte(Integer id, String nom, Integer preu, List<Pair<Integer, Integer>> relacions) throws ExcepcioProducteJaExisteix, ExcepcioProducteNoExisteix, ExcepcioCatalegBuit {
        cataleg.afegeixProducte(id, nom, preu, relacions);
        //CAL APLICAR L'ALGORITME DE NOU PER QUE S'ACTUALITZI LA DISTRIBUCIÓ
        distribucio.distribuirProductes();
    }

    /**
     * Eliminar un producte del cataleg
     * @param id Identificador del producte
     * @throws ExcepcioProducteNoExisteix
     * @throws ExcepcioCatalegBuit
     */
    //Eliminar un producte del cataleg
    public void eliminarProducte(Integer id) throws ExcepcioProducteNoExisteix, ExcepcioCatalegBuit {
        cataleg.eliminarProducte(id);
        //CAL APLICAR L'ALGORITME DE NOU PER QUE S'ACTUALITZI LA DISTRIBUCIÓ PERO NO CAL SI ESTA BUIT
       distribucio.distribuirProductes();
    }

    /**
     * Modificar id d'un producte del cataleg
     * @param id Identificador del producte
     * @param nouId Nou identificador del producte
     * @throws ExcepcioProducteNoExisteix
     * @throws ExcepcioIdJaEstaEnUs
     * @throws RelacioNotExistException
     * @throws ExcepcioCatalegBuit
     */
    //Modificar id d'un producte del cataleg
    public void modificarIdProducte(Integer id, Integer nouId) throws ExcepcioProducteNoExisteix, ExcepcioIdJaEstaEnUs, RelacioNotExistException, ExcepcioCatalegBuit {
        cataleg.modificarIdProducte(id, nouId);
        //CAL APLICAR L'ALGORITME DE NOU PER QUE S'ACTUALITZI LA DISTRIBUCIÓ
        distribucio.distribuirProductes();
    }

    /**
     * Modificar nom d'un producte del cataleg
     * @param id Identificador del producte
     * @param nouNom Nou nom del producte
     * @throws ExcepcioProducteNoExisteix
     * @throws ExcepcioCatalegBuit
     */
    //Modificar nom d'un producte del cataleg
    public void modificarNomProducte(Integer id, String nouNom) throws ExcepcioProducteNoExisteix, ExcepcioCatalegBuit {
        cataleg.modificarNomProducte(id, nouNom);
    }

    /**
     * Modificar preu d'un producte del cataleg
     * @param id Identificador del producte
     * @param nouPreu Nou preu del producte
     * @throws ExcepcioProducteNoExisteix
     * @throws ExcepcioCatalegBuit
     */
    //Modificar preu d'un producte del cataleg
    public void modificarPreuProducte(Integer id, Integer nouPreu) throws ExcepcioProducteNoExisteix, ExcepcioCatalegBuit {
        cataleg.modificarPreuProducte(id, nouPreu);
    }

    /**
     * Modificar relacio entre dos productes del cataleg
     * @param id1 Identificador del primer producte
     * @param id2 Identificador del segon producte
     * @param relacio Relacio entre els dos productes
     * @throws ExcepcioProducteNoExisteix
     * @throws RelacioNotExistException
     * @throws ExcepcioCatalegBuit
     */
    //Modificar la relacio entre dos productes del cataleg
    public void modificarRelacio(Integer id1, Integer id2, Integer relacio) throws ExcepcioProducteNoExisteix, RelacioNotExistException, ExcepcioCatalegBuit {
        cataleg.modificarRelacio(id1, id2, relacio);
        //CAL APLICAR L'ALGORITME DE NOU PER QUE S'ACTUALITZI LA DISTRIBUCIÓ
        distribucio.distribuirProductes();
    }

    /**
     * Canviar l'algorisme de calcul de la distribucio a algorisme de força bruta
     * @throws ExcepcioCatalegBuit
     */
    //Canviar a algorisme de força bruta
    public void escollirAlgoritmeForcaBruta() throws ExcepcioCatalegBuit {
        distribucio.escollirAlgoritmeForcaBruta();
    }

    /**
     * Canviar l'algorisme de calcul de la distribucio a algorisme d'aproximacio
     * @throws ExcepcioCatalegBuit
     */
    //Canviar a algorisme d'aproximacio
    public void escollirAlgoritmeAproximacio()throws ExcepcioCatalegBuit {
        distribucio.escollirAlgoritmeAproximacio();
    }

    /**
     * Canviar la posicio de dos productes a la prestatgeria
     * @param id1 Identificador del primer producte
     * @param id2 Identificador del segon producte
     * @throws ExcepcioProducteNoExisteix
     * @throws ExcepcioCatalegBuit
     */
    //Modificar la distribucio actual
    public void modificarDistribucio(int id1, int id2) throws ExcepcioProducteNoExisteix, ExcepcioCatalegBuit {
        distribucio.modificarDistribucio(id1, id2);
    }

    /**
     * Guardar la distribucio actual al programa amb identificador "id"
     * @param id Identificador de la distribucio
     * @throws ExcepcioJaExisteixDistribucio
     * @throws ExcepcioCatalegBuit
     */
    //Guardar una distribucio
    public void guardarDistribucio(String id) throws ExcepcioJaExisteixDistribucio, ExcepcioCatalegBuit {
        if (!ctrlPersistencia.existId(id)) {
            //Obtenir tots els atributs a guardar
            Map<Integer, Producte> c = new HashMap<Integer, Producte>(cataleg.getCataleg());
            Map<Pair<Integer, Integer>, Integer> r = new HashMap<Pair<Integer, Integer>, Integer>(cataleg.getRelacions());
            Vector<Integer> distribucio = this.distribucio.getDistribucio();
            int altura = this.distribucio.getAltura();
            //Guardar tots els atributs de la distribucio en les estructures amb l'identificador "id"
            ctrlPersistencia.guardarTot(id, altura, distribucio, c, r);
        }
        else throw new ExcepcioJaExisteixDistribucio(id);
    }

    /**
     * Guardar la distribucio actual a un fitxer amb identificador "id"
     * @param id Identificador de la distribucio
     * @throws ExcepcioNoExisteixDistribucio
     */
    //Guardar una distribucio a un fitxer
    public void guardarDistribucioFitxer(String id) throws ExcepcioJaExisteixDistribucio, IOException, ExcepcioCatalegBuit {
            //Obtenir tots els atributs a guardar
            Map<Integer, Producte> c = new HashMap<Integer, Producte>(cataleg.getCataleg());
            Map<Pair<Integer, Integer>, Integer> r = new HashMap<Pair<Integer, Integer>, Integer>(cataleg.getRelacions());
            Vector<Integer> distribucio = this.distribucio.getDistribucio();
            int altura = this.distribucio.getAltura();
            //Guardar tots els atributs de la distribucio en les estructures amb l'identificador "id"
            ctrlPersistencia.guardarTotFitxer(id, altura, distribucio, c, r);
    }

    /**
     * Carregar una distribucio amb identificador "id"
     * @param id Identificador de la distribucio
     * @throws ExcepcioNoExisteixDistribucio
     */
    //Carregar una distribucio
    public void carregarDistribucio(String id) throws ExcepcioNoExisteixDistribucio {
        if (ctrlPersistencia.existId(id)) {
            //Obtenir tots els atributs a carregar
            Vector<Integer> distribucio = ctrlPersistencia.getDistribucio(id);
            int altura = ctrlPersistencia.getAltura(id);
            Map<Integer, Producte> c = new HashMap<Integer, Producte> (ctrlPersistencia.getCataleg(id));
            Map<Pair<Integer, Integer>, Integer> relacions = ctrlPersistencia.getRelacio(id);
            //Carregar tots els atributs
            this.distribucio.carregarDistribucio(distribucio, altura);
            cataleg.setCataleg(c);
            cataleg.setRelacions(relacions);
        }
        else throw new ExcepcioNoExisteixDistribucio(id);
    }

    /**
     * Carregar una distribucio d'un fitxer amb identificador "id"
     * @param id Identificador de la distribucio
     * @throws ExcepcioNoExisteixDistribucio
     * @throws IOException
     * @throws ClassNotFoundException
     */
    //Carregar una distribucio d'un fitxer
    public void carregarDistribucioFitxer(String id) throws ExcepcioJaExisteixDistribucio, IOException, ClassNotFoundException, ExcepcioNoExisteixDistribucio {
            //Obtenir tots els atributs a carregar
            Vector<Integer> distribucio = ctrlPersistencia.getDistribucioFitxer(id);
            int altura = ctrlPersistencia.getAlturaFitxer(id);
            Map<Integer, Producte> c = new HashMap<Integer, Producte> (ctrlPersistencia.getCatalegFitxer(id));
            Map<Pair<Integer, Integer>, Integer> relacions = ctrlPersistencia.getRelacioFitxer(id);
            //Carregar tots els atributs
            this.distribucio.carregarDistribucio(distribucio, altura);
            cataleg.setCataleg(c);
            cataleg.setRelacions(relacions);
    }

    /**
     * Iniciar les dades del programa amb les que hi ha al fitxer amb identificador "id"
     * @param id Identificador del fitxer
     * @throws IOException
     */
    public void carregarSistema(String id) throws IOException{
        ctrlPersistencia.carregarSistema(id);
    }

    /**
     * Guardar les dades del programa al fitxer amb identificador "id"
     * @param id Identificador del fitxer
     * @throws IOException
     */
    public void guardarSistema(String id) throws IOException{
        ctrlPersistencia.guardarSistema(id);
    }
}