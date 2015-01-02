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

package ui.custo;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

/**
 * Classe représentant le panel où l'on dessinera.
 * 
 * @author Jordi CHARPENTIER jordi.charpentier@gmail.com
 */

@SuppressWarnings("serial")
public class PanelPaint extends JPanel{
	private Color pointerColor = Color.BLACK;
	private String pointerType = "rond";
	private boolean erasing = true;
	private int pointerSize = 15;
	private ArrayList<Point> points = new ArrayList<Point>();	
	private String path_img;

	public PanelPaint() {
		super();
		
		this.addMouseListener(new MouseAdapter(){
			public void mousePressed(MouseEvent e){
				if(SwingUtilities.isLeftMouseButton(e)) {
					points.add(new Point(e.getX() - (pointerSize / 2), e.getY() - (pointerSize / 2), pointerSize, pointerColor, pointerType));
					repaint();
				}
			}
		});

		this.addMouseMotionListener(new MouseMotionAdapter(){
			public void mouseDragged(MouseEvent e) {
				if(SwingUtilities.isLeftMouseButton(e)) {
					points.add(new Point(e.getX() - (pointerSize / 2), e.getY() - (pointerSize / 2), pointerSize, pointerColor, pointerType));
					repaint();
				}
			}
		});
	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		if(this.erasing) this.erasing = false;

		else {
			for(Point p : this.points) {
				g.setColor(p.getColor());

				if(p.getType().equals("carre")) g.fillRect(p.getX(), p.getY(), p.getSize(), p.getSize());
				else if(p.getType().equals("rond")) g.fillOval(p.getX(), p.getY(), p.getSize(), p.getSize());
			}
		}
		
		if(path_img == null);
		
		else {
			Image img = Toolkit.getDefaultToolkit().getImage(path_img);
			g.drawImage(img, 0, 0, this);
		}
	}
	
	/* Accesseur. */
	
	/**
	 * Méthode permettant de savoir combien de points sont dessinés sur le JPanel.
	 * 
	 * @return Le nombre de points dessinés sur le JPanel.
	 */
	
	public int getNbPoints() {
		return this.points.size();
	}
	
	/* Mutateurs. */
	
	/**
	 * Méthode permettant de modifier la couleur du pointeur.
	 * 
	 * @param c La nouvelle couleur du pointeur.
	 */

	public void setPointerColor(Color c) {
		this.pointerColor = c;
	}
	
	/**
	 * Méthode permettant de modifier la forme du pointeur.
	 * 
	 * @param str La nouvelle forme du pointeur.
	 */

	public void setPointerType(String str) {
		this.pointerType = str;
	}
	
	/**
	 * Méthode permettant de modifier la taille du pointeur.
	 * 
	 * @param str La nouvelle forme du pointeur.
	 */

	public void setPointerSize(int taille) {
		this.pointerSize = taille;
	}
	
	/* Autres. */
	
	/**
	 * Méthode permettant d'effacer tout le dessin actuel.
	 */
	
	public void erase() {
		this.erasing = true;
		this.points = new ArrayList<Point>();
		repaint();
	}
	
	/**
	 * Méthode permettant de modifier le fond du JPanel.
	 * 
	 * @param path_img Le chemin de l'image.
	 */
	
	public void setBackground(String path_img) {
		this.path_img = path_img;
	}
}