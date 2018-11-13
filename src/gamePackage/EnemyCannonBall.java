package gamePackage;

import java.awt.Graphics;

public class EnemyCannonBall extends Game_Object {
	String dir;
	boolean isAlive;
	boolean dirSet = false;

	EnemyCannonBall(int x, int y, int width, int height, int health) {
		super(x, y, width, height, health);

	}

	void draw(Graphics g) {
		g.drawImage(GamePanel.cannonBallProjectile, x, y, width, height, null);
	}

	void update() {
		super.update();
		checkBounds();

	}

	void checkBounds() {
		if (GamePanel.EnemycannonballList.size() > 0) {
			if (x < 0) {
				GamePanel.EnemycannonballList.remove(GamePanel.cannonballList.size() - 1);
			}
			if (x > JourneyToTheLostTreasure.WIDTH) {
				GamePanel.EnemycannonballList.remove(GamePanel.cannonballList.size() - 1);
			}
			if (y < 0) {
				GamePanel.EnemycannonballList.remove(GamePanel.cannonballList.size() - 1);
			}
			if (y > JourneyToTheLostTreasure.HEIGHT) {
				GamePanel.EnemycannonballList.remove(GamePanel.cannonballList.size() - 1);
			}
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

}
