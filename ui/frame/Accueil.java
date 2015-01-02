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

import ui.custo.PanelPaint;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import javax.swing.filechooser.*;

/**
 * Classe représentant l'accueil de JPaint.
 *
 * @author Jordi CHARPENTIER jordi.charpentier@gmail.com
 */
public class Accueil extends JFrame {
    
    /* Barre de menu. */

    private JMenuBar barre_menu = new JMenuBar();
    private JMenu menu_fichier = new JMenu("Fichier");
    private JMenuItem sous_menu_nouveau = new JMenuItem("Nouveau");
    private JMenuItem sous_menu_ouvrir = new JMenuItem("Ouvrir");
    private JMenuItem sous_menu_enregistrer = new JMenuItem("Enregistrer");
    private JMenuItem sous_menu_quitter = new JMenuItem("Quitter");
    private JMenu menu_aide = new JMenu("Aide");
    private JMenuItem sous_menu_a_propos = new JMenuItem("A Propos");

    /* Tool barre. */
    
    private JToolBar tool_barre = new JToolBar();
    private JButton tb_carre = new JButton(new ImageIcon(getClass().getResource("/images/tool_barre/formes/carre.jpg")));
    private JButton tb_rond = new JButton(new ImageIcon(getClass().getResource("/images/tool_barre/formes/rond.jpg")));
    private JButton tb_couleur = new JButton(new ImageIcon(getClass().getResource("/images/tool_barre/couleur.png")));
    private JButton tb_gomme = new JButton(new ImageIcon(getClass().getResource("/images/tool_barre/gomme.png")));
    private JButton tb_redimensionner = new JButton(new ImageIcon(getClass().getResource("/images/tool_barre/redimensionner.png")));
    private Object[] images_traits = {new ImageIcon(getClass().getResource("/images/tool_barre/tailles_traits/1.png")), new ImageIcon(getClass().getResource("/images/tool_barre/tailles_traits/2.png")), new ImageIcon(getClass().getResource("/images/tool_barre/tailles_traits/3.png")), new ImageIcon(getClass().getResource("/images/tool_barre/tailles_traits/4.png")), new ImageIcon(getClass().getResource("/images/tool_barre/tailles_traits/5.png"))};
    private JComboBox tailles_traits = new JComboBox(images_traits);

    /* Sélecteur de fichier. */
    
    private JFileChooser image_chooser = new JFileChooser();
    private FileNameExtensionFilter png = new FileNameExtensionFilter("Fichiers .png", "png");

    /* Autres. */
    
    private PanelPaint panel_dessin = new PanelPaint();
    private ActionsListener actions_listener = new ActionsListener();

