package gamePackage;

import java.awt.Color;
import java.awt.Graphics;

public class StrongBandit extends Game_Object {
	int speed = 5;

	StrongBandit(int x, int y, int width, int height, int health, int speed) {
		super(x, y, width, height, health);
		this.speed = speed;
	}

	void update() {
		super.update();
	}

	void draw(Graphics g) {
		g.setColor(Color.RED);
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
