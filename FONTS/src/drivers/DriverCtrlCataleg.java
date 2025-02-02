package drivers;

import main.domain.controllers.CtrlCataleg;
import main.domain.classes.Producte;
import main.domain.exceptions.*;
import main.domain.libs.Pair;

import java.util.*;

/**
 * Driver de la classe CtrlCataleg
 * Aquest driver permet executar tots els metodes de la classe CtrlCataleg
 * @author keinthdc
 */
public class DriverCtrlCataleg {
    /**
     * Atributs de la classe CtrlCataleg
     * c: Instància de la classe CtrlCataleg
     */
    private CtrlCataleg c;

    /**
     * Constructora per defecte
     * S'assigna c com una nova instància de CtrlCataleg
     */
    public DriverCtrlCataleg() {
        c = new CtrlCataleg();
    }

    /**
     * Mètode per provar la constructora de la classe CtrlCataleg
     * S'espera que no es llenci cap excepció
     */
    public void testConstructora()
    {
        try {
            System.out.println("Test de Constructora:");
            c = new CtrlCataleg();
            Map<Integer, Producte> cataleg = c.getCataleg();
            Map<Pair<Integer, Integer>, Integer> relacions = c.getRelacions();
        } catch (Exception e) {
            if (e instanceof ExcepcioCatalegBuit) System.out.println("S'ha creat un cataleg buit correctament");
            else System.out.println("Error al crear el cataleg");
        }
    }

    /**
     * Mètode per provar el mètode getCataleg de la classe CtrlCataleg
     */
    public void testGetCataleg()
    {
        try {
            System.out.println("Test de getCataleg:");
            Map<Integer, Producte> cataleg = c.getCataleg();
            if (cataleg != null) {
                System.out.println("El catàleg s'ha obtingut correctament. Mida: " + cataleg.size());
            }
        }
        catch (Exception e) {
            System.out.println("Error al obtenir el catàleg: " + e.getMessage());
        }
    }

    /**
     * Mètode per provar el mètode getProducte de la classe CtrlCataleg
     */
    public void testGetProducte()
    {
        System.out.println("Test de getProducte:");
        System.out.print("Introdueix el ID del producte a obtenir: ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        try {
            Producte p = c.getProducte(id);
            System.out.println("Producte obtingut: " + p.getNom() + " amb preu " + p.getPreu());
        } catch (Exception e) {
            System.out.println("Error al obtenir el producte: " + e.getMessage());
        }
    }

    /**
     * Mètode per provar el mètode existProducte de la classe CtrlCataleg
     */
    public void testExistProducte()
    {
        System.out.println("Test de existProducte:");
        System.out.print("Introdueix el ID del producte a verificar: ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        try {
            if (c.existProducte(id)) {
                System.out.println("El producte amb ID " + id + " existeix al catàleg.");
            }
        }
        catch (Exception e) {
            System.out.println("Error al verificar l'existència del producte: " + e.getMessage());
        }
    }

    /**
     * Mètode per provar el mètode getRelacions de la classe CtrlCataleg
     */
    public void testGetRelacions()
    {
        try {
            System.out.println("Test de getRelacions:");
            Map<Pair<Integer, Integer>, Integer> relacions = c.getRelacions();
            if (relacions != null) {
                System.out.println("S'han obtingut les relacions correctament");
            }
        }
        catch (Exception e) {
            System.out.println("Error al obtenir les relacions: " + e.getMessage());
        }
    }

    /**
     * Mètode per provar el mètode existRelacio de la classe CtrlCataleg
     */
    public void testExistRelacio()
    {
        System.out.println("Test de existRelacio:");
        System.out.print("Introdueix l'ID del primer producte: ");
        Scanner sc = new Scanner(System.in);
        int id1 = sc.nextInt();
        System.out.print("Introdueix l'ID del segon producte: ");
        int id2 = sc.nextInt();
        try {
            if (c.existRelacio(id1, id2)) {
                System.out.println("Existeix una relació entre els productes amb ID " + id1 + " i " + id2);
            }
        }
        catch (Exception e) {
            System.out.println("Error al verificar l'existència de la relació: " + e.getMessage());
        }
    }

