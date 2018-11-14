package gamePackage;

import java.awt.Graphics;
public class EnemyShip extends Game_Object {
	int speed = 2;
	int level;
	int health;
boolean addedRewards = false;
boolean damageDelt = false;
	boolean isAlive = true;

	EnemyShip(int x, int y, int width, int height, int health, int level) {
		super(x, y, width, height, health);
		this.level = level;
		this.health = health;

	}

	void update() {
		super.update();

		
		
	}

	void draw(Graphics g) {
		if (isAlive) {
			g.drawImage(GamePanel.enemyShip, x, y, width, height, null);
			g.drawString(Integer.toString(health), x+width/2, y+height+height/2);
		}
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
