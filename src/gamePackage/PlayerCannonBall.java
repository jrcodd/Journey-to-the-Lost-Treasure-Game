package gamePackage;

import java.awt.Graphics;

public class PlayerCannonBall extends Game_Object {
	static String dir;
	boolean isAlive;
	boolean dirSet = false;
	

	PlayerCannonBall(int x, int y, int width, int height, int health) {
		super(x, y, width, height, health);
		

	}

	void draw(Graphics g) {
		g.drawImage(GamePanel.cannonBallProjectile, x, y, width, height, null);
	}

	void update() {
		super.update();
		checkBounds();
		checkDirection();
		
	}

	void checkDirection() {/*
							 * if (PlayerShip.direction == 0) { if (dirSet == false) { dir = "LEFT"; dirSet
							 * = true; } } if (PlayerShip.direction == 3) { if (dirSet == false) { dir =
							 * "RIGHT"; dirSet = true; } } if (PlayerShip.direction == 1 ||
							 * PlayerShip.direction == 4) { if (dirSet == false) { dir = "UP"; dirSet =
							 * true; } } if (PlayerShip.direction == 2 || PlayerShip.direction == 5) { if
							 * (dirSet == false) { dir = "DOWN"; dirSet = true; } } switch (dir) {
							 * 
							 * case "LEFT": x -= 1; break; case "RIGHT": x += 1; break; case "UP": y -= 1;
							 * break; case "DOWN": y += 1; break; default:
							 * System.out.println("ERROR NO DIRECTION"); }
							 */
	}

	void checkBounds() {
		// works
		if (x < 0) {
			GamePanel.cannonballList.remove(GamePanel.cannonballList.size() - 1);
		}
		if (x > JourneyToTheLostTreasure.WIDTH) {
			GamePanel.cannonballList.remove(GamePanel.cannonballList.size() - 1);
		}
		if (y < 0) {
			GamePanel.cannonballList.remove(GamePanel.cannonballList.size() - 1);
		}
		if (y > JourneyToTheLostTreasure.HEIGHT) {
			GamePanel.cannonballList.remove(GamePanel.cannonballList.size() - 1);
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
