package main.presentation.classes;

import main.presentation.controllers.CtrlPresentacio;
import main.domain.exceptions.ExcepcioCatalegBuit;

import javax.swing.*;
import java.awt.*;

/**
 * Aquesta classe proporciona una finestra per mostrar la distribució dels productes en el sistema.
 * La classe interactua amb el controlador de la presentació (CtrlPresentacio) per obtenir la distribució dels productes
 * i presentar-los en una interfície gràfica utilitzant un panell de desplaçament.
 *
 * El panell es carrega dins d'un JScrollPane que permet desplaçar-se pels productes si la finestra és massa petita.
 * Si no hi ha productes disponibles en el catàleg, es mostra un missatge d'error.
 */
public class VistaMostrarDistribucio extends JFrame {
    private CtrlPresentacio ctrlPresentacio;

    /**
     * Constructor de la classe VistaMostrarDistribucio.
     * Inicialitza la finestra i mostra la distribució dels productes en un panell de desplaçament.
     * Si el catàleg està buit, es mostra un missatge d'error.
     *
     * @param ctrlPresentacio Controlador de la presentació que gestiona les operacions del sistema i obté la distribució dels productes.
     */
    public VistaMostrarDistribucio(CtrlPresentacio ctrlPresentacio) {
        this.ctrlPresentacio = ctrlPresentacio;
        this.setLayout(new BorderLayout());
        this.setSize(800, 400);
        this.setLocationRelativeTo(null);

        //Panel para los productos
        try {
            PanelDistribucioProductes panelProductes = new PanelDistribucioProductes(ctrlPresentacio);
            JScrollPane scrollPane = new JScrollPane(panelProductes,
                    JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                    JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

            scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Marges exteriors
            scrollPane.getHorizontalScrollBar().setUnitIncrement(16); // Ajustar velocitat de l'scroll horizontal

            this.add(scrollPane, BorderLayout.CENTER);
            this.add(Box.createRigidArea(new Dimension(10, 10)), BorderLayout.NORTH);
            this.add(Box.createRigidArea(new Dimension(10, 10)), BorderLayout.EAST);
            this.add(Box.createRigidArea(new Dimension(10, 10)), BorderLayout.WEST);
            this.add(Box.createRigidArea(new Dimension(10, 10)), BorderLayout.SOUTH);

            this.setVisible(true);
        } catch (ExcepcioCatalegBuit ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}