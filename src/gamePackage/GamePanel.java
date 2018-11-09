package gamePackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

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
	public static BufferedImage level1BoatLeft;
	public static BufferedImage level1BoatLeftUp;
	public static BufferedImage level1BoatLeftDown;
	public static BufferedImage level1BoatRight;
	public static BufferedImage level1BoatRightUp;
	public static BufferedImage level1BoatRightDown;
	
	
	
	public static BufferedImage level2BoatLeft;
	public static BufferedImage level2BoatLeftUp;
	public static BufferedImage level2BoatLeftDown;
	public static BufferedImage level2BoatRight;
	public static BufferedImage level2BoatRightUp;
	public static BufferedImage level2BoatRightDown;
	
	
	
	public static BufferedImage level3BoatLeft;
	public static BufferedImage level3BoatLeftUp;
	public static BufferedImage level3BoatLeftDown;
	public static BufferedImage level3BoatRight;
	public static BufferedImage level3BoatRightUp;
	public static BufferedImage level3BoatRightDown;
	
	
	public static BufferedImage cannonBallProjectile;
	public static BufferedImage enemyShip;
	boolean doneAttacking;
	final static int fps = 60;
	Timer t;
	
	OldMan man = new OldMan(600, 75, 20, 60, 200);
	Shack s = new Shack(530, 20, 300, 300, 2000, false);
	BayShop bayShop = new BayShop(500, 10, 340, 340, 100, false);
	PlayerShip ship = new PlayerShip(50, 50, 433/3, 381/3, 500, 2);
	EnemyShip eShip = new EnemyShip(50, 50,433/3,381/3, 500, 1);
	SpeedyBoots caveBoots = new SpeedyBoots(230, 600, 10, 20, 50, false);
	Player p = new Player(100, 500, 20, 60, 100, 8);
	TreasureMap m = new TreasureMap(400, 100, 10, 10, 10, false);
	Sword sword = new Sword(115, 500, 10, 40, 100, false);
	StrongBandit b = new StrongBandit(400, 100, 40, 80, 300, 1);
	WeakBandit b1 = new WeakBandit(300, 100, 20, 60, 100, 2);
	WeakBandit b2 = new WeakBandit(500, 100, 20, 60, 100, 2);
	HealthPotion pot = new HealthPotion(500, 468, 10, 10, 30, false);
	Object_Manager o = new Object_Manager(p, m, caveBoots, s, bayShop, ship, man, sword, b, pot, b1, b2);
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
   static ArrayList<PlayerCannonBall> cannonballList = new ArrayList<PlayerCannonBall>();
	static int mapRow = 5;
	static int mapColumn = 2;
	
	final static int INVENTORY_SLOT1 = 200;
	final static int INVENTORY_SLOT2 = 350;
	final static int INVENTORY_SLOT3 = 500;
	final static int INVENTORY_SLOT4 = 650;
	
	
	final static int NO_PLACE = -10;
	final static int MENU_STATE = 0;
	final static int GAME_STATE = -1;
	final static int END_STATE = -2;
	final static int FOREST_STATE = 1;
	final static int LAGOON_STATE = 2;
	final static int CAVE_STATE = 3;
	final static int FOREST_EDGE = 4;
	final static int PATH1_STATE = 6;
	final static int PATH2_STATE = 7;
	final static int BAY_STATE = 8;
	final static int BAY_SHOP_STATE = 9;
	final static int OCEAN_STATE = 10;
	final static int ISLAND_STATE = 11;
	final static int mapStates[][] = { { OCEAN_STATE, OCEAN_STATE, OCEAN_STATE, OCEAN_STATE, ISLAND_STATE },
			{ OCEAN_STATE, OCEAN_STATE, OCEAN_STATE, OCEAN_STATE, OCEAN_STATE },
			{ OCEAN_STATE, OCEAN_STATE, OCEAN_STATE, OCEAN_STATE, BAY_STATE },
			{ OCEAN_STATE, OCEAN_STATE, OCEAN_STATE, NO_PLACE, PATH2_STATE },
			{ OCEAN_STATE, OCEAN_STATE, OCEAN_STATE, NO_PLACE, PATH1_STATE },
			{ NO_PLACE, CAVE_STATE, FOREST_STATE, LAGOON_STATE, FOREST_EDGE } };

	boolean mapOpen;
	boolean updatedSpeed;

	static int currentState = MENU_STATE;
	
	static boolean playerisSailing;
	
	

	GamePanel() {
		inventoryFont = new Font("Arial", Font.PLAIN, 25);
		menuFont = new Font("Arial", Font.ITALIC, 50);
		instructionsFont = new Font("Arial", Font.PLAIN, 35);
		healthFont = new Font("Arial", Font.ITALIC, 20);
		t = new Timer(1000 / fps, this);
		t.start();
		try {
			level1BoatLeft = ImageIO.read(this.getClass().getResourceAsStream("Level1Boat.Left.png"));
			level1BoatLeftUp = ImageIO.read(this.getClass().getResourceAsStream("Level1Boat.LeftUp.png"));
			level1BoatLeftDown = ImageIO.read(this.getClass().getResourceAsStream("Level1Boat.LeftDown.png"));
			level1BoatRight = ImageIO.read(this.getClass().getResourceAsStream("Level1Boat.Right.png"));
			level1BoatRightDown = ImageIO.read(this.getClass().getResourceAsStream("Level1Boat.RightDown.png"));
			level1BoatRightUp = ImageIO.read(this.getClass().getResourceAsStream("Level1Boat.RightUp.png"));
			
			
			
			
			level2BoatLeft = ImageIO.read(this.getClass().getResourceAsStream("Level2Boat.Left.png"));
			level2BoatLeftUp = ImageIO.read(this.getClass().getResourceAsStream("Level2Boat.LeftUp.png"));
			level2BoatLeftDown = ImageIO.read(this.getClass().getResourceAsStream("Level2Boat.LeftDown.png"));
			level2BoatRight = ImageIO.read(this.getClass().getResourceAsStream("Level2Boat.Right.png"));
			level2BoatRightUp = ImageIO.read(this.getClass().getResourceAsStream("Level2Boat.RightUp.png"));
			level2BoatRightDown = ImageIO.read(this.getClass().getResourceAsStream("Level2Boat.RightDown.png"));
			
			
			
			level3BoatLeft = ImageIO.read(this.getClass().getResourceAsStream("Level3Boat.Left.png"));
			level3BoatLeftUp = ImageIO.read(this.getClass().getResourceAsStream("Level3Boat.LeftUp.png"));
			level3BoatLeftDown = ImageIO.read(this.getClass().getResourceAsStream("Level3Boat.LeftDown.png"));
			level3BoatRight = ImageIO.read(this.getClass().getResourceAsStream("Level3Boat.Right.png"));
			level3BoatRightUp = ImageIO.read(this.getClass().getResourceAsStream("Level3Boat.RightUp.png"));
			level3BoatRightDown = ImageIO.read(this.getClass().getResourceAsStream("Level3Boat.RightDown.png"));
			
			
			cannonBallProjectile = ImageIO.read(this.getClass().getResourceAsStream("cannonBallProjectile.png"));
			MenuImg = ImageIO.read(this.getClass().getResourceAsStream("JourneyMenuImg.png"));
			enemyShip = ImageIO.read(this.getClass().getResourceAsStream("EnemyShip.png"));
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
		checkUp();
		checkDown();
		checkLeft();
		checkRight();
		o.update();
		o.checkCollision();
		repaint();

	}

	void updateLagoonState() {
		checkUp();
		checkDown();
		checkLeft();
		checkRight();
		o.update();
		o.checkCollision();
		repaint();

	}

	void updateCaveState() {
		checkUp();
		checkDown();
		checkLeft();
		checkRight();
		o.update();
		o.checkCollision();
		repaint();

	}

	void updateShackState() {
		checkUp();
		checkDown();
		checkLeft();
		checkRight();

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

		if (p.collisionBox.y > JourneyToTheLostTreasure.HEIGHT - 60) {
			s.inside = false;
			System.out.println(s.inside);

			// currentState = FOREST_EDGE;

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
		checkUp();
		checkDown();
		checkLeft();
		checkRight();
		o.update();
		b.update();
		o.checkCollision();
		repaint();
	}

	void updatePath2State() {
		checkUp();
		checkDown();
		checkLeft();
		checkRight();

		o.update();

		repaint();
	}

	void updateBayState() {
		checkUp();
		checkDown();
		checkLeft();
		checkRight();
		o.update();
		o.checkCollision();
		repaint();

	}

	void drawForestState(Graphics g) {

		g.setColor(Color.GREEN);
		g.fillRect(0, 0, JourneyToTheLostTreasure.WIDTH, JourneyToTheLostTreasure.HEIGHT);
		g.setColor(Color.GRAY);
		if (!playerisSailing) {
			p.draw(g);
		}
		g.fillRect(850, 0, 150, 700);
		if (m.isFound == false) {
			m.draw(g);
		}
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

		if (!playerisSailing) {
			if (p.health < 30) {
				g.setColor(Color.red);
			} else if (p.health < 50) {
				g.setColor(Color.YELLOW);
			} else if (p.health > 50) {
				g.setColor(Color.darkGray);
			}
		}

	}

	void drawLagoonState(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(0, 0, JourneyToTheLostTreasure.WIDTH, JourneyToTheLostTreasure.HEIGHT);
		g.setColor(Color.GRAY);
		if (!playerisSailing) {
			p.draw(g);
		}

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
		if (!playerisSailing) {
			if (p.health < 30) {
				g.setColor(Color.red);
			} else if (p.health < 50) {
				g.setColor(Color.YELLOW);
			} else if (p.health > 50) {
				g.setColor(Color.darkGray);
			}
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
		if (!playerisSailing) {
			p.draw(g);
		}
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

		if (!playerisSailing) {
			if (p.health < 30) {
				g.setColor(Color.red);
			} else if (p.health < 50) {
				g.setColor(Color.YELLOW);
			} else if (p.health > 50) {
				g.setColor(Color.darkGray);
			}
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
		if (!playerisSailing) {
			p.draw(g);
		}

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

		if (!playerisSailing) {
			if (p.health < 30) {
				g.setColor(Color.red);
			} else if (p.health < 50) {
				g.setColor(Color.YELLOW);
			} else if (p.health > 50) {
				g.setColor(Color.darkGray);
			}
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
		if (!playerisSailing) {
			p.draw(g);
		}
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

		if (!playerisSailing) {
			if (p.health < 30) {
				g.setColor(Color.red);
			} else if (p.health < 50) {
				g.setColor(Color.YELLOW);
			} else if (p.health > 50) {
				g.setColor(Color.darkGray);
			}
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
		if (!playerisSailing) {
			p.draw(g);
		}

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
		g.drawString("Path 2", JourneyToTheLostTreasure.WIDTH / 3, 50);
		g.setFont(healthFont);

		if (!playerisSailing) {
			if (p.health < 30) {
				g.setColor(Color.red);
			} else if (p.health < 50) {
				g.setColor(Color.YELLOW);
			} else if (p.health > 50) {
				g.setColor(Color.darkGray);
			}
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
		if (!playerisSailing) {
			p.draw(g);
		}

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

		if (!playerisSailing) {
			if (p.health < 30) {
				g.setColor(Color.red);
			} else if (p.health < 50) {
				g.setColor(Color.YELLOW);
			} else if (p.health > 50) {
				g.setColor(Color.darkGray);
			}
		}
		g.drawString(Integer.toString(p.health), p.getX() - 10, p.getY() + 80);
		if (sword.isFound) {
			sword.draw(g);
		}

	}

	void drawBayState(Graphics g) {

		g.setColor(Color.GREEN);
		g.fillRect(0, 0, JourneyToTheLostTreasure.WIDTH, JourneyToTheLostTreasure.HEIGHT);
		g.setColor(Color.blue);
		g.fillRect(0, 0, JourneyToTheLostTreasure.WIDTH / 3, JourneyToTheLostTreasure.HEIGHT);
		g.setColor(Color.GRAY);
		g.fillRect(550, 10, 290, 290);
		if (!playerisSailing) {
			p.draw(g);
		}
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
		g.drawString("Bay", JourneyToTheLostTreasure.WIDTH / 3, 50);
		ship.draw(g);
		g.setFont(healthFont);

		if (!playerisSailing) {
			if (p.health < 30) {
				g.setColor(Color.red);
			} else if (p.health < 50) {
				g.setColor(Color.YELLOW);
			} else if (p.health > 50) {
				g.setColor(Color.darkGray);
			}
		}
		g.drawString(Integer.toString(p.health), p.getX() - 10, p.getY() + 80);
		if (sword.isFound) {
			sword.draw(g);
		}

		repaint();
	}

	public void paintComponent(Graphics g) {
		
		if (currentState == MENU_STATE) {
			drawMenuState(g);
		} else if (currentState == END_STATE) {
		} else {
			System.out.println(s.inside);
			System.out.println(mapStates[mapRow][mapColumn]);
			if (mapStates[mapRow][mapColumn] == FOREST_STATE) {

				updateForestState();
				drawForestState(g);

			} else if (mapStates[mapRow][mapColumn] == LAGOON_STATE) {
				updateLagoonState();
				drawLagoonState(g);
			} else if (mapStates[mapRow][mapColumn] == CAVE_STATE) {

				updateCaveState();
				drawCaveState(g);
			} else if (mapStates[mapRow][mapColumn] == FOREST_EDGE) {

				updateShackState();
				drawShackState(g);
			}

			else if (mapStates[mapRow][mapColumn] == PATH1_STATE) {
				updatePath1State();
				drawPath1State(g);
				if (b.getHealth() > 0) {
					b.draw(g);
				}

			} else if (mapStates[mapRow][mapColumn] == PATH2_STATE) {
				drawPath2State(g);
				updatePath2State();
				if (b1.getHealth() > 0) {
					b1.draw(g);

				}
				if (b2.getHealth() > 0) {
					b2.draw(g);

				}
			} else if (mapStates[mapRow][mapColumn] == BAY_STATE) {
				drawBayState(g);
				updateBayState();
			} else if (mapStates[mapRow][mapColumn] == OCEAN_STATE) {
				drawOceanState(g);
				updateOceanState();
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
			if (bayShop.inside) {
				drawInShopState(g);
				updateInShopState();
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
					b1.setX(b1.getX() - 1);
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
			}
		repaint();
	}

	void updateOceanState() {
		
		
		checkUp();
		checkDown();
		checkLeft();
		checkRight();
		o.update();
		o.checkCollision();
		repaint();

	}

	void drawOceanState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, JourneyToTheLostTreasure.WIDTH, JourneyToTheLostTreasure.HEIGHT);

		g.setColor(Color.GRAY);
		if (!playerisSailing) {
			p.draw(g);
		}
		ship.draw(g);
		eShip.draw(g, eShip.getX(), eShip.getY());
		cannonballList.get(cannonballList.size()).draw(g);
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
		g.drawString("Ocean", JourneyToTheLostTreasure.WIDTH / 3, 50);

		repaint();
	}

	void updateInShopState() {
		if (p.collisionBox.y < 0) {
			up = false;

		}
		if (p.collisionBox.x < 0) {
			left = false;
		}
		if (p.collisionBox.x > JourneyToTheLostTreasure.WIDTH - 160) {
			right = false;
		}

		else if (p.collisionBox.y > JourneyToTheLostTreasure.HEIGHT) {
			p.setX(10);
			sword.setX(25);
			if (mapRow < 6) {
				mapRow += 1;
			}
		}
		o.update();
		o.checkCollision();
		repaint();

	}

	void drawInShopState(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
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
		g.drawString("Bay Shop", JourneyToTheLostTreasure.WIDTH / 3, 50);
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

	@Override
	public void keyPressed(KeyEvent e) {
		
		
if(e.getKeyCode() == KeyEvent.VK_H) {
	mapStates[mapRow][mapColumn] = BAY_STATE;
	o.coins += 100;
}
		// System.out.println("pressed");
		
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				if(playerisSailing) {
					if (ship.direction == 0) {
						cannonballList.add(new PlayerCannonBall(ship.getX(), ship.getY(), 10, 10, 100));
						
					}
					
				}
				
				
				
				if (sword.isFound) {
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
				mapRow = 5;
				mapColumn = 2;
				currentState = GAME_STATE;
			}}
			else if(currentState == END_STATE) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					currentState = MENU_STATE;
				}
			}

		
			else {
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
			if (pot.isFound) {

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

			if (caveBoots.isFound) {
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
					}
				}
			}

			if (mapStates[mapRow][mapColumn] == LAGOON_STATE) {

				if (pot.positionInInv == INVENTORY_SLOT1) {
					if (e.getKeyCode() == KeyEvent.VK_1) {
						if (pot.isDrank == true) {

							pot.isDrank = false;

						}
					}
				} else if (pot.positionInInv == INVENTORY_SLOT2) {
					if (e.getKeyCode() == KeyEvent.VK_2) {
						if (pot.isDrank == true) {

							pot.isDrank = false;

						}
					}
				} else if (pot.positionInInv == INVENTORY_SLOT3) {
					if (e.getKeyCode() == KeyEvent.VK_3) {
						if (pot.isDrank == true) {

							pot.isDrank = false;

						}
					}
				} else if (pot.positionInInv == INVENTORY_SLOT4) {
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
		} else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
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
		
		eShip.setX(eShip.getX() - ((eShip.getX() - ship.getX()) / 100));
		eShip.setY(eShip.getY() - ((eShip.getY() - ship.getY()) / 100));
		
		
		if (!playerisSailing) {
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
		} else if (playerisSailing) {

			if (up) {

				ship.setY(ship.getY() - ship.getSpeed());
				sword.setY(sword.getY() - ship.getSpeed());
			}
			if (right) {
				ship.setX(ship.getX() + ship.getSpeed());
				sword.setX(sword.getX() + ship.getSpeed());
			}
			if (left) {
				System.out.println("should be going left");
				ship.setX(ship.getX() - ship.getSpeed());
				sword.setX(sword.getX() - ship.getSpeed());
			}
			if (down) {
				ship.setY(ship.getY() + ship.getSpeed());
				sword.setY(sword.getY() + ship.getSpeed());

			}
		}
		repaint();
	}

	

	void changePos(int newRow, int newColomn) {
		mapRow = newRow;
		mapColumn = newColomn;
		currentState = mapStates[mapRow][mapColumn];
	}

	void checkLeft() {
		if (p.collisionBox.x < 0 || ship.collisionBox.x < 0) {
			if (mapColumn > 0 && mapStates[mapRow][mapColumn - 1] != NO_PLACE) {
               if(!playerisSailing) {
				p.setX(800);
				sword.setX(815);}
               else if(playerisSailing) {           	   
            	   ship.setX(815);
               }
               changePos(mapRow, mapColumn - 1);
				System.out.println("left");
				System.out.println(mapStates[mapRow][mapColumn]);
			} else {
				left = false;
			}
		}
	}

	void checkRight() {
		if (p.collisionBox.x > 815 || ship.collisionBox.x > 815) {
			if (mapColumn < 4 && mapStates[mapRow][mapColumn + 1] != NO_PLACE) {
				if (!playerisSailing) {
					p.setX(10);
					sword.setX(25);
				} else if (playerisSailing) {
					ship.setX(10);
				}

				changePos(mapRow, mapColumn + 1);
				System.out.println("right");
				System.out.println(mapStates[mapRow][mapColumn]);
			} else {
				right = false;
			}
		}
	}

	void checkUp() {
		if (p.collisionBox.y < 0 || ship.collisionBox.y < 0) {
			if (mapRow > 0 && mapStates[mapRow - 1][mapColumn] != NO_PLACE) {
				if (!playerisSailing) {
					p.setY(800);
					sword.setY(800);
				} else if (playerisSailing) {
					ship.setY(800);
				}
				changePos(mapRow - 1, mapColumn);
				System.out.println("up");
				System.out.println(mapStates[mapRow][mapColumn]);
			} else {
				up = false;
			}
		}
	}

	void checkDown() {
		if (p.collisionBox.y > 800 || ship.collisionBox.y > 800) {
			if (mapRow < 5 && mapStates[mapRow + 1][mapColumn] != NO_PLACE) {
				if (!playerisSailing) {
					p.setY(10);
					sword.setY(10);
				} else if (playerisSailing) {
					ship.setY(10);
				}
				changePos(mapRow + 1, mapColumn);
				System.out.println("down");
				System.out.println(mapStates[mapRow][mapColumn]);
			} else {
				down = false;
			}
		}
	}
}
