package main.presentation.classes;

import main.presentation.controllers.CtrlPresentacio;
import main.domain.exceptions.ExcepcioCatalegBuit;
import main.domain.exceptions.ExcepcioProducteNoExisteix;
import main.domain.exceptions.RelacioNotExistException;
import main.domain.libs.Pair;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.TreeMap;

/**
 * Aquesta classe proporciona una interfície gràfica per modificar les relacions entre productes en el sistema.
 * La classe interactua amb el controlador de la presentació (CtrlPresentacio) per obtenir i modificar les relacions.
 *
 * El sistema permet a l'usuari editar la relació entre dos productes mitjançant la visualització de les seves ID i
 * un camp editable per modificar el valor de la relació. Les relacions es gestionen dins d'un catàleg de productes.
 *
 */
public class VistaModificarRelacio {
    private CtrlPresentacio ctrlPresentacio;

    /**
     * Constructor de la classe VistaModificarRelacio.
     * Inicialitza la interfície gràfica per mostrar les relacions entre productes i permet l'usuari editar-les.
     *
     * @param ictrlPresentacio Controlador de la presentació que gestiona les operacions del sistema.
     */
    public VistaModificarRelacio(CtrlPresentacio ictrlPresentacio) {
        ctrlPresentacio = ictrlPresentacio;

        // Crear el panel principal
        JPanel panelGeneral = new JPanel();
        panelGeneral.setLayout(new GridLayout(0, 1, 10, 10)); // Filas dinámicas, una fila por relación

        // Agafar les relacions del sistema
        Map<Pair<Integer, Integer>, Integer> relacions = new TreeMap<>();
        try {
            relacions = ctrlPresentacio.agafaRelacions();
        } catch (ExcepcioCatalegBuit e) {
            JOptionPane.showMessageDialog(null, "No hi ha cap producte al sistema", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar que hay relaciones
        if (relacions.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hi ha cap relació al sistema", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Crear les files per cada relació
        for (Map.Entry<Pair<Integer, Integer>, Integer> entry : relacions.entrySet()) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1, 3, 10, 10)); // 3 columnas: ID1, ID2, valor relación

            //Label amb el primer Id
            JLabel label1 = new JLabel("ID " + entry.getKey().getFirstVal());
            //Fer el label mes gran
            label1.setPreferredSize(new Dimension(70,20));
            //Posar color al label
            label1.setOpaque(true);
            label1.setForeground(Color.WHITE);
            label1.setBackground(new Color(192, 192, 192));
            //Centrar el text
            label1.setHorizontalAlignment(SwingConstants.CENTER);

            //Label amb el segon Id
            JLabel label2 = new JLabel("ID " + entry.getKey().getSecondVal());
            //Fer el label mes gran
            label2.setPreferredSize(new Dimension(70,20));
            //Posar color al label
            label2.setOpaque(true);
            label2.setForeground(Color.WHITE);
            label2.setBackground(new Color(192, 192, 192));
            //Centrar el text
            label2.setHorizontalAlignment(SwingConstants.CENTER);

            JTextField relacioField = new JTextField(2);
            relacioField.setText(entry.getValue().toString());

            // Añadir componentes al panel de fila
            panel.add(label1);
            panel.add(label2);
            panel.add(relacioField);

            // Añadir fila al panel general
            panelGeneral.add(panel);
        }

        // Crear un JScrollPane para el panel general
        JScrollPane scrollPane = new JScrollPane(panelGeneral);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        int preferredheight = 200;
        if (relacions.size() == 1) preferredheight = 25;
        if (relacions.size() == 3) preferredheight = 100;
        scrollPane.setPreferredSize(new Dimension(400, preferredheight)); // Tamaño visible del JScrollPane

        // Mostrar el JScrollPane en un JOptionPane
        int result = JOptionPane.showConfirmDialog(
                null,
                scrollPane,
                "Editar Relacions",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        // Manejo de eventos según el botón seleccionado
        if (result == JOptionPane.OK_OPTION) {
            int i = 0;
            for (Map.Entry<Pair<Integer, Integer>, Integer> entry : relacions.entrySet()) {
                JPanel panel = (JPanel) panelGeneral.getComponent(i);
                JTextField textRelacio = (JTextField) panel.getComponent(2);
                int value = -1;
                try {value = Integer.parseInt(textRelacio.getText().trim());}
                catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Totes les relacions han de ser un número enter", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (value != entry.getValue()) {
                    //Comprovar que la relacio es correcta
                    if (value < 0 || value > 100) {
                        JOptionPane.showMessageDialog(null, "Les relacions han de ser un número entre 0 i 100", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    //Editar la relacio
                    try{
                        ctrlPresentacio.modificarRelacio(entry.getKey().getFirstVal(), entry.getKey().getSecondVal(), value);
                    }
                    catch (RelacioNotExistException e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    catch (ExcepcioCatalegBuit e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    catch (ExcepcioProducteNoExisteix e) {
                        JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                i++;
            }
        } else {
            System.out.println("Operació cancel·lada.");
        }
    }
}