package main.domain.classes;

import com.sun.nio.sctp.SctpStandardSocketOptions;
import main.domain.controllers.CtrlDistribucio;
import main.domain.libs.Pair;

import java.util.*;

/**
 * Classe que implementa la interfície {@link Algoritme}.
 * Aquesta classe implementa un algoritme de força bruta per calcular la distribució de productes.
 * @author keinthdc
 * @author MarcosMartinezMartinez
 */
public class AlgoritmeFBruta implements Algoritme {
    /**
     * Atributs de la classe:
     * n: Número de productes
     * path: Cami parcial
     * bestPath: Millor solució
     * millorCost: Millor cost trobat
     * M: Relacions entre productes
     */
    private int n;                                  // Número de productos
    private Vector<Integer> path;                   // Camino parcial
    private Vector<Integer> bestPath;               // Mejor solución
    private Integer millorCost;                     // Mejor costo encontrado
    private Map<Pair<Integer, Integer>, Integer> M; // Relaciones entre productos

    /**
     * Constructora per defecte de la classe.
     */
    public AlgoritmeFBruta() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector<Integer> aplicarAlgoritme(Map<Pair<Integer, Integer>, Integer> relacions, int quantitatProductes) {
        // Crear mapeo entre nodos originales y nodos consecutivos
        Map<Integer, Integer> nodoToIndex = new HashMap<>();
        Map<Integer, Integer> indexToNodo = new HashMap<>();
        int index = 0;

        for (Pair<Integer, Integer> par : relacions.keySet()) {
            if (!nodoToIndex.containsKey(par.getFirstVal())) {
                nodoToIndex.put(par.getFirstVal(), index);
                indexToNodo.put(index, par.getFirstVal());
                index++;
            }
            if (!nodoToIndex.containsKey(par.getSecondVal())) {
                nodoToIndex.put(par.getSecondVal(), index);
                indexToNodo.put(index, par.getSecondVal());
                index++;
            }
        }

        // Crear nueva matriz de relaciones con índices consecutivos
        Map<Pair<Integer, Integer>, Integer> relacionsIndex = new HashMap<>();
        for (Map.Entry<Pair<Integer, Integer>, Integer> entry : relacions.entrySet()) {
            int u = nodoToIndex.get(entry.getKey().getFirstVal());
            int v = nodoToIndex.get(entry.getKey().getSecondVal());
            relacionsIndex.put(new Pair<>(u, v), entry.getValue());
        }

        // Ejecutar el algoritmo con nodos consecutivos
        M = relacionsIndex;
        n = nodoToIndex.size();
        path = new Vector<>();
        bestPath = new Vector<>();
        millorCost = Integer.MAX_VALUE;

        calcDistribució(0, 1, 0); // Iniciar el recorrido desde el nodo 0

        // Convertir los índices consecutivos del mejor camino a nodos originales
        Vector<Integer> bestPathOriginal = new Vector<>();
        for (int idx : bestPath) {
            bestPathOriginal.add(indexToNodo.get(idx));
        }

        return bestPathOriginal;
    }

    /**
     * Mètode recursiu que calcula la distribució de productes.
     * @param v Node actual
     * @param t Nombre de nodes visitats
     * @param c Cost acumulat
     */
    private void calcDistribució(int v, int t, int c) {
        path.add(v); // Añadir el nodo actual al camino

        if (t == n) { // Si hemos visitado todos los nodos
            // Verificar la conexión de vuelta al nodo inicial
            Pair<Integer, Integer> rel = new Pair<>(v, 0);
            if (!M.containsKey(rel)) rel = new Pair<>(0, v);

            int totalCost = c + M.get(rel);
            if (totalCost < millorCost) {
                millorCost = totalCost; // Actualizar el mejor coste
                bestPath = new Vector<>(path); // Guardar el mejor camino
            }
        } else {
            for (int u = 0; u < n; u++) {
                Pair<Integer, Integer> rel = new Pair<>(v, u);
                if (!M.containsKey(rel)) rel = new Pair<>(u, v);

                // Si u no ha sido visitado y el coste acumulado es menor que el mejor coste
                if (!path.contains(u) && (c + M.get(rel) < millorCost)) {
                    calcDistribució(u, t + 1, c + M.get(rel)); // Recursión con el siguiente nodo
                }
            }
        }

        path.remove(path.size() - 1); // Backtrack: quitar el nodo actual del camino
    }

    /**
     * Mètode que retorna el millor valor trobat.
     * @return Millor valor trobat
     */
    public Integer getMillorValor() {
        return millorCost;
    }
}
