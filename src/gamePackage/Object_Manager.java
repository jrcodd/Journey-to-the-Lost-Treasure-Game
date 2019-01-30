package gamePackage;

import java.awt.Graphics;
import java.util.ArrayList;

public class Object_Manager {

	static ArrayList<Game_Object> inv;
	Shack s;
	ArrayList<EnemyShip> enemyShipList = new ArrayList<EnemyShip>();
	ArrayList<PlayerCannonBall> cannonballList = new ArrayList<PlayerCannonBall>();
	ArrayList<EnemyCannonBall> EnemycannonballList = new ArrayList<EnemyCannonBall>();
	boolean enemyHasFired = false;
	boolean playerHasFired = false;
	boolean treasureIsFound = false;
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
	boolean strongBanditIsAttacking;
	boolean takeDamage = false;
	static HealthPotion pot;
	static ShipRepairKit kit;
	boolean bStart;
	int coins;
	boolean coinsAdded;
	static boolean playerisSailing;
	private boolean coinsAdded1;
	private boolean coinsAdded2;
	boolean isDefending2;
	boolean isDefending1;
	boolean b1Start;
	boolean b2Start;
	boolean damageDelt = false;
	boolean potionmessage = true;
	boolean mapmessage = true;
	boolean bootsmessage = true;
	static String currentMessage1 = "Welcome To Journey To The Lost Treasure!!";
	static String currentMessage2 = "To move use WASD or the arrow keys. Use space to attack";
	static String currentMessage3 = "and the corresponding number key to use items.";

