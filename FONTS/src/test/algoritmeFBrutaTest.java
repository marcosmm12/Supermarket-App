package test;
import main.domain.classes.AlgoritmeFBruta;
import main.domain.libs.Pair;
import java.util.*;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value= Parameterized.class)
public class algoritmeFBrutaTest {
    private static AlgoritmeFBruta a;

    private Map<Pair<Integer, Integer>, Integer> nodos;
    private int numProductos;
    private int costoOptimo;

    public algoritmeFBrutaTest(int[][] matrix, int numProductos, int costoOptimo) {
        this.nodos = convertirMatrizAGrafo(matrix);
        this.numProductos = numProductos;
        this.costoOptimo = costoOptimo;
    }

    @BeforeClass
    public static void beforeClass()
    {
        //inicializacion del testing
        System.out.println("Inicialización del testing");
        a = new AlgoritmeFBruta();
    }

    @AfterClass
    public static void afterClass()
    {
        //finalizacion del testing
        System.out.println("Finalización del testing");
    }

    @After
    public void after()
    {
        //despues del test
        System.out.println("Test ejecutado");
    }

    @Test
    public void testaplicarAlgoritme()
    {
        Vector<Integer> result = a.aplicarAlgoritme(nodos, numProductos);
        assertNotNull("La solución debe ser nula", result);
        assertEquals("El ciclo debe incluir todos los productos", numProductos, result.size());

        int costoObtenido = a.getMillorValor();
        assertEquals("El costo debe ser como máximo dos veces el óptimo", costoOptimo, costoObtenido);
    }

    @Parameters
    public static Collection<Object[]> getTestParameters() {
        return Arrays.asList(new Object[][]{
                // Caso de 4 nodos
                {
                        new int[][]{
                                {0, 10, 15, 20},
                                {10, 0, 35, 25},
                                {15, 35, 0, 30},
                                {20, 25, 30, 0}
                        },
                        4,
                        80
                },
                // Caso de 5 nodos
                {
                        new int[][]{
                                {0, 3, 4, 2, 7},
                                {3, 0, 4, 6, 3},
                                {4, 4, 0, 5, 8},
                                {2, 6, 5, 0, 6},
                                {7, 3, 8, 6, 0}
                        },
                        5,
                        19
                },
                // Caso de 9 nodos
                {
                        new int[][]{
                                {0, 29, 82, 46, 68, 52, 72, 42, 51},
                                {29, 0, 55, 46, 42, 43, 43, 23, 23},
                                {82, 55, 0, 68, 46, 55, 23, 43, 41},
                                {46, 46, 68, 0, 82, 15, 72, 31, 62},
                                {68, 42, 46, 82, 0, 74, 23, 52, 21},
                                {52, 43, 55, 15, 74, 0, 61, 23, 55},
                                {72, 43, 23, 72, 23, 61, 0, 42, 23},
                                {42, 23, 43, 31, 52, 23, 42, 0, 33},
                                {51, 23, 41, 62, 21, 55, 23, 33, 0}
                        },
                        9,
                        246
                },
                // Caso de 15 nodos
                {
                        new int[][]{
                                {0, 29, 82, 46, 68, 52, 72, 42, 51, 55, 29, 74, 23, 72, 46},
                                {29, 0, 55, 46, 42, 43, 43, 23, 23, 31, 41, 51, 11, 52, 21},
                                {82, 55, 0, 68, 46, 55, 23, 43, 41, 29, 79, 21, 64, 31, 51},
                                {46, 46, 68, 0, 82, 15, 72, 31, 62, 42, 21, 51, 51, 43, 64},
                                {68, 42, 46, 82, 0, 74, 23, 52, 21, 46, 82, 58, 46, 65, 23},
                                {52, 43, 55, 15, 74, 0, 61, 23, 55, 31, 33, 37, 51, 29, 59},
                                {72, 43, 23, 72, 23, 61, 0, 42, 23, 31, 77, 37, 51, 46, 33},
                                {42, 23, 43, 31, 52, 23, 42, 0, 33, 15, 37, 33, 33, 31, 37},
                                {51, 23, 41, 62, 21, 55, 23, 33, 0, 29, 62, 46, 29, 51, 11},
                                {55, 31, 29, 42, 46, 31, 31, 15, 29, 0, 51, 21, 41, 23, 37},
                                {29, 41, 79, 21, 82, 33, 77, 37, 62, 51, 0, 65, 42, 59, 61},
                                {74, 51, 21, 51, 58, 37, 37, 33, 46, 21, 65, 0, 61, 11, 55},
                                {23, 11, 64, 51, 46, 51, 51, 33, 29, 41, 42, 61, 0, 62, 23},
                                {72, 52, 31, 43, 65, 29, 46, 31, 51, 23, 59, 11, 62, 0, 59},
                                {46, 21, 51, 64, 23, 59, 33, 37, 11, 37, 61, 55, 23, 59, 0}
                        },
                        15,
                        291
                }
        });
    }

    //Transforms the entry test to the desired parameter
    private static Map<Pair<Integer, Integer>, Integer> convertirMatrizAGrafo(int[][] matrix) {
        Map<Pair<Integer, Integer>, Integer> graph = new HashMap<>();
        int size = matrix.length;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++)
                graph.put(new Pair<>(i, j), matrix[i][j]);
        }
        return graph;
    }

}
