package main.persistence.classes;

import java.io.IOException;
import java.util.Vector;

/**
 * Aquesta classe és una interfície que permet gestionar la persistència de les dades de la prestatgeria.
 * Aquesta classe permet guardar i carregar les dades de la prestatgeria.
 * @author keinthdc
 */

 public interface PrestatgeriaAd {

    /**
     * Retorna l'altura de la prestatgeria amb identificador id.
     * @param id Identificador de la prestatgeria.
     * @return Retorna l'altura de la prestatgeria amb identificador id.
     */
    public Integer getAltura(String id);

    /**
     * Retorna si existeix l'altura de la prestatgeria amb identificador id.
     * @param id Identificador de la prestatgeria.
     * @return Retorna si existeix l'altura de la prestatgeria amb identificador id.
     */
    public boolean existAltura(String id);

    /**
     * Retorna la distribució de la prestatgeria amb identificador id.
     * @param id Identificador de la prestatgeria.
     * @return Retorna la distribució de la prestatgeria amb identificador id.
     */
    public Vector<Integer> getDistribucio(String id);

    /**
     * Retorna si existeix la distribució de la prestatgeria amb identificador id.
     * @param id Identificador de la prestatgeria.
     * @return Retorna si existeix la distribució de la prestatgeria amb identificador id.
     */
    public boolean existDistribucio(String id);

    /**
     * Guarda l'altura de la prestatgeria amb identificador id.
     * @param id Identificador de la prestatgeria.
     * @param altura Altura de la prestatgeria.
     */
    public void guardarAltura(String id, Integer altura);

    /**
     * Guarda la distribució de la prestatgeria amb identificador id.
     * @param id Identificador de la prestatgeria.
     * @param distribucio Distribució de la prestatgeria.
     */
    public void guardarDistribucio(String id, Vector<Integer> distribucio);

    /**
     * Carrega les altures de la prestatgeria des d'un fitxer.
     * @param nomFitxer Nom del fitxer on es troben les altures.
     * @throws IOException
     */
    public void carregarAltures(String nomFitxer) throws IOException;

    /**
     * Carrega les distribucions de la prestatgeria des d'un fitxer.
     * @param nomFitxer Nom del fitxer on es troben les distribucions.
     * @throws IOException
     */
    public void carregarDistribucions(String nomFitxer) throws IOException;

    /**
     * Guarda les altures de la prestatgeria en un fitxer.
     * @param nomFitxer Nom del fitxer on es guardaran les altures.
     * @throws IOException
     */
    public void guardarAltures(String nomFitxer) throws IOException;

    /**
     * Guarda les distribucions de la prestatgeria en un fitxer.
     * @param nomFitxer Nom del fitxer on es guardaran les distribucions.
     * @throws IOException
     */
    public void guardarDistribucions(String nomFitxer) throws IOException;

    /**
     * Retorna l'altura de la prestatgeria que es troba en el fitxer.
     * @param nomFitxer Nom del fitxer on es troba l'altura.
     * @return Retorna l'altura de la prestatgeria.
     * @throws IOException
     */
    public Integer getAlturaFitxer(String nomFitxer) throws IOException;

    /**
     * Retorna la distribució de la prestatgeria que es troba en el fitxer.
     * @param nomFitxer Nom del fitxer on es troba la distribució.
     * @return Retorna la distribució de la prestatgeria.
     * @throws IOException
     */
    public Vector<Integer> getDistribucioFitxer(String nomFitxer) throws IOException;

    /**
     * Guarda l'altura en un fitxer.
     * @param nomFitxer Nom del fitxer on es guardarà l'altura.
     * @param altura Altura de la prestatgeria.
     * @throws IOException
     */
    public void guardarAlturaFitxer(String nomFitxer, Integer altura) throws IOException;

    /**
     * Guarda la distribució en un fitxer.
     * @param nomFitxer Nom del fitxer on es guardarà la distribució.
     * @param distribucio Distribució de la prestatgeria.
     * @throws IOException
     */
    public void guardarDistribucioFitxer(String nomFitxer, Vector<Integer> distribucio) throws IOException;
}