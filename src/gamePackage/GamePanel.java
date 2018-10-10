package gamePackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static BufferedImage MenuImg;
	boolean doneAttacking;
	final static int fps = 60;
	Timer t;
	OldMan man = new OldMan(600, 75, 20, 60, 200);
	Shack s = new Shack(530, 20, 300, 300, 2000, false);
	SpeedyBoots caveBoots = new SpeedyBoots(250, 600, 10, 20, 50, false);
	Player p = new Player(100, 500, 20, 60, 100, 5);
	TreasureMap m = new TreasureMap(400, 100, 10, 10, 10, false);
	Sword sword = new Sword(115, 500, 10, 40, 100, false);
	StrongBandit b = new StrongBandit(400, 100, 40, 80, 300, 1);
	WeakBandit b1 = new WeakBandit(300, 100, 20, 60, 100, 2);
	WeakBandit b2 = new WeakBandit(500, 100, 20, 60, 100, 2);
	HealthPotion pot = new HealthPotion(500, 468, 10, 10, 30, false);
	static boolean up = false;
	static boolean down = false;
	static boolean right = false;
	static boolean left = false;
	static boolean swordUp;
	static boolean swordDown;
	Font inventoryFont;
	Font menuFont;
	Font instructionsFont;
	Font healthFont;

	final static int INVENTORY_SLOT1 = 200;
	final static int INVENTORY_SLOT2 = 350;
	final static int INVENTORY_SLOT3 = 500;
	final static int INVENTORY_SLOT4 = 650;
	final static int MENU_STATE = 0;
	final static int FOREST_STATE = 1;
	final static int LAGOON_STATE = 2;
	final static int CAVE_STATE = 3;
	final static int SHACK_STATE = 4;
	final static int IN_SHACK_STATE = 5;
	final static int PATH1_STATE = 6;
	final static int PATH2_STATE = 7;
	final static int BAY_STATE = 8;
	final static int OCEAN_STATE = 9;
	final static int ISLAND_STATE = 10;
	// ocean2 and bay2 are after treasure is found
	final static int OCEAN2_STATE = 11;
	final static int BAY2_STATE = 12;
	final static int END_STATE = 13;
	boolean mapOpen;
	boolean updatedSpeed;
	static int currentState = MENU_STATE;
	Object_Manager o = new Object_Manager(p, m, caveBoots, s, man, sword, b, pot, b1, b2);

	GamePanel() {
		inventoryFont = new Font("Arial", Font.PLAIN, 25);
		menuFont = new Font("Arial", Font.ITALIC, 50);
		instructionsFont = new Font("Arial", Font.PLAIN, 35);
		healthFont = new Font("Arial", Font.ITALIC, 20);
		t = new Timer(1000 / fps, this);
		t.start();
		try {

			MenuImg = ImageIO.read(this.getClass().getResourceAsStream("JourneyMenuImg.png"));

		} catch (IOException e) {

			e.printStackTrace();

		}
	}

	void drawMenuState(Graphics g) {
		g.drawImage(GamePanel.MenuImg, 0, 0, JourneyToTheLostTreasure.WIDTH, JourneyToTheLostTreasure.HEIGHT, null);
		g.setColor(Color.BLACK);
		g.setFont(menuFont);
		g.drawString("Journey To The Lost Treasure", JourneyToTheLostTreasure.WIDTH / 7,
				JourneyToTheLostTreasure.HEIGHT / 4);
		g.setFont(instructionsFont);
		g.drawString("Press ENTER to start", JourneyToTheLostTreasure.WIDTH / 3, JourneyToTheLostTreasure.HEIGHT / 2);
	}

	void updateForestState() {
		if (p.collisionBox.y > JourneyToTheLostTreasure.HEIGHT - 60) {
			down = false;

		} else if (p.collisionBox.y < 0) {
			up = false;

		}
		if (p.collisionBox.x < 0) {
			p.setX(800);
			sword.setX(815);
			currentState = CAVE_STATE;
		} else if (p.collisionBox.x > JourneyToTheLostTreasure.WIDTH - 170) {
			p.setX(10);
			sword.setX(25);
			currentState = LAGOON_STATE;
		}
		o.update();
		o.checkCollision();
		repaint();

	}

	void updateLagoonState() {
		if (p.collisionBox.y > JourneyToTheLostTreasure.HEIGHT - 60) {
			down = false;

		}
		if (p.collisionBox.y < 0) {
			up = false;

		}
		if (p.collisionBox.x < 0) {
			p.setX(800);
			sword.setX(815);

			currentState = FOREST_STATE;
		}
		if (p.collisionBox.x > JourneyToTheLostTreasure.WIDTH - 150) {
			p.setX(10);
			sword.setX(25);
			currentState = SHACK_STATE;
		}
		o.update();
		o.checkCollision();
		repaint();

	}

	void updateCaveState() {
		if (p.collisionBox.y > JourneyToTheLostTreasure.HEIGHT - 60) {
			down = false;

		}
		if (p.collisionBox.y < 0) {
			up = false;

		}
		if (p.collisionBox.x > JourneyToTheLostTreasure.WIDTH - 150) {
			p.setX(10);
			sword.setX(25);
			currentState = FOREST_STATE;
		}
		if (p.collisionBox.x < 0) {
			currentState = SHACK_STATE;
			p.setX(800);
			sword.setX(815);
		}
		o.update();
		o.checkCollision();
		repaint();

	}

	void updateShackState() {
		if (p.collisionBox.y > JourneyToTheLostTreasure.HEIGHT - 60) {
			down = false;

		}

		if (p.collisionBox.x < 0) {
			p.setX(800);
			sword.setX(815);
			currentState = LAGOON_STATE;
		}
		if (p.collisionBox.x > JourneyToTheLostTreasure.WIDTH - 150) {
			p.setX(10);
			sword.setX(25);
			currentState = CAVE_STATE;
		}
		if (p.collisionBox.y < 0) {
			p.setY(700);
			sword.setY(700);
			currentState = PATH1_STATE;
		}
		if (s.inside) {
			currentState = IN_SHACK_STATE;
		}
		o.update();
		o.checkCollision();
		repaint();
	}

	void updateInShackState() {
		if (p.collisionBox.y < 0) {
			up = false;

		}
		if (p.collisionBox.x < 0) {
			left = false;
		}
		if (p.collisionBox.x > JourneyToTheLostTreasure.WIDTH - 160) {
			right = false;
		}

		if (p.collisionBox.y > JourneyToTheLostTreasure.HEIGHT) {
			s.inside = false;

			currentState = SHACK_STATE;
			p.setY(350);
			p.setX(560);
			sword.setX(575);
			sword.setY(350);
		}
		o.update();
		o.checkCollision();
		repaint();
	}

	void updatePath1State() {
		if (p.collisionBox.y > JourneyToTheLostTreasure.HEIGHT) {

			currentState = SHACK_STATE;
			p.setY(50);
			sword.setY(65);

		} else if (p.collisionBox.y < 0) {
			if (b.isDead == true) {
				currentState = PATH2_STATE;
				p.setY(700);
				sword.setY(700);
			} else if (b.isDead == false) {
				up = false;
			}
		}
		o.update();
		b.update();
		o.checkCollision();
		repaint();
	}

	void updatePath2State() {
		if (p.collisionBox.y > JourneyToTheLostTreasure.HEIGHT) {

			currentState = PATH1_STATE;
			p.setY(50);
			sword.setY(65);
		}
		if (p.collisionBox.x < 0) {
			left = false;
		}
		if (p.collisionBox.x > JourneyToTheLostTreasure.WIDTH - 150) {
			right = false;
		}
		if (p.collisionBox.y < 0) {
			p.setY(700);
			sword.setY(700);
			currentState = BAY_STATE;
		}

		o.update();
		
		

		repaint();
	}

	void drawForestState(Graphics g) {

		g.setColor(Color.GREEN);
		g.fillRect(0, 0, JourneyToTheLostTreasure.WIDTH, JourneyToTheLostTreasure.HEIGHT);
		g.setColor(Color.GRAY);
		p.draw(g);
		g.fillRect(850, 0, 150, 700);
		g.setFont(inventoryFont);
		g.setColor(Color.WHITE);
		g.drawString("1", 860, 150);
		g.drawString("2", 860, 300);
		g.drawString("3", 860, 450);
		g.drawString("4", 860, 600);
		g.setColor(Color.YELLOW);
		g.drawString(Integer.toString(o.coins), 870, 50);
		g.setFont(menuFont);
		g.setColor(Color.white);
		g.drawString("Forest", JourneyToTheLostTreasure.WIDTH / 3, 50);
		g.setFont(healthFont);

		if (p.health < 30) {
			g.setColor(Color.red);
		} else if (p.health < 50) {
			g.setColor(Color.YELLOW);
		} else if (p.health > 50) {
			g.setColor(Color.darkGray);
		}
		g.drawString(Integer.toString(p.health), p.getX() - 10, p.getY() + 80);
		if (sword.isFound) {
			sword.draw(g);
		}
		if (m.isFound == false) {
			m.draw(g);
		}

	}

	void drawLagoonState(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(0, 0, JourneyToTheLostTreasure.WIDTH, JourneyToTheLostTreasure.HEIGHT);
		g.setColor(Color.GRAY);
		p.draw(g);

		g.fillRect(850, 0, 150, 700);
		g.setFont(inventoryFont);
		g.setColor(Color.WHITE);
		g.drawString("1", 860, 150);
		g.drawString("2", 860, 300);
		g.drawString("3", 860, 450);
		g.drawString("4", 860, 600);
		g.setColor(Color.YELLOW);
		g.drawString(Integer.toString(o.coins), 870, 50);
		g.setFont(menuFont);
		g.setColor(Color.white);
		g.drawString("Lagoon", JourneyToTheLostTreasure.WIDTH / 3, 50);
		g.setFont(healthFont);
		if (pot.isFound == false) {
			pot.draw(g);
		}
		if (p.health < 30) {
			g.setColor(Color.red);
		} else if (p.health < 50) {
			g.setColor(Color.YELLOW);
		} else if (p.health > 50) {
			g.setColor(Color.darkGray);
		}
		g.drawString(Integer.toString(p.health), p.getX() - 10, p.getY() + 80);
		if (sword.isFound) {
			sword.draw(g);
		}

	}

	void drawCaveState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, JourneyToTheLostTreasure.WIDTH, JourneyToTheLostTreasure.HEIGHT);

		g.setColor(Color.GRAY);
		p.draw(g);
		g.fillRect(850, 0, 150, 700);
		g.setFont(inventoryFont);
		g.setColor(Color.WHITE);
		g.drawString("1", 860, 150);
		g.drawString("2", 860, 300);
		g.drawString("3", 860, 450);
		g.drawString("4", 860, 600);
		g.setColor(Color.YELLOW);
		g.drawString(Integer.toString(o.coins), 870, 50);
		g.setFont(menuFont);
		g.setColor(Color.white);
		g.drawString("Cave", JourneyToTheLostTreasure.WIDTH / 3, 50);
		g.setFont(healthFont);

		if (p.health < 30) {
			g.setColor(Color.red);
		} else if (p.health < 50) {
			g.setColor(Color.YELLOW);
		} else if (p.health > 50) {
			g.setColor(Color.darkGray);
		}
		g.drawString(Integer.toString(p.health), p.getX() - 10, p.getY() + 80);
		if (sword.isFound) {
			sword.draw(g);
		}
		if (caveBoots.isFound == false) {
			caveBoots.draw(g);
		}

		repaint();
	}

	void drawShackState(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, JourneyToTheLostTreasure.WIDTH, JourneyToTheLostTreasure.HEIGHT);
		s.draw(g);
		g.setColor(Color.GRAY);
		p.draw(g);

		g.fillRect(850, 0, 150, 700);
		g.setFont(inventoryFont);
		g.setColor(Color.WHITE);
		g.drawString("1", 860, 150);
		g.drawString("2", 860, 300);
		g.drawString("3", 860, 450);
		g.drawString("4", 860, 600);
		g.setColor(Color.YELLOW);
		g.drawString(Integer.toString(o.coins), 870, 50);
		g.setFont(menuFont);
		g.setColor(Color.white);
		g.drawString("Forest Edge", JourneyToTheLostTreasure.WIDTH / 4, 50);
		g.setFont(healthFont);

		if (p.health < 30) {
			g.setColor(Color.red);
		} else if (p.health < 50) {
			g.setColor(Color.YELLOW);
		} else if (p.health > 50) {
			g.setColor(Color.darkGray);
		}
		g.drawString(Integer.toString(p.health), p.getX() - 10, p.getY() + 80);
		if (sword.isFound) {
			sword.draw(g);
		}

	}

	void drawMapState(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, JourneyToTheLostTreasure.WIDTH, JourneyToTheLostTreasure.HEIGHT);

	}

	void drawInShackState(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(0, 0, JourneyToTheLostTreasure.WIDTH, JourneyToTheLostTreasure.HEIGHT);
		g.setColor(Color.GRAY);
		p.draw(g);
		man.draw(g);

		g.fillRect(850, 0, 150, 700);
		g.setFont(inventoryFont);
		g.setColor(Color.WHITE);
		g.drawString("1", 860, 150);
		g.drawString("2", 860, 300);
		g.drawString("3", 860, 450);
		g.drawString("4", 860, 600);
		g.setColor(Color.YELLOW);
		g.drawString(Integer.toString(o.coins), 870, 50);
		g.setFont(menuFont);
		g.setColor(Color.white);
		g.drawString("Shack", JourneyToTheLostTreasure.WIDTH / 3, 50);
		g.setFont(healthFont);

		if (p.health < 30) {
			g.setColor(Color.red);
		} else if (p.health < 50) {
			g.setColor(Color.YELLOW);
		} else if (p.health > 50) {
			g.setColor(Color.darkGray);
		}
		g.drawString(Integer.toString(p.health), p.getX() - 10, p.getY() + 80);
		if (sword.isFound) {

			sword.draw(g);
		}

		repaint();
	}

	void drawPath2State(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(0, 0, JourneyToTheLostTreasure.WIDTH, JourneyToTheLostTreasure.HEIGHT);
		g.setColor(Color.GRAY);
		p.draw(g);
		

		g.setColor(Color.GRAY);
		g.fillRect(850, 0, 150, 700);
		g.setFont(inventoryFont);
		g.setColor(Color.WHITE);
		g.drawString("1", 860, 150);
		g.drawString("2", 860, 300);
		g.drawString("3", 860, 450);
		g.drawString("4", 860, 600);
		g.setColor(Color.YELLOW);
		g.drawString(Integer.toString(o.coins), 870, 50);
		g.setFont(menuFont);
		g.setColor(Color.white);
		g.drawString("Path", JourneyToTheLostTreasure.WIDTH / 3, 50);
		g.setFont(healthFont);

		if (p.health < 30) {
			g.setColor(Color.red);
		} else if (p.health < 50) {
			g.setColor(Color.YELLOW);
		} else if (p.health > 50) {
			g.setColor(Color.darkGray);
		}
		g.drawString(Integer.toString(p.health), p.getX() - 10, p.getY() + 80);
		if (sword.isFound) {
			sword.draw(g);
		}

	}

	void drawPath1State(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, JourneyToTheLostTreasure.WIDTH, JourneyToTheLostTreasure.HEIGHT);
		g.setColor(Color.GRAY);
		p.draw(g);

		g.setColor(Color.GRAY);
		g.fillRect(850, 0, 150, 700);
		g.setFont(inventoryFont);
		g.setColor(Color.WHITE);
		g.drawString("1", 860, 150);
		g.drawString("2", 860, 300);
		g.drawString("3", 860, 450);
		g.drawString("4", 860, 600);
		g.setColor(Color.YELLOW);
		g.drawString(Integer.toString(o.coins), 870, 50);
		g.setFont(menuFont);
		g.setColor(Color.white);
		g.drawString("Path", JourneyToTheLostTreasure.WIDTH / 3, 50);
		g.setFont(healthFont);

		if (p.health < 30) {
			g.setColor(Color.red);
		} else if (p.health < 50) {
			g.setColor(Color.YELLOW);
		} else if (p.health > 50) {
			g.setColor(Color.darkGray);
		}
		g.drawString(Integer.toString(p.health), p.getX() - 10, p.getY() + 80);
		if (sword.isFound) {
			sword.draw(g);
		}

	}

	public void paintComponent(Graphics g) {

		if (currentState == MENU_STATE) {
			drawMenuState(g);
		} else if (currentState == FOREST_STATE) {

			updateForestState();
			drawForestState(g);

		} else if (currentState == LAGOON_STATE) {
			updateLagoonState();
			drawLagoonState(g);
		} else if (currentState == CAVE_STATE) {

			updateCaveState();
			drawCaveState(g);
		} else if (currentState == SHACK_STATE) {

			updateShackState();
			drawShackState(g);
		}

		else if (currentState == PATH1_STATE) {
			updatePath1State();
			drawPath1State(g);
			if (b.getHealth() > 0) {
				b.draw(g);
			}

		} else if (currentState == PATH2_STATE) {
			drawPath2State(g);
			updatePath2State();
			if (b1.getHealth() > 0) {
				b1.draw(g);
				
			}
			if (b2.getHealth() > 0) {
				b2.draw(g);
				
			}
		}
		if (m.isFound) {
			m.drawInInv(g);
		}
		if (pot.isFound) {
			pot.drawInInv(g, pot.isDrank);
		}
		if (caveBoots.isFound) {
			caveBoots.drawInInv(g);
		}
		if (s.inside) {

			updateInShackState();
			drawInShackState(g);
		}
		if (mapOpen) {
			drawMapState(g);
		}
		if (swordDown) {
			sword.attack1(g);
		} else if (swordUp) {
			sword.attack2(g);
			doneAttacking = false;
		}
		if (b.isDead == false) {
			if (b.left) {
				b.setX(b.getX() - 1);
			} else if (b.right) {
				b.setX(b.getX() + 1);
			}
		}
		if (b1.isDead == false) {
			if (b1.left) {
				b1.setX(b.getX() - 1);
			} else if (b1.right) {
				b1.setX(b1.getX() + 1);
			}
		}
		if (b2.isDead == false) {
			if (b2.left) {
				b2.setX(b2.getX() - 1);
			} else if (b2.right) {
				b2.setX(b2.getX() + 1);
			}
		}

		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("pressed");
		if (sword.isFound) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				if (doneAttacking == false) {
					swordDown = true;
					swordUp = false;
					doneAttacking = true;
				}
			}
		}

		if (mapOpen) {
			if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				mapOpen = false;
			}
		}
		if (currentState == MENU_STATE) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				currentState = FOREST_STATE;

			}

		}  if (currentState > MENU_STATE) {
			if (e.getKeyCode() == KeyEvent.VK_W) {
				up = true;

			} else if (e.getKeyCode() == KeyEvent.VK_S) {
				down = true;

			} else if (e.getKeyCode() == KeyEvent.VK_A) {
				left = true;
				System.out.println("a pressed");

			} else if (e.getKeyCode() == KeyEvent.VK_D) {
				right = true;

			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				up = true;

			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				down = true;

			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				left = true;
				System.out.println("a pressed");

			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				right = true;

			}
			if (caveBoots.isFound()) {
				if (Object_Manager.inv.contains(caveBoots)) {
					if (caveBoots.positionInInv == INVENTORY_SLOT1) {
						if (e.getKeyCode() == KeyEvent.VK_1) {
							if (mapOpen == false) {

								if (updatedSpeed == false) {
									p.speed += 3;
									updatedSpeed = true;
								}
							}
						}
					} else if (caveBoots.positionInInv == INVENTORY_SLOT2) {
						if (e.getKeyCode() == KeyEvent.VK_2) {
							if (mapOpen == false) {

								if (updatedSpeed == false) {
									p.speed += 3;
									updatedSpeed = true;
								}
							}
						}
					} else if (caveBoots.positionInInv == INVENTORY_SLOT3) {
						if (e.getKeyCode() == KeyEvent.VK_3) {
							if (mapOpen == false) {

								if (updatedSpeed == false) {
									p.speed += 3;
									updatedSpeed = true;
								}
							}
						}
					}
					if (caveBoots.positionInInv == INVENTORY_SLOT4) {
						if (e.getKeyCode() == KeyEvent.VK_4) {
							if (mapOpen == false) {

								if (updatedSpeed == false) {
									p.speed += 3;
									updatedSpeed = true;
								}
							}
						}
					}
				}
			}
			if (m.isFound()) {
				if (Object_Manager.inv.contains(m)) {
					if (m.positionInInv == INVENTORY_SLOT1) {
						if (e.getKeyCode() == KeyEvent.VK_1) {
							if (mapOpen == false) {

								mapOpen = true;
							}
						} else if (m.positionInInv == INVENTORY_SLOT2) {
							if (e.getKeyCode() == KeyEvent.VK_2) {
								if (mapOpen == false) {

									mapOpen = true;
								}
							}
						} else if (m.positionInInv == INVENTORY_SLOT3) {
							if (e.getKeyCode() == KeyEvent.VK_3) {
								if (mapOpen == false) {

									mapOpen = true;
								}
							}
						} else if (m.positionInInv == INVENTORY_SLOT4) {
							if (e.getKeyCode() == KeyEvent.VK_4) {
								if (mapOpen == false) {

									mapOpen = true;
								}
							}
						}
						if (pot.positionInInv == INVENTORY_SLOT1) {
							if (e.getKeyCode() == KeyEvent.VK_1) {
								if (pot.isDrank == false) {
									if (p.health < 100) {
										p.health = 100;
										pot.isDrank = true;
									}
								}
							}
						} else if (pot.positionInInv == INVENTORY_SLOT2) {
							if (e.getKeyCode() == KeyEvent.VK_2) {
								if (pot.isDrank == false) {
									if (p.health < 100) {
										p.health = 100;
										pot.isDrank = true;
									}
								}
							}
						} else if (pot.positionInInv == INVENTORY_SLOT3) {
							if (e.getKeyCode() == KeyEvent.VK_3) {
								if (pot.isDrank == false) {
									if (p.health < 100) {
										p.health = 100;
										pot.isDrank = true;
									}
								}
							}
						} else if (pot.positionInInv == INVENTORY_SLOT4) {
							if (e.getKeyCode() == KeyEvent.VK_4) {
								if (pot.isDrank == false) {
									if (p.health < 100) {
										p.health = 100;
										pot.isDrank = true;
									}
								}
							}
						}
					}
				}
			}
		}
		if (currentState == LAGOON_STATE) {
			if (pot.isDrank == true) {
				if (pot.positionInInv == INVENTORY_SLOT1) {
					if (e.getKeyCode() == KeyEvent.VK_1) {
						if (pot.isDrank == true) {

							pot.isDrank = false;

						}
					}
				}
				if (pot.positionInInv == INVENTORY_SLOT2) {
					if (e.getKeyCode() == KeyEvent.VK_2) {
						if (pot.isDrank == true) {

							pot.isDrank = false;

						}
					}
				}
				if (pot.positionInInv == INVENTORY_SLOT3) {
					if (e.getKeyCode() == KeyEvent.VK_3) {
						if (pot.isDrank == true) {

							pot.isDrank = false;

						}
					}
				}
				if (pot.positionInInv == INVENTORY_SLOT4) {
					if (e.getKeyCode() == KeyEvent.VK_4) {
						if (pot.isDrank == true) {

							pot.isDrank = false;

						}
					}
				}
			}
		}
		repaint();

	}

	@Override
	public void keyReleased(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_W) {
			up = false;
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			down = false;
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			right = false;
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			left = false;
			System.out.println("a released");
		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			up = false;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = false;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = false;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = false;
			System.out.println("a released");
		}else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (sword.isFound) {
				swordDown = false;
				swordUp = true;
			}
		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (up) {

			p.setY(p.getY() - p.getSpeed());
			sword.setY(sword.getY() - p.getSpeed());
		}
		if (right) {
			p.setX(p.getX() + p.getSpeed());
			sword.setX(sword.getX() + p.getSpeed());
		}
		if (left) {
			System.out.println("should be going left");
			p.setX(p.getX() - p.getSpeed());
			sword.setX(sword.getX() - p.getSpeed());
		}
		if (down) {
			p.setY(p.getY() + p.getSpeed());
			sword.setY(sword.getY() + p.getSpeed());

		}

		repaint();
	}

	void setState(int newState) {
		currentState = newState;
	}

	static int getState() {
		return currentState;
	}

}
