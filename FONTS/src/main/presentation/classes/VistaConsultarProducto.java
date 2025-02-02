package main.presentation.classes;

import main.presentation.controllers.CtrlPresentacio;
import main.domain.classes.Producte;

import javax.swing.*;
import java.awt.*;

/**
 * Classe VistaConsultarProducto que proporciona una interfície gràfica per consultar informació d'un producte
 * a partir de la seva ID. Si el catàleg està buit, es mostrarà un missatge d'error.
 * Si la ID introduïda és vàlida, es mostra la informació del producte (ID, Nom, Preu).
 */
public class VistaConsultarProducto {

    /**
     * Constructor de la classe VistaConsultarProducto.
     * Crea una finestra emergent que permet a l'usuari introduir l'ID d'un producte per consultar la seva informació.
     * Mostra els detalls del producte, com el nom, ID i preu.
     *
     * @param ctrlPresentacio Instància del controlador de presentació per gestionar les operacions del sistema.
     */
    public VistaConsultarProducto(CtrlPresentacio ctrlPresentacio) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        //Si el cataleg està buit no deixo avançar
        try{ctrlPresentacio.getCataleg().isEmpty();}
        catch (Exception e) {
            JOptionPane.showMessageDialog(panel, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JTextField idField = new JTextField("Ingressi l'ID del producte a consultar");
        idField.setPreferredSize(new Dimension(300, 20));
        idField.setForeground(Color.GRAY); // Texto del placeholder
        idField.setFont(new Font("Arial", Font.ITALIC, 14));
        idField.setHorizontalAlignment(SwingConstants.LEFT);

        idField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (idField.getText().equals("Ingressi l'ID del producte a consultar")) {
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
                    idField.setText("Ingressi l'ID del producte a consultar");
                }
            }
        });

        panel.add(idField);

        JPanel panelGeneral = new JPanel();
        panelGeneral.setLayout(new BoxLayout(panelGeneral, BoxLayout.Y_AXIS));
        panelGeneral.add(panel);

        int result = JOptionPane.showConfirmDialog(
                null,
                panelGeneral,
                "Consultar Producte",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            String productId = idField.getText().trim();
            if (!productId.isEmpty()) {
                int id = -1;
                try {id = Integer.parseInt(productId);}
                catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "El ID del producte ha de ser un número.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                //Agafar el producte
                Producte p;
                try {p = ctrlPresentacio.getProducte(id);}
                catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Mostrar la informació del producte
                String nomP = p.getNom();
                Integer idP = p.getId();
                Integer preuP = p.getPreu();
                //Posar la info en un panell pel resultat
                JPanel panelInfo = new JPanel();
                panelInfo.setLayout(new GridLayout(1,3,10,10));

                //Label pel nom
                JLabel labelNom = new JLabel("Nom: " + nomP);
                labelNom.setOpaque(true);   //Permetre pintar el label
                labelNom.setForeground(Color.WHITE);    //Color de la lletra
                labelNom.setBackground(new Color(192, 192, 192)); //Color de fons
                labelNom.setHorizontalAlignment(SwingConstants.CENTER); //Centrar el text
                labelNom.setFont(new Font("Times New Roman", Font.BOLD, 14));   //Font

                //Label per l'Id
                JLabel labelId = new JLabel("ID: " + idP.toString());
                labelId.setOpaque(true);   //Permetre pintar el label
                labelId.setForeground(Color.WHITE);    //Color de la lletra
                labelId.setBackground(new Color(192, 192, 192)); //Color de fons
                labelId.setHorizontalAlignment(SwingConstants.CENTER); //Centrar el text
                labelId.setFont(new Font("Times New Roman", Font.BOLD, 14));   //Font

                //Label pel preu
                JLabel labelPreu = new JLabel("Preu: " + preuP.toString());
                labelPreu.setOpaque(true);   //Permetre pintar el label
                labelPreu.setForeground(Color.WHITE);    //Color de la lletra
                labelPreu.setBackground(new Color(192, 192, 192)); //Color de fons
                labelPreu.setHorizontalAlignment(SwingConstants.CENTER); //Centrar el text
                labelPreu.setFont(new Font("Times New Roman", Font.BOLD, 14));   //Font

                //Insertar els elements al panell
                panelInfo.add(labelId);
                panelInfo.add(labelNom);
                panelInfo.add(labelPreu);

                //Mostrar el panell amb la informació
                JOptionPane.showMessageDialog( null, panelInfo, "Producte trobat", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "El camp de l'Id està buit.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            System.out.println("Operació cancel·lada.");
        }
    }
}