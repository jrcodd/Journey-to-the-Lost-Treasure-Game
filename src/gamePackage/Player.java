package gamePackage;

import java.awt.Graphics;

public class Player extends Game_Object {

	int speed = 5;
	String dir = "LEFT";
	boolean dirSet = false;

	Player(int x, int y, int width, int height, int health, int speed) {
		super(x, y, width, height, health);
		this.speed = speed;
	}
	void update() {
		super.update();
		
	}
	void draw(Graphics g) {

		if (!Object_Manager.playerisSailing) {

			if (GamePanel.left) {

				dir = "LEFT";

			}
			if (GamePanel.right) {
				dir = "RIGHT";
			}
			switch (dir) {
			case "LEFT":
				if (GamePanel.swordUp) {
					g.drawImage(GamePanel.PlayerSwordUpLeft, x, y, width, height, null);
				}
				break;

			case "RIGHT":
				if (GamePanel.swordUp) {
					g.drawImage(GamePanel.PlayerSwordUpRight, x, y, width, height, null);
				}
				break;
			}

			if (GamePanel.swordDown) {
				if (GamePanel.left) {

					g.drawImage(GamePanel.PlayerSwordDownLeft, x, y, width, height, null);
				}
			}
			if (GamePanel.swordDown) {
				if (GamePanel.right) {
					g.drawImage(GamePanel.PlayerSwordDownRight, x, y, width, height, null);
				}
			}
			if (GamePanel.swordUp) {
				if (GamePanel.left) {

				}
			}
			if (GamePanel.swordUp) {
				if (GamePanel.right) {
					g.drawImage(GamePanel.PlayerSwordUpRight, x, y, width, height, null);
				}
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
		if (newX > GamePanel.RIGHT) {
			x = GamePanel.RIGHT+1;
		} else if (newX < 0) {
			x = -1;
		} else {
			x = newX;
		}
	}

	void setY(int newY) {
		if (newY > GamePanel.BOTTOM) {
			y = GamePanel.BOTTOM + 1;
		} else if (newY < 0) {
			y = -1;
		} else {
			y = newY;
		}
	}
	int getSpeed() {
		return speed;
	}
}
