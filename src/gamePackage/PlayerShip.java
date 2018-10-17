package gamePackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PlayerShip extends Game_Object {
	int speed = 5;
    boolean isBought;
    Rectangle hitBox = new Rectangle(x, y, width, height);
	PlayerShip(int x, int y, int width, int height, int health) {
		super(x, y, width, height, health);

	}

	void update() {
		super.update();
		hitBox.setBounds(x, y, width, height);
	}

	void draw(Graphics g) {
g.setColor(Color.ORANGE);
		g.fillRect(x, y, width, height);

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

	int getSpeed() {
		return speed;
	}
}
