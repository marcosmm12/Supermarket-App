package main.presentation.controllers;

import main.domain.exceptions.*;
import main.domain.libs.Pair;
import main.presentation.classes.*;
import main.domain.classes.Producte;
import main.domain.controllers.CtrlDomain;

import javax.swing.*;
import java.util.List;
import java.util.Vector;
import java.util.Map;

/**
 * Aquesta classe és el controlador de presentació
 * Aquesta classe es l'encarregada de gestionar les vistes i la comunicació amb el controlador de domini.
 * @author MarcosMartinezMartinez
 * @author keinthdc
 */
public class CtrlPresentacio {
    /**
     * Atributs
     * ctrlDomain: Controlador de domini
     * vistaPrincipal: Vista principal
     */
    private CtrlDomain ctrlDomain;
    private vistaPrincipal vistaPrincipal;

    /**
     * Mètode principal
     * @param args
     */
    public static void main(String[] args) {
        CtrlPresentacio ctrlPresentacio = new CtrlPresentacio();
    }

    /**
     * Constructora que inicialitza el controlador de domini i la vista d'inici
     */
    public CtrlPresentacio() {
        ctrlDomain = new CtrlDomain();
        vistaInici vistaInici = new vistaInici(this);
        vistaInici.setVisible(true);
    }

    /**
     * Mètode per canviar de vista
     * @param vista Nom de la vista a la que es vol canviar
     */
    public void canviaVista(String vista) {
        switch(vista) {
            case "Vista_Inici":
                vistaInici vistaInici = new vistaInici(this);
                vistaInici.setVisible(true);
                break;
            case "Vista_Principal":
                vistaPrincipal = new vistaPrincipal(this);
                vistaPrincipal.setVisible(true);
                break;
            default:
                break;
        }
    }

