package main.presentation.classes;

import main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Aquesta classe representa un panell per gestionar les accions de guardar.
 * Proporciona botons per guardar distribucions localment, en fitxers o al sistema.
 *
 * @see CtrlPresentacio
 */
public class PanelGuardar extends JPanel {

    /**
     * Constructor de la classe.
     * Inicialitza el panell amb botons per a diverses opcions de guardar.
     *
     * @param ctrlPresentacio Controlador de presentació que gestiona les accions del panell.
     */
    public PanelGuardar(CtrlPresentacio ctrlPresentacio) {
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        this.setBackground(Color.white);

        // Crear botons amb icones
        JButton button1 = createButtonWithIcon("icons/dolocal.png");
        JButton button2 = createButtonWithIcon("icons/dofile.png");
        JButton button3 = createButtonWithIcon("icons/downsystem.png");

        // Assignar accions als botons
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obrir la vista per guardar localment
                new VistaGuardarLocal(ctrlPresentacio);
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obrir un selector de fitxers per guardar la distribució en un fitxer
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("."));
                int response = fileChooser.showSaveDialog(null);
                if (response == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String nomFitxer = getNomFitxer(selectedFile.getName());
                    try {
                        ctrlPresentacio.guardarDistribucioFitxer(nomFitxer);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obrir un selector de fitxers per guardar la distribució al sistema
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("."));
                int response = fileChooser.showSaveDialog(null);
                if (response == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String nomFitxer = getNomFitxer2(selectedFile.getName());
                    try {
                        ctrlPresentacio.guardarSistemaFitxer(nomFitxer);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        // Afegir botons al panell
        this.add(button1);
        this.add(button2);
        this.add(button3);
    }

    /**
     * Crea un JButton amb una icona del fitxer especificat.
     * Configura la mida i escala la imatge per ajustar-se al botó.
     *
     * @param filePath El camí al fitxer de la icona.
     * @return Un JButton configurat amb la icona especificada.
     */
    private JButton createButtonWithIcon(String filePath) {
        JButton button = new JButton();
        button.setPreferredSize(new Dimension(50, 50));

        ImageIcon originalIcon = new ImageIcon(filePath);
        Image scaledImage = originalIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(scaledImage);

        button.setIcon(resizedIcon);
        return button;
    }

    /**
     * Extreu el nom base del fitxer eliminant els sufixos especificats.
     *
     * @param nom    El nom original del fitxer.
     * @param sufixos Una llista de sufixos per eliminar.
     * @return El nom base del fitxer sense els sufixos especificats.
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
     * Extreu el nom base del fitxer sense els sufixos específics.
     *
     * @param nom El nom original del fitxer.
     * @return El nom base del fitxer.
     */
    private String getNomFitxer(String nom) {
        String[] sufixos = {"Cat.json", "Dist.json", "Rel.json", "Alt.json"};
        return getNomBase(nom, sufixos);
    }

    /**
     * Extreu el nom base del fitxer sense els sufixos específics del sistema.
     *
     * @param nom El nom original del fitxer.
     * @return El nom base del fitxer.
     */
    private String getNomFitxer2(String nom) {
        String[] sufixos = {"CatSistema.json", "DistSistema.json", "RelSistema.json", "AltSistema.json"};
        return getNomBase(nom, sufixos);
    }
}