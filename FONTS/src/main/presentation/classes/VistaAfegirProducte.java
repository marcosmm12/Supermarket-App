package main.presentation.classes;

import main.presentation.controllers.CtrlPresentacio;

import main.domain.exceptions.ExcepcioCatalegBuit;
import main.domain.exceptions.ExcepcioProducteJaExisteix;
import main.domain.exceptions.ExcepcioProducteNoExisteix;
import main.domain.libs.Pair;

import javax.swing.*;
import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Aquesta classe representa la vista per afegir un nou producte al sistema.
 * Permet introduir l'ID, nom, preu i relacions del producte i validar la seva entrada.
 */
public class VistaAfegirProducte {
    private CtrlPresentacio ctrlPresentacio;
    private JFrame frame;
    //Relacions dels productes (s'ha de fer aquí per poder actualitzar-lo en qualsevol moment)
    private List<Pair<Integer, Integer>> relacions = new ArrayList<Pair<Integer, Integer>>();

    /**
     * Constructor de la classe.
     * Inicialitza la finestra amb els camps necessaris per introduir un nou producte.
     *
     * @param ictrlPresentacio Controlador de presentació que gestiona la comunicació amb la capa de domini.
     */
    public VistaAfegirProducte(CtrlPresentacio ictrlPresentacio) {
        this.ctrlPresentacio = ictrlPresentacio;

        //Crear el marc base
        frame = new JFrame("Gestió del supermercat - Afegir Producte");
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);    //només tancar la finestra actual
        frame.setSize(420, 220);
        frame.setLocationRelativeTo(null);

        //Crear el panell principal
        JPanel panelGeneral = new JPanel();
        panelGeneral.setLayout(new GridLayout(3,1,10,10));
        panelGeneral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        //Panell de cada fila
        JTextField idField = new JTextField("Ingressi un ID pel producte");
        idField.setPreferredSize(new Dimension(300, 20));
        idField.setForeground(Color.GRAY); // Texto del placeholder
        idField.setFont(new Font("Arial", Font.ITALIC, 14));
        idField.setHorizontalAlignment(SwingConstants.LEFT);

