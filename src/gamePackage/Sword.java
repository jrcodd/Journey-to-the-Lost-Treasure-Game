package gamePackage;

import java.awt.Color;
import java.awt.Graphics;

public class Sword extends Game_Object {
	Player p = new Player(100, 500, 20, 60, 100, 5);
	boolean isFound;
	Sword(int x, int y, int width, int height, int health, boolean isFound) {
		super(x, y, width, height, health);

	}

	void draw(Graphics g) {
	g.setColor(Color.WHITE);
	g.fillRect(x, y, width, height);
	g.setColor(Color.DARK_GRAY);
	g.fillRect(x-5, y+25, height/2, width/2);
	}
	int getX() {
		return x;

	}

	int getY() {
		return y;
	}

	void setX(int newX) {
		x = newX;
	}

	void setY(int newY) {
		y = newY;
	}
	void update() {
		super.update();
	}

}
