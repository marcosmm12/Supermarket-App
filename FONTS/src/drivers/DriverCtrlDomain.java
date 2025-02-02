package drivers;

import main.domain.controllers.*;
import main.domain.classes.*;
import main.domain.exceptions.*;
import main.domain.libs.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * DriverCtrlDomain de la classe CtrlDomain, classe controladora del domini.
 * Aquest driver permet executar les funcionalitats de la classe CtrlDomain.
 * @author MarcosMartinezMartinez
 * @author erikGauchia
 */
public class DriverCtrlDomain {
    /**
     * Atributs de la classe CtrlDomain.
     * ctrlDomain: Instància de la classe CtrlDomain.
     * mode: Mode d'entrada de les dades (manual o fitxer).
     * reader: Buffer de lectura de fitxers.
     */
    private CtrlDomain ctrlDomain;
    int mode = 0;  // 1: manual, 2: fitxer
    BufferedReader reader = null;

    /**
     * Constructor de la classe DriverCtrlDomain.
     * @param mode Mode d'entrada de les dades (manual o fitxer).
     * @param reader Buffer de lectura de fitxers.
     */
    public DriverCtrlDomain(int mode, BufferedReader reader) {
        ctrlDomain = new CtrlDomain();
        this.mode = mode;
        this.reader = reader;
    }

