package main.presentation.classes;

import main.presentation.controllers.CtrlPresentacio;
import main.domain.classes.Producte;

import javax.swing.*;
import java.awt.*;

/**
 * La classe VistaModificarProducte gestiona la interfície d'usuari per modificar un producte existent al catàleg.
 * Permet a l'usuari cercar un producte per ID, editar les seves dades (ID, nom i preu), i guardar els canvis
 * després de realitzar les comprovacions corresponents.
 */
public class VistaModificarProducte {

    /**
     * Constructor de la classe VistaModificarProducte. Aquest mètode mostra una finestra on l'usuari pot
     * cercar i modificar un producte pel seu ID.
     *
     * @param ctrlPresentacio El controlador per gestionar la lògica de presentació i modificació de productes.
     */
    public VistaModificarProducte(CtrlPresentacio ctrlPresentacio) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        //Si el cataleg està buit no deixo fer res
        try {
            ctrlPresentacio.getCataleg();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JTextField idField = new JTextField("Ingressi l'ID del producte a modificar");
        idField.setPreferredSize(new Dimension(300, 20));
        idField.setForeground(Color.GRAY); // Texto del placeholder
        idField.setFont(new Font("Arial", Font.ITALIC, 14));
        idField.setHorizontalAlignment(SwingConstants.LEFT);

        idField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (idField.getText().equals("Ingressi l'ID del producte a modificar")) {
                    idField.setText("");
                    idField.setForeground(Color.BLACK); // Cambiar el color del texto al escribir
                    idField.setFont(new Font("Arial", Font.PLAIN, 14));
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (idField.getText().isEmpty()) {
                    idField.setForeground(Color.GRAY);
                    idField.setFont(new Font("Arial", Font.ITALIC, 14));
                    idField.setText("Ingressi l'ID del producte a modificar");
                }
            }
        });

        panel.add(idField);

        int result = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Modificar Producte",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String productId = idField.getText().trim();
            //tratar si productID es vacio
            if (productId.isEmpty()) {
                JOptionPane.showMessageDialog(null, "El camp ID no pot estar buit.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //Comprovar si l'Id és un enter
            int id = -1;
            try {
                id = Integer.parseInt(productId);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "El camp ID ha de ser un número enter.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            //Agafar el producte a modificar
            Producte p;
            try {
                p = ctrlPresentacio.getProducte(id);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            //Agafar les dades del producte
            Integer idp = p.getId();
            String nomP = p.getNom();
            Integer preuP = p.getPreu();

            //Mostrar les dades als textFields per editar-les
            //Id
            JPanel panel2 = new JPanel();
            panel2.setLayout(new GridLayout(3, 2, 10, 10));
            JLabel labelIdP = new JLabel("ID:");
            labelIdP.setHorizontalAlignment(SwingConstants.CENTER);
            labelIdP.setOpaque(true);
            labelIdP.setBackground(new Color(192, 192, 192));
            labelIdP.setForeground(Color.WHITE);
            JTextField idFieldProd = new JTextField(idp.toString(), 10);

            //Nom
            JLabel labelNomP = new JLabel("Nom:");
            labelNomP.setHorizontalAlignment(SwingConstants.CENTER);
            labelNomP.setOpaque(true);
            labelNomP.setBackground(new Color(192, 192, 192));
            labelNomP.setForeground(Color.WHITE);
            JTextField nomFieldProd = new JTextField(nomP, 10);

            //Preu
            JLabel labelPreuP = new JLabel("Preu:");
            labelPreuP.setHorizontalAlignment(SwingConstants.CENTER);
            labelPreuP.setOpaque(true);
            labelPreuP.setBackground(new Color(192, 192, 192));
            labelPreuP.setForeground(Color.WHITE);
            JTextField preuFieldProd = new JTextField(preuP.toString(), 10);

            //Insertar els elements al panell
            panel2.add(labelIdP);
            panel2.add(idFieldProd);
            panel2.add(labelNomP);
            panel2.add(nomFieldProd);
            panel2.add(labelPreuP);
            panel2.add(preuFieldProd);

            //Mostrar el panell amb les dades del producte
            int result2 = JOptionPane.showConfirmDialog(
                    null,
                    panel2,
                    "Modificar Producte",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.PLAIN_MESSAGE
            );

            //Si es prem OK
            if (result2 == JOptionPane.OK_OPTION) {
                //Agafar les dades dels textFields
                String idProd = idFieldProd.getText().trim();
                String nomProd = nomFieldProd.getText().trim();
                String preuProd = preuFieldProd.getText().trim();

                //Comprovar si els camps estan buits
                if (idProd.isEmpty() || nomProd.isEmpty() || preuProd.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Els camps no poden estar buits.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                //Comprovar si l'Id és un enter
                int idPEditat = -1;
                try {
                    idPEditat = Integer.parseInt(idProd);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "El camp ID ha de ser un número enter.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                //Comprovar si el preu és un enter
                int preuPEditat = -1;
                try {
                    preuPEditat = Integer.parseInt(preuProd);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "El camp Preu ha de ser un número enter.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                //Modificar el producte
                if (id != idPEditat) {
                    try {
                        ctrlPresentacio.modificarIdProducte(id, idPEditat);
                        ctrlPresentacio.actualitzaProductes();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                if (nomP != nomProd) {
                    if (!nomProd.matches(".*[a-zA-Z]+.*") && nomProd.matches("\\d+")) {
                        JOptionPane.showMessageDialog(null, "El camp Nom ha de contenir almenys una lletra.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        try {
                            ctrlPresentacio.modificarNomProducte(idPEditat, nomProd);
                            ctrlPresentacio.actualitzaProductes();
                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                }
                if (preuP != preuPEditat) {
                    try {
                        ctrlPresentacio.modificarPreuProducte(idPEditat, preuPEditat);
                        ctrlPresentacio.actualitzaProductes();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            } else {
                System.out.println("Operació cancel·lada.");
            }
        }else

        {
            System.out.println("Operació cancel·lada.");
        }
    }
}