package gamePackage;


import java.awt.Graphics;
import java.awt.Rectangle;

public class EnemyShip extends Game_Object {
	int speed = 5;
	int level = 1;
    boolean isBought;
    Rectangle hitBox = new Rectangle(x, y, width, height);
	EnemyShip(int x, int y, int width, int height, int health, int level) {
		super(x, y, width, height, health);
this.level = level;
	}

	void update() {
		super.update();
		hitBox.setBounds(x, y, width, height);
	}

	void draw(Graphics g) {

if(level == 1) {
		g.drawImage(GamePanel.level1Boat, x,y,width, height, null);
}else if(level == 2) {
	g.drawImage(GamePanel.level2Boat, x,y,width, height, null);
}
else if(level == 3) {
	g.drawImage(GamePanel.level3Boat, x,y,width, height, null);
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
