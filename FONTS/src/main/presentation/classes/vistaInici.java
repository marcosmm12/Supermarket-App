package main.presentation.classes;

import main.presentation.controllers.CtrlPresentacio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * La classe vistaInici crea la finestra inicial de l'aplicació de gestió del supermercat,
 * on l'usuari pot començar el procés de gestió fent clic al botó "Comença".
 * Aquesta finestra inclou el títol de l'aplicació, una icona relacionada i un botó d'inici.
 */
public class vistaInici {
    private CtrlPresentacio ctrlPresentacio;
    private JFrame frame;

    /**
     * Constructor de la classe vistaInici.
     * Crea la finestra inicial de l'aplicació amb un títol, una icona i un botó "Comença".
     * Quan l'usuari fa clic al botó, es canvia la vista actual per la vista principal.
     *
     * @param ictrlPresentacio Instància del controlador de presentació per gestionar les operacions del sistema.
     */
    public vistaInici(CtrlPresentacio ictrlPresentacio) {
        this.ctrlPresentacio = ictrlPresentacio;

        frame = new JFrame("Gestió del supermercat");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);

        JLabel titol = new JLabel("Gestió del supermercat");
        titol.setFont(new Font("Arial", Font.BOLD, 30));
        titol.setAlignmentX(Component.CENTER_ALIGNMENT);

        ImageIcon icon = new ImageIcon("icons/cart1.jpg");
        Image img = icon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
        ImageIcon iconRed = new ImageIcon(img);
        JLabel iconLabel = new JLabel(iconRed);
        iconLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton startButton = new JButton("Comença");
        startButton.setFont(new Font("Arial", Font.PLAIN, 20));
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(titol);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(iconLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        mainPanel.add(startButton);
        mainPanel.add(Box.createVerticalGlue());

        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                ctrlPresentacio.canviaVista("Vista_Principal");
            }
        });

    }

    /**
     * Permet controlar la visibilitat de la finestra inicial.
     *
     * @param visible Boolean que determina si la finestra ha de ser visible o no.
     */
    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
}