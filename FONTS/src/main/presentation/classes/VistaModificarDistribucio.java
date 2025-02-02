package main.presentation.classes;

import main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;

/**
 * La classe VistaModificarDistribucio mostra una finestra per modificar la distribució de productes al catàleg.
 * Permet a l'usuari intercanviar dos productes en la distribució existent, basant-se en els seus IDs.
 * Abans de permetre la modificació, comprova si el catàleg té més d'un producte i valida que els IDs introduïts siguin vàlids.
 */
public class VistaModificarDistribucio {

    /**
     * Constructor de la classe VistaModificarDistribucio.
     * Crea la finestra per modificar la distribució de productes, on l'usuari pot introduir els IDs dels productes
     * que vol intercanviar. Si no hi ha prou productes al catàleg o si els IDs no són vàlids, mostra missatges d'error.
     * Si la modificació és exitosa, es mostra un missatge d'èxit.
     *
     * @param ctrlPresentacio Controlador que gestiona les operacions de la vista, com ara la modificació de la distribució.
     */
    public VistaModificarDistribucio(CtrlPresentacio ctrlPresentacio) {
        //Si no hi ha cap producte al catàleg, mostrem un missatge d'error
        try {
            ctrlPresentacio.getCataleg();
            if (ctrlPresentacio.getCataleg().size() == 1) {
                JOptionPane.showMessageDialog(null, "Només hi ha 1 producte al catàleg.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Panell general
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3,1,10,10));

        //Text field Id1
        JTextField idField1 = new JTextField("Ingressi l'ID del primer producte");
        idField1.setPreferredSize(new Dimension(300, 20));
        idField1.setForeground(Color.GRAY); // Texto del placeholder
        idField1.setFont(new Font("Arial", Font.ITALIC, 14));
        idField1.setHorizontalAlignment(SwingConstants.LEFT);

        idField1.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (idField1.getText().equals("Ingressi l'ID del primer producte")) {
                    idField1.setText("");
                    idField1.setForeground(Color.BLACK); // Cambiar el color del texto al escribir
                    idField1.setFont(new Font("Arial", Font.PLAIN, 14));
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (idField1.getText().isEmpty()) {
                    idField1.setForeground(Color.GRAY);
                    idField1.setFont(new Font("Arial", Font.ITALIC, 14));
                    idField1.setText("Ingressi l'ID del primer producte");
                }
            }
        });

        //Text field Id2
        JTextField idField2 = new JTextField("Ingressi l'ID del segon producte");
        idField2.setPreferredSize(new Dimension(300, 20));
        idField2.setForeground(Color.GRAY); // Texto del placeholder
        idField2.setFont(new Font("Arial", Font.ITALIC, 14));
        idField2.setHorizontalAlignment(SwingConstants.LEFT);

        idField2.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (idField2.getText().equals("Ingressi l'ID del segon producte")) {
                    idField2.setText("");
                    idField2.setForeground(Color.BLACK); // Cambiar el color del texto al escribir
                    idField2.setFont(new Font("Arial", Font.PLAIN, 14));
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (idField2.getText().isEmpty()) {
                    idField2.setForeground(Color.GRAY);
                    idField2.setFont(new Font("Arial", Font.ITALIC, 14));
                    idField2.setText("Ingressi l'ID del segon producte");
                }
            }
        });

        //Afegir els camps al panell
        panel.add(new JLabel("Ingressa els IDs dels productes que vols intercanviar en la distribució", SwingConstants.CENTER));
        panel.add(idField1);
        panel.add(idField2);

        int result = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Modificar Distribució",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String productId1 = idField1.getText().trim();
            String productId2 = idField2.getText().trim();
            //Comprovar que s'han omplert tots els camps
            if (productId1.isEmpty() || productId2.isEmpty()) {
                JOptionPane.showMessageDialog(null, "S'han d'omplir tots els camps.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //Comprovar que els IDs siguin enters
            Integer id1 = -1;
            Integer id2 = -1;
            try {
                id1 = Integer.parseInt(productId1);
                id2 = Integer.parseInt(productId2);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Els IDs han de ser enters.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //Modificar la distribució
            try {
                ctrlPresentacio.modificarDistribucio(id1, id2);
                JOptionPane.showMessageDialog(null, "Distribució modificada correctament.", "Modificar Distribució", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            System.out.println("Operación cancelada.");
        }
    }
}