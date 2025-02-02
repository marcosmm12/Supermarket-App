package main.presentation.classes;

import main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;

/**
 * Classe VistaGuardarLocal que proporciona una interfície gràfica per guardar la distribució actual
 * amb un nom personalitzat. Si no s'introdueix un nom vàlid, es mostrarà un missatge d'error.
 */
public class VistaGuardarLocal {

    /**
     * Constructor de la classe VistaGuardarLocal.
     * Crea una finestra emergent on l'usuari pot introduir un nom per guardar la distribució actual.
     * Si el nom introduït és vàlid, s'inicia el procés de guardar la distribució. En cas contrari,
     * es mostra un missatge d'error.
     *
     * @param ctrlPresentacio Instància del controlador de presentació per gestionar les operacions del sistema.
     */
    public VistaGuardarLocal(CtrlPresentacio ctrlPresentacio) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JTextField idField = new JTextField("Introdueix el nom amb el que vols guardar la distribució");
        idField.setPreferredSize(new Dimension(400, 20));
        idField.setForeground(Color.GRAY); // Texto del placeholder
        idField.setFont(new Font("Arial", Font.ITALIC, 14));
        idField.setHorizontalAlignment(SwingConstants.LEFT);

        idField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (idField.getText().equals("Introdueix el nom amb el que vols guardar la distribució")) {
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
                    idField.setText("Introdueix el nom amb el que vols guardar la distribució");
                }
            }
        });

        panel.add(idField);

        int result = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Guardar distribució en la sessió",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String nomDist = idField.getText().trim();
            //Comprovar que s'ha introduït un nom d'arxiu
            if(nomDist.isEmpty() || nomDist.equals("Introdueix el nom amb el que vols guardar la distribució")) {
                JOptionPane.showMessageDialog(null,
                        "S'ha d'escriure un nom per guardar la distribució.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            //Cridar al domini
            try {ctrlPresentacio.guardarDistribucio(nomDist);}
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            System.out.println("Operació cancel·lada.");
        }
    }
}