package test;

import main.domain.classes.Producte;

import static org.junit.Assert.*;
import org.junit.Test;


public class ProducteTest {
    Producte p = new Producte(1, "Patates", 10);

    @Test
    public void TestGetProducte() {
        assertEquals(p, p.getProducte());
    }

    @Test
    public void TestGetId() {
        assertEquals(1, p.getId().intValue());
    }

    @Test
    public void TestGetNom() {
        assertEquals("Patates", p.getNom());
    }

    @Test
    public void TestGetPreu() {
        assertEquals(10, p.getPreu().intValue());
    }

    @Test
    public void TestSetId() {
        p.setId(2);
        assertEquals(2, p.getId().intValue());
    }

    @Test
    public void TestSetNom() {
        p.setNom("Pomes");
        assertEquals("Pomes", p.getNom());
    }

    @Test
    public void TestSetPreu() {
        p.setPreu(20);
        assertEquals(20, p.getPreu().intValue());
    }
}
