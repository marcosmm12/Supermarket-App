package main.domain.exceptions;

/**
 * Excepció que es llança quan el id ja està sent utilitzat
 * @author MarcosMartinezMartinez
 */
public class ExcepcioIdJaEstaEnUs extends Exception {
    /**
     * Atributs de la classe
     * id: id que ja està sent utilitzat
     */
    private Integer id;

    /**
     * Constructor de la classe
     * @param id id que ja està sent utilitzat
     */
    public ExcepcioIdJaEstaEnUs(Integer id) {
        super("El id" + id + " ja esta sent utilitzat.");
        this.id = id;
    }

    /**
     * Mètode que retorna el missatge de l'excepció
     * @return missatge de l'excepció
     */
    public String toString() {
        return "Excepcio Producte " + id;
    }
}

