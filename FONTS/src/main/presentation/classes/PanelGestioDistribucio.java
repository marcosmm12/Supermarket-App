package main.presentation.classes;

import main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Aquesta classe representa un panell de gestió de la distribució.
 * Proporciona botons per aplicar diversos algoritmes i visualitzar o modificar la distribució.
 * Cada botó activa una acció específica, com canviar de vista o aplicar un algoritme.
 *
 * @see CtrlPresentacio
 */
public class PanelGestioDistribucio extends JPanel {

    /**
     * Constructor de la classe.
     * Inicialitza el panell amb botons per gestionar la distribució, aplicant diversos algoritmes o modificant i mostrant la distribució.
     *
     * @param ctrlPresentacio Controlador de presentació que gestiona les accions del panell.
     */
    public PanelGestioDistribucio(CtrlPresentacio ctrlPresentacio) {
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        this.setBackground(Color.white);

        // Crear botons amb icones
        JButton button1 = createButtonWithIcon("icons/aprox.png");
        JButton button2 = createButtonWithIcon("icons/fbruta.png");
        JButton button3 = createButtonWithIcon("icons/modDist.png");
        JButton button4 = createButtonWithIcon("icons/show.png");

        // Assignar accions als botons
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Canviar a l'algoritme d'aproximació
                ctrlPresentacio.canviaAAproximacio();
                JOptionPane.showMessageDialog(null, "Algoritme d'aproximació aplicat.", "Algoritme", JOptionPane.PLAIN_MESSAGE);
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Canviar a l'algoritme de força bruta
                ctrlPresentacio.canviaAFBruta();
                JOptionPane.showMessageDialog(null, "Algoritme de Força Bruta aplicat.", "Algoritme", JOptionPane.PLAIN_MESSAGE);
            }
        });

        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obrir la vista de modificació de distribució
                new VistaModificarDistribucio(ctrlPresentacio);
            }
        });

        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obrir la vista per mostrar la distribució
                new VistaMostrarDistribucio(ctrlPresentacio);
            }
        });

        // Afegir els botons al panell
        this.add(button1);
        this.add(button2);
        this.add(button3);
        this.add(button4);
    }

    /**
     * Crea un JButton amb una icona del fitxer especificat.
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
}
