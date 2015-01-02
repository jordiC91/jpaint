/*
 * Copyright 2012 Jordi CHARPENTIER jordi.charpentier@gmail.com
 * 
 * This file is part of JChat.

 * JChat is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.

 * JChat is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.

 * You should have received a copy of the GNU General Public License
 * along with JChat. If not, see <http://www.gnu.org/licenses/>.
 */

package ui.custo;

import javax.swing.*;
import javax.swing.text.*;

/**
 * Classe représantant un JTextField personnalisé : il ne pourra pas contenir qu'un nombre donné de caractère(s).
 * 
 * @author Jordi CHARPENTIER jordi.charpentier@gmail.com
 */

@SuppressWarnings({"serial"})
public class JTextFieldPerso extends JTextField {
	private int limite;

	public JTextFieldPerso(int limite) {
		this.limite = limite;
		changeLimite();
	}

	private void changeLimite() {
		setDocument(new LimiteDigitDocument(this.limite));
	}

	private class LimiteDigitDocument extends PlainDocument {
		private int limite;

		public LimiteDigitDocument(int limite) {
			super();
			this.limite = limite;
		}

		public void insertString (int off, String s, AttributeSet a) throws BadLocationException  {
			if((s.length() + getLength() > limite) || (s == null)) return;

			super.insertString(off, s, a);
		}
	}
}