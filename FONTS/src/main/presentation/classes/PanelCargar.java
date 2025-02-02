package main.presentation.classes;

import main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * Aquesta classe representa un panell per carregar fitxers a l'aplicació.
 * Proporciona botons per carregar fitxers locals, fitxers del sistema i altres fonts.
 * Cada botó desencadena una acció per carregar els fitxers respectius.
 *
 * @see CtrlPresentacio
 */
public class PanelCargar extends JPanel {

    /**
     * Constructor de la classe.
     * Afegeix botons per carregar fitxers locals, fitxers del sistema i altres fonts.
     * Cada botó desencadena una acció per carregar els fitxers respectius.
     *
     * @param ctrlPresentacio Controlador de presentació
     */
    public PanelCargar(CtrlPresentacio ctrlPresentacio) {
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        this.setBackground(Color.white);

        JButton button1 = createButtonWithIcon("icons/uplocal.png");
        JButton button2 = createButtonWithIcon("icons/upfile.png");
        JButton button3 = createButtonWithIcon("icons/upsystem.png");

        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //ctrme lPresentacio.afageixVista("Vista_CarregarLocal");
                VistaCarregarLocal vistaCarregarLocal = new VistaCarregarLocal(ctrlPresentacio);
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //ctrlPresentacio.afageixVista("Vista_CarregarFitxer");
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("."));
                int response = fileChooser.showOpenDialog(null);
                if (response == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String nom = selectedFile.getName();
                    String nomFitxer = getNomFitxer(nom);
                    try {
                        ctrlPresentacio.carregarDistribucioFitxer(nomFitxer);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //ctrlPresentacio.afageixVista("Vista_CarregarSistema");
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File("."));
                int response = fileChooser.showOpenDialog(null);
                if (response == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String nom = selectedFile.getName();
                    String nomFitxer = getNomFitxer2(nom);
                    try {
                        ctrlPresentacio.carregarSistemaFitxer(nomFitxer);
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        this.add(button1);
        this.add(button2);
        this.add(button3);
    }


    /**
     * Crea un JButton amb una icona del fitxer especificat.
     *
     * @param filePath el camí al fitxer de la icona
     * @return un JButton amb la icona especificada
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
     * Extreu el nom base del fitxer sense els sufixos específics.
     *
     * @param nom el nom original del fitxer
     * @return el nom base del fitxer
     */
    private String getNomFitxer(String nom) {
        String[] sufixos = {"Cat.json", "Dist.json", "Rel.json", "Alt.json"};
        return getNomBase(nom, sufixos);
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