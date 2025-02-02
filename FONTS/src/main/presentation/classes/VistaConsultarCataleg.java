package main.presentation.classes;

import main.presentation.controllers.CtrlPresentacio;
import main.domain.classes.Producte;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe VistaConsultarCataleg que proporciona una interfície gràfica per consultar el catàleg de productes
 * en el sistema. Mostra una llista dels productes disponibles, incloent el seu ID, nom i preu.
 */
public class VistaConsultarCataleg {
    private CtrlPresentacio ctrlPresentacio;

    /**
     * Constructor de la classe VistaConsultarCataleg.
     * Crea una finestra emergent que mostra el catàleg de productes amb informació detallada com l'ID,
     * el nom i el preu de cada producte.
     *
     * @param ictrlPresentacio Instància del controlador de presentació per gestionar les operacions del sistema.
     */
    public VistaConsultarCataleg(CtrlPresentacio ictrlPresentacio) {
        ctrlPresentacio = ictrlPresentacio;

        // Crear el panell principal
        JPanel panelGeneral = new JPanel();
        panelGeneral.setLayout(new GridLayout(0, 1, 10, 10)); // Usar GridLayout dinámico (filas variables)

        // Agafar els ids dels productes del sistema
        Map<Integer, Producte> cataleg = new HashMap<>();
        try {
            cataleg = ctrlPresentacio.getCataleg();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No hi ha cap producte al sistema", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear les files de Id y textField per introduir la relació
        for (Map.Entry<Integer, Producte> entry : cataleg.entrySet()) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 3, 10, 10));

            JLabel label1 = new JLabel("ID: " + entry.getKey());
            label1.setHorizontalAlignment(SwingConstants.CENTER);
            label1.setOpaque(true);
            label1.setBackground(new Color(192, 192, 192));
            label1.setForeground(Color.WHITE);

            JLabel label2 = new JLabel("Nom: " + entry.getValue().getNom());
            label2.setHorizontalAlignment(SwingConstants.CENTER);
            label2.setOpaque(true);
            label2.setBackground(new Color(192, 192, 192));
            label2.setForeground(Color.WHITE);

            JLabel label3 = new JLabel("Preu: " + entry.getValue().getPreu());
            label3.setHorizontalAlignment(SwingConstants.CENTER);
            label3.setOpaque(true);
            label3.setBackground(new Color(192, 192, 192));
            label3.setForeground(Color.WHITE);

            panel.add(label1);
            panel.add(label2);
            panel.add(label3);

            panelGeneral.add(panel);
        }

        // Crear un JScrollPane
        JScrollPane scrollPane = new JScrollPane(panelGeneral);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        int preferredheight = 200;
        if(cataleg.size() <= 8) preferredheight = cataleg.size()*25;
        scrollPane.setPreferredSize(new Dimension(400, preferredheight)); // Tamaño visible del JScrollPane

        // Mostrar el JScrollPane en un JOptionPane
        JOptionPane.showMessageDialog(
                null,
                scrollPane,
                "Consultar catàleg",
                JOptionPane.PLAIN_MESSAGE
        );
    }
}