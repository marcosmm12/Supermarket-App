package main.domain.classes;

import java.util.Map;
import java.util.Vector;

import main.domain.controllers.CtrlDistribucio;
import main.domain.libs.Pair;

/**
 * Interfície que defineix els mètodes que ha d'implementar un algoritme de distribució.
 *
 * Un algoritme de distribució és un algoritme que, donat un conjunt de relacions entre productes i una quantitat de productes a distribuir,
 * retorna una llista de productes distribuits segons les seves relacions.
 *
 * Aquesta interfície defineix un únic mètode, aplicarAlgoritme, que rep un Map<Pair<Integer, Integer>, Integer> relacions i un int quantitat_producte
 * i retorna un Vector<Integer> amb els productes distribuits.
 *
 * Les claus del Map<Pair<Integer, Integer>, Integer> relacions són parelles de productes, i el valor és la relacio entre els dos productes.
 *
 * El paràmetre int quantitat_producte és la quantitat de productes a distribuir.
 *
 * El Vector<Integer> retornat és una llista de productes ja distribuits (amb l'algorisme escollit).
 *
 * Aquesta interfície és implementada per les classes algoritmeAproximacio i AlgoritmeFBruta.
 *
 * @author MarcosMartinezMartinez
 *
 * @see algoritmeAproximacio
 * @see AlgoritmeFBruta
 * @see CtrlDistribucio
 */
public interface  Algoritme {

    /**
     * Mètode que aplica l'algoritme de calcul de la distribució.
     * @param relacions Relacions entre els productes del cataleg
     * @param quantitat_producte Quantitat de productes a distribuir
     * @return Vector<Integer> amb els productes distribuits
     */
    public Vector<Integer> aplicarAlgoritme(Map<Pair<Integer, Integer>, Integer> relacions, int quantitat_producte);
}
