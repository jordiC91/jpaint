/*
 * Copyright 2012 Jordi CHARPENTIER jordi.charpentier@gmail.com
 * 
 * This file is part of JPaint.
 *
 * JPaint is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * JPaint is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with JPaint. If not, see <http://www.gnu.org/licenses/>.
 */

package ui.frame;

import ui.custo.JTextFieldPerso;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 * Classe contenant la fen�tre du redimensionnement de la zone de dessin du programme.
 *
 * @author Jordi CHARPENTIER jordi.charpentier@gmail.com
 */
public class Redimensionner extends JFrame {

    /* El�ments graphiques. */
    
    private final JPanel container = new JPanel();
    private final JLabel lab_largeur = new JLabel("Largeur (en px)");
    private final JTextFieldPerso ta_largeur = new JTextFieldPerso(4);
    private final JLabel lab_hauteur = new JLabel("Hauteur (en px)");
    private final JTextFieldPerso ta_hauteur = new JTextFieldPerso(4);
    private final JButton bout_valider = new JButton("Valider");

    /* Autre. */
    
    private boolean isClosingRight = false;
    private int largeur;
    private int hauteur;

    public Redimensionner(int largeur_initiale, int hauteur_initiale) {
        this.setSize(240, 160);
        this.setLocationRelativeTo(null);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/icones/icone.png")));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Redimensionner");
        this.getContentPane().add(container);

        initContainer();
        setListener();

        ta_largeur.setText("" + largeur_initiale);
        ta_hauteur.setText("" + hauteur_initiale);

        this.setVisible(true);
        this.setResizable(false);
    }

    /* M�thode graphique. */
    
    private void initContainer() {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        container.add(Box.createVerticalStrut(5));
        container.add(lab_largeur);
        lab_largeur.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        container.add(ta_largeur);
        ta_largeur.setMaximumSize(new Dimension(100, 25));
        container.add(Box.createVerticalStrut(5));
        container.add(lab_hauteur);
        lab_hauteur.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        container.add(ta_hauteur);
        ta_hauteur.setMaximumSize(new Dimension(100, 25));
        container.add(Box.createVerticalStrut(5));
        container.add(bout_valider);
        bout_valider.setAlignmentX(JComponent.CENTER_ALIGNMENT);
        container.add(Box.createVerticalStrut(5));
    }

    private void setListener() {
        bout_valider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if ((ta_largeur.getText().length() > 0) && (ta_hauteur.getText().length() > 0)) {
                    if ((Integer.parseInt(ta_largeur.getText()) > 750) || (Integer.parseInt(ta_hauteur.getText()) > 750)) {
                        JOptionPane.showMessageDialog(null, "Au moins un des champs est trop grand. Limite : 750x750 px.", "Erreur", JOptionPane.ERROR_MESSAGE);
                    } else {
                        isClosingRight = true;

                        largeur = Integer.parseInt(ta_largeur.getText());
                        hauteur = Integer.parseInt(ta_hauteur.getText());

                        dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Au moins un des champs n'est pas correctement rempli.", "Erreur", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        ta_largeur.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    bout_valider.doClick();
                }
            }
        });

        ta_hauteur.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    bout_valider.doClick();
                }
            }
        });
    }

    /* Autres. */
    
    /**
     * M�thode permettant de savoir si la fen�tre a �t� ferm�e via le bouton "Valider" ou via la croix rouge en haut � droite de celle-ci.
     *
     * @return <b>true</b> si la fen�tre a �t� ferm�e via un appui sur le bouton "Valider".<br />
     * <b>false</b> sinon.
     */
    public boolean isClosingRight() {
        return this.isClosingRight;
    }

    /**
     * M�thode permettant de r�cup�rer la largeur entr�e par l'utilisateur dans la zone appropri�e.
     *
     * @return La largeur entr�e par l'utilisateur.
     */
    public int getLargeur() {
        return this.largeur;
    }

    /**
     * M�thode permettant de r�cup�rer la hauteur entr�e par l'utilisateur dans la zone appropri�e.
     *
     * @return La hauteur entr�e par l'utilisateur.
     */
    public int getHauteur() {
        return this.hauteur;
    }
}
