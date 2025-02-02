package main.domain.exceptions;

/**
 * Excepció que es llança quan ja existeix una relació entre dos elements
 * @author keinthdc
 */
public class RelacioExistException extends RuntimeException {
    /**
     * Constructor de la classe
     * @param message missatge de l'excepció
     */
    public RelacioExistException(String message) {
        super(message);
    }
}
