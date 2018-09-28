package gamePackage;

public class Object_Manager {
	Player p;
	TreasureMap m;

	Object_Manager(Player p, TreasureMap m) {
		this.p = p;
		this.m = m;

	}

	void checkCollision() {
		if (p.collisionBox.intersects(m.collisionBox)) {
			m.found = true;
		}

	}

	void update() {
		p.update();
		checkCollision();
	}
}
