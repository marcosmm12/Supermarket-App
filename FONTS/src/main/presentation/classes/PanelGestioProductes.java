package main.presentation.classes;

import main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Aquesta classe representa un panell per gestionar productes.
 * Proporciona botons per afegir, eliminar, modificar, consultar productes i relacions,
 * així com per consultar el catàleg complet de productes.
 *
 * @see CtrlPresentacio
 */
public class PanelGestioProductes extends JPanel {

    /**
     * Constructor de la classe.
     * Inicialitza el panell amb botons per a diverses operacions relacionades amb els productes,
     * com afegir, eliminar, modificar, consultar, i gestionar relacions.
     *
     * @param ctrlPresentacio Controlador de presentació que gestiona les accions del panell.
     */
    public PanelGestioProductes(CtrlPresentacio ctrlPresentacio) {
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        this.setBackground(Color.white);

        // Crear botons amb icones
        JButton button1 = createButtonWithIcon("icons/add.png");
        JButton button2 = createButtonWithIcon("icons/delete.png");
        JButton button3 = createButtonWithIcon("icons/edit.png");
        JButton button4 = createButtonWithIcon("icons/search.png");
        JButton button5 = createButtonWithIcon("icons/modRel.png");
        JButton button6 = createButtonWithIcon("icons/consultarCataleg.png");

        // Assignar accions als botons
        button1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obrir la vista per afegir un nou producte
                VistaAfegirProducte vistaAfegirProducte = new VistaAfegirProducte(ctrlPresentacio);
                vistaAfegirProducte.setVisible(true);
            }
        });

        button2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obrir la vista per eliminar un producte
                new VistaEliminarProducte(ctrlPresentacio);
            }
        });

        button3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obrir la vista per modificar un producte
                new VistaModificarProducte(ctrlPresentacio);
            }
        });

        button4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obrir la vista per consultar un producte
                new VistaConsultarProducto(ctrlPresentacio);
            }
        });

        button5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obrir la vista per modificar relacions entre productes
                new VistaModificarRelacio(ctrlPresentacio);
            }
        });

        button6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Obrir la vista per consultar el catàleg de productes
                new VistaConsultarCataleg(ctrlPresentacio);
            }
        });

        // Afegir els botons al panell
        this.add(button1);
        this.add(button2);
        this.add(button3);
        this.add(button4);
        this.add(button5);
        this.add(button6);
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

        try {
            ImageIcon originalIcon = new ImageIcon(filePath);
            Image scaledImage = originalIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(scaledImage);
            button.setIcon(resizedIcon);
        } catch (Exception e) {
            System.err.println("No s'ha pogut carregar la icona: " + filePath);
        }

        return button;
    }
}