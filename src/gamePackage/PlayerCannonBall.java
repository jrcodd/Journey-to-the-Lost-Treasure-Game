package gamePackage;

import java.awt.Color;
import java.awt.Graphics;

public class PlayerCannonBall extends Game_Object {

	PlayerCannonBall(int x, int y, int width, int height, int health) {
		super(x, y, width, height, health);

	}

	void draw(Graphics g) {
		System.out.println("drawing cannon ball");
		System.out.println("x: "+ x);
		System.out.println("y: "+ y);
		g.setColor(Color.gray);
		g.fillRect(x, y, width, height);
		//g.drawImage(GamePanel.cannonBallProjectile, x, y, width, height, null);
	}

	void update() {
		super.update();
		
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
