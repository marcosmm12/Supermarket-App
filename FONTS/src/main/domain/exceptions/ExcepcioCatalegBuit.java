package main.domain.exceptions;

/**
 * Excepció que es llança quan el catàleg de productes està buit
 * @author MarcosMartinezMartinez
 */
public class ExcepcioCatalegBuit extends Exception {

    /**
     * Constructor de la classe
     */
    public ExcepcioCatalegBuit () {
        super("El catàleg de productes està buit");
    }

    /**
     * Mètode que retorna el missatge de l'excepció
     * @return missatge de l'excepció
     */
    public String toString() {
        return "Excepcio Cataleg";
    }
}
