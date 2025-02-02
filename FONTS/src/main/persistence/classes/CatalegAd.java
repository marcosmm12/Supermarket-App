package main.persistence.classes;

import java.util.Map;
import main.domain.classes.Producte;
import java.io.IOException;

/**
 * Aquesta classe és una interfície que conté els mètodes que s'han d'implementar a qualsevol classe que actuï com a base de dades del catàleg de productes.
 * Aquesta interfície conté mètodes per obtenir un catàleg, guardar-ne un, verificar-ne si existeix un, i guardar i carregar l'estructura on es guarden tots els catalegs.
 * @author DavidComino
 * @author keinthdc
 */ 

 public interface CatalegAd {
    
    /**
     * Obte un catàleg complet
     * @return Mapa amb els productes i els seus identificadors
     */
    public Map<Integer, Producte> getCataleg(String id);
    
    /**
     * Guarda un cataleg al programa
     * @param cataleg Mapa amb els productes i els seus identificadors
     */
    public void guardarCataleg(String id, Map<Integer, Producte> cataleg);
    
    /**
     * Verifica si existeix un catàleg amb l'identificador especificat
     * @param id Identificador del cataleg
     * @return true si existeix, false en cas contrari
     */
    public boolean existCataleg(String id);
    
    /**
     * Carrega els catalegs des d'un fitxer
     * @param nomFitxer nom del fitxer on es guarden els catalegs
     */
    public void carregarCatalegs(String nomFitxer) throws IOException;
    
    /**
     * Guarda els catalegs en un fitxer
     * @param nomFitxer nom del fitxer on es guardaran els catalegs
     */
    public void guardarCatalegs(String nomFitxer) throws IOException;
    
    /**
     * Obte un catàleg complet d'un fitxer
     * @param nomFitxer nom del fitxer
     * @return Mapa amb els productes i els seus identificadors
     */
    public Map<Integer, Producte> getCatalegFitxer(String nomFitxer) throws IOException;
    
    /**
     * Guarda un cataleg en un fitxer
     * @param nomFitxer nom del fitxer
     * @param cataleg Mapa amb els productes i els seus identificadors
     */
    public void guardarCatalegFitxer(String nomFitxer, Map<Integer, Producte> cataleg) throws IOException;
}
