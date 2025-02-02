package drivers;

import main.persistence.controllers.CtrlPersistencia;
import main.domain.classes.Producte;
import main.domain.libs.Pair;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Vector;

/**
 * Driver de la classe CtrlPersistencia
 * Aquest driver permet provar les funcionalitats de la classe CtrlPersistencia
 * @author keinthdc
 */
public class DriverCtrlPersistencia {
    /**
     * Atributs de la classe DriverCtrlPersistencia
     * ctrlPersistencia: Instància de la classe CtrlPersistencia
     * scanner: Scanner per llegir les dades de l'usuari
     */
    private CtrlPersistencia ctrlPersistencia;
    private Scanner scanner;

    /**
     * Constructora per defecte
     * Crea un objecte DriverCtrlPersistencia
     */
    public DriverCtrlPersistencia() {
        ctrlPersistencia = new CtrlPersistencia();
    }

    /**
     * Mostra el menú d'opcions del driver
     */
    public void mostrarMenu() {
        System.out.println("\n=== Menú del Driver CtrlPersistencia ===");
        System.out.println("1. Test Constructor");
        System.out.println("2. Test Get Cataleg");
        System.out.println("3. Test Get Relacio");
        System.out.println("4. Test Get Altura");
        System.out.println("5. Test Get Distribució");
        System.out.println("6. Test Exist ID");
        System.out.println("7. Test Guardar Sistema");
        System.out.println("8. Test Carregar Sistema");
        System.out.println("9. Test Guardar Tot");
        System.out.println("10. Test Guardar Tot en Fitxer");
        System.out.println("11. Test Get de Fitxer");
        System.out.println("12. Mostrar Menu");
        System.out.println("0. Sortir");
    }

    /**
     * Prova la constructora de la classe CtrlPersistencia
     */
    public void testConstructora() {
        ctrlPersistencia = new CtrlPersistencia();
        if (ctrlPersistencia == null) {
            System.out.println("Error: CtrlPersistencia no ha sigut creat correctament");
        } else {
            System.out.println("S'ha creat un objecte CtrlPersistencia correctament");
        }
    }

    /**
     * Prova el mètode getCataleg de la classe CtrlPersistencia
     */
    public void testGetCataleg() {
        System.out.print("Introdueix l'ID del catàleg: ");
        String id = scanner.nextLine();
        Map<Integer, Producte> cataleg = ctrlPersistencia.getCataleg(id);
        System.out.println("Cataleg obtingut:" + cataleg);
    }

    /**
     * Prova el mètode getRelacio de la classe CtrlPersistencia
     */
    public void testGetRelacio() {
        System.out.print("Introdueix l'ID de la relació: ");
        String id = scanner.nextLine();
        Map<Pair<Integer, Integer>, Integer> relacio = ctrlPersistencia.getRelacio(id);
        System.out.println("Relació obtinguda: " + relacio);
    }

    /**
     * Prova el mètode getAltura de la classe CtrlPersistencia
     */
    public void testGetAltura() {
        System.out.print("Introdueix l'ID de l'altura: ");
        String id = scanner.nextLine();
        int altura = ctrlPersistencia.getAltura(id);
        System.out.println("Altura obtinguda: " + altura);
    }

    /**
     * Prova el mètode getDistribucio de la classe CtrlPersistencia
     */
    public void testGetDistribucio() {
        System.out.print("Introdueix l'ID de la distribució: ");
        String id = scanner.nextLine();
        Vector<Integer> distribucio = ctrlPersistencia.getDistribucio(id);
        System.out.println("Distribució obtinguda: " + distribucio);
    }

    /**
     * Prova el mètode existId de la classe CtrlPersistencia
     */
    public void testExistID() {
        System.out.print("Introdueix l'ID a comprovar: ");
        String id = scanner.nextLine();
        boolean exists = ctrlPersistencia.existId(id);
        System.out.println("ID exists: " + exists);
    }

    /**
     * Prova el mètode guardarSistema de la classe CtrlPersistencia
     */
    public void testGuardarSistema() {
        //TRATAR SI ESTA VACIO ES DECIR SI SE GUARDA ANTES DE CARGAR
        System.out.print("Introdueix el nom del fitxer per guardar el sistema: ");
        String nomFitxer = scanner.nextLine();
        try {
            ctrlPersistencia.guardarSistema(nomFitxer);
            System.out.println("Sistema guardat correctament al fitxer '" + nomFitxer + "'.");
        } catch (IOException e) {
            System.out.println("Error: No s'ha pogut guardar el sistema al fitxer '" + nomFitxer + "'.");
        }
    }

    /**
     * Prova el mètode carregarSistema de la classe CtrlPersistencia
     */
    public void testCarregarSistema() {
        //NO ME SALTA ERROR NOSE PORQUE
        System.out.print("Introdueix el nom del fitxer per carregar el sistema: ");
        String nomFitxer = scanner.nextLine();
        try {
            ctrlPersistencia.carregarSistema(nomFitxer);
            System.out.println("Sistema carregat correctament del fitxer '" + nomFitxer + "'.");
        } catch (IOException e) {
            System.out.println("Error: No s'ha pogut carregar el sistema del fitxer '" + nomFitxer + "'.");
        }
    }

