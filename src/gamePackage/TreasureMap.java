package gamePackage;

import java.awt.Color;
import java.awt.Graphics;

public class TreasureMap extends Game_Object {
	boolean found;

	TreasureMap(int x, int y, int width, int height, int health, boolean found) {
		super(x, y, width, height, health);
		this.found = found;
	}

	void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, width, height);

	}

}
