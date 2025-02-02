package main.presentation.classes;

import main.presentation.controllers.CtrlPresentacio;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

/**
 * Aquesta classe proporciona la finestra principal de l'aplicació per gestionar un supermercat,
 * incloent la gestió dels productes, la distribució, la càrrega i la seguretat de la informació.
 * Inclou diferents pestanyes per accedir a les funcionalitats del sistema i permet guardar l'estat abans de tancar l'aplicació.
 *
 * També implementa la confirmació de l'usuari per desar el sistema quan es vol sortir de l'aplicació.
 */
public class vistaPrincipal {
    private CtrlPresentacio ctrlPresentacio;
    private JFrame frame;
    private PanelProductes panelProductes;

    /**
     * Constructor de la classe vistaPrincipal.
     * Inicialitza la finestra principal, afegeix les pestanyes per gestionar els productes,
     * la distribució, carregar i guardar el sistema. A més, configura la finestra de confirmació per desar
     * abans de sortir de l'aplicació.
     *
     * @param ictrlPresentacio Controlador de la presentació que gestiona les operacions del sistema.
     */
    public vistaPrincipal(CtrlPresentacio ictrlPresentacio) {
        this.ctrlPresentacio = ictrlPresentacio;
        // Crear el marc base
        frame = new JFrame("Gestió del supermercat - Àrea de treball");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);
        // Afegir finestra de confirmació al tancar
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Object[] options = {"Guardar", "No Guardar", "Cancel·lar"};
                int option = JOptionPane.showOptionDialog(
                    frame,
                    "Vols guardar abans de sortir?",
                    "Sortir de l'aplicació",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE,
                    null,
                    options,
                    options[0]
                );
                
                if (option == JOptionPane.YES_OPTION) {
                    // guardar sistema
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setCurrentDirectory(new File("."));
                    int response = fileChooser.showSaveDialog(null);
                    if (response == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = fileChooser.getSelectedFile();
                        String nom = selectedFile.getName();
                        String nomFitxer = getNomFitxer2(nom);
                        try {
                            ctrlPresentacio.guardarSistemaFitxer(nomFitxer);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    System.exit(0);  
                }
                else if (option == JOptionPane.NO_OPTION) {
                    System.exit(0);
                }
                // Si es CANCEL_OPTION no fa res i es manté l'aplicació oberta
            }
        });

        // Crear un contenidor amb pestanyes
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0)); // Marge superior de 10 píxels


        tabbedPane.addTab("Gestió Productes", new PanelGestioProductes(ctrlPresentacio));
        tabbedPane.addTab("Gestió Distribució", new PanelGestioDistribucio(ctrlPresentacio));
        tabbedPane.addTab("Carregar", new PanelCargar(ctrlPresentacio));
        tabbedPane.addTab("Guardar", new PanelGuardar(ctrlPresentacio));

        // Afegir el contenidor de pestanyes en la part superior
        frame.add(tabbedPane, BorderLayout.NORTH);

        // Agregar el PanelProductes con un JScrollPane
        panelProductes = new PanelProductes(ctrlPresentacio);
        JScrollPane scrollPane = new JScrollPane(panelProductes,
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Personalizar el JScrollPane
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Marges exteriors
        scrollPane.getHorizontalScrollBar().setUnitIncrement(16); // Ajustar velocitat de l'scroll horizontal

        frame.add(scrollPane, BorderLayout.CENTER);

        frame.add(Box.createRigidArea(new Dimension(10, 10)), BorderLayout.EAST);
        frame.add(Box.createRigidArea(new Dimension(10, 10)), BorderLayout.WEST);
        frame.add(Box.createRigidArea(new Dimension(10, 10)), BorderLayout.SOUTH);
    }

    /**
     * Estableix la visibilitat de la finestra principal.
     *
     * @param visible Un valor booleà que determina si la finestra serà visible o no.
     */
    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    /**
     * Actualitza la llista de productes mostrada a la finestra.
     * Aquest mètode elimina el panell actual de productes i el substitueix per un de nou.
     */
    public void actualitzaProductes() {
        //Obtenir el JScrollPane
        JScrollPane scrollPane = (JScrollPane) frame.getContentPane().getComponent(1);

        //Treure el panell de producte actual
        scrollPane.getViewport().remove(panelProductes);

        //Crear el nou panell de productes
        panelProductes = new PanelProductes(ctrlPresentacio);

        //Afegir el nou panell de productes al JScrollPane
        scrollPane.setViewportView(panelProductes);

        //Refrescar el frame
        frame.revalidate();
        frame.repaint();
    }

    /**
     * Extreu el nom base del fitxer eliminant els sufixos especificats.
     *
     * @param nom   el nom original del fitxer
     * @param sufixos una llista de sufixos per eliminar
     * @return el nom base del fitxer sense els sufixos especificats
     */
    private String getNomBase(String nom, String[] sufixos) {
        for (String sufix : sufixos) {
            if (nom.endsWith(sufix)) {
                int pos = nom.lastIndexOf(sufix);
                if (pos > 0) {
                    return nom.substring(0, pos);
                }
            }
        }
        return nom;
    }

    /**
     * Extreu el nom base del fitxer sense els sufixos específics del sistema.
     *
     * @param nom el nom original del fitxer
     * @return el nom base del fitxer
     */
    private String getNomFitxer2(String nom) {
        String[] sufixos = {"CatSistema.json", "DistSistema.json", "RelSistema.json", "AltSistema.json"};
        return getNomBase(nom, sufixos);
    }
}