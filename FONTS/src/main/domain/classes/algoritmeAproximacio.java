package main.domain.classes;

import main.domain.controllers.CtrlDistribucio;
import main.domain.libs.Pair;
import main.domain.classes.Algoritme;
import java.util.*;

/**
 * Classe que implementa la interfície {@link Algoritme}.
 * Aquesta classe implementa un algorisme d'aproximació per calcular la distribució de productes.
 * @author keinthdc
 */
public class algoritmeAproximacio implements Algoritme {

    /**
     * Constructora per defecte.
     */
    public algoritmeAproximacio() {
        super();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Vector<Integer> aplicarAlgoritme(Map<Pair<Integer, Integer>, Integer> products, int numProductos) {
        // Crear mapeo entre nodos originales y nodos consecutivos
        Map<Integer, Integer> nodoToIndex = new HashMap<>();
        Map<Integer, Integer> indexToNodo = new HashMap<>();
        int index = 0;

        for (Pair<Integer, Integer> par : products.keySet()) {
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

        int numVertices = nodoToIndex.size();
        int numAristas = (numVertices * (numVertices - 1)) / 2;

        // Crear el grafo con índices consecutivos
        List<Arista> graph = new ArrayList<>(numAristas);
        for (Map.Entry<Pair<Integer, Integer>, Integer> entry : products.entrySet()) {
            Pair<Integer, Integer> nodes = entry.getKey();
            Integer pes = entry.getValue();

            Integer org = nodoToIndex.get(nodes.getFirstVal());
            Integer dst = nodoToIndex.get(nodes.getSecondVal());
            graph.add(new Arista(org, dst, pes));
        }

        // ---------- Primer paso: MST ----------------------
        // Algoritmo de Kruskal con índices consecutivos
        Collections.sort(graph, Comparator.comparingInt(a -> a.cost));
        List<Arista> MST = new ArrayList<>(numVertices);
        Integer[] root = iniRoot(numVertices);

        for (Arista a : graph) {
            if (unir(a.src, a.dst, root)) {
                MST.add(a);
            }
        }

        // ---------- Segundo paso: Grafo Dirigido ----------
        Map<Integer, List<Integer>> graphDirigido = new HashMap<>();
        for (Arista a : MST) {
            graphDirigido.putIfAbsent(a.src, new ArrayList<>());
            graphDirigido.putIfAbsent(a.dst, new ArrayList<>());

            graphDirigido.get(a.src).add(a.dst);
            graphDirigido.get(a.dst).add(a.src);
        }

        // ---------- Tercer paso: Ciclo Euleriano ----------
        List<Integer> cicloEuler = new ArrayList<>();
        DFS(graphDirigido, 0, cicloEuler);

        // ---------- Cuarto paso: Ciclo Hamiltoniano -------
        List<Integer> cicloHam = elimRepetits(cicloEuler);

        // Convertir índices consecutivos de vuelta a los nodos originales
        List<Integer> cicloOriginal = new ArrayList<>();
        for (Integer indexNodo : cicloHam) {
            cicloOriginal.add(indexToNodo.get(indexNodo));
        }

        // ---------- Quinto paso: Hill Climbing ------------
        List<Integer> solOptima = hillClimbing(cicloOriginal, products);

        return new Vector<>(solOptima);
    }

    /**
     * Calcula el cost total d'un cicle hamiltonia.
     * @param products Mapa de productes.
     * @param ciclo Cicle hamiltonia.
     * @return Cost total del cicle.
     */
    public Integer calcularCostoCiclo(Map<Pair<Integer, Integer>, Integer> products, List<Integer> ciclo)
    {
        Integer costTotal = 0;
        for (int i = 0; i < ciclo.size() - 1; ++i) {
            int nodoActual = ciclo.get(i);
            int nodoSig = ciclo.get(i + 1);
            Pair<Integer, Integer> rel = new Pair<>(nodoActual, nodoSig);
            if (!products.containsKey(rel)) rel = new Pair<>(nodoSig, nodoActual);
            costTotal += products.get(rel);
        }
        int nodoFinal = ciclo.get(ciclo.size() - 1);
        int nodoInicial = ciclo.get(0);
        Pair<Integer, Integer> rel2 = new Pair<>(nodoFinal, nodoInicial);
        if (!products.containsKey(rel2)) rel2 = new Pair<>(nodoInicial, nodoFinal);
        costTotal += products.get(rel2);

        return costTotal;
    }

    /**
     * Inicialitza un array de tamany tam amb valors -1.
     * @param tam Tamany de l'array.
     * @return Array d'enters inicialitzat.
     */
    private Integer[] iniRoot(int tam)
    {
        Integer[] res = new Integer[tam];
        for (int i = 0; i < tam; ++i)
            res[i] = -1;
        return res;
    }

    /**
     * Classe que representa una aresta.
     */
    private static class Arista {
        Integer src;
        Integer dst;
        Integer cost;

        Arista(Integer src, Integer dest, Integer c) {
            this.src = src;
            this.dst = dest;
            this.cost = c;
        }
    }

    /**
     * Busca el node pare d'un altre node.
     * @param v Node.
     * @param root Array de conjunts.
     * @return Node pare.
     */
    private Integer buscar(Integer v, Integer[] root)
    {
        if (root[v] < 0) return v;
        else {
            Integer pare = buscar(root[v], root);
            root[v] = pare;
            return pare;
        }
    }

    /**
     * Uneix dos conjunts.
     * @param v1 Node 1.
     * @param v2 Node 2.
     * @param root Array de conjunts.
     * @return Cert si s'han unit, fals altrament.
     */
    private boolean unir(Integer v1, Integer v2, Integer[] root)
    {
        Integer conjA = buscar(v1, root);
        Integer conjB = buscar(v2, root);

        if (!conjA.equals(conjB)) {
            if (root[conjA] <= root[conjB]) {
                root[conjA] = root[conjA] + root[conjB];
                root[conjB] = conjA;
            } else {
                root[conjB] = root[conjA] + root[conjB];
                root[conjA] = conjB;
            }
            return true;
        }
        else
            return false;
    }

    /**
     * Realitza un recorregut DFS sobre un graf.
     * @param G Graf.
     * @param v Node inicial.
     * @param cicloEuler Llista on es guarda el recorregut.
     */
    private void DFS(Map<Integer, List<Integer>> G, int v, List<Integer> cicloEuler)
    {
        while(!G.get(v).isEmpty()) {
            int vecino = G.get(v).remove(0);
            DFS(G, vecino, cicloEuler);
        }
        cicloEuler.add(v);
    }

    /**
     * Elimina els nodes repetits d'un cicle eulerià.
     * @param cicloEuler Cicle eulerià.
     * @return Cicle hamiltonià.
     */
    private List<Integer> elimRepetits(List<Integer> cicloEuler)
    {
        Set<Integer> vis = new HashSet<>();
        List<Integer> cicloHam = new ArrayList<>();

        for (Integer nodo : cicloEuler) {
            if (!vis.contains(nodo)) {
                cicloHam.add(nodo);
                vis.add(nodo);
            }
        }
        return cicloHam;
    }

    /**
     * Realitza l'operació 2-opt sobre un cicle.
     * @param ciclo Cicle.
     * @param i Índex inicial.
     * @param k Índex final.
     * @return Cicle modificat.
     */
    private List<Integer> A2Opt(List<Integer> ciclo, int i, int k)
    {
        List<Integer> nuevoCiclo = new ArrayList<>();
        // Copiar la primera parte del ciclo hasta el índice i (inclusive)
        for (int j = 0; j <= i; j++)
            nuevoCiclo.add(ciclo.get(j));
        // Invertir la sección entre i y k
        for (int j = k; j > i; j--)
            nuevoCiclo.add(ciclo.get(j));
        // Copiar la última parte del ciclo después de k
        for (int j = k + 1; j < ciclo.size(); j++)
            nuevoCiclo.add(ciclo.get(j));

        return nuevoCiclo;
    }

    /**
     * Realitza l'algorisme Hill Climbing per optimitzar un cicle hamiltonià.
     * @param solInicial Cicle hamiltonià inicial.
     * @param products Mapa de productes.
     * @return Cicle hamiltonià optimitzat.
     */
    private List<Integer> hillClimbing(List<Integer> solInicial, Map<Pair<Integer, Integer>, Integer> products) {
        List<Integer> mejorCiclo = new ArrayList<>(solInicial);
        int mejorCosto = calcularCostoCiclo(products, mejorCiclo);
        boolean mejora = true;

        while (mejora) {
            mejora = false;
            for (int i = 0; i < solInicial.size() - 1; ++i) {
                for (int j = i + 1; j < solInicial.size(); ++j) {
                    List<Integer> nuevoCiclo = A2Opt(mejorCiclo, i, j);
                    int nuevoCosto = calcularCostoCiclo(products, nuevoCiclo);

                    if (nuevoCosto < mejorCosto) {
                        mejorCosto = nuevoCosto;
                        mejorCiclo = nuevoCiclo;
                        mejora = true;
                    }
                }
            }
        }
        return mejorCiclo;
    }
}
