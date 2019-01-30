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
	boolean ready = true;
	int cooldown = 100;

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

	void drawLeft(Graphics g) {
		if (!isDead) {
			g.drawImage(GamePanel.StrongEnemyLeft, x, y, width, height, null);
			drawHealth(g);
		}
	}

	void drawRight(Graphics g) {
		if (!isDead) {
			g.drawImage(GamePanel.StrongEnemyRight, x, y, width, height, null);
			drawHealth(g);
		}
	}

	void drawLeftAttack(Graphics g) {
		if (!isDead) {
			g.drawImage(GamePanel.StrongEnemyLeftAttacking, x, y, width, height, null);
			drawHealth(g);
		}
	}

	void drawRightAttack(Graphics g) {
		if (!isDead) {
			g.drawImage(GamePanel.StrongEnemyRightAttacking, x, y, width, height, null);
			drawHealth(g);
		}
	}

	void drawHealth(Graphics g) {
		if (health < 30) {
			g.setColor(Color.red);
		} else if (health < 50) {
			g.setColor(Color.YELLOW);
		} else if (health > 50) {
			g.setColor(Color.darkGray);
		}
		g.drawString(Integer.toString(health), x + (width * (2 / 3)), y + height + 10);

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

	boolean getReady() {
		return ready;
	}

	void setReady(boolean newReady) {
		ready = newReady;
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

	int getWidth() {
		return width;
	}

	void setWidth(int newWidth) {
		width = newWidth;
	}

	int getHeight() {
		return height;
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
