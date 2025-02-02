package main.domain.exceptions;

/**
 * Excepció que es llança quan el producte ja existeix
 * @author MarcosMartinezMartinez
 */
public class ExcepcioProducteJaExisteix extends Exception {
  /**
   * Atributs de la classe
   * id: id del producte que ja existeix
   */
  private Integer id;

  /**
   * Constructor de la classe
   * @param id id del producte que ja existeix
   */
  public ExcepcioProducteJaExisteix(Integer id) {
      super("El producte amb id " + id + " ja existeix");
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
