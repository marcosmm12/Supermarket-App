package main.domain.exceptions;

/**
 * Excepció que es llança quan ja existeix una distribució amb el mateix nom
 * @author MarcosMartinezMartinez
 */
public class ExcepcioJaExisteixDistribucio extends Exception {
  /**
   * Atributs de la classe
   * id: identificador de la distribució
   */
  private String id;

  /**
   * Constructor de la classe
   * @param id identificador de la distribució
   */
  public ExcepcioJaExisteixDistribucio(String id) {
      super("Ja existeix una distribució guardada amb el nom " + id);
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
