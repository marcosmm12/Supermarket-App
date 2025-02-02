package main.presentation.classes;

import main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;

import java.awt.*;
import java.util.Vector;
import java.util.ArrayList;

import main.domain.exceptions.ExcepcioCatalegBuit;
import main.domain.libs.Pair;

/**
 * Classe VistaAfegirRelacions que proporciona una interfície gràfica per afegir relacions
 * entre productes dins del sistema. Mostra els IDs dels productes existents i permet
 * que l'usuari introdueixi un valor de relació per a cadascun d'ells.
 */
public class VistaAfegirRelacions {
    private CtrlPresentacio ctrlPresentacio;

    /**
     * Constructor de la classe VistaAfegirRelacions.
     *
     * @param ictrlPresentacio Instància del controlador de presentació per interactuar amb la lògica de negoci.
     * @param vistaAfegirProducte Instància de VistaAfegirProducte per actualitzar les relacions afegides.
     */
    public VistaAfegirRelacions(CtrlPresentacio ictrlPresentacio, VistaAfegirProducte vistaAfegirProducte) {
        ctrlPresentacio = ictrlPresentacio;

        //Crear el panell principal
        JPanel panelGeneral = new JPanel();
        panelGeneral.setLayout(new GridLayout(0, 1, 10, 10));

        //Agafar els ids dels productes del sistema
        Vector<Integer> ids = new Vector<Integer>();
        try {ids = ctrlPresentacio.agafaIdsProductes();}
        catch (ExcepcioCatalegBuit e) {
            JOptionPane.showMessageDialog(null, "No hi ha cap producte al sistema", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Crear les files de Id y textField per introduir la relacio
        for (int i = 0; i < ids.size(); i++) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1,2,10,10));

            JLabel label = new JLabel("ID " + ids.get(i));
            //Fer el label mes gran
            label.setPreferredSize(new Dimension(10,15));
            //Posar color al label
            label.setOpaque(true);
            label.setForeground(Color.WHITE);
            label.setBackground(new Color(192, 192, 192));
            //Centrar el text
            label.setHorizontalAlignment(SwingConstants.CENTER);

            JTextField idField = new JTextField(10);
            idField.setPreferredSize(new Dimension(10,15));

            panel.add(label);
            panel.add(idField);
            panel.setPreferredSize(new Dimension(10,15));
            panelGeneral.add(panel);
        }

        //Creo un panell amb scrollBar
        JScrollPane scrollPane = new JScrollPane(panelGeneral);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        int preferredheight = 200;
        //if (ids.size() == 2) preferredheight = 25;
        //if (ids.size() == 1) preferredheight = 100;
        scrollPane.setPreferredSize(new Dimension(400, preferredheight)); // Tamaño visible del JScrollPane

        //Botó de confirmar y cancelar l'operació
        int result = JOptionPane.showConfirmDialog(
                null,
                scrollPane,
                "Afegir Relacions",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        //Si es prem OK
        if (result == JOptionPane.OK_OPTION) {
            //Agafar les relacions introduides
            ArrayList<Pair<Integer, Integer>> relacions = new ArrayList<Pair<Integer, Integer>>();
            for (int i = 0; i < ids.size(); i++) {
                JPanel panel = (JPanel) panelGeneral.getComponent(i);
                JTextField textRelacio = (JTextField) panel.getComponent(1);
                if (!textRelacio.getText().isEmpty()) {
                    try {
                        Integer relacio = Integer.parseInt(textRelacio.getText());
                        if (relacio < 0 || relacio > 100) {
                            JOptionPane.showMessageDialog(null, "Les relacions han de ser un número entre 0 i 100", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                        relacions.add(new Pair<Integer, Integer>(ids.get(i), relacio));
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Totes les relacions han de ser un número enter", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }
            //Afegir les relacions al sistema
            vistaAfegirProducte.setRelacions(relacions);
        }

        //Si es prem Cancel
        if (result == JOptionPane.CANCEL_OPTION) {
            System.out.println("Operació cancel·lada.");
        }

    }
}