    public Accueil() {
        this.setTitle("JPaint");
        this.setSize(755, 825);
        this.setLocationRelativeTo(null);
        this.setFocusable(true);
        this.requestFocus();
        this.setVisible(true);
        this.setJMenuBar(barre_menu);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/icones/icone.png")));
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.setResizable(false);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (panel_dessin.getNbPoints() > 0) {
                    if (JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter ?", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                        System.exit(0);
                    }
                } else {
                    System.exit(0);
                }
            }
        });

        this.setLayout(new BorderLayout());
        this.getContentPane().add(tool_barre, BorderLayout.NORTH);
        this.getContentPane().add(panel_dessin, BorderLayout.CENTER);

        panel_dessin.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        initMenu();
        initToolBar();
        initFileChooser();
        setIconeBarreMenu();
        setListener();
    }

    /* Méthodes graphiques. */
    
    private void initMenu() {
        barre_menu.add(menu_fichier);
        menu_fichier.setMnemonic('F');
        menu_fichier.add(sous_menu_nouveau);
        menu_fichier.addSeparator();
        menu_fichier.add(sous_menu_ouvrir);
        menu_fichier.add(sous_menu_enregistrer);
        menu_fichier.addSeparator();
        menu_fichier.add(sous_menu_quitter);
        barre_menu.add(menu_aide);
        menu_aide.setMnemonic('A');
        menu_aide.add(sous_menu_a_propos);
    }

    private void initToolBar() {
        tool_barre.setFloatable(false);

        tool_barre.add(tb_carre);
        tool_barre.add(tb_rond);
        tool_barre.add(Box.createHorizontalStrut(10));
        tool_barre.add(tb_couleur);
        tool_barre.add(tb_gomme);
        tool_barre.add(Box.createHorizontalStrut(10));
        tool_barre.add(tailles_traits);
        tailles_traits.setMaximumSize(tailles_traits.getPreferredSize());
        tailles_traits.setSelectedIndex(3);

        tailles_traits.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                panel_dessin.requestFocus();

                if ((e.getItem() == tailles_traits.getItemAt(0)) && (e.getStateChange() == ItemEvent.SELECTED)) {
                    panel_dessin.setPointerSize(3);
                } else if ((e.getItem() == tailles_traits.getItemAt(1)) && (e.getStateChange() == ItemEvent.SELECTED)) {
                    panel_dessin.setPointerSize(5);
                } else if ((e.getItem() == tailles_traits.getItemAt(2)) && (e.getStateChange() == ItemEvent.SELECTED)) {
                    panel_dessin.setPointerSize(10);
                } else if ((e.getItem() == tailles_traits.getItemAt(3)) && (e.getStateChange() == ItemEvent.SELECTED)) {
                    panel_dessin.setPointerSize(15);
                } else if ((e.getItem() == tailles_traits.getItemAt(4)) && (e.getStateChange() == ItemEvent.SELECTED)) {
                    panel_dessin.setPointerSize(18);
                }
            }
        });

        tool_barre.add(Box.createHorizontalStrut(10));
        tool_barre.add(tb_redimensionner);
    }

    private void initFileChooser() {
        image_chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        image_chooser.setAcceptAllFileFilterUsed(false);
        image_chooser.addChoosableFileFilter(png);
    }

    private void setIconeBarreMenu() {
        sous_menu_nouveau.setIcon(new ImageIcon(getClass().getResource("/images/menus/nouveau.png")));
        sous_menu_ouvrir.setIcon(new ImageIcon(getClass().getResource("/images/menus/ouvrir.png")));
        sous_menu_enregistrer.setIcon(new ImageIcon(getClass().getResource("/images/menus/enregistrer.png")));
        sous_menu_quitter.setIcon(new ImageIcon(getClass().getResource("/images/menus/quitter.png")));
        sous_menu_a_propos.setIcon(new ImageIcon(getClass().getResource("/images/menus/a_propos.png")));
    }

    private void setListener() {
        /* Barre de menu. */

        sous_menu_nouveau.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (panel_dessin.getNbPoints() > 0) {
                        if (JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment effacer ce dessin ?", "Confirmation", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
                            panel_dessin.erase();
                        }
                    }
                }
            }
        });

        sous_menu_ouvrir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    ouvrirImg();
                }
            }
        });

        sous_menu_enregistrer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    sauvegarderImg();
                }
            }
        });

        sous_menu_quitter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    System.exit(0);
                }
            }
        });

        sous_menu_a_propos.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    APropos aPropos = new APropos();
                }
            }
        });

        /* Tool barre. */
        
        tb_carre.addActionListener(actions_listener);
        tb_rond.addActionListener(actions_listener);
        tb_couleur.addActionListener(actions_listener);
        tb_gomme.addActionListener(actions_listener);
        tb_redimensionner.addActionListener(actions_listener);
    }

    /* Autres. */
    
    /**
     * Méthode permettant d'enregistrer le dessin.
     *
     * @return <b>true</b> si le dessin a bien été enregistré.<br />
     * <b>false</b> sinon.
     */
    private boolean sauvegarderImg() {
        if (image_chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            String extension = image_chooser.getFileFilter().getDescription().substring(9);
            String nom_img = image_chooser.getSelectedFile() + extension;

            if (!new File(nom_img).exists()) {
                try {
                    BufferedImage bi = new BufferedImage(panel_dessin.getSize().width, panel_dessin.getSize().height, BufferedImage.TYPE_INT_ARGB);
                    Graphics g = bi.createGraphics();
                    panel_dessin.paint(g);
                    g.dispose();
                    ImageIO.write(bi, extension.substring(1), new File(nom_img));
                } catch (IOException e) {
                    return false;
                }

                return true;
            } else {
                if (JOptionPane.showConfirmDialog(null, "Le fichier existe déjà. Voulez-vous continuer ?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.OK_OPTION) {
                    try {
                        BufferedImage bi = new BufferedImage(panel_dessin.getSize().width, panel_dessin.getSize().height, BufferedImage.TYPE_INT_ARGB);
                        Graphics g = bi.createGraphics();
                        panel_dessin.paint(g);
                        g.dispose();
                        ImageIO.write(bi, extension.substring(1), new File(nom_img));
                    } catch (IOException e) {
                        return false;
                    }

                    return true;
                } else {
                    sauvegarderImg();
                }
            }
        }

        return false;
    }

    /**
     * Méthode permettant d'ouvrir une image.
     *
     * @return <b>true</b> si l'image a bien été ouverte.<br />
     * <b>false</b> sinon.
     */
    private boolean ouvrirImg() {
        if (image_chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            panel_dessin.setBackground(image_chooser.getSelectedFile().toString());
            panel_dessin.repaint();

            return true;
        }

        return false;
    }

    /* Listener. */
    
    /**
     * Classe interne représentant un action listener.<br />
     * Ici, le listener permet de détecter un click sur un objet.
     *
     * @author Jordi CHARPENTIER jordi.charpentier@gmail.com
     */
    private class ActionsListener implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            panel_dessin.requestFocus();

            if (e.getSource() == tb_carre) {
                panel_dessin.setPointerType("carre");
            } else if (e.getSource() == tb_rond) {
                panel_dessin.setPointerType("rond");
            } else if (e.getSource() == tb_couleur) {
                panel_dessin.setPointerColor(JColorChooser.showDialog(null, "Couleur du texte", Color.WHITE));
            } else if (e.getSource() == tb_gomme) {
                panel_dessin.setPointerColor(Color.WHITE);
            } else if (e.getSource() == tb_redimensionner) {
                final Redimensionner redim_frame = new Redimensionner(panel_dessin.getWidth(), panel_dessin.getHeight());

                redim_frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        if (redim_frame.isClosingRight()) {
                            panel_dessin.setBounds(0, tool_barre.getHeight(), redim_frame.getLargeur(), redim_frame.getHauteur());
                        }
                    }
                });
            }
        }
    }
}
