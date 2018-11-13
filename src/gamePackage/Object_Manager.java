package gamePackage;

import java.util.ArrayList;

public class Object_Manager {
	static ArrayList<Game_Object> inv;
	Shack s;
	boolean enemyHasFired = false;
	boolean playerHasFired = false;
	BayShop bayShop;
	PlayerShip ship;
	EnemyShip eShip;
	static SpeedyBoots caveBoots;
	Player p;
	static TreasureMap m;
	OldMan man;
	Sword sword;
	StrongBandit b;
	WeakBandit b1;
	WeakBandit b2;
	boolean isDefending;
	static HealthPotion pot;
	boolean bStart;
	int coins;
	boolean coinsAdded;
	boolean addedRewards;
	private boolean coinsAdded1;
	private boolean coinsAdded2;
	boolean isDefending2;
	boolean isDefending1;
	boolean b1Start;
	boolean b2Start;
	boolean damageDelt = false;
	boolean damageDelt2 = false;

	Object_Manager(Player p, TreasureMap m, SpeedyBoots caveBoots, Shack s, BayShop bayShop, PlayerShip ship,
			OldMan man, Sword sword, StrongBandit b, HealthPotion pot, WeakBandit b1, WeakBandit b2, EnemyShip eShip) {
		this.s = s;
		this.bayShop = bayShop;
		this.p = p;
		Object_Manager.m = m;
		Object_Manager.caveBoots = caveBoots;
		this.man = man;
		this.sword = sword;
		this.b = b;
		this.b1 = b1;
		this.b2 = b2;
		this.ship = ship;
		this.eShip = eShip;
		Object_Manager.pot = pot;
		inv = new ArrayList<Game_Object>();
	}

