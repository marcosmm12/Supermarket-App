package main.domain.exceptions;

/**
 * Excepció que es llança quan no existeix cap distribució guardada amb el nom indicat
 * @author MarcosMartinezMartinez
 */
public class ExcepcioNoExisteixDistribucio extends Exception {
    /**
     * Atributs de la classe
     * id: nom de la distribució que no existeix
     */
    private String id;

    /**
     * Constructor de la classe
     * @param id nom de la distribució que no existeix
     */
    public ExcepcioNoExisteixDistribucio(String id) {
        super("No existeix cap distribució guardada amb el nom " + id);
        this.id = id;
    }

    /**
     * Mètode que retorna el missatge de l'excepció
     * @return missatge de l'excepció
     */
    public String toString() {
        return "Excepcio Distribucio " + id;
    }
}
