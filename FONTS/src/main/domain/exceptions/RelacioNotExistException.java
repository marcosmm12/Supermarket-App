package main.domain.exceptions;

/**
 * Excepció que es llança quan la relació no existeix
 * @author keinthdc
 */
public class RelacioNotExistException extends Exception {
    /**
     * Constructor de la classe
     * @param message missatge de l'excepció
     */
    public RelacioNotExistException(String message) {
        super(message);
    }
}
