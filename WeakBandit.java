package gamePackage;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class WeakBandit extends Game_Object {

	int speed;
	Rectangle attackRadius;
	boolean left;
	boolean right;
	boolean isDead = false;
	boolean Start = false;
	WeakBandit(int x, int y, int width, int height, int health, int speed) {
		super(x, y, width, height, health);
		this.speed = speed;

	}

	void update() {
		super.update();
		

	}

	void draw(Graphics g) {
		g.setColor(Color.MAGENTA);
		g.fillRect(x, y, width, height);
		
		drawHealth(g);
		

	}

	private void drawHealth(Graphics g) {
		if (health < 30) {
			g.setColor(Color.red);
		} else if (health < 50) {
			g.setColor(Color.YELLOW);
		} else if (health > 50) {
			g.setColor(Color.darkGray);
		}
		g.drawString(Integer.toString(health), x - 10, y + 80);
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

}
