package gamePackage;

import java.awt.Color;
import java.awt.Graphics;

public class BayShop extends Game_Object {
	boolean inside = false;

	BayShop(int x, int y, int width, int height, int health, boolean inside) {
		super(x, y, width, height, health);
		this.inside = inside;
	}

	void draw(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(530, 20, 300, 300);
	}

}
