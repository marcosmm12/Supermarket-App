package main.domain.exceptions;

/**
 * Excepció que es llança quan el producte no existeix
 * @author MarcosMartinezMartinez
 */
public class ExcepcioProducteNoExisteix extends Exception {
  /**
   * Atributs de la classe
   * id: id del producte que no existeix
   */
  private Integer id;

  /**
   * Constructor de la classe
   * @param id id del producte que no existeix
   */
  public ExcepcioProducteNoExisteix(Integer id) {
      super("El producte amb id " + id + " no existeix");
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