	Object_Manager(Player p, TreasureMap m, ShipRepairKit kit, SpeedyBoots caveBoots, Shack s, PlayerShip ship,
			OldMan man, Sword sword, StrongBandit b, HealthPotion pot, WeakBandit b1, WeakBandit b2, X x) {
		this.s = s;

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

				if (pot.hasTalked == false) {
					GamePanel.up = false;
					GamePanel.down = false;
					GamePanel.right = false;
					GamePanel.left = false;
					pot.hasTalked = true;
					pot.talk();
					pot.isFound = true;

				}

				// pot.isFound = true;
				// if (potionmessage) {
				// JOptionPane.showMessageDialog(null,
				// "This potion heals the player back to full health and can be refilled at the
				// lagoon.");
				// GamePanel.stop();
				// potionmessage = false;
				// }
			}
		}
		if (GamePanel.mapStates[GamePanel.mapRow][GamePanel.mapColumn] == GamePanel.PATH1_STATE) {

			if (b.isDead == false) {

				if (p.collisionBox.intersects(b.collisionBox)) {
					if (b.getReady()) {
						strongBanditIsAttacking = true;
						p.health -= 15;
						b.setReady(false);
						strongBanditIsAttacking = false;
					}
				}

				// if (!GamePanel.swordDown) {
				// if (isDefending == false) {
				// if (b.getReady()) {
				// p.health -= 15;
				// }
				// System.out.println("player health: " + p.health);
				// isDefending = true;
				// }
				// }
				// }
				if (p.collisionBox.intersects(b.collisionBox)) {
					if (GamePanel.swordDown) {

						b.setHealth(b.getHealth() - 30);

					}

					System.out.println("bandit health: " + b.health);
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
			if (p.y >= GamePanel.BOTTOM) {
				s.inside = false;
				p.y = s.y + s.width + 3;
				p.x = s.x + 10;
			} else if (p.y <= 2) {
				GamePanel.up = false;
			} else if (p.x <= 2) {
				GamePanel.left = false;
			}
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
				if (m.hasTalked == false) {
					GamePanel.up = false;
					GamePanel.down = false;
					GamePanel.right = false;
					GamePanel.left = false;
					m.hasTalked = true;
					m.talk();
					m.isFound = true;

				}

				// if (mapmessage) {
				// JOptionPane.showMessageDialog(null,
				// "This map can be used to know where the player is. (You can find your current
				// location at the top of the screen)");
				//
				// mapmessage = false;
				// GamePanel.stop();
				// }

			}
		}
		if (GamePanel.mapStates[GamePanel.mapRow][GamePanel.mapColumn] == GamePanel.CAVE_STATE) {
			if (caveBoots.collisionBox.intersects(p.collisionBox)) {
				if (caveBoots.hasTalked == false) {
					GamePanel.up = false;
					GamePanel.down = false;
					GamePanel.right = false;
					GamePanel.left = false;
					caveBoots.hasTalked = true;
					caveBoots.talk();
					caveBoots.isFound = true;

				}

				//
				// caveBoots.isFound = true;
				// if (bootsmessage) {
				// JOptionPane.showMessageDialog(null,
				// "These boots allow the player to travel at a much faster speed.");
				// bootsmessage = false;
				// GamePanel.stop();
				// }
			}
		}
		if (GamePanel.mapStates[GamePanel.mapRow][GamePanel.mapColumn] == GamePanel.FOREST_EDGE) {
			if (p.collisionBox.intersects(s.collisionBox)) {

				s.inside = true;
			}
		}
		if (GamePanel.mapStates[GamePanel.mapRow][GamePanel.mapColumn] == GamePanel.OCEAN_STATE) {
			damageDelt = false;
			for (int i = 0; i < enemyShipList.size(); i++) {

				enemyShipList.get(i).damageDelt = false;
			}
			if (!enemyShipList.isEmpty()) {
				for (int i = 0; i < enemyShipList.size(); i++) {
					System.out.println(enemyShipList.get(i).health);
					if (enemyShipList.get(i).health <= 0) {
						enemyShipList.get(i).isAlive = false;

						if (!enemyShipList.get(i).addedRewards) {
							coins += 50;
							ship.level += 1;
							ship.maxHealth = (ship.maxHealth + 10 * ship.level);
							ship.health = ship.maxHealth;
							enemyShipList.get(i).addedRewards = true;
							enemyShipList.remove(0);
						}
					}

					if (cannonballList.size() > 0) {
						for (int o = 0; o < cannonballList.size(); o++) {
							if (cannonballList.get(o).collisionBox.intersects(enemyShipList.get(i).collisionBox)) {

								if (damageDelt == false) {
									System.out.println("hit");
									cannonballList.remove(cannonballList.size() - 1);
									enemyShipList.get(i).health -= 30;
									System.out.println("enemy Ship health: " + enemyShipList.get(i).health);
									damageDelt = true;
								}
							}
						}
					}
				}
			}
			for (int j = 0; j < enemyShipList.size(); j++) {

				if (EnemycannonballList.size() > 0) {
					if (enemyShipList.get(j).isAlive) {
						for (int k = 0; k < EnemycannonballList.size(); k++) {
							if (EnemycannonballList.get(k).collisionBox.intersects(ship.collisionBox)) {
								if (enemyShipList.get(j).damageDelt == false) {
									System.out.println("hit");
									EnemycannonballList.remove(EnemycannonballList.size() - 1);
									ship.health -= 30;
									System.out.println("player Ship health: " + ship.health);
									enemyShipList.get(j).damageDelt = true;
								}
							}
						}
					}
				}
			}

			for (int l = 0; l < EnemycannonballList.size(); l++) {

				if ((EnemycannonballList.get(l).collisionBox.intersects(ship.collisionBox))) {
					EnemycannonballList.remove(EnemycannonballList.size() - 1);
				}

			}
		}

	}

	private void processWeakBanditIsAlive(WeakBandit wb) {
		if (wb.collisionBox.intersects(p.collisionBox)) {
			if (!GamePanel.swordDown) {
				if (isDefending2 == false) {
					p.health -= 8;
					System.out.println("player health: " + p.health);
					isDefending2 = true;
				}
			}
		}
		if (p.collisionBox.intersects(wb.collisionBox)) {
			if (GamePanel.swordDown) {
				if (isDefending2 == true) {
					wb.setHealth(wb.getHealth() - 20);
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
		} else if (coins < 100 && playerisSailing == false) {
			if (!ship.hasTalked) {

				GamePanel.up = false;
				GamePanel.down = false;
				GamePanel.right = false;
				GamePanel.left = false;
				ship.hasTalked = true;
				ship.talk();

			}
		}
	}

	void LeaveInShip() {
		if (ship.isBought) {
			playerisSailing = true;
			p.setX(ship.getX() + 50);
			p.setY(ship.getY() + 20);
			sword.setX(ship.getX() + 60);
			sword.setY(ship.getY() + 25);
		}
	}

	void update() {
		// if (cannonballList.size() != 0) {
		for (int i = 0; i < cannonballList.size(); i++) {

			cannonballList.get(i).update();
		}
		// }
		// if (EnemycannonballList.size() != 0) {
		for (int i = 0; i < EnemycannonballList.size(); i++) {

			EnemycannonballList.get(i).update();
			// }
		}
		p.update();
		ship.update();
		for (int j = 0; j < enemyShipList.size(); j++) {

			enemyShipList.get(j).update();

		}

		sword.update();
		b.update();
		b1.update();
		b2.update();
		pot.update();
		checkCollision();
		createEnemyCannonBalls();

		purgeBullets();
	}

	void createEnemyCannonBalls() {
		if (GamePanel.mapStates[GamePanel.mapRow][GamePanel.mapColumn] == GamePanel.OCEAN_STATE) {
			for (int j = 0; j < enemyShipList.size(); j++) {

				if (enemyShipList.get(j).getX() - ship.getX() <= 100) {
					if (playerHasFired == true) {
						enemyHasFired = false;
					}
					if (!enemyHasFired) {
						EnemycannonballList
								.add(new EnemyCannonBall(enemyShipList.get(j).x, enemyShipList.get(j).y, 10, 10, 10));
						enemyHasFired = true;
						playerHasFired = false;
					}
				}
			}
		}
		if (GamePanel.mapStates[GamePanel.mapRow][GamePanel.mapColumn] == GamePanel.ISLAND_STATE) {
			if (p.collisionBox.intersects(x.collisionBox)) {
				treasureIsFound = true;

			}
		}
	}

	void moveEnemyShip() {
		for (int i = 0; i < enemyShipList.size(); i++) {

			enemyShipList.get(i)
					.setX(enemyShipList.get(i).getX() - ((enemyShipList.get(i).getX() - ship.getX()) / 100));
			enemyShipList.get(i)
					.setY(enemyShipList.get(i).getY() - ((enemyShipList.get(i).getY() - ship.getY()) / 100));
		}
	}

	void moveBullets() {
		for (EnemyCannonBall b : EnemycannonballList) {

			b.setX(b.getX() - ((b.getX() - (ship.getX() + ship.width / 2)) / 10));

			b.setY(b.getY() - ((b.getY() - (ship.getY() + ship.height / 2)) / 10));
		}
		for (PlayerCannonBall b : cannonballList) {

			b.setX(b.getX() - ((b.getX() - (enemyShipList.get(enemyShipList.size() - 1).getX()
					+ enemyShipList.get(enemyShipList.size() - 1).width / 2)) / 10));

			b.setY(b.getY() - ((b.getY() - (enemyShipList.get(enemyShipList.size() - 1).getY()
					+ enemyShipList.get(enemyShipList.size() - 1).height / 2)) / 10));

		}
	}

	void purgeBullets() {
		for (int i = 0; i < EnemycannonballList.size(); i++) {

			checkEnemyBulletBounds(EnemycannonballList.get(i).x, EnemycannonballList.get(i).y);
		}
		for (int i = 0; i < cannonballList.size(); i++) {

			checkPlayerBulletBounds(cannonballList.get(i).x, cannonballList.get(i).y);
		}
		if (EnemycannonballList.size() >= 20) {
			EnemycannonballList.remove(EnemycannonballList.size() - 1);
		}
		if (cannonballList.size() >= 20) {
			cannonballList.remove(cannonballList.size() - 1);
		}
	}

	void checkEnemyBulletBounds(int x, int y) {
		if (EnemycannonballList.size() > 0) {
			if (x < 0) {
				EnemycannonballList.remove(EnemycannonballList.size() - 1);
			}
			if (x > JourneyToTheLostTreasure.WIDTH) {
				EnemycannonballList.remove(EnemycannonballList.size() - 1);
			}
			if (y < 0) {
				EnemycannonballList.remove(EnemycannonballList.size() - 1);
			}
			if (y > JourneyToTheLostTreasure.HEIGHT) {
				EnemycannonballList.remove(EnemycannonballList.size() - 1);
			}
		}
	}

	void checkPlayerBulletBounds(int x, int y) {
		if (cannonballList.size() > 0) {
			if (x < 0) {
				cannonballList.remove(cannonballList.size() - 1);
			}
			if (x > JourneyToTheLostTreasure.WIDTH) {
				cannonballList.remove(cannonballList.size() - 1);
			}
			if (y < 0) {
				cannonballList.remove(cannonballList.size() - 1);
			}
			if (y > JourneyToTheLostTreasure.HEIGHT) {
				cannonballList.remove(cannonballList.size() - 1);
			}
		}
	}

	void drawStrongBandit(Graphics g) {

		if (!b.isDead) {
			if (p.getX() - b.getX() < 0) {
				// strong bandit is facing left
				if (strongBanditIsAttacking) {
					b.setWidth(320 / 2 + 50);
					b.drawLeft(g);
					// not attacking and facing left
				} else if (!strongBanditIsAttacking) {
					b.setWidth(320 / 2 + 50);
					b.drawLeftAttack(g);
					// strong bandit is attacking and facing left
				}
			} else if (p.getX() - b.getX() > 0) {
				// strong bandit is facing right
				if (strongBanditIsAttacking) {
					b.setWidth(320 / 2 + 50);
					b.drawRight(g);
					// strong bandit is facing left and not attacking
				} else if (!strongBanditIsAttacking) {
					b.setWidth(320 / 2 + 50);
					b.drawRightAttack(g);
					// strong bandit is attacking and facing right
				}
			}
		}
	}

	void drawWeakBandits(Graphics g) {
		if (!b1.isDead) {
			if (p.getX() - b1.getX() < 0) {
				// Weak Bandit 1 is facing left
				if (isDefending2) {
					b1.drawLeft(g);
					// Weak Bandit 1 is not attacking and facing left
				} else if (!isDefending2) {
					b1.drawLeftAttack(g);
					// Weak Bandit 1 is attacking and facing left
				}
			} else if (p.getX() - b1.getX() > 0) {
				if (isDefending2) {
					b1.drawRight(g);
					// Weak Bandit 1 is not attacking and facing right
				}
				// Weak Bandit 1 is facing right
				else if (!isDefending2) {
					b1.drawRightAttack(g);
					// Weak Bandit 1 is attacking and facing right
				}
			}
		}
		if (!b2.isDead) {
			if (p.getX() - b2.getX() < 0) {
				// Weak bandit 2 is facing left
				if (isDefending2) {
					b2.drawLeft(g);
					// weak bandit 2 is not attacking and facing left
				} else if (!isDefending2) {
					b2.drawLeftAttack(g);
					// weak bandit 2 is attacking and facing left
				}
			} else if (p.getX() - b2.getX() > 0) {
				if (isDefending2) {
					b2.drawRight(g);
					// weak bandit 2 is not attacking and facing right
				}
				// Weak bandit 2 is facing right
				else if (!isDefending2) {
					b2.drawRightAttack(g);
					// Weak bandit 2 is attacking and facing right
				}
			}
		}
		// else {
		// if (!b1.isDead) {
		// b1.drawLeft(g);
		// }
		// if (!b2.isDead) {
		// b2.drawLeft(g);
		// }
		// }

	}

	boolean getPlayerisSailing() {
		return playerisSailing;
	}

	void setPlayerisSailing(boolean newValue) {
		playerisSailing = newValue;
	}

	public interface DeathListener {
		// public static void death();
	}
}