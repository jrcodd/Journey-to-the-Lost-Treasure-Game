package gamePackage;

import java.awt.Color;

import java.awt.Graphics;

import javax.swing.JOptionPane;

public class OldMan extends Game_Object {

	boolean hasTalked;

	OldMan(int x, int y, int width, int height, int health) {
		super(x, y, width, height, health);

	}

	void draw(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(x, y, width, height);
	}

	void talk() {

		JOptionPane.showMessageDialog(null, "Here is a Ship Repair Kit. Be careful out there traveler.");
		JOptionPane.showMessageDialog(null,
				"The Ship Repair kit can be used to heal the ship to full health. It has a cooldown.");
	}

}
