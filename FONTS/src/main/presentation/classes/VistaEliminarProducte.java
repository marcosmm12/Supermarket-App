package main.presentation.classes;

import main.presentation.controllers.CtrlPresentacio;
import main.domain.classes.Producte;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * Classe VistaEliminarProducte que proporciona una interfície gràfica per eliminar un producte del catàleg
 * mitjançant la seva ID. Si l'ID introduïda és vàlida, el producte es elimina del sistema.
 * En cas d'error, es mostra un missatge d'error.
 */
public class VistaEliminarProducte {

    /**
     * Constructor de la classe VistaEliminarProducte.
     * Crea una finestra emergent que permet a l'usuari introduir l'ID d'un producte per eliminar-lo.
     * Mostra un missatge de confirmació si el producte es elimina correctament o un missatge d'error en cas contrari.
     *
     * @param ctrlPresentacio Instància del controlador de presentació per gestionar les operacions del sistema.
     */
    public VistaEliminarProducte(CtrlPresentacio ctrlPresentacio) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        //Si el cataleg esta buit no deixem avançar
        Map<Integer, Producte> c;
        try{c = ctrlPresentacio.getCataleg();}
        catch (Exception e) {
            JOptionPane.showMessageDialog(panel, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JTextField idField = new JTextField("Ingressi l'ID del producte a esborrar");
        idField.setPreferredSize(new Dimension(300, 20));
        idField.setForeground(Color.GRAY); // Texto del placeholder
        idField.setFont(new Font("Arial", Font.ITALIC, 14));
        idField.setHorizontalAlignment(SwingConstants.LEFT);

        idField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (idField.getText().equals("Ingressi l'ID del producte a esborrar")) {
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
                    idField.setText("Ingressi l'ID del producte a esborrar");
                }
            }
        });

        panel.add(idField);

        int result = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Eliminar Producte",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String productId = idField.getText().trim();
            if (!productId.isEmpty()) {
                int id = -1;
                try{id = Integer.parseInt(productId);}
                catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "L'ID ha de ser un número enter.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                try {
                    ctrlPresentacio.eliminarProducte(id);
                    ctrlPresentacio.actualitzaProductes();
                    JOptionPane.showMessageDialog(null, "Producte eliminat correctament.", "Producte eliminat", JOptionPane.PLAIN_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "El camp de l'ID està buit.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            System.out.println("Operació cancel·lada.");
        }
    }
}
