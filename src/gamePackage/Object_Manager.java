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
	WeakBandit b1;
	WeakBandit b2;
	boolean isDefending;
	static HealthPotion pot;
	boolean hasStarted = false;
	int coins;
	boolean coinsAdded;
	private boolean coinsAdded1;
	private boolean coinsAdded2;
	boolean isDefending2;
	boolean isDefending1;
	boolean b1Start;
	boolean b2Start;

	Object_Manager(Player p, TreasureMap m, SpeedyBoots caveBoots, Shack s, OldMan man, Sword sword, StrongBandit b,
			HealthPotion pot, WeakBandit b1, WeakBandit b2) {
		this.s = s;
		this.p = p;
		Object_Manager.m = m;
		Object_Manager.caveBoots = caveBoots;
		this.man = man;
		this.sword = sword;
		this.b = b;
		this.b1 = b1;
		this.b2 = b2;
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
			if (b.isDead == false) {
				if (b.collisionBox.x > JourneyToTheLostTreasure.WIDTH - 150) {
					System.out.println("hit the wall");
					isDefending = false;
					b.right = false;
					b.left = true;
				} else if (b.collisionBox.x < 0) {
					System.out.println("hit the other wall");
					isDefending = false;
					b.right = true;
					b.left = false;
				}

				if (hasStarted == false) {
					b.right = true;
					hasStarted = true;
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
			if (b.getHealth() <= 0) {
				b.isDead = true;
				if (coinsAdded == false) {
					coins += 50;
					coinsAdded = true;
				}
			}
		} else if (GamePanel.getState() == GamePanel.PATH2_STATE) {

			if (!b1.isDead) {
				processWeakBanditIsAlive(b1);
			}
			if (!b2.isDead) {
				processWeakBanditIsAlive(b2);
			}

			if (b1.getHealth() <= 0) {
				b1.isDead = true;
				if (coinsAdded1 == false) {
					coins += 25;
					coinsAdded1 = true;
				}
			}
			
			if (b2.getHealth() <= 0) {
				b2.isDead = true;
				if (coinsAdded2 == false) {
					coins += 25;
					coinsAdded2 = true;
				}
			}
		}
		if (GamePanel.getState() == 5)

		{
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

	private void processWeakBanditIsAlive(WeakBandit wb) {
		if(!wb.Start) {
			wb.right = true;
		}
		
		if (wb.collisionBox.x > JourneyToTheLostTreasure.WIDTH - 150) {
			System.out.println("hit the wall");
			isDefending2 = false;
			wb.right = false;
			wb.left = true;
		} else if (wb.collisionBox.x < 0) {
			System.out.println("hit the other wall");
			isDefending2 = false;
			wb.right = true;
			wb.left = false;
		}
		if (p.collisionBox.intersects(wb.collisionBox)) {
			if (isDefending2 == false) {
				p.health -= 3;
				System.out.println("player health: " + p.health);
				isDefending2 = true;
			}
		}
		if (p.collisionBox.intersects(wb.collisionBox)) {
			if (GamePanel.swordDown) {
				wb.setHealth(wb.getHealth() - 30);
				isDefending2 = false;
				System.out.println("Weak bandit2 health: " + b2.health);
				System.out.println("Weak bandit 1 health " + b1.health);
			}
		}
	}

	

	void update() {
		p.update();
		sword.update();
		b.update();
		b1.update();
		b2.update();
		pot.update();
		checkCollision();

	}

}