    /**
     * Mètode per canviar l'algorisme de calcul de la distribucio a algorisme de força bruta
     */
    public void canviaAFBruta() {
        try {
            ctrlDomain.escollirAlgoritmeForcaBruta();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Mètode per canviar l'algorisme de calcul de la distribucio a algorisme d'aproximacio
     */
    public void canviaAAproximacio() {
        try {
            ctrlDomain.escollirAlgoritmeAproximacio();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Metode per afegir un producte
     * @param idInt Identificador del producte
     * @param nom Nom del producte
     * @param preuInt Preu del producte
     * @param relacions Relacions del producte amb altres productes
     * @throws ExcepcioProducteNoExisteix
     * @throws ExcepcioProducteJaExisteix
     * @throws ExcepcioCatalegBuit
     */
    public void afegirProducte(Integer idInt, String nom, Integer preuInt, List<Pair<Integer, Integer>> relacions) throws ExcepcioProducteNoExisteix, ExcepcioProducteJaExisteix, ExcepcioCatalegBuit {
        ctrlDomain.afegirProducte(idInt, nom, preuInt, relacions);
    }

    /**
     * Metode per agafar tots els identificadors dels productes
     * @return Vector amb tots els identificadors dels productes
     * @throws ExcepcioCatalegBuit
     */
    public Vector<Integer> agafaIdsProductes() throws ExcepcioCatalegBuit {
        Vector<Integer> ids = ctrlDomain.getDistribucio();
        if (ids.size() == 0) throw new ExcepcioCatalegBuit();
        return ids;
    }

    /**
     * Metode per agafar totes les relacions dels productes
     * @return Map amb totes les relacions dels productes
     * @throws ExcepcioCatalegBuit
     */
    public Map<Pair<Integer, Integer>, Integer> agafaRelacions() throws ExcepcioCatalegBuit {
        return ctrlDomain.getRelacions();
    }

    /**
     * Metode per modificar una relacio
     * @param firstVal Identificador del primer producte
     * @param secondVal Identificador del segon producte
     * @param value Valor de la relacio
     * @throws RelacioNotExistException
     * @throws ExcepcioProducteNoExisteix
     * @throws ExcepcioCatalegBuit
     */
    public void modificarRelacio(Integer firstVal, Integer secondVal, int value) throws RelacioNotExistException, ExcepcioProducteNoExisteix, ExcepcioCatalegBuit {
        ctrlDomain.modificarRelacio(firstVal, secondVal, value);
    }

    /**
     * Metode per eliminar un producte
     * @param id Identificador del producte a eliminar
     * @throws RelacioNotExistException
     * @throws ExcepcioProducteNoExisteix
     * @throws ExcepcioCatalegBuit
     */
    public void eliminarProducte(int id) throws ExcepcioProducteNoExisteix, ExcepcioCatalegBuit {
        ctrlDomain.eliminarProducte(id);
    }

    /**
     * Metode per obtenir el cataleg de productes
     * @return Map amb tots els productes
     * @throws ExcepcioCatalegBuit
     */
    public Map<Integer, Producte> getCataleg() throws ExcepcioCatalegBuit {
        return ctrlDomain.getCataleg();
    }

    /**
     * Metode per obtenir un producte
     * @param id Identificador del producte
     * @return Producte amb l'identificador id
     * @throws ExcepcioProducteNoExisteix
     * @throws ExcepcioCatalegBuit
     */
    public Producte getProducte(int id) throws ExcepcioProducteNoExisteix, ExcepcioCatalegBuit {
        return ctrlDomain.getProducte(id);
    }

    /**
     * Metode per modificar l'id d'un producte
     * @param id Identificador del producte
     * @param idPEditat Nou identificador del producte
     * @throws ExcepcioProducteNoExisteix
     * @throws ExcepcioCatalegBuit
     */
    public void modificarIdProducte(int id, int idPEditat) throws RelacioNotExistException, ExcepcioProducteNoExisteix, ExcepcioIdJaEstaEnUs, ExcepcioCatalegBuit {
        ctrlDomain.modificarIdProducte(id, idPEditat);
    }

    /**
     * Metode per modificar el nom d'un producte
     * @param id Identificador del producte
     * @param nomProd Nou nom del producte
     * @throws ExcepcioProducteNoExisteix
     * @throws ExcepcioCatalegBuit
     */
    public void modificarNomProducte(int id, String nomProd) throws ExcepcioProducteNoExisteix, ExcepcioCatalegBuit {
        ctrlDomain.modificarNomProducte(id, nomProd);
    }

    /**
     * Metode per modificar el preu d'un producte
     * @param id Identificador del producte
     * @param preuPEditat Nou preu del producte
     * @throws ExcepcioProducteNoExisteix
     * @throws ExcepcioCatalegBuit
     */
    public void modificarPreuProducte(int id, int preuPEditat) throws ExcepcioProducteNoExisteix, ExcepcioCatalegBuit {
        ctrlDomain.modificarPreuProducte(id, preuPEditat);
    }

    /**
     * Metode per modificar la posicio de dos productes
     * @param id1 Identificador del primer producte
     * @param id2 Identificador del segon producte
     * @throws ExcepcioProducteNoExisteix
     * @throws ExcepcioCatalegBuit
     */
    public void modificarDistribucio(Integer id1, Integer id2) throws ExcepcioProducteNoExisteix, ExcepcioCatalegBuit {
        ctrlDomain.modificarDistribucio(id1, id2);
    }

    /**
     * Metode per carregar al programa l'estat guardat al programa amb nom "nomDist"
     * @param nomDist Nom de l'estat a carregar
     * @throws ExcepcioNoExisteixDistribucio
     */
    public void carregarDistribucio(String nomDist) throws ExcepcioNoExisteixDistribucio {
        ctrlDomain.carregarDistribucio(nomDist);
    }

    /**
     * Metode per carregar al programa l'estat guardat al fitxer amb nom "nomFitxer"
     * @param nomFitxer Nom del fitxer a carregar
     * @throws Exception
     */
    public void carregarDistribucioFitxer(String nomFitxer) throws Exception {
        ctrlDomain.carregarDistribucioFitxer(nomFitxer);
        JOptionPane.showMessageDialog(null, "Dades carregades correctament", "Èxit", JOptionPane.INFORMATION_MESSAGE);
        actualitzaProductes();
    }

    /**
     * Metode per iniciar les dades del programa amb les que hi ha al fitxer amb nom "nomFitxer"
     * @param nomFitxer Nom del fitxer a carregar
     * @throws Exception
     */
    public void carregarSistemaFitxer(String nomFitxer) throws Exception {
        ctrlDomain.carregarSistema(nomFitxer);
        JOptionPane.showMessageDialog(null, "Dades carregades correctament", "Èxit", JOptionPane.INFORMATION_MESSAGE);
        actualitzaProductes();
    }

    /**
     * Metode per guardar l'estat actual del programa amb nom "nomDist"
     * @param nomDist Nom de l'estat a guardar
     * @throws ExcepcioJaExisteixDistribucio
     * @throws ExcepcioCatalegBuit
     */
    public void guardarDistribucio(String nomDist) throws ExcepcioJaExisteixDistribucio, ExcepcioCatalegBuit {
        ctrlDomain.guardarDistribucio(nomDist);
    }

    /**
     * Metode per guardar l'estat actual del programa al fitxer amb nom "nomFitxer"
     * @param nomFitxer Nom del fitxer a guardar
     * @throws Exception
     */
    public void guardarDistribucioFitxer(String nomFitxer) throws Exception {
        ctrlDomain.guardarDistribucioFitxer(nomFitxer);
        JOptionPane.showMessageDialog(null, "Dades guardades correctament", "Èxit", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Metode per guardar les dades del programa al fitxer amb identificador "nomFitxer"
     * @param nomFitxer Nom del fitxer a guardar
     * @throws Exception
     */
    public void guardarSistemaFitxer(String nomFitxer) throws Exception {
        ctrlDomain.guardarSistema(nomFitxer);
        JOptionPane.showMessageDialog(null, "Dades guardades correctament", "Èxit", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Metode per obtenir l'altura de la distribucio actual
     * @return Altura de la distribucio actual
     */
    public int getAltura() {
        return ctrlDomain.getAltura();
    }

    /**
     * Metode per obtenir la distribucio actual
     * @return Vector amb la distribucio actual
     * @throws ExcepcioCatalegBuit
     */
    public Vector<Integer> getDistribucio() throws ExcepcioCatalegBuit {
        return ctrlDomain.getDistribucio();
    }

    /**
     * Metode per actualitzar els productes mostrats a la vistaPrincipal
     */
    public void actualitzaProductes() {
        vistaPrincipal.actualitzaProductes();
    }
}
