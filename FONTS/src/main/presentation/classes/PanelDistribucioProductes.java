package main.presentation.classes;

import main.presentation.controllers.CtrlPresentacio;
import main.domain.exceptions.ExcepcioCatalegBuit;
import main.domain.classes.Producte;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.Vector;

/**
 * Aquesta classe representa un panell que mostra la distribució de productes
 * basada en una configuració definida pel controlador de presentació.
 * Els productes es disposen en un disseny de quadrícula segons la distribució i el catàleg proporcionats.
 *
 * @see CtrlPresentacio
 */
public class PanelDistribucioProductes extends JPanel {

    private CtrlPresentacio ctrlPresentacio;

    /**
     * Constructor de la classe.
     * Inicialitza el panell amb la distribució de productes segons el catàleg.
     *
     * @param ictrlPresentacio Controlador de presentació que proporciona les dades necessàries.
     * @throws ExcepcioCatalegBuit Si el catàleg està buit, es llença aquesta excepció.
     */
    public PanelDistribucioProductes(CtrlPresentacio ictrlPresentacio) throws ExcepcioCatalegBuit {
        this.ctrlPresentacio = ictrlPresentacio;

        int altura = -1;
        try {
            altura = ctrlPresentacio.getAltura();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        this.setLayout(new GridLayout(altura, 0, 10, 10));
        this.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        this.setBackground(Color.WHITE);

        Vector<Integer> distribucio = ctrlPresentacio.getDistribucio();
        Map<Integer, Producte> productes = ctrlPresentacio.getCataleg();

        mostraDistribucio(distribucio, productes, this);
    }

    /**
     * Mostra la distribució dels productes al panell segons la informació proporcionada.
     * Per cada element de la distribució, afegeix un panell representant el producte.
     *
     * @param distribucio Un vector d'identificadors de productes que defineix l'ordre de la distribució.
     * @param productes   Un mapa que associa els identificadors amb els objectes Producte.
     * @param panel       El panell al qual s'afegiran els productes.
     */
    private void mostraDistribucio(Vector<Integer> distribucio, Map<Integer, Producte> productes, JPanel panel) {
        for (int i = 0; i < distribucio.size(); i++) {
            String nom = "";
            try {
                nom = productes.get(distribucio.get(i)).getNom();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            String info = "<html><div style='text-align:center;'> Producte " + distribucio.get(i) + "<br>" + nom + "</html>";

            this.add(crearPanelProducto(info));
        }
        if (distribucio.size() < 20) {
            for (int i = distribucio.size(); i <= 20; i++) {
                this.add(crearPanelProducto(""));
            }
        }
    }

    /**
     * Crea un panell individual que representa un producte.
     *
     * @param nom El nom o informació del producte a mostrar al panell.
     * @return Un panell configurat amb la informació del producte.
     */
    private static JPanel crearPanelProducto(String nom) {
        JPanel panel = new JPanel();
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