    /**
     * Mètode per provar el mètode getRelacio de la classe CtrlCataleg
     */
    public void testGetRelacio()
    {
        System.out.println("Test de getRelacio:");
        System.out.print("Introdueix l'ID del primer producte: ");
        Scanner sc = new Scanner(System.in);
        int id1 = sc.nextInt();
        System.out.print("Introdueix l'ID del segon producte: ");
        int id2 = sc.nextInt();
        try {
            Integer val = c.getRelacio(id1, id2);
            System.out.println("El valor de la relació entre els productes amb ID " + id1 + " i " + id2 + " és " + val);
        }
        catch (Exception e) {
            System.out.println("Error al obtenir la relació: " + e.getMessage());
        }
    }

    /**
     * Mètode per provar el mètode setCataleg de la classe CtrlCataleg
     */
    public void testSetCataleg() {
        System.out.println("Test de setCataleg:");
        Map<Integer, Producte> nuevoCataleg = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("Introdueix el número de productos a afegir: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Introdueix l'ID del producte ");
            int id = sc.nextInt();
            System.out.print("Introdueix el nom del producte: ");
            String nom = sc.next();
            System.out.print("Introdueix el preu del producte: ");
            int preu = sc.nextInt();

            Producte p = new Producte(id, nom, preu);
            nuevoCataleg.put(id, p);
        }

