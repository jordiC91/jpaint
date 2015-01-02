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

/**
 * Classe repr�sentant un point.
 *
 * @author Jordi CHARPENTIER jordi.charpentier@gmail.com
 */
public class Point {

    private Color color = Color.RED;
    private int size = 10;
    private int x = -10;
    private int y = -10;
    private String type = "rond";

    public Point(int x, int y, int size, Color color, String type) {
        this.size = size;
        this.x = x;
        this.y = y;
        this.color = color;
        this.type = type;
    }

    /* Getters. */
    
    public Color getColor() {
        return this.color;
    }

    public int getSize() {
        return this.size;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public String getType() {
        return this.type;
    }

    /* Setters. */
    
    public void setColor(Color color) {
        this.color = color;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setType(String type) {
        this.type = type;
    }
}