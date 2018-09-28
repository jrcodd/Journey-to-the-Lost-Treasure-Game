package gamePackage;

import java.awt.Rectangle;

public class Game_Object {
	int health;
	int x;
	int y;
	int height;
	int width;
Rectangle collisionBox;
	Game_Object(int x, int y, int width, int height, int health) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.health = health;
		collisionBox = new Rectangle(x, y, width, height);
	}

	void update() {
		collisionBox.setBounds(x, y, width, height);
	}
}