        c.setCataleg(nuevoCataleg);
        try {
            System.out.println("Catàleg actualitzat correctament. Mida actual: " + c.getCataleg().size());
        }
        catch (Exception e) {
            System.out.println("Error al actualitzar el catàleg: " + e.getMessage());
        }
    }

    /**
     * Mètode per provar el mètode setRelacions de la classe CtrlCataleg
     */
    public void testSetRelacions() {
        System.out.println("Test de setRelacions:");
        Map<Pair<Integer, Integer>, Integer> nuevasRelacions = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("Introdueix el número de relacions a afegir: ");
        int n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("Introdueix l'ID del primer producte: ");
            int id1 = sc.nextInt();
            System.out.print("Introdueix l'ID del segon producte: ");
            int id2 = sc.nextInt();
            System.out.print("Introdueix el valor de la relació: ");
            int valor = sc.nextInt();

            Pair<Integer, Integer> relacion = new Pair<>(id1, id2);
            nuevasRelacions.put(relacion, valor);
        }

        c.setRelacions(nuevasRelacions);
        try {
            System.out.println("Relacions actualizades correctament. Total de relacions: " + c.getRelacions().size());
        }
        catch (Exception e) {
            System.out.println("Error al actualitzar les relacions: " + e.getMessage());
        }
    }

    /**
     * Mètode per provar el mètode afegeixProducte de la classe CtrlCataleg
     */
    public void testAfegeixProducte()
    {
        System.out.println("Test de afegeixProducte:");
        System.out.print("Afegeix l'ID del producte a afegir: ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        System.out.print("Introdueix el nom del producte: ");
        String nom = sc.next();
        System.out.print("Introdueix el preu del producte: ");
        int preu = sc.nextInt();
        System.out.print("Introdueix el número de relacions a afegir: ");
        int n = sc.nextInt();
        System.out.println("Introdueix les relacions amb el producte a afegir en el format 'IDProducte ValorRelacio'");
        List<Pair<Integer, Integer>> rel = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            int id2 = sc.nextInt();
            int val = sc.nextInt();
            rel.add(new Pair<>(id2, val));
        }
        try {
            c.afegeixProducte(id, nom, preu, rel);
            System.out.println("Producte afegit correctament.");
        } catch (Exception e) {
            System.out.println("Error al afegir el producte: " + e.getMessage());
        }
    }

    /**
     * Mètode per provar el mètode eliminarProducte de la classe CtrlCataleg
     */
    public void testEliminarProducte()
    {
        System.out.println("Test de eliminarProducte:");
        System.out.print("Introdueix l'ID del producte a eliminar: ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        try {
            c.eliminarProducte(id);
            System.out.println("Producte eliminat correctament.");
        } catch (Exception e) {
            System.out.println("Error al eliminar el producte: " + e.getMessage());
        }
    }

    /**
     * Mètode per provar el mètode modificarProducte de la classe CtrlCataleg
     */
    public void testModificarProducte()
    {
        System.out.println("Test de modificarProducte:");
        System.out.print("Introdueix l'ID del producte a modificar: ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        System.out.print("Introdueix el nou ID del producte: ");
        int id2 = sc.nextInt();
        try {
            c.modificarIdProducte(id, id2);
            System.out.println("Producte modificat correctament.");
        } catch (Exception e) {
            System.out.println("Error al modificar el producte: " + e.getMessage());
        }
    }

    /**
     * Mètode per provar el mètode modificarNomProducte de la classe CtrlCataleg
     */
    public void testModificarNomProducte()
    {
        System.out.println("Test de modificarNomProducte:");
        System.out.print("Introdueix l'ID del producte a modificar: ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        System.out.print("Introdueix el nou nom del producte: ");
        String nom = sc.next();
        try {
            c.modificarNomProducte(id, nom);
            System.out.println("Nom modificat correctament.");
        } catch (Exception e) {
            System.out.println("Error al modificar el nom del producte: " + e.getMessage());
        }
    }

    /**
     * Mètode per provar el mètode modificarPreuProducte de la classe CtrlCataleg
     */
    public void testModificarPreuProducte()
    {
        System.out.println("Test de modificarPreuProducte:");
        System.out.print("Introdueix l'ID del producte a modificar: ");
        Scanner sc = new Scanner(System.in);
        int id = sc.nextInt();
        System.out.print("Introdueix el nou preu del producte: ");
        int preu = sc.nextInt();
        try {
            c.modificarPreuProducte(id, preu);
            System.out.println("Preu modificat correctament.");
        } catch (Exception e) {
            System.out.println("Error al modificar el preu del producte: " + e.getMessage());
        }
    }

    /**
     * Mètode per provar el mètode modificarRelacions de la classe CtrlCataleg
     */
    public void testModificarRelacions()
    {
        System.out.println("Test de modificarRelacions:");
        System.out.print("Introdueix l'ID del primer producte: ");
        Scanner sc = new Scanner(System.in);
        int id1 = sc.nextInt();
        System.out.print("Introdueix l'ID del segon producte: ");
        int id2 = sc.nextInt();
        System.out.print("Introdueix el nou valor de la relació: ");
        int val = sc.nextInt();
        try {
            c.modificarRelacio(id1, id2, val);
            System.out.println("Relació modificada correctament.");
        } catch (Exception e) {
            System.out.println("Error al modificar la relació: " + e.getMessage());
        }
    }

    /**
     * Mètode per mostrar els mètodes de la classe CtrlCataleg
     */
    public void show_methods()
    {
        System.out.println("Driver de la classe CtrlCataleg");
        System.out.println("Opcions:");
        System.out.println("1 - Provar Constructora");
        System.out.println("2 - Provar getCataleg");
        System.out.println("3 - Provar getProducte");
        System.out.println("4 - Provar existProducte");
        System.out.println("5 - Provar getRelacions");
        System.out.println("6 - Provar existRelacio");
        System.out.println("7 - Provar getRelacio");
        System.out.println("8 - Provar afegeixProducte");
        System.out.println("9 - Provar eliminarProducte");
        System.out.println("10 - Provar modificarProducte");
        System.out.println("11 - Provar modificarNomProducte");
        System.out.println("12 - Provar modificarPreuProducte");
        System.out.println("13 - Provar modificarRelacions");
        System.out.println("14 - Provar setCataleg");
        System.out.println("15 - Provar setRelacions");
        System.out.println("16 - Mostrar mètodes");
        System.out.println("0 - Sortir");
    }

    /**
     * Mètode main del driver
     * @param args Arguments de la línia de comandes
     */
    public static void main(String[] args) {
        DriverCtrlCataleg dc = new DriverCtrlCataleg();
        Scanner entry = new Scanner(System.in);

        dc.show_methods();
        int option = -1;
        while (option != 0) {
            option = entry.nextInt();
            switch (option) {
                case 1:
                    dc.testConstructora();
                    break;
                case 2:
                    dc.testGetCataleg();
                    break;
                case 3:
                    dc.testGetProducte();
                    break;
                case 4:
                    dc.testExistProducte();
                    break;
                case 5:
                    dc.testGetRelacions();
                    break;
                case 6:
                    dc.testExistRelacio();
                    break;
                case 7:
                    dc.testGetRelacio();
                    break;
                case 8:
                    dc.testAfegeixProducte();
                    break;
                case 9:
                    dc.testEliminarProducte();
                    break;
                case 10:
                    dc.testModificarProducte();
                    break;
                case 11:
                    dc.testModificarNomProducte();
                    break;
                case 12:
                    dc.testModificarPreuProducte();
                    break;
                case 13:
                    dc.testModificarRelacions();
                    break;
                case 14:
                    dc.testSetCataleg();
                    break;
                case 15:
                    dc.testSetRelacions();
                    break;
                case 16:
                    dc.show_methods();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opció no vàlida");
                    break;
            }
        }
    }
}