    /**
     * Metode per obtenir el cataleg de productes.
     */
    public void testGetCataleg() {
        try {
            Map<Integer, Producte> cataleg = ctrlDomain.getCataleg();
            System.out.println("Productes del catàleg:");

            for (Map.Entry<Integer, Producte> producte : cataleg.entrySet()) {
                System.out.println("Id: " + producte.getValue().getId() + " Nom: " + producte.getValue().getNom() +
                        " Preu: " + producte.getValue().getPreu());
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Metode per obtenir un producte del cataleg.
     */
    public void GetProducte() {
        try {
            //CORECCION!!!: No se puede acceder a cataleg directamente, hay que hacerlo a través de CtrlDomain
            //Map<Integer, Producte> cataleg = ctrlDomain.getCataleg();
            int id = 0;
            if (mode == 1) id = introdueixEnter("ID");
            else {
                id = introdueixEnterDesDeFitxer(reader, "ID");
            }
            String nom = ctrlDomain.getProducteNom(id);
            int preu = ctrlDomain.getProductePreu(id);
            System.out.println("Id: " + id + " Nombre: " + nom + " Preu: " + preu);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Metode per afegir un producte al cataleg.
     */
    public void afegirProducte() {
        try {
            // Recolectar información básica del producto
            int id = 0;
            if (mode == 1) id = introdueixEnter("ID");
            else id = introdueixEnterDesDeFitxer(reader, "ID");
            String nom = "";
            if (mode == 1) nom = introdueixNom();
            else nom = intodueixNomDesDeFitxer(reader);
            int preu = 0;
            if (mode == 1) preu = introdueixEnter("preu");
            else preu = introdueixEnterDesDeFitxer(reader, "preu");
            Map<Integer, Integer> relacionsMap = new HashMap<>();
            System.out.println("Introdueix les relacions del producte (id, pes) (101 per acabar):");

            boolean continuar = true;
            while (continuar) {
                int id2 = 0;
                if (mode == 1) id2 = introdueixEnter("ID del producte relacionat");
                else id2 = introdueixEnterDesDeFitxer(reader, "ID del producte relacionat");
                // Si id2 es 101, dejamos de pedir relaciones
                if (id2 == 101) {
                    continuar = false; // Cambiar la variable para salir del bucle
                } else {
                    if (!ctrlDomain.existProducte(id2)) {
                        System.out.println("El producte amb ID " + id2 + " no existeix. Torna a introduir un ID.");
                    } else if (relacionsMap.containsKey(id2)) {
                        System.out.println("Aquest producte ja ha sigut relacionat. Torna a introduir un ID.");
                    } else {
                        int pes = 0;
                        if (mode == 1) pes = introdueixEnter("pes (valor entre 0 i 100)");
                        else pes = introdueixEnterDesDeFitxer(reader, "pes (valor entre 0 i 100)");
                        while (pes < 0 || pes > 100) {
                            System.out.println("El pes ha de ser un valor entre 0 i 100. Torna a introduir el pes.");
                            if (mode == 1) pes = introdueixEnter("pes (valor entre 0 i 100)");
                            else pes = introdueixEnterDesDeFitxer(reader, "pes (valor entre 0 i 100)");
                        }
                        relacionsMap.put(id2, pes);
                    }
                }
            }

            List<Pair<Integer, Integer>> relacionsList = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : relacionsMap.entrySet()) {
                relacionsList.add(new Pair<>(entry.getKey(), entry.getValue()));
            }
            ctrlDomain.afegirProducte(id, nom, preu, relacionsList);
            System.out.println("Producte afegit correctament.");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Metode per eliminar un producte del cataleg.
     */
    public void EliminarProducte() {
        try {
            Map<Integer, Producte> cataleg = ctrlDomain.getCataleg();

            int id = 0;
            if (mode == 1) id = introdueixEnter("ID");
            else id = introdueixEnterDesDeFitxer(reader, "ID");
            ctrlDomain.eliminarProducte(id);

            System.out.println("Producte eliminat.");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Metode per modificar un producte del cataleg.
     */
    public void modificarProducte() {
        System.out.println("Selecciona què vols fer:");
        System.out.println("1 - Modificar ID");
        System.out.println("2 - Modificar nom");
        System.out.println("3 - Modificar preu");
        System.out.println("4 - Modificar una relació");

        int comanda = 0;
        if (mode == 1) comanda = introdueixEnter("opció (número entre 1 i 4)");
        else {
            try {
                comanda = introdueixEnterDesDeFitxer(reader, "opció (número entre 1 i 4)");
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        while (comanda < 1 && comanda > 4) {
            System.out.println("Opció incorrecta. ");
            if (mode == 1) comanda = introdueixEnter("opció (número entre 1 i 4)");
            else {
                try {
                    comanda = introdueixEnterDesDeFitxer(reader, "opció (número entre 1 i 4)");
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
        switch (comanda) {
            case 1:
                modificarIdProducte();
                break;
            case 2:
                modificarNomProducte();
                break;
            case 3:
                modificarPreuProducte();
                break;
            case 4:
                modificarRelacio();
                break;
            default:
                System.out.println("Opció incorrecta.");
        }
    }

    /**
     * Metode per modificar l'ID d'un producte del cataleg.
     */
    private void modificarIdProducte() {
        try {
            int id = 0;
            if (mode == 1) id = introdueixEnter("ID");
            else id = introdueixEnterDesDeFitxer(reader, "ID");
            int nouId = 0;
            if (mode == 1) nouId = introdueixEnter("nou ID");
            else nouId = introdueixEnterDesDeFitxer(reader, "nou ID");
            ctrlDomain.modificarIdProducte(id, nouId);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Metode per modificar el nom d'un producte del cataleg.
     */
    private void modificarNomProducte() {
        try {
            int id = 0;
            if (mode == 1) id = introdueixEnter("ID");
            else id = introdueixEnterDesDeFitxer(reader, "ID");
            String nouNom = "";
            if (mode == 1) nouNom = introdueixNom();
            else nouNom = intodueixNomDesDeFitxer(reader);
            ctrlDomain.modificarNomProducte(id, nouNom);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Metode per modificar el preu d'un producte del cataleg.
     */
    private void modificarPreuProducte() {
        try {
            int id = 0;
            if (mode == 1) id = introdueixEnter("ID");
            else id = introdueixEnterDesDeFitxer(reader, "ID");
            int nouPreu = 0;
            if (mode == 1) nouPreu = introdueixEnter("nou preu");
            else nouPreu = introdueixEnterDesDeFitxer(reader, "nou preu");
            ctrlDomain.modificarPreuProducte(id, nouPreu);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Metode per modificar una relacio entre dos productes del cataleg.
     */
    private void modificarRelacio() {
        try {
            int id1 = 0;
            if (mode == 1) id1 = introdueixEnter("ID");
            else id1 = introdueixEnterDesDeFitxer(reader, "ID");
            int id2 = 0;
            if (mode == 1) id2 = introdueixEnter("ID");
            else id2 = introdueixEnterDesDeFitxer(reader, "ID");
            int relacio = 0;
            if (mode == 1) relacio = introdueixEnter("relació (valor entre 0 i 100)");
            else relacio = introdueixEnterDesDeFitxer(reader, "relació (valor entre 0 i 100)");
            while (relacio < 0 || relacio > 100) {
                System.out.println("Relació incorrecta.");
                relacio = 0;
                if (mode == 1) relacio = introdueixEnter("relació (valor entre 0 i 100)");
                else relacio = introdueixEnterDesDeFitxer(reader, "relació (valor entre 0 i 100)");
            }
            ctrlDomain.modificarRelacio(id1, id2, relacio);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Metode per escollir l'algorisme a utilitzar.
     */
    public void escollirTipusAlgorisme() {
        System.out.println("Selecciona quin algorisme vols utilitzar:");
        System.out.println("1 - Força bruta");
        System.out.println("2 - Aproximació");

        int comanda = 0;
        if (mode == 1) comanda = introdueixEnter("opció (número entre 1 i 2)");
        else {
            try {
                comanda = introdueixEnterDesDeFitxer(reader, "opció (número entre 1 i 2)");
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        while (comanda < 1 && comanda > 2) {
            System.out.println("Opció incorrecta. ");
            comanda = 0;
            if (mode == 1) comanda = introdueixEnter("opció (número entre 1 i 2)");
            else {
                try {
                    comanda = introdueixEnterDesDeFitxer(reader, "opció (número entre 1 i 2)");
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
        switch (comanda) {
            case 1:
                escollirAlgoritmeForcaBruta();
                break;
            case 2:
                escollirAlgoritmeAproximacio();
                break;
            default:
                System.out.println("Opció incorrecta.");
        }
    }

    /**
     * Metode per escollir l'algorisme de força bruta.
     */
    private void escollirAlgoritmeForcaBruta() {
        try {
            ctrlDomain.escollirAlgoritmeForcaBruta();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Metode per escollir l'algorisme d'aproximació.
     */
    private void escollirAlgoritmeAproximacio() {
        try {
            ctrlDomain.escollirAlgoritmeAproximacio();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Metode per modificar la distribució actual.
     */
    public void modificarDistribucio() {
        try {
            int id1 = 0;
            if (mode == 1) id1 = introdueixEnter("ID (primer producte que vols cambiar de posició)");
            else id1 = introdueixEnterDesDeFitxer(reader, "ID (primer producte que vols cambiar de posició)");
            int id2 = 0;
            if (mode == 1) id2 = introdueixEnter("ID (segon producte que vols cambiar de posició)");
            else id2 = introdueixEnterDesDeFitxer(reader, "ID (segon producte que vols cambiar de posició)");
            ctrlDomain.modificarDistribucio(id1, id2);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Metode per consultar la distribució actual.
     */
    public void consultarDistribucio() {
        try {
            Vector<Integer> distribucio = ctrlDomain.getDistribucio();
            int altura = ctrlDomain.getAltura(); // Altura fija: 3
            int num_elements_fila = (int) Math.ceil((double) distribucio.size() / altura); // Calcular columnas necesarias

            // Imprimir los productos actuales en el catálogo
            System.out.println("Distribució actual:");

            // Crear una matriz para almacenar los elementos por filas y columnas
            String[][] matriz = new String[altura][num_elements_fila];

            int index = 0;

            // Llenar la matriz con los productos
            for (int i = 0; i < altura; ++i) {
                for (int j = 0; j < num_elements_fila; ++j) {
                    if (index < distribucio.size()) {
                        matriz[i][j] = distribucio.get(index).toString();
                        index++;
                    } else {
                        matriz[i][j] = "-"; // Relleno vacío
                    }
                }
            }

            // Imprimir la matriz de la forma que deseas
            for (int i = 0; i < altura; ++i) {
                if (i % 2 == 1) {
                    // Si la fila es impar, imprimir de derecha a izquierda
                    for (int j = num_elements_fila - 1; j >= 0; --j) {
                        System.out.print(matriz[i][j] + " ");
                    }
                } else {
                    // Si la fila es par, imprimir de izquierda a derecha
                    for (int j = 0; j < num_elements_fila; ++j) {
                        System.out.print(matriz[i][j] + " ");
                    }
                }
                System.out.println(); // Salto de línea después de cada fila
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Metode per guardar la distribució actual.
     */
    public void guardarDistribucio() {
        System.out.println("Selecciona què vols fer:");
        System.out.println("1 - Guardar la distribució actual (al programa)");
        System.out.println("2 - Guardar la distribució actual en un fitxer");

        int comanda = 0;
        if (mode == 1) comanda = introdueixEnter("opció (número entre 1 i 2)");
        else {
            try {
                comanda = introdueixEnterDesDeFitxer(reader, "opció (número entre 1 i 2)");
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        while (comanda < 1 && comanda > 2) {
            System.out.println("Opció incorrecta. ");
            comanda = 0;
            if (mode == 1) comanda = introdueixEnter("opció (número entre 1 i 2)");
            else {
                try {
                    comanda = introdueixEnterDesDeFitxer(reader, "opció (número entre 1 i 2)");
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
        switch (comanda) {
            case 1:
                guardarDistribucioPrograma();
                break;
            case 2:
                guardarDistribucioFitxer();
                break;
            default:
                System.out.println("Opció incorrecta.");
        }
    }

    /**
     * Metode per guardar la distribucio actual al programa.
     */
    private void guardarDistribucioPrograma() {
        String nom = "";
        if (mode == 1) nom = introdueixNom();
        else {
            try {
                nom = intodueixNomDesDeFitxer(reader);
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        try {
            ctrlDomain.guardarDistribucio(nom);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Metode per guardar la distribucio actual en un fitxer.
     */
    private void guardarDistribucioFitxer() {
        String nom = "";
        if (mode == 1) nom = introdueixNom();
        else {
            try {
                nom = intodueixNomDesDeFitxer(reader);
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        try {
            ctrlDomain.guardarDistribucioFitxer(nom);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Metode per carregar una distribucio.
     */
    public void carregarDistribucio() {
        System.out.println("Selecciona què vols fer:");
        System.out.println("1 - Carregar una distribució guardada (al programa)");
        System.out.println("2 - Carregar una distribució guardada a un fitxer");

        int comanda = 0;
        if (mode == 1) comanda = introdueixEnter("opció (número entre 1 i 2)");
        else {
            try {
                comanda = introdueixEnterDesDeFitxer(reader, "opció (número entre 1 i 2)");
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        while (comanda < 1 && comanda > 2) {
            System.out.println("Opció incorrecta. ");
            comanda = 0;
            if (mode == 1) comanda = introdueixEnter("opció (número entre 1 i 2)");
            else {
                try {
                    comanda = introdueixEnterDesDeFitxer(reader, "opció (número entre 1 i 2)");
                } catch (IOException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
        switch (comanda) {
            case 1:
                carregarDistribucioPrograma();
                break;
            case 2:
                carregarDistribucioFitxer();
                break;
            default:
                System.out.println("Opció incorrecta.");
        }
    }

    /**
     * Metode per carregar una distribucio guardada al programa.
     */
    private void carregarDistribucioPrograma() {
        String nom = "";
        if (mode == 1) nom = introdueixNom();
        else {
            try {
                nom = intodueixNomDesDeFitxer(reader);
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        try {
            ctrlDomain.carregarDistribucio(nom);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Metode per carregar una distribucio guardada en un fitxer.
     */
    private void carregarDistribucioFitxer() {
        String nom = "";
        if (mode == 1) nom = introdueixNom();
        else {
            try {
                nom = intodueixNomDesDeFitxer(reader);
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        try {
            ctrlDomain.carregarDistribucioFitxer(nom);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Metode per carregar les dades del sistema.
     */
    private void carregarSistema() {
        String nomFitxer = "";
        if (mode == 1) nomFitxer = introdueixNom();
        else {
            try {
                nomFitxer = intodueixNomDesDeFitxer(reader);
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        try {
            ctrlDomain.carregarSistema(nomFitxer);
            System.out.println("Sistema carregat correctament");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Metode per guardar les dades del sistema.
     */
    private void guardarSistema() {
        String nomFitxer = "";
        if (mode == 1) nomFitxer = introdueixNom();
        else {
            try {
                nomFitxer = intodueixNomDesDeFitxer(reader);
            } catch (IOException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
        try {
            ctrlDomain.guardarSistema(nomFitxer);
            System.out.println("Sistema guardat correctament");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Metode per mostrar els mètodes disponibles.
     */
    //Mètodes disponibles
    public void showMethods() {
        System.out.println("Opcions disponibles:");
        System.out.println("1 - Consultar el catàleg de productes");
        System.out.println("2 - Consultar un producte");
        System.out.println("3 - Afegir un producte");
        System.out.println("4 - Eliminar un producte");
        System.out.println("5 - Modificar un producte");
        System.out.println("6 - Escollir tipus d'algorisme");
        System.out.println("7 - Modificar la distribució actual");
        System.out.println("8 - Consultar la distribució actual");
        System.out.println("9 - Guardar la distribució actual");
        System.out.println("10 - Carregar una distribució guardada");
        System.out.println("11 - Carregar dades del sistema");
        System.out.println("12 - Guardar dades del sistema");
        System.out.println("13 - Mostrar els mètodes disponibles");
        System.out.println("0 - Sortir");
    }

    /**
     * Metode per introduir un enter.
     * @param atribut Nom de l'atribut.
     * @return Enter introduit.
     */
    private static int introdueixEnter(String atribut) {
        Scanner teclat = new Scanner(System.in);
        int enter = -1;
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Introdueix un/a " + atribut + ": ");
            if (teclat.hasNextInt()) {
                enter = teclat.nextInt();
                validInput = true;
            } else {
                System.out.println("Entrada incorrecta. Torna a introduir un/a " + atribut + " (número).");
                teclat.next();
            }
        }
        return enter;
    }

    /**
     * Metode per introduir un nom.
     * @return Nom introduit.
     */
    private String introdueixNom() {
        Scanner teclat = new Scanner(System.in);
        String nom = "";
        boolean validInput = false;
        while (!validInput) {
            System.out.print("Introdueix un nom: ");
            if (teclat.hasNext()) {
                nom = teclat.next();
                validInput = true;
            } else {
                System.out.println("Entrada incorrecta. Torna a introduir un nom (String).");
                teclat.next();
            }
        }
        return nom;
    }

    /**
     * Metode per introduir un enter des de fitxer.
     * @param reader Buffer de lectura de fitxers.
     * @param atribut Nom de l'atribut.
     * @return Enter introduit.
     * @throws IOException
     */
    private int introdueixEnterDesDeFitxer(BufferedReader reader, String atribut) throws IOException {
        int enter = -1;
        boolean validInput = false;
        while (!validInput) {
            try {
                String line = reader.readLine();
                if (line != null && !line.isEmpty()) {
                    try {
                        enter = Integer.parseInt(line);
                        validInput = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Error: l'atribut " + atribut + " ha de ser un número.");
                    }
                }
            } catch (IOException e) {
                System.out.println("Error: no s'ha pogut llegir l'atribut " + atribut + " del fitxer.");
                return -1;
            }
        }
        return enter;
    }

    /**
     * Metode per introduir un nom des de fitxer.
     * @param reader Buffer de lectura de fitxers.
     * @return Nom introduit.
     * @throws IOException
     */
    private String intodueixNomDesDeFitxer(BufferedReader reader) throws IOException {
        String nom = "";
        boolean validInput = false;
        while (!validInput) {
            try {
                String line = reader.readLine();
                if (line != null && !line.isEmpty()) {
                    nom = line;
                    validInput = true;
                }
            } catch (IOException e) {
                System.out.println("Error: no s'ha pogut llegir el nom del fitxer.");
                return "";
            }
        }
        return nom;
    }

    /**
     * Metode per calcular el mínim entre dos enters.
     * @param a Primer enter.
     * @param b Segon enter.
     * @return Mínim entre a i b.
     */
    private int min(int a, int b) {
        if (a < b) return a;
        return b;
    }

    /**
     * Metode principal del driver.
     * @param arg Arguments de la línia de comandes.
     */
    public static void main(String[] arg) {
        //Demanem si vol fer entrada manual o per fitxer
        Scanner scanner = new Scanner(System.in);
        System.out.println("Selecciona el mode d'entrada de les dades: ");
        System.out.println("1 - Entrada manual");
        System.out.println("2 - Entrada per fitxer");
        int mode = 0;
        mode = introdueixEnter("mode (1 o 2)");
        //Lectura des de fitxer
        if (mode == 2) {
            System.out.println("Introdueix el nom del fitxer: ");
            String nomFitxer = scanner.nextLine();
            try {
                BufferedReader reader = null;
                String path = System.getProperty("user.dir") + "/EXE/inputs/" + nomFitxer;
                reader = new BufferedReader(new FileReader(path));
                DriverCtrlDomain dcd = new DriverCtrlDomain(mode, reader);
                int opcio = dcd.introdueixEnterDesDeFitxer(reader, "opció (número entre 0 i 13)");
                while (opcio != 0) {
                    switch (opcio) {
                        case 1:
                            dcd.testGetCataleg();
                            break;
                        case 2:
                            dcd.GetProducte();
                            break;
                        case 3:
                            dcd.afegirProducte();
                            break;
                        case 4:
                            dcd.EliminarProducte();
                            break;
                        case 5:
                            dcd.modificarProducte();
                            break;
                        case 6:
                            dcd.escollirTipusAlgorisme();
                            break;
                        case 7:
                            dcd.modificarDistribucio();
                            break;
                        case 8:
                            dcd.consultarDistribucio();
                            break;
                        case 9:
                            dcd.guardarDistribucio();
                            break;
                        case 10:
                            dcd.carregarDistribucio();
                            break;
                        case 11:
                            dcd.carregarSistema();
                            break;
                        case 12:
                            dcd.guardarSistema();
                            break;
                        case 13:
                            dcd.showMethods();
                            break;
                        default:
                            System.out.println("Opció incorrecta.");
                    }
                    opcio = dcd.introdueixEnterDesDeFitxer(reader, "opció (número entre 0 i 13)");
                }
            } catch (Exception e) {
                System.out.println("Error a l'obrir el fitxer " + nomFitxer + ": " + e.getMessage());
                return;
            }
        } else {
            BufferedReader reader = null;
            DriverCtrlDomain dcd = new DriverCtrlDomain(mode, reader);
            dcd.showMethods();
            int opcio = dcd.introdueixEnter("opció (número entre 0 i 13)");
            while (opcio != 0) {
                switch (opcio) {
                    case 1:
                        dcd.testGetCataleg();
                        break;
                    case 2:
                        dcd.GetProducte();
                        break;
                    case 3:
                        dcd.afegirProducte();
                        break;
                    case 4:
                        dcd.EliminarProducte();
                        break;
                    case 5:
                        dcd.modificarProducte();
                        break;
                    case 6:
                        dcd.escollirTipusAlgorisme();
                        break;
                    case 7:
                        dcd.modificarDistribucio();
                        break;
                    case 8:
                        dcd.consultarDistribucio();
                        break;
                    case 9:
                        dcd.guardarDistribucio();
                        break;
                    case 10:
                        dcd.carregarDistribucio();
                        break;
                    case 11:
                        dcd.carregarSistema();
                        break;
                    case 12:
                        dcd.guardarSistema();
                        break;
                    case 13:
                        dcd.showMethods();
                        break;
                    default:
                        System.out.println("Opció incorrecta.");
                }
                opcio = dcd.introdueixEnter("opció (número entre 0 i 13)");
            }
        }
    }
}