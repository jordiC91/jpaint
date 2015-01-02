/*
 * Copyright 2012 Jordi CHARPENTIER jordi.charpentier@gmail.com
 * 
 * This file is part of JPaint.

 * JPaint is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.

 * JPaint is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with JPaint. If not, see <http://www.gnu.org/licenses/>.
 */

package ui;

import javax.swing.*;
import ui.frame.Accueil;

/**
 * Classe contenant la méthode "main", méthode principale du programme.
 *
 * @author Jordi CHARPENTIER jordi.charpentier@gmail.com
 */
public class Main {

    public static void main(String[] args) {

        /* Le code suivant permet d'utiliser le LaF du système sur lequel JPaint est lancé. */
        
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            /* On ignore cette exception. */
        }
        
        /* Lancement de l'accueil de JPaint. */
        
        Accueil accueil = new Accueil();
    }
}
