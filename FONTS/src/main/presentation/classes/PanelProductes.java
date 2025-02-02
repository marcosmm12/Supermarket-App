package main.presentation.classes;

import main.presentation.controllers.CtrlPresentacio;
import main.domain.classes.Producte;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Aquesta classe representa un panell que visualitza els productes disponibles.
 * Els productes es mostren organitzats en prestatgeries dins del panell principal.
 */
public class PanelProductes extends JPanel {

    private CtrlPresentacio ctrlPresentacio;

    /**
     * Constructor de la classe.
     * Inicialitza el panell organitzat per mostrar prestatgeries amb productes.
     *
     * @param ictrlPresentacio Controlador de presentació que proporciona accés al catàleg de productes.
     */
    public PanelProductes(CtrlPresentacio ictrlPresentacio) {
        this.ctrlPresentacio = ictrlPresentacio;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        this.setBackground(Color.WHITE);

        Map<Integer, Producte> productes = new HashMap<>();
        try {
            productes = ctrlPresentacio.getCataleg();
        } catch (Exception e) {
        }

        int productosPorEstanteria = 5;
        int maxEstanterias = 3;
        creaEstanterias(productes, productosPorEstanteria, this);
    }

    /**
     * Crea les prestatgeries per visualitzar els productes.
     * Organitza els productes de manera uniforme entre les prestatgeries.
     *
     * @param productes          Map de productes disponibles amb el seu ID com a clau.
     * @param productosPorEstanteria Nombre màxim de productes per prestatgeria.
     * @param panelPrincipal     Panell principal on s'afegiran les prestatgeries.
     */
    private void creaEstanterias(Map<Integer, Producte> productes, int productosPorEstanteria, JPanel panelPrincipal) {
        int numEstanterias = 3;
        JPanel[] estanterias = new JPanel[numEstanterias];
        int productosPorFila = productes.size() / numEstanterias + (productes.size() % numEstanterias == 0 ? 0 : 1);

        for (int i = 0; i < numEstanterias; i++) {
            estanterias[i] = new JPanel(new GridLayout(1, productosPorFila, 10, 10));
            estanterias[i].setBorder(BorderFactory.createTitledBorder("Prestatgeria " + (i + 1)));
            estanterias[i].setBackground(Color.WHITE);
            panelPrincipal.add(estanterias[i]);
        }

        int contador = 0;
        for (Map.Entry<Integer, Producte> entry : productes.entrySet()) {
            int estanteriaIndex = contador % numEstanterias;
            JPanel estanteriaActual = estanterias[estanteriaIndex];

            Integer key = entry.getKey();
            Producte value = entry.getValue();
            String nom = value.getNom();
            String info = "<html><div style='text-align:center;'> Producte " + key + "<br>" + nom + "</html>";

            estanteriaActual.add(crearPanelProducto(info));
            contador++;
        }

        for (int i = 0; i < numEstanterias; i++) {
            int productosEnEstanteria = estanterias[i].getComponentCount();
            int vacios = productosPorFila - productosEnEstanteria;
            for (int j = 0; j < vacios; j++) {
                estanterias[i].add(crearPanelProducto(""));
            }
        }
    }

    /**
     * Crea un panell individual per a cada producte o per a espais buits.
     *
     * @param nom El nom o identificador del producte que es mostrarà al panell.
     * @return Un JPanel configurat per mostrar el producte o un espai buit.
     */
    private static JPanel crearPanelProducto(String nom) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        panel.setBackground(new Color(245, 245, 245));

        JLabel label = new JLabel(nom, SwingConstants.CENTER);
        panel.add(label, BorderLayout.CENTER);

        return panel;
    }
}