    /**
     * Prova el mètode guardarTot de la classe CtrlPersistencia
     */
    public void testGuardarTot() {
        System.out.print("Introdueix l'ID: ");
        String id = scanner.nextLine();
        System.out.print("Introdueix l'altura: ");
        int altura = Integer.parseInt(scanner.nextLine());
        System.out.print("Introdueix la distribució (valors separats per comes): ");
        String[] distribucioInput = scanner.nextLine().split(",");
        Vector<Integer> distribucio = new Vector<>();
        for (String s : distribucioInput) {
            distribucio.add(Integer.parseInt(s.trim()));
        }
        // Construir el catàleg basant-se en la distribució
        Map<Integer, Producte> cataleg = new HashMap<>();
        for (int i = 0; i < distribucio.size(); i++) {
            Producte producte = new Producte(i, "Producte" + i, i);
            cataleg.put(i, producte);
        }
        // Construir la relació basant-se en els productes
        Map<Pair<Integer, Integer>, Integer> relacio = new HashMap<>();
        for (int i = 0; i < distribucio.size(); i++) {
            for (int j = i + 1; j < distribucio.size(); j++) {
                relacio.put(new Pair<>(i, j), 50);
            }
        }

        ctrlPersistencia.guardarTot(id, altura, distribucio, cataleg, relacio);
    }

    /**
     * Prova el mètode guardarTotFitxer de la classe CtrlPersistencia
     */
    public void testGuardarTotFitxer() {
        System.out.print("Introdueix el nom del fitxer: ");
        String nomFitxer = scanner.nextLine();
        System.out.print("Introdueix l'altura: ");
        int altura = scanner.nextInt();
        scanner.nextLine(); // Consumir el salt de línia
        System.out.print("Introdueix la distribució (valors separats per comes): ");
        String[] distribucioInput = scanner.nextLine().split(",");
        Vector<Integer> distribucio = new Vector<>();
        for (String s : distribucioInput) {
            distribucio.add(Integer.parseInt(s.trim()));
        }
        // Construir el catàleg basant-se en la distribució
        Map<Integer, Producte> cataleg = new HashMap<>();
        for (int i = 0; i < distribucio.size(); i++) {
            Producte producte = new Producte(i, "Producte" + i, i);
            cataleg.put(i, producte);
        }
        // Construir la relació basant-se en els productes
        Map<Pair<Integer, Integer>, Integer> relacio = new HashMap<>();
        for (int i = 0; i < distribucio.size(); i++) {
            for (int j = i + 1; j < distribucio.size(); j++) {
                relacio.put(new Pair<>(i, j), 50);
            }
        }

        try {
            ctrlPersistencia.guardarTotFitxer(nomFitxer, altura, distribucio, cataleg, relacio);
            System.out.println("Tots els components s'han guardat al fitxer '" + nomFitxer + "'.");
        } catch (IOException e) {
            System.out.println("Error: No s'han pogut guardar els components al fitxer '" + nomFitxer + "'.");
        }
    }

    /**
     * Prova el mètode getFromFitxer de la classe CtrlPersistencia
     */
    public void testGetFromFitxer() {
        System.out.print("Introdueix el nom del fitxer: ");
        String nomFitxer = scanner.nextLine();
        try {
            Map<Integer, Producte> cataleg = ctrlPersistencia.getCatalegFitxer(nomFitxer);
            Map<Pair<Integer, Integer>, Integer> relacio = ctrlPersistencia.getRelacioFitxer(nomFitxer);
            int altura = ctrlPersistencia.getAlturaFitxer(nomFitxer);
            Vector<Integer> distribucio = ctrlPersistencia.getDistribucioFitxer(nomFitxer);

            System.out.println("Catàleg: " + cataleg);
            System.out.println("Relació: " + relacio);
            System.out.println("Altura: " + altura);
            System.out.println("Distribució: " + distribucio);
        } catch (IOException e) {
            System.out.println("Error: No s'han pogut obtenir els components del fitxer '" + nomFitxer + "'.");
        }
    }

    /**
     * Mètode principal del driver
     * @param args Arguments de la línia de comandes
     */
    public static void main(String[] args) {
        DriverCtrlPersistencia driver = new DriverCtrlPersistencia();
        driver.scanner = new Scanner(System.in);
        boolean sortir = false;
        driver.mostrarMenu();

        while (!sortir) {
            System.out.print("Selecciona una opció: ");
            int opcio = Integer.parseInt(driver.scanner.nextLine());
            try {
                switch (opcio) {
                    case 1 : driver.testConstructora();
                    case 2 : driver.testGetCataleg();
                    case 3 : driver.testGetRelacio();
                    case 4 : driver.testGetAltura();
                    case 5 : driver.testGetDistribucio();
                    case 6 : driver.testExistID();
                    case 7 : driver.testGuardarSistema();
                    case 8 : driver.testCarregarSistema();
                    case 9 : driver.testGuardarTot();
                    case 10 : driver.testGuardarTotFitxer();
                    case 11 : driver.testGetFromFitxer();
                    case 12 : driver.mostrarMenu();
                    case 0 : sortir = true;
                    default : System.out.println("Opció no vàlida. Torna a intentar.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        System.out.println("Sortint del driver.");
    }
}
