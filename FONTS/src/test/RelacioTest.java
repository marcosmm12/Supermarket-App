package test;

import main.domain.classes.Relacio;
import main.domain.exceptions.*;

import static org.junit.Assert.*;
import org.junit.*;

public class RelacioTest {
    Relacio r;

    @BeforeClass
    public static void beforeClass()
    {
        //inicializacion del testing
        System.out.println("Inicialización del testing");
    }

    @AfterClass
    public static void afterClass()
    {
        //finalizacion del testing
        System.out.println("Finalización del testing");
    }

    @Before
    public void before()
    {
        //antes del test
        r = new Relacio();
    }

    @After
    public void after()
    {
        //despues del test
        System.out.println("Test ejecutado");
    }

    @Test
    public void testGetValRelacio()
    {
        try {
            r.addRelacio(1, 2, 10);
            Integer value = r.getValRelacio(1, 2);
            assertNotNull("La relación debería existir", value);
            assertEquals("El valor de la relación debería ser 10", Integer.valueOf(10), value);
        } catch (RelacioExistException | RelacioNotExistException e) {
            fail("No debería lanzar RelacioExistException");
        }
    }

    @Test
    public void testExistRelacio()
    {
        try {
            r.addRelacio(1, 2, 10);
            assertTrue("La relación debería existir", r.existRelacio(1, 2));
            assertFalse("La relación no debería existir", r.existRelacio(2, 3));
        } catch (RelacioExistException e) {
            fail("No debería lanzar RelacioExistException");
        }
    }

    @Test
    public void testEraseRelacio()
    {
        try {
            r.addRelacio(1, 2, 10);
            assertTrue("La relación debería existir antes de borrarla", r.existRelacio(1, 2));
            r.eraseRelacio(1, 2);
            assertFalse("La relación debería haberse borrado", r.existRelacio(1, 2));
        } catch (RelacioExistException | RelacioNotExistException e) {
            fail("No debería lanzar RelacioExistException");
        }
    }

    @Test(expected = RelacioNotExistException.class)
    public void testEraseRelacioNotExist() throws RelacioNotExistException {
        //intentar borrar una relación que no existe lanza la excepción
        r.eraseRelacio(1, 2);
    }

    @Test
    public void testAddRelacio()
    {
        try {
            r.addRelacio(1, 2, 10);
            assertTrue("La relación debería existir", r.existRelacio(1, 2 ));
        } catch (RelacioExistException e) {
            fail("No debería lanzar RelacioExistException");
        }
    }

    @Test(expected = RelacioExistException.class)
    public void testAddRelacioExist() throws RelacioExistException {
        //intentar añadir una relación que ya existe lanza la excepción
        r.addRelacio(1, 2, 10);
        r.addRelacio(1, 2, 10);
    }

    @Test
    public void testChangeValRelacio()
    {
        try {
            r.addRelacio(1, 2, 10);
            r.changeValRelacio(1, 2, 20);
            assertEquals("El valor de la relación debería haber cambiado a 20", Integer.valueOf(20), r.getValRelacio(1, 2));
        } catch (RelacioExistException | RelacioNotExistException e) {
            fail("No debería lanzar RelacioExistException");
        }
    }

    @Test
    public void testEliminaRelacio()
    {
        try {
            r.addRelacio(1, 2, 10);
            r.addRelacio(2, 3, 15);
            r.addRelacio(1, 3, 10);
            r.eliminaRelacio(1);

            assertFalse("La relación (1,2) y (1,3) debería haberse eliminado", r.existRelacio(1, 3));
            assertTrue("La relación (2,3) debería seguir existiendo", r.existRelacio(2, 3));
        } catch (RelacioExistException e) {
            fail("No debería lanzar RelacioExistException");
        }
    }
}
