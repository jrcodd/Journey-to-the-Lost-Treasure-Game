package gamePackage;

import java.awt.Graphics;

public class Player extends Game_Object {
	int speed = 5;

	Player(int x, int y, int width, int height, int health, int speed) {
		super(x, y, width, height, health);
		this.speed = speed;
	}

	void update() {
		super.update();
	}

	void draw(Graphics g) {

		g.fill3DRect(x, y, width, height, true);

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
