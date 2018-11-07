package gamePackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class StrongBandit extends Game_Object {
	int speed;
	Rectangle attackRadius;
	boolean left;
	boolean right;
	 boolean isDead = false;

	StrongBandit(int x, int y, int width, int height, int health, int speed) {
		super(x, y, width, height, health);
		this.speed = speed;

	}

	void update() {
		super.update();
		if (health <= 0) {
			isDead = true;
		}
		if (isDead == false) {

		}
	}

	void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(x, y, width, height);
		if (health < 30) {
			g.setColor(Color.red);
		} else if (health < 50) {
			g.setColor(Color.YELLOW);
		} else if (health > 50) {
			g.setColor(Color.darkGray);
		}
		g.drawString(Integer.toString(health), getX(), getY() + 110);
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

	void setHealth(int newHealth) {
		health = newHealth;
	}

	int getHealth() {
		return health;
	}

	void move() {
		if (left) {
			x -= 1;

		}
		if (right) {
			x += 1;

		}
	}

}
