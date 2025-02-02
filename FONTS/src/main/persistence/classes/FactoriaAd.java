package main.persistence.classes;

/**
 * Aquesta classe és la factoria de les classes d'accés a dades.
 * Aquesta classe és un singleton, per lo que només hi haurà una instància d'aquesta classe.
 * Aquesta classe és la que s'encarrega de crear i retornar les instàncies de les classes d'accés a dades.
 * @author MarcosMartinezMartinez
 */
public class FactoriaAd {
    /**
     * Atributs de la classe:
     * instance: Instància de la classe FactoriaAd.
     * prestatgeriaAd: Instància de la classe PrestatgeriaAd.
     * catalegAd: Instància de la classe CatalegAd.
     * relacioAd: Instància de la classe RelacioAd.
     */
    private static FactoriaAd instance = null;
    private PrestatgeriaAd prestatgeriaAd;
    private CatalegAd catalegAd;
    private RelacioAd relacioAd;

    /**
     * Constructor privat de la classe FactoriaAd.
     */
    private FactoriaAd() {
    }

    /**
     * Mètode que retorna la instància de la classe FactoriaAd.
     * @return Instància de la classe FactoriaAd.
     */
    //Aquesta factoria es un singleton, per lo que necessitem aquesta funció
    public static FactoriaAd getInstance() {
        if (instance == null) instance = new FactoriaAd();
        return instance;
    }

    //Getters de les interfícies de la BD

    /**
     * Mètode que retorna la instància de la classe CatalegAd.
     * @return Instància de la classe CatalegAd.
     */
    public CatalegAd getCatalegAd() {
        if (catalegAd == null) catalegAd = new CatalegBD();
        return catalegAd;
    }

    /**
     * Mètode que retorna la instància de la classe PrestatgeriaAd.
     * @return Instància de la classe PrestatgeriaAd.
     */
    public PrestatgeriaAd getPrestatgeriaAd() {
        if (prestatgeriaAd == null) prestatgeriaAd = new PrestatgeriaBD();
        return prestatgeriaAd;
    }

    /**
     * Mètode que retorna la instància de la classe RelacioAd.
     * @return Instància de la classe RelacioAd.
     */
    public RelacioAd getRelacioAd() {
        if (relacioAd == null) relacioAd = new RelacioBD();
        return relacioAd;
    }
}
