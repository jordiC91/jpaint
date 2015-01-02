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

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

/**
 * Classe représentant la fenêtre d'à propos de JPaint.
 *
 * @author Jordi CHARPENTIER jordi.charpentier@gmail.com
 */
public class APropos extends JFrame {
    
    /* Eléments graphiques. */

    private final JPanel container = new JPanel();

    private final JPanel container_haut = new JPanel();
    private final JLabel lab_logo = new JLabel(new ImageIcon(getClass().getResource("/images/icones/icone_grande.png")));
    private final Box box = Box.createVerticalBox();
    private final JLabel lab_nom_logi = new JLabel();
    private final JLabel lab_auteur = new JLabel("Auteur : Jordi CHARPENTIER");
    private final JLabel lab_contact = new JLabel("Contact : contact@jordi-charpentier.com");

    private final JEditorPane zone_texte = new JEditorPane();
    private final JScrollPane scroll_texte = new JScrollPane(zone_texte);
    private final JButton bout_ok = new JButton("OK");

    public APropos() {
        this.setSize(400, 400);
        this.setLocationRelativeTo(null);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getClassLoader().getResource("images/icones/icone.png")));
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("A Propos");
        this.getContentPane().add(container);

        initContainers();

        this.setVisible(true);
        this.setResizable(false);
    }

    /* Méthode graphique. */
    
    private void initContainers() {
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        container.add(Box.createVerticalStrut(5));

        container.add(container_haut);
        container_haut.add(lab_logo);
        lab_logo.setAlignmentX(Component.CENTER_ALIGNMENT);

        container_haut.add(box);
        box.add(lab_nom_logi);
        lab_nom_logi.setText("JPaint : " + lireFichier("/version"));
        lab_nom_logi.setAlignmentX(Component.LEFT_ALIGNMENT);
        box.add(lab_auteur);
        lab_auteur.setAlignmentX(Component.LEFT_ALIGNMENT);
        box.add(lab_contact);
        lab_contact.setAlignmentX(Component.LEFT_ALIGNMENT);

        container.add(scroll_texte);
        scroll_texte.setMaximumSize(new Dimension(250, 250));
        scroll_texte.setAlignmentX(Component.CENTER_ALIGNMENT);

        zone_texte.setContentType("text/html");
        zone_texte.setText("<font face=\"Arial\"<center>"
                + "<h3>GNU General Public Licence</h3></center></font>"
                + "<font face=\"Arial\" size=3>This program is free software; "
                + "you can redistribute it and/or modify it under the terms of "
                + "the GNU General Public License as published by the Free "
                + "Software Foundation; either version 3 of the License, or (at your "
                + "option) any later version. <br><br> This program is distributed "
                + "in the hope that it will be useful, but WITHOUT ANY WARRANTY; "
                + "without even the implied warranty of MERCHANTABILITY or FITNESS "
                + "FOR A PARTICULAR PURPOSE. See the GNU General Public License for "
                + "more details. <br><br> You should have received a copy of the GNU"
                + " General Public License along with this program; if not, write"
                + " to the Free Software Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.</font>");

        zone_texte.setCaretPosition(0);
        zone_texte.setEditable(false);

        container.add(Box.createVerticalStrut(5));

        container.add(bout_ok);
        bout_ok.setAlignmentX(Component.CENTER_ALIGNMENT);
        bout_ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        container.add(Box.createVerticalStrut(5));
    }

    /* Autre. */
    
    /**
     * Méthode permettant de lire un fichier qui se situe dans le ".jar" du logiciel.
     *
     * @param path_file Le chemin du fichier à lire. Le fichier doit se situer dans le ".jar".
     * 
     * @return Le contenu du fichier si la lecture s'est bien passée.<br /><b>-1</b> sinon.
     */
    private String lireFichier(String path_file) {
        String contenu_fichier = "";

        try {
            int n;
            InputStream fis = APropos.this.getClass().getResourceAsStream(path_file);

            while ((n = fis.available()) > 0) {
                byte[] b = new byte[n];
                int result = fis.read(b);
                if (result == -1) {
                    break;
                }
                contenu_fichier = new String(b);
            }
        } catch (IOException err) {
            return "-1";
        }

        return contenu_fichier;
    }
}
