package gamePackage;

import java.awt.Rectangle;

public class Object_Manager {
	Shack s;
	SpeedyBoots caveBoots;
	Player p;
	TreasureMap m;
	Rectangle shack = new Rectangle(500, 50, 280, 280);

	Object_Manager(Player p, TreasureMap m, SpeedyBoots caveBoots, Shack s) {
		this.s=s;
		this.p = p;
		this.m = m;
		this.caveBoots = caveBoots;
	}

	void checkCollision() {
		if (p.collisionBox.intersects(m.collisionBox)) {
			m.found = true;
		}
		
		if (caveBoots.collisionBox.intersects(p.collisionBox)) {
			caveBoots.isFound = true;
		}
		if(p.collisionBox.intersects(shack)) {
			s.inside = true;
		}

	}

	void update() {
		p.update();
		checkCollision();
		
	}
}
