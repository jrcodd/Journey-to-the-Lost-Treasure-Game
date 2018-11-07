package gamePackage;

import java.awt.Graphics;
import java.awt.Rectangle;

public class EnemyShip extends Game_Object {
	int speed = 2;
	int level;
	Rectangle hitBox = new Rectangle(x, y, width, height);

	EnemyShip(int x, int y, int width, int height, int health, int level) {
		super(x, y, width, height, health);
		this.level = level;
	}

	void update() {
		super.update();
		hitBox.setBounds(x, y, width, height);
	}

	void draw(Graphics g, int x, int y) {
			g.drawImage(GamePanel.enemyShip, x, y, width, height, null);
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

	void setHealth(int newHealth) {
		health = newHealth;
	}

	int getHealth() {
		return health;
	}

	int getSpeed() {
		return speed;
	}
}
