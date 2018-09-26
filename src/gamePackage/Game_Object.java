package gamePackage;

public class Game_Object {
	int health;
	int x;
	int y;
	int height;
	int width;

	Game_Object(int x, int y, int width, int height, int health) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.health = health;
	}

	void update() {

	}
}
