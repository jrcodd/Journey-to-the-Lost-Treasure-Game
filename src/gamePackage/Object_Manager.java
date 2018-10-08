package gamePackage;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Object_Manager {
	static ArrayList<Game_Object> inv;
	Shack s;
	static SpeedyBoots caveBoots;
	Player p;
	static TreasureMap m;
	Rectangle shack = new Rectangle(500, 50, 280, 280);
	OldMan man;
	Sword sword;
	StrongBandit b;
	boolean isDefending;
	static HealthPotion pot;
     
	Object_Manager(Player p, TreasureMap m, SpeedyBoots caveBoots, Shack s, OldMan man, Sword sword, StrongBandit b,
			HealthPotion pot) {
		this.s = s;
		this.p = p;
		Object_Manager.m = m;
		Object_Manager.caveBoots = caveBoots;
		this.man = man;
		this.sword = sword;
		this.b = b;
		Object_Manager.pot = pot;
		inv = new ArrayList<Game_Object>();
	}

	void checkCollision() {
		if (GamePanel.getState() == GamePanel.LAGOON_STATE) {
			if (p.collisionBox.intersects(pot.collisionBox)) {
				pot.isFound = true;
				

			}
		}
		if (GamePanel.getState() == GamePanel.PATH1_STATE) {
			if (StrongBandit.isDead == false) {
				if (b.collisionBox.x <= 30) {
					b.right = true;
					b.left = false;
				} else if (b.collisionBox.x >= JourneyToTheLostTreasure.WIDTH - 150) {
					b.right = false;
					b.left = true;
				}
				if (p.collisionBox.intersects(b.collisionBox)) {
					if (isDefending == false) {
						p.health -= 5;
						System.out.println("player health: " + p.health);
						isDefending = true;
					}
				}
				if (p.collisionBox.intersects(b.collisionBox)) {
					if (GamePanel.swordDown) {
						b.setHealth(b.getHealth() - 30);
						isDefending = false;
						System.out.println("bandit health: " + b.health);
					}
				}
			}
		}
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
				m.isFound = true;
				
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
		b.update();
		pot.update();
		checkCollision();

	}

}
