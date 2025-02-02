package test;

import main.domain.classes.Prestatgeria;
import main.domain.classes.Producte;
import main.domain.libs.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

public class PrestatgeriaTest {

    private Prestatgeria prestatgeria;
    private Vector<Integer> distribucioTest;
    private Map<Integer, Producte> catalegTest;
    private Map<Pair<Integer, Integer>, Integer> relacionsTest;

    @Before
    public void setDadesTest() {
        prestatgeria = new Prestatgeria();
        distribucioTest = new Vector<>(Arrays.asList(1, 2, 4, 3));
        catalegTest = new HashMap<>();
        catalegTest.put(1, new Producte(1, "Producte A", 5));
        catalegTest.put(2, new Producte(2, "Producte B", 5));
        catalegTest.put(3, new Producte(3, "Producte C", 5));
        catalegTest.put(4, new Producte(4, "Producte D", 10));

        relacionsTest = new HashMap<>();
        relacionsTest.put(new Pair<>(1, 2), 10);
        relacionsTest.put(new Pair<>(2, 4), 20);
    }

    @Test
    public void testSetAndGetDistribucio() {
        prestatgeria.setDistribucio(distribucioTest);
        assertEquals("La distribució no coincideix.", distribucioTest, prestatgeria.getDistribucio());
    }

    @Test
    public void testCarregarDistribucio() {
        distribucioTest = new Vector<>(Arrays.asList(1, 5, 4, 3, 8, 23));
        prestatgeria.carregarDistribucio(distribucioTest);
        assertEquals("La distribució no coincideix.", distribucioTest, prestatgeria.getDistribucio());
    }

    @Test
    public void testCarregarAltura() {
        int altura = 5;
        prestatgeria.carregarAltura(altura);
        assertEquals("L'altura no coincideix.", altura, prestatgeria.getAltura());
    }

    @Test
    public void testGetAltura() {
        assertEquals("L'altura no coincideix.", 3, prestatgeria.getAltura());
    }

    @Test
    public void testModificarDistribucio() {
        prestatgeria.setDistribucio(distribucioTest);
        prestatgeria.modificarDistribucio(1, 4);

        Vector<Integer> expected = new Vector<>(Arrays.asList(4, 2, 1, 3));
        assertEquals("La distribució modificada no coincideix.", expected, prestatgeria.getDistribucio());
    }

    @After 
    public void borrarFitxerTest() {
        String[] fitxers = {
                "testFitxerDistribucio.dat",
                "testFitxerCataleg.dat",
                "testFitxerRelacions.dat",
                "testFitxerAltura.dat"
        };

        for (String nomFitxer : fitxers) {
            File fitxer = new File(nomFitxer);
            if (fitxer.exists()) {
                boolean esborrat = fitxer.delete();
                if (!esborrat) {
                    System.err.println("No s'ha pogut esborrar el fitxer: " + nomFitxer);
                }
            }
        }
    }
}