	void checkCollision() {

		if (GamePanel.mapStates[GamePanel.mapRow][GamePanel.mapColumn] == GamePanel.BAY_STATE) {
			if (ship.hitBox.x <= 0) {
				GamePanel.mapRow = 3;
				GamePanel.mapColumn = 2;
			}
			if (p.collisionBox.intersects(bayShop.collisionBox)) {
				bayShop.inside = true;
			}

			if (p.collisionBox.intersects(ship.hitBox)) {
				purchaseShip();
				LeaveInShip();
			}

		}

		if (GamePanel.mapStates[GamePanel.mapRow][GamePanel.mapColumn] != GamePanel.PATH1_STATE) {
			b.right = false;
			b.left = false;
		}
		if (GamePanel.mapStates[GamePanel.mapRow][GamePanel.mapColumn] != GamePanel.PATH2_STATE) {
			b1.right = false;
			b2.right = false;
			b1.left = false;
			b1.right = false;
		}
		if (GamePanel.mapStates[GamePanel.mapRow][GamePanel.mapColumn] == GamePanel.LAGOON_STATE) {
			if (p.collisionBox.intersects(pot.collisionBox)) {
				pot.isFound = true;

			}
		}
		if (GamePanel.mapStates[GamePanel.mapRow][GamePanel.mapColumn] == GamePanel.PATH1_STATE) {

			if (b.isDead == false) {

				if (p.collisionBox.intersects(b.collisionBox)) {
					if (!GamePanel.swordDown) {
						if (isDefending == false) {
							p.health -= 5;
							System.out.println("player health: " + p.health);
							isDefending = true;
						}
					}
				}
				if (p.collisionBox.intersects(b.collisionBox)) {
					if (GamePanel.swordDown) {
						if (isDefending == true) {
							b.setHealth(b.getHealth() - 30);
							isDefending = false;
							System.out.println("bandit health: " + b.health);
						}
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
		} else if (GamePanel.mapStates[GamePanel.mapRow][GamePanel.mapColumn] == GamePanel.PATH2_STATE) {
			System.out.println("weak bandit2 is dead: " + b2.isDead);
			if (!b1.isDead) {
				processWeakBanditIsAlive(b1);
			}
			if (!b2.isDead) {
				processWeakBanditIsAlive(b2);
			}

			if (b1.getHealth() <= 0) {
				b1.isDead = true;
				b2.isDead = false;
				if (coinsAdded1 == false) {
					b2.setX(300);
					b2.setY(30);
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

		if (s.inside) {
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
		if (GamePanel.mapStates[GamePanel.mapRow][GamePanel.mapColumn] == GamePanel.FOREST_STATE) {
			if (p.collisionBox.intersects(m.collisionBox)) {
				m.isFound = true;

			}
		}
		if (GamePanel.mapStates[GamePanel.mapRow][GamePanel.mapColumn] == GamePanel.CAVE_STATE) {
			if (caveBoots.collisionBox.intersects(p.collisionBox)) {
				caveBoots.isFound = true;

			}
		}
		if (GamePanel.mapStates[GamePanel.mapRow][GamePanel.mapColumn] == GamePanel.FOREST_EDGE) {
			if (p.collisionBox.intersects(s.collisionBox)) {

				s.inside = true;
			}
		}
		if (GamePanel.mapStates[GamePanel.mapRow][GamePanel.mapColumn] == GamePanel.OCEAN_STATE) {
			damageDelt = false;
			damageDelt2 = false;
			if (eShip.health <= 0) {
				eShip.isAlive = false;
				if (!addedRewards) {
					coins += 50;
					ship.level += 1;
					addedRewards = true;
				}
			}
			if (GamePanel.cannonballList.size() > 0) {
				for (PlayerCannonBall ball : GamePanel.cannonballList) {
					if (ball.collisionBox.intersects(eShip.collisionBox)) {
						System.out.println("damage is delt? " + damageDelt);
						if (damageDelt == false) {
							System.out.println("hit");
							GamePanel.cannonballList.remove(GamePanel.cannonballList.size() - 1);
							eShip.health -= 30;
							System.out.println("enemy Ship health: " + eShip.health);
							damageDelt = true;
						}
					}
				}
			}
			if (GamePanel.EnemycannonballList.size() > 0) {
				for (EnemyCannonBall ball : GamePanel.EnemycannonballList) {
					if (ball.collisionBox.intersects(ship.collisionBox)) {
						if (damageDelt2 == false) {
							System.out.println("hit");
							GamePanel.EnemycannonballList.remove(GamePanel.EnemycannonballList.size() - 1);
							ship.health -= 30;
							System.out.println("player Ship health: " + ship.health);
							damageDelt2 = true;
						}
					}
				}
			}
		}
	}

	private void processWeakBanditIsAlive(WeakBandit wb) {
		if (wb.collisionBox.intersects(p.collisionBox)) {
			if (!GamePanel.swordDown) {
				if (isDefending2 == false) {
					p.health -= 3;
					System.out.println("player health: " + p.health);
					isDefending2 = true;
				}
			}
		}
		if (p.collisionBox.intersects(wb.collisionBox)) {
			if (GamePanel.swordDown) {
				if (isDefending2 == true) {
					wb.setHealth(wb.getHealth() - 30);
					isDefending2 = false;
					System.out.println("weak bandit1 health: " + b1.health);
					System.out.println("weak bandit2 health: " + b2.health);
				}
			}
		}
	}

	void purchaseShip() {
		if (coins >= 100) {
			if (!ship.isBought) {
				coins -= 100;
				ship.isBought = true;
			}
		}
	}

	void LeaveInShip() {
		if (ship.isBought) {
			GamePanel.playerisSailing = true;
			p.setX(ship.getX() + 50);
			p.setY(ship.getY() + 20);
			sword.setX(ship.getX() + 60);
			sword.setY(ship.getY() + 25);
		}
	}

	void update() {
		// if (GamePanel.cannonballList.size() != 0) {
		for (int i = 0; i < GamePanel.cannonballList.size(); i++) {

			GamePanel.cannonballList.get(i).update();
		}
		// }
		// if (GamePanel.EnemycannonballList.size() != 0) {
		for (int i = 0; i < GamePanel.EnemycannonballList.size(); i++) {

			GamePanel.EnemycannonballList.get(i).update();
			// }
		}
		p.update();
		ship.update();
		eShip.update();
		sword.update();
		b.update();
		b1.update();
		b2.update();
		pot.update();
		checkCollision();
		createEnemyCannonBalls();
	}

	void createEnemyCannonBalls() {
		if (GamePanel.mapStates[GamePanel.mapRow][GamePanel.mapColumn] == GamePanel.OCEAN_STATE) {
			if (eShip.getX() - ship.getX() <= 100) {
				if (playerHasFired == true) {
					enemyHasFired = false;
				}
				if (!enemyHasFired) {
					GamePanel.EnemycannonballList.add(new EnemyCannonBall(eShip.x, eShip.y, 10, 10, 10));
					enemyHasFired = true;
					playerHasFired = false;
				}
			}
		}
	}
}