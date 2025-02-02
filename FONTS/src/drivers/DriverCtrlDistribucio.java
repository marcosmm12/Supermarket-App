package drivers;

import main.domain.controllers.CtrlCataleg;
import main.domain.controllers.CtrlDistribucio;
import main.domain.classes.Algoritme;
import main.domain.classes.Prestatgeria;
import main.domain.classes.algoritmeAproximacio;
import main.domain.libs.Pair;
import main.domain.exceptions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Vector;

/**
 * Driver de la classe CtrlDistribucio
 * Aquest driver permet provar el funcionament dels diferents metodes de la classe CtrlDistribucio
 * @author keinthdc
 */
public class DriverCtrlDistribucio {
    /**
     * Atributs de la classe CtrlDistribucio
     * d: Instància de la classe CtrlDistribucio
     * c: Instància de la classe CtrlCataleg
     * a: Instància de la classe Algoritme
     * p: Instància de la classe Prestatgeria
     */
    private CtrlDistribucio d;
    private CtrlCataleg c;
    private Algoritme a;
    private Prestatgeria p;

    /**
     * Metode per configurar el cataleg amb productes d'exemple i les seves relacions
     */
    public void configurarCataleg()
    {
        System.out.println("Configuració del cataleg:");
        c = new CtrlCataleg();
        // Añadir productos de ejemplo
        try {
            // Añadimos los productos al catálogo
            c.afegeixProducte(100, "Producte A", 100, new ArrayList<>());
            c.afegeixProducte(21, "Producte B", 150, new ArrayList<>());
            c.afegeixProducte(32, "Producte C", 200, new ArrayList<>());
            c.afegeixProducte(34, "Producte D", 250, new ArrayList<>());

            // Establecemos las relaciones según la matriz dada
            int[][] valoresRelacions = {
                    {0, 10, 15, 20},
                    {10, 0, 35, 25},
                    {15, 35, 0, 30},
                    {20, 25, 30, 0}
            };

            // Identificadores de productos
            int[] idsProductos = {100, 21, 32, 34};

            // Actualizamos las relaciones usando modificarRelacio
            for (int i = 0; i < valoresRelacions.length; i++) {
                for (int j = 0; j < valoresRelacions[i].length; j++) {
                    if (i != j) { // Excluir relaciones de un producto consigo mismo
                        c.modificarRelacio(idsProductos[i], idsProductos[j], valoresRelacions[i][j]);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error al afegir productes d'exemple: " + e.getMessage());
        }
        System.out.println("Cataleg configurat amb èxit");
    }

    /**
     * Metode per configurar l'algorisme d'aproximacio com l'algorisme inicial per defecte
     */
    public void configurarAlgoritme()
    {
        System.out.println("Configuració de l'algoritme:");
        a = new algoritmeAproximacio();
        System.out.println("Algoritme d'aproximació configurat per defecte.");
    }

    /**
     * Metode per configurar la prestatgeria amb una prestatgeria buida
     */
    public void configurarPrestatgeria()
    {
        System.out.println("Configuració de la prestatgeria:");
        p = new Prestatgeria();
        System.out.println("Prestatgeria configurada amb èxit");
    }

    /**
     * Constructor de la classe DriverCtrlDistribucio
     */
    public DriverCtrlDistribucio()
    {
        configurarCataleg();
        configurarAlgoritme();
        configurarPrestatgeria();
    }

    /**
     * Metode per provar la constructora de la classe CtrlDistribucio
     */
    public void testConstructora()
    {
        System.out.println("Test de Constructora:");
        d = new CtrlDistribucio(c, a, p);
        if (d == null) System.out.println("Error: Constructora no funciona correctament");
        else System.out.println("S'ha creat una distribució correctament.");
    }

    /**
     * Metode per provar el metode distribuirProductes de la classe CtrlDistribucio
     */
    public void testDistribuirProductes()
    {
        System.out.println("Test de distribuirProductes:");
        try {
            d.distribuirProductes();
            System.out.println("Distribució calculada i actualizada");
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Metode per provar el metode modificarDistribucio de la classe CtrlDistribucio
     */
    public void testModificarDistribucio()
    {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Introdueix l'ID del primer producte que vols canviar: ");
            int id1 = Integer.parseInt(scanner.nextLine());
            System.out.print("Introdueix l'ID del segon producte que vols canviar: ");
            int id2 = Integer.parseInt(scanner.nextLine());

            d.modificarDistribucio(id1, id2);
            System.out.println("Distribució modificada amb èxit.");
        } catch (NumberFormatException e) {
            System.out.println("Entrada invàlida.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Metode per provar el metode escollirAlgoritmeForcaBruta de la classe CtrlDistribucio
     */
    public void testEscollirAlgoritmeForcaBruta()
    {
        System.out.println("Test d'escollirAlgoritmeForcaBruta:");
        try {
            d.escollirAlgoritmeForcaBruta();
            System.out.println("Algoritme de força bruta aplicat correctament.");
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Metode per provar el metode escollirAlgoritmeAproximacio de la classe CtrlDistribucio
     */
    public void testEscollirAlgoritmeAproximacio()
    {
        System.out.println("Test d'escollirAlgoritmeAproximacio:");
        try {
            d.escollirAlgoritmeAproximacio();
            System.out.println("Algoritme d'aproximació aplicat correctament.");
        }
        catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Metode per provar el metode getDistribucio de la classe CtrlDistribucio
     */
    public void testGetDistribucio()
    {
        System.out.println("Test de getDistribucio:");
        System.out.print("Distribució actual: ");
        try {
        Vector<Integer> distribucio = d.getDistribucio();
        int altura = d.getAltura();
        int num_elements_fila = distribucio.size() / altura;
        if ((distribucio.size() % altura) != 0) ++num_elements_fila;
        int i = 0;
        int j = 0;
        for (i = 0; i < distribucio.size(); i += num_elements_fila) {
            for (j = i; j < min((i + num_elements_fila), distribucio.size()); ++j) {
                System.out.print(distribucio.get(j) + " ");
            }
            System.out.println();
        }
        //Imprimir els llocs que falten de les altures
        while (i < altura) {
            while (j < num_elements_fila) {
                System.out.print("--- ");
                ++j;
            }
            j = 0;
            System.out.println();
            ++i;
        }
        System.out.println();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("Distribució mostrada correctament.");
    }

    /**
     * Metode per provar el metode setDistribucio de la classe CtrlDistribucio
     */
    public void testSetDistribucio()
    {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Compte amb introduir IDs que no existeixin al catàleg.");
            System.out.print("Introdueix la nova distribució: ");
            String[] distribucio = scanner.nextLine().split(" ");
            Vector<Integer> novaDistribucio = new Vector<>();
            for (String s : distribucio) {
                novaDistribucio.add(Integer.parseInt(s));
            }
            d.setDistribucio(novaDistribucio);
            System.out.println("Distribució modificada correctament.");
        } catch (NumberFormatException e) {
            System.out.println("Entrada invàlida.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Metode per mostrar els mètodes de la classe CtrlDistribucio
     */
    public void show_methods()
    {
        System.out.println("Driver de la classe CtrlDistribucio");
        System.out.println("Opcions:");
        System.out.println("1 - Test de la Constructora");
        System.out.println("2 - Test de distribuirProductes");
        System.out.println("3 - Test de modificarDistribucio");
        System.out.println("4 - Test de escollirAlgoritmeForcaBruta");
        System.out.println("5 - Test de escollirAlgoritmeAproximacio");
        System.out.println("6 - Test de getDistribucio");
        System.out.println("7 - Test de setDistribucio");
        System.out.println("8 - Mostrar mètodes");
        System.out.println("0 - Sortir");

    }

    /**
     * Metode per calcular el minim entre dos enters
     * @param a Primer enter
     * @param b Segon enter
     * @return El minim entre a i b
     */
    //Funcio per calcular el minim entre dos enters
    private int min(int a, int b) {
        if (a < b) return a;
        return b;
    }

    /**
     * Metode principal del driver
     * @param args Arguments de la funció principal
     */
    public static void main(String[] args) {
        DriverCtrlDistribucio dcd = new DriverCtrlDistribucio();
        Scanner scanner = new Scanner(System.in);

        dcd.show_methods();
        int option = Integer.parseInt(scanner.nextLine());
        while (option != 0) {
            switch (option) {
                case 1:
                    dcd.testConstructora();
                    break;
                case 2:
                    dcd.testDistribuirProductes();
                    break;
                case 3:
                    dcd.testModificarDistribucio();
                    break;
                case 4:
                    dcd.testEscollirAlgoritmeForcaBruta();
                    break;
                case 5:
                    dcd.testEscollirAlgoritmeAproximacio();
                    break;
                case 6:
                    dcd.testGetDistribucio();
                    break;
                case 7:
                    dcd.testSetDistribucio();
                    break;
                case 8:
                    dcd.show_methods();
                    break;
                default:
                    System.out.println("Opció no vàlida.");
                    break;
            }
            option = Integer.parseInt(scanner.nextLine());
        }
    }
}
