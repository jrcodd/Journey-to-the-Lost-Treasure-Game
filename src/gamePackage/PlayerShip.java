package gamePackage;

import java.awt.Graphics;
import java.awt.Rectangle;

public class PlayerShip extends Game_Object {
	int speed = 5;
	int level;
	int maxHealth;
	int health;
	static int direction = 0;
	boolean isBought;
	Rectangle hitBox = new Rectangle(x, y, width, height);

	PlayerShip(int x, int y, int width, int height, int health, int level) {
		super(x, y, width, height, health);
		this.level = level;
this.health = health;
maxHealth = health;
	}

	void update() {
		super.update();
				hitBox.setBounds(x, y, width, height);
				if(maxHealth<health) {
					maxHealth = health;
				}
	}

	void draw(Graphics g) {
		if (GamePanel.playerisSailing) {
			if (level == 1) {
				if (GamePanel.left == true && GamePanel.right == false && GamePanel.up == false
						&& GamePanel.down == false) {
					direction = 0;

				} else if (GamePanel.left == true && GamePanel.up == true && GamePanel.down == false) {
					direction = 1;

				} else if (GamePanel.left == true && GamePanel.up == false && GamePanel.down == true) {
					direction = 2;

				} else if (GamePanel.left == false && GamePanel.right == true && GamePanel.up == false
						&& GamePanel.down == false) {
					direction = 3;

				} else if (GamePanel.left == false && GamePanel.right == true && GamePanel.up == true
						&& GamePanel.down == false) {
					direction = 4;

				} else if (GamePanel.left == false && GamePanel.right == true && GamePanel.up == false
						&& GamePanel.down == true) {
					direction = 5;

				} else if (GamePanel.left == false && GamePanel.right == false && GamePanel.up == false
						&& GamePanel.down == true && direction == 0) {
					direction = 2;
				} else if (GamePanel.left == false && GamePanel.right == false && GamePanel.up == false
						&& GamePanel.down == true && direction == 3) {
					direction = 5;
				} else if (GamePanel.left == false && GamePanel.right == false && GamePanel.up == true
						&& GamePanel.down == false && direction == 0) {
					direction = 1;
				} else if (GamePanel.left == false && GamePanel.right == false && GamePanel.up == true
						&& GamePanel.down == false && direction == 3) {
					direction = 4;
				} else if (GamePanel.left == false && GamePanel.right == false && GamePanel.up == false
						&& GamePanel.down == true && direction == 1) {
					direction = 5;
				} else if (GamePanel.left == false && GamePanel.right == false && GamePanel.up == false
						&& GamePanel.down == true && direction == 4) {
					direction = 2;
				} else if (GamePanel.left == false && GamePanel.right == false && GamePanel.up == true
						&& GamePanel.down == false && direction == 2) {
					direction = 4;
				} else if (GamePanel.left == false && GamePanel.right == false && GamePanel.up == true
						&& GamePanel.down == false && direction == 5) {
					direction = 1;
				}
				switch (direction) {
				case 0:
					g.drawImage(GamePanel.level1BoatLeft, x, y, width, height, null);
					break;
				case 1:
					g.drawImage(GamePanel.level1BoatLeftUp, x, y, height, width, null);
					break;
				case 2:
					g.drawImage(GamePanel.level1BoatLeftDown, x, y, height, width, null);
					break;
				case 3:
					g.drawImage(GamePanel.level1BoatRight, x, y, width, height, null);
					break;
				case 4:
					g.drawImage(GamePanel.level1BoatRightUp, x, y, height, width, null);
					break;
				case 5:
					g.drawImage(GamePanel.level1BoatRightDown, x, y, height, width, null);
					break;
				default:
					System.out.println("ERROR: NO DIRECTION");
					break;
				}

			} else if (level == 2) {
				if (GamePanel.left == true && GamePanel.right == false && GamePanel.up == false
						&& GamePanel.down == false) {
					direction = 0;

				} else if (GamePanel.left == true && GamePanel.up == true && GamePanel.down == false) {
					direction = 1;

				} else if (GamePanel.left == true && GamePanel.up == false && GamePanel.down == true) {
					direction = 2;

				} else if (GamePanel.left == false && GamePanel.right == true && GamePanel.up == false
						&& GamePanel.down == false) {
					direction = 3;

				} else if (GamePanel.left == false && GamePanel.right == true && GamePanel.up == true
						&& GamePanel.down == false) {
					direction = 4;

				} else if (GamePanel.left == false && GamePanel.right == true && GamePanel.up == false
						&& GamePanel.down == true) {
					direction = 5;

				} else if (GamePanel.left == false && GamePanel.right == false && GamePanel.up == false
						&& GamePanel.down == true && direction == 0) {
					direction = 2;
				} else if (GamePanel.left == false && GamePanel.right == false && GamePanel.up == false
						&& GamePanel.down == true && direction == 3) {
					direction = 5;
				} else if (GamePanel.left == false && GamePanel.right == false && GamePanel.up == true
						&& GamePanel.down == false && direction == 0) {
					direction = 1;
				} else if (GamePanel.left == false && GamePanel.right == false && GamePanel.up == true
						&& GamePanel.down == false && direction == 3) {
					direction = 4;
				} else if (GamePanel.left == false && GamePanel.right == false && GamePanel.up == false
						&& GamePanel.down == true && direction == 1) {
					direction = 5;
				} else if (GamePanel.left == false && GamePanel.right == false && GamePanel.up == false
						&& GamePanel.down == true && direction == 4) {
					direction = 2;
				} else if (GamePanel.left == false && GamePanel.right == false && GamePanel.up == true
						&& GamePanel.down == false && direction == 2) {
					direction = 4;
				} else if (GamePanel.left == false && GamePanel.right == false && GamePanel.up == true
						&& GamePanel.down == false && direction == 5) {
					direction = 1;
				}
				switch (direction) {
				case 0:
					g.drawImage(GamePanel.level2BoatLeft, x, y, width, height, null);
					break;
				case 1:
					g.drawImage(GamePanel.level2BoatLeftUp, x, y, height, width, null);
					break;
				case 2:
					g.drawImage(GamePanel.level2BoatLeftDown, x, y, height, width, null);
					break;
				case 3:
					g.drawImage(GamePanel.level2BoatRight, x, y, width, height, null);
					break;
				case 4:
					g.drawImage(GamePanel.level2BoatRightUp, x, y, height, width, null);
					break;
				case 5:
					g.drawImage(GamePanel.level2BoatRightDown, x, y, height, width, null);
					break;
				default:
					System.out.println("ERROR: NO DIRECTION");
					break;
				}

			} else if (level == 3) {
				if (GamePanel.left == true && GamePanel.right == false && GamePanel.up == false
						&& GamePanel.down == false) {
					direction = 0;

				} else if (GamePanel.left == true && GamePanel.up == true && GamePanel.down == false) {
					direction = 1;

				} else if (GamePanel.left == true && GamePanel.up == false && GamePanel.down == true) {
					direction = 2;

				} else if (GamePanel.left == false && GamePanel.right == true && GamePanel.up == false
						&& GamePanel.down == false) {
					direction = 3;

				} else if (GamePanel.left == false && GamePanel.right == true && GamePanel.up == true
						&& GamePanel.down == false) {
					direction = 4;

				} else if (GamePanel.left == false && GamePanel.right == true && GamePanel.up == false
						&& GamePanel.down == true) {
					direction = 5;

				} else if (GamePanel.left == false && GamePanel.right == false && GamePanel.up == false
						&& GamePanel.down == true && direction == 0) {
					direction = 2;
				} else if (GamePanel.left == false && GamePanel.right == false && GamePanel.up == false
						&& GamePanel.down == true && direction == 3) {
					direction = 5;
				} else if (GamePanel.left == false && GamePanel.right == false && GamePanel.up == true
						&& GamePanel.down == false && direction == 0) {
					direction = 1;
				} else if (GamePanel.left == false && GamePanel.right == false && GamePanel.up == true
						&& GamePanel.down == false && direction == 3) {
					direction = 4;
				} else if (GamePanel.left == false && GamePanel.right == false && GamePanel.up == false
						&& GamePanel.down == true && direction == 1) {
					direction = 5;
				} else if (GamePanel.left == false && GamePanel.right == false && GamePanel.up == false
						&& GamePanel.down == true && direction == 4) {
					direction = 2;
				} else if (GamePanel.left == false && GamePanel.right == false && GamePanel.up == true
						&& GamePanel.down == false && direction == 2) {
					direction = 4;
				} else if (GamePanel.left == false && GamePanel.right == false && GamePanel.up == true
						&& GamePanel.down == false && direction == 5) {
					direction = 1;
				}
				switch (direction) {
				case 0:
					g.drawImage(GamePanel.level3BoatLeft, x, y, width, height, null);
					break;
				case 1:
					g.drawImage(GamePanel.level3BoatLeftUp, x, y, height, width, null);
					break;
				case 2:
					g.drawImage(GamePanel.level3BoatLeftDown, x, y, height, width, null);
					break;
				case 3:
					g.drawImage(GamePanel.level3BoatRight, x, y, width, height, null);
					break;
				case 4:
					g.drawImage(GamePanel.level3BoatRightUp, x, y, height, width, null);
					break;
				case 5:
					g.drawImage(GamePanel.level3BoatRightDown, x, y, height, width, null);
					break;
				default:
					System.out.println("ERROR: NO DIRECTION");
					break;
				}

			}
		} else {
			g.drawImage(GamePanel.level1BoatLeft, x, y, width, height, null);
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

	int getSpeed() {
		return speed;
	}
}
