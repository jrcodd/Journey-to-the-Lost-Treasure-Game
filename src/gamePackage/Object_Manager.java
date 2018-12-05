package gamePackage;

import java.util.ArrayList;

public class Object_Manager {
	static ArrayList<Game_Object> inv;
	Shack s;
	boolean enemyHasFired = false;
	boolean playerHasFired = false;
	BayShop bayShop;
	PlayerShip ship;
	X x;
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
	static ShipRepairKit kit;
	boolean bStart;
	int coins;
	boolean coinsAdded;

	private boolean coinsAdded1;
	private boolean coinsAdded2;
	boolean isDefending2;
	boolean isDefending1;
	boolean b1Start;
	boolean b2Start;
	boolean damageDelt = false;

	Object_Manager(Player p, TreasureMap m, ShipRepairKit kit, SpeedyBoots caveBoots, Shack s, BayShop bayShop,
			PlayerShip ship, OldMan man, Sword sword, StrongBandit b, HealthPotion pot, WeakBandit b1, WeakBandit b2,
			X x) {
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
		this.x = x;
		Object_Manager.kit = kit;

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
					kit.isFound = true;
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
			for (int i = 0; i < GamePanel.enemyShipList.size(); i++) {

				GamePanel.enemyShipList.get(i).damageDelt = false;
			}
			if (!GamePanel.enemyShipList.isEmpty()) {
				for (int i = 0; i < GamePanel.enemyShipList.size(); i++) {
					System.out.println(GamePanel.enemyShipList.get(i).health);
					if (GamePanel.enemyShipList.get(i).health <= 0) {
						GamePanel.enemyShipList.get(i).isAlive = false;

						if (!GamePanel.enemyShipList.get(i).addedRewards) {
							coins += 50;
							ship.level += 1;
							ship.maxHealth = (ship.maxHealth + 10 * ship.level);
							ship.health = ship.maxHealth;
							GamePanel.enemyShipList.get(i).addedRewards = true;
							GamePanel.enemyShipList.remove(0);
						}
					}

					if (GamePanel.cannonballList.size() > 0) {
						for (int o = 0; o < GamePanel.cannonballList.size(); o++) {
							if (GamePanel.cannonballList.get(i).collisionBox
									.intersects(GamePanel.enemyShipList.get(i).collisionBox)) {

								if (damageDelt == false) {
									System.out.println("hit");
									GamePanel.cannonballList.remove(GamePanel.cannonballList.size() - 1);
									GamePanel.enemyShipList.get(i).health -= 30;
									System.out.println("enemy Ship health: " + GamePanel.enemyShipList.get(i).health);
									damageDelt = true;
								}
							}
						}
					}
				}
				for (int j = 0; j < GamePanel.enemyShipList.size(); j++) {

					if (GamePanel.EnemycannonballList.size() > 0) {
						if (GamePanel.enemyShipList.get(j).isAlive) {
							for (int k = 0; k < GamePanel.EnemycannonballList.size(); k++) {
								if (GamePanel.EnemycannonballList.get(k).collisionBox.intersects(ship.collisionBox)) {
									if (GamePanel.enemyShipList.get(j).damageDelt == false) {
										System.out.println("hit");
										GamePanel.EnemycannonballList.remove(GamePanel.EnemycannonballList.size() - 1);
										ship.health -= 30;
										System.out.println("player Ship health: " + ship.health);
										GamePanel.enemyShipList.get(j).damageDelt = true;
									}
								}
							}
						}
					}
				}
			}
		}
		for (int l = 0; l < GamePanel.EnemycannonballList.size(); l++) {

			if ((GamePanel.EnemycannonballList.get(l).collisionBox.intersects(ship.collisionBox))) {
				GamePanel.EnemycannonballList.remove(GamePanel.EnemycannonballList.size() - 1);
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
		for (int j = 0; j < GamePanel.enemyShipList.size(); j++) {

			GamePanel.enemyShipList.get(j).update();
		}

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
			for (int j = 0; j < GamePanel.enemyShipList.size(); j++) {

				if (GamePanel.enemyShipList.get(j).getX() - ship.getX() <= 100) {
					if (playerHasFired == true) {
						enemyHasFired = false;
					}
					if (!enemyHasFired) {
						GamePanel.EnemycannonballList.add(new EnemyCannonBall(GamePanel.enemyShipList.get(j).x,
								GamePanel.enemyShipList.get(j).y, 10, 10, 10));
						enemyHasFired = true;
						playerHasFired = false;
					}
				}
			}
		}
		if (GamePanel.mapStates[GamePanel.mapRow][GamePanel.mapColumn] == GamePanel.ISLAND_STATE) {
			if (p.collisionBox.intersects(x.collisionBox)) {
				System.out.println("TREASURE IS FOUND");
			}
		}
	}
	

	void processDeath() {
//		p.health = 50;
//		ship.maxHealth = 400;
//		ship.health = ship.maxHealth;
//		GamePanel.playerisSailing = false;
//		m.isFound = false;
//		pot.isFound = false;
//		kit.isFound = false;
//		caveBoots.isFound = false;
//		pot.isDrank = false;
//		p.setX(100);
//		p.setY(500);
//		ship.setX(50);
//		ship.setY(50);
//		b.isDead = false;
//		man.hasTalked = false;
//		b.isDead = false;
//		b1.isDead = false;
//		b2.isDead = false;
//		b.setHealth(300);
//		b1.setHealth(100);
//		b2.setHealth(100);
	}
	
	
	
	public interface DeathListener{
	//	public static void death();
	}
}