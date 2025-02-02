package main.domain.libs;

import java.io.Serializable;

/**
 * Aquesta classe representa un parell de valors de tipus K i V.
 * @author keinthdc
 */
public class Pair<K extends Comparable<K>, V extends Comparable<V>> implements Comparable<Pair<K, V>>, Serializable {
    /**
     * Atributs de la classe Pair
     * firstVal: primer valor del parell
     * secondVal: segon valor del parell
     */
    private K firstVal;
    private V secondVal;

    /**
     * Constructor de la classe Pair
     * @param firstVal primer valor del parell
     * @param secondVal segon valor del parell
     */
    // Constructor
    public Pair(K firstVal, V secondVal) {
        this.firstVal = firstVal;
        this.secondVal = secondVal;
    }

    // Getters
    /**
     * Getter del primer valor del parell
     * @return primer valor del parell
     */
    public K getFirstVal() { return firstVal; }

    /**
     * Getter del segon valor del parell
     * @return segon valor del parell
     */
    public V getSecondVal() { return secondVal; }

    // Setters
    /**
     * Setter del primer valor del parell
     * @param firstVal nou valor del primer element del parell
     */
    public void setFirstVal(K firstVal) { this.firstVal = firstVal; }

    /**
     * Setter del segon valor del parell
     * @param secondVal nou valor del segon element del parell
     */
    public void setSecondVal(V secondVal) { this.secondVal = secondVal; }

    /**
     * Mètode que compara dos parells segons els seus valors
     * @param other parell a comparar
     * @return un enter negatiu, zero o positiu si el parell actual és menor, igual o major que el parell a comparar
     */
    // Implementación de compareTo
    @Override
    public int compareTo(Pair<K, V> other) {
        // Compara el primer valor
        int firstComparison = this.firstVal.compareTo(other.firstVal);
        if (firstComparison != 0)
            return firstComparison;
        // Si los primeros valores son iguales, compara el segundo valor
        return this.secondVal.compareTo(other.secondVal);
    }

    /**
     * Metode per calcular el hashCode del parell
     * @return un enter que representa la posicio del parell a la taula de hash
     */
    // Implementar hashCode y equals (importante para su uso en estructuras como TreeMap)
    @Override
    public int hashCode() {
        return firstVal.hashCode() * 31 + secondVal.hashCode();
    }

    /**
     * Mètode que compara dos parells segons els seus valors
     * @param obj objecte a comparar
     * @return true si els parells són iguals, false altrament
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) obj;
        return firstVal.equals(pair.firstVal) && secondVal.equals(pair.secondVal);
    }

    /**
     * Mètode que retorna una representació en format String del parell
     * @return un String amb la representació JSON del parell
     */
    // Sobrescribir el método toString para que se muestre de forma legible en el JSON
    @Override
    public String toString() {
        return String.format("{\"firstVal\":%s, \"secondVal\":%s}", firstVal, secondVal);
    }
}