        idField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (idField.getText().equals("Ingressi un ID pel producte")) {
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
                    idField.setText("Ingressi un ID pel producte");
                }
            }
        });

        JTextField nomField = new JTextField("Ingressi un nom pel producte");
        nomField.setPreferredSize(new Dimension(300, 20));
        nomField.setForeground(Color.GRAY); // Texto del placeholder
        nomField.setFont(new Font("Arial", Font.ITALIC, 14));
        nomField.setHorizontalAlignment(SwingConstants.LEFT);

        nomField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (nomField.getText().equals("Ingressi un nom pel producte")) {
                    nomField.setText("");
                    nomField.setForeground(Color.BLACK); // Cambiar el color del texto al escribir
                    nomField.setFont(new Font("Arial", Font.PLAIN, 14));
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (nomField.getText().isEmpty()) {
                    nomField.setForeground(Color.GRAY);
                    nomField.setFont(new Font("Arial", Font.ITALIC, 14));
                    nomField.setText("Ingressi un nom pel producte");
                }
            }
        });

        JTextField preuField = new JTextField("Ingressi el preu del producte");
        preuField.setPreferredSize(new Dimension(300, 20));
        preuField.setForeground(Color.GRAY); // Texto del placeholder
        preuField.setFont(new Font("Arial", Font.ITALIC, 14));
        preuField.setHorizontalAlignment(SwingConstants.LEFT);

        preuField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {
                if (preuField.getText().equals("Ingressi el preu del producte")) {
                    preuField.setText("");
                    preuField.setForeground(Color.BLACK); // Cambiar el color del texto al escribir
                    preuField.setFont(new Font("Arial", Font.PLAIN, 14));
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (preuField.getText().isEmpty()) {
                    preuField.setForeground(Color.GRAY);
                    preuField.setFont(new Font("Arial", Font.ITALIC, 14));
                    preuField.setText("Ingressi el preu del producte");
                }
            }
        });
        //Afegir les files al panell general
        //Insertar textField de l'Id
        panelGeneral.add(idField);
        //Insertar textField del nom
        panelGeneral.add(nomField);
        //Insertar textField del preu
        panelGeneral.add(preuField);

        //Botó per afegir relacions
        JButton afegir = new JButton("Afegir relacions");
        JPanel panellBoto = new JPanel();
        panellBoto.setLayout(new FlowLayout(FlowLayout.CENTER));
        panellBoto.add(afegir);

        //Obrir vistaAfegirRelacions
        afegir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //ctrlPresentacio.afageixVista("Vista_AfegirRelacions");
                VistaAfegirRelacions vistaAfegirRelacions = new VistaAfegirRelacions(ctrlPresentacio, VistaAfegirProducte.this);
            }
        });

        //Botó per confirmar o cancelar l'acció
        JPanel panellBotoConfirm = new JPanel();
        panellBotoConfirm.setLayout(new FlowLayout(FlowLayout.CENTER));
        JButton botoAfegirProducte = new JButton("Afegir");
        botoAfegirProducte.setPreferredSize(new Dimension(110, 20));
        panellBotoConfirm.add(botoAfegirProducte);
        panellBotoConfirm.add(Box.createHorizontalStrut( 20));
        JButton botoCancelar = new JButton("Cancelar");
        botoCancelar.setPreferredSize(new Dimension(110, 20));
        panellBotoConfirm.add(botoCancelar);

        //Afegir els panells al frame
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(panelGeneral);
        panel.add(panellBoto);
        panel.add(panellBotoConfirm);
        frame.add(panel, BorderLayout.CENTER);

        //Accio d'Afegir producte
        botoAfegirProducte.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String nom = nomField.getText();
                String preu = preuField.getText();
                if (id.equals("") || nom.equals("") || preu.equals("") || id.equals("Ingressi un ID pel producte") || nom.equals("Ingressi un nom pel producte") || preu.equals("Ingressi el preu del producte")) {
                    JOptionPane.showMessageDialog(frame, "Has d'omplir tots els camps", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    boolean check = true;
                    Integer idInt = -1;
                    try {idInt = Integer.parseInt(id);}
                    catch (NumberFormatException exFormat) {
                        check = false;
                        JOptionPane.showMessageDialog(frame, "L'ID ha de ser un número", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    Integer preuInt = -1;
                    try {preuInt = Integer.parseInt(preu);}
                    catch (NumberFormatException exFormat) {
                        check = false;
                        JOptionPane.showMessageDialog(frame, "El preu ha de ser un número", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    if (!nom.matches(".*[a-zA-Z]+.*") && nom.matches("\\d+")) {
                        check = false;
                        JOptionPane.showMessageDialog(null, "El nom ha de contenir almenys una lletra i no pot ser només un número.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    if (check) {
                        try {
                            ctrlPresentacio.afegirProducte(idInt, nom, preuInt, relacions);
                            ctrlPresentacio.actualitzaProductes();
                        } catch (ExcepcioProducteJaExisteix ex) {
                            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        } catch (ExcepcioProducteNoExisteix ex) {
                            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        } catch (ExcepcioCatalegBuit ex) {
                            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(frame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        frame.setVisible(false);
                    }
                }
            }
        });

        //Acció de Cancelar l'operació
        botoCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
            }
        });

    }

    /**
     * Mostra o amaga la finestra d'aquesta vista.
     *
     * @param visible {@code true} per mostrar la finestra, {@code false} per amagar-la.
     */
    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }

    /**
     * Estableix les relacions del producte que s'està afegint.
     *
     * @param relacions Llista de relacions a assignar al producte.
     */
    public void setRelacions(List<Pair<Integer, Integer>> relacions) {
        this.relacions = relacions;
    }
}