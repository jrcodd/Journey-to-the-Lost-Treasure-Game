package gamePackage;

import java.awt.Rectangle;

public class Object_Manager {
	Shack s;
	SpeedyBoots caveBoots;
	Player p;
	TreasureMap m;
	Rectangle shack = new Rectangle(500, 50, 280, 280);
	OldMan man;
	Sword sword;

	Object_Manager(Player p, TreasureMap m, SpeedyBoots caveBoots, Shack s, OldMan man, Sword sword) {
		this.s = s;
		this.p = p;
		this.m = m;
		this.caveBoots = caveBoots;
		this.man = man;
		this.sword = sword;
	}

	void checkCollision() {
		if (GamePanel.getState() == 5) {
			if (p.collisionBox.intersects(man.collisionBox)) {

				if (man.hasTalked == false) {
					GamePanel.up = false;
					GamePanel.down = false;
					GamePanel.right = false;
					GamePanel.left = false;
					man.hasTalked = true;
					man.talk();
					sword.isFound = true;
				}
			}
		}
		if (GamePanel.getState() == 1) {
			if (p.collisionBox.intersects(m.collisionBox)) {
				m.found = true;
			}
		}
		if (GamePanel.getState() == 3) {
			if (caveBoots.collisionBox.intersects(p.collisionBox)) {
				caveBoots.isFound = true;
			}
		}
		if (GamePanel.getState() == 4) {
			if (p.collisionBox.intersects(shack)) {
				s.inside = true;
			}
		}

	}

	void update() {
		p.update();
		sword.update();

		checkCollision();

	}

}
