package main.presentation.classes;

import main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;

/**
 * Classe VistaCarregarLocal que proporciona una interfície gràfica per carregar una distribució
 * local en el sistema. Permet a l'usuari introduir el nom de la distribució i en cas d'acceptació
 * intenta carregar-la.
 */
public class VistaCarregarLocal {

    /**
     * Constructor de la classe VistaCarregarLocal.
     * Crea una finestra emergent amb un camp de text per introduir el nom de la distribució
     * que es vol carregar al sistema.
     *
     * @param ctrlPresentacio Instància del controlador de presentació per gestionar les operacions del sistema.
     */
    public VistaCarregarLocal(CtrlPresentacio ctrlPresentacio) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        JTextField idField = new JTextField("Introdueix el nom de la distribució que vols carregar");
        idField.setPreferredSize(new Dimension(400, 20));
        idField.setForeground(Color.GRAY); // Texto del placeholder
        idField.setFont(new Font("Arial", Font.ITALIC, 14));
        idField.setHorizontalAlignment(SwingConstants.LEFT);

        idField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (idField.getText().equals("Introdueix el nom de la distribució que vols carregar")) {
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
                    idField.setText("Introdueix el nom de la distribució que vols carregar");
                }
            }
        });

        panel.add(idField);

        int result = JOptionPane.showConfirmDialog(
                null,
                panel,
                "Carregar distribució de la sessió",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String nomDist = idField.getText().trim();
            //Comprovar que s'ha introduït un nom
            if(nomDist.isEmpty() || nomDist.equals("Introdueix el nom de la distribució que vols carregar")) {
                JOptionPane.showMessageDialog(null,
                        "S'ha d'escriure el nom de la distribució que es vol carregar.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            try {
                ctrlPresentacio.carregarDistribucio(nomDist);
                ctrlPresentacio.actualitzaProductes();
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            System.out.println("Operació cancel·lada.");
        }
    }
}