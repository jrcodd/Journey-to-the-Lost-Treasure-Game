package gamePackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
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
	public static BufferedImage PlayerSwordDownLeft;
	public static BufferedImage PlayerSwordDownRight;
	public static BufferedImage PlayerSwordUpLeft;
	public static BufferedImage PlayerSwordUpRight;
	public static BufferedImage potion;
	public static BufferedImage emptyPotion;
	public static BufferedImage boots;
	public static BufferedImage treasureMarker;
	public static BufferedImage RepairKitImg;
	public static Image enemy;
	boolean doneAttacking;
	final static int fps = 80;
	private static final int LAST_ROW = 6;
	private static final int LAST_COL = 4;
	Timer t;
	OldMan man = new OldMan(600, 75, 20, 60, 200);
	Shack s = new Shack(530, 20, 300, 300, 2000, false);
	BayShop bayShop = new BayShop(500, 10, 340, 340, 100, false);
	PlayerShip ship = new PlayerShip(50, 50, 433 / 3, 381 / 3, 400, 1);

	SpeedyBoots caveBoots = new SpeedyBoots(230, 600, 10, 20, 50, false);
	Player p = new Player(100, 500, 162 / 2, 152 / 2, 50, 8);
	TreasureMap m = new TreasureMap(400, 100, 10, 10, 10, false);
	ShipRepairKit kit = new ShipRepairKit(400, 100, 10, 10, 10, false);
	Sword sword = new Sword(115, 500, 10, 40, 100, false);
	StrongBandit b = new StrongBandit(400, 100, 40, 80, 300, 1);
	WeakBandit b1 = new WeakBandit(30, 30, 20, 60, 100, 2, false);
	WeakBandit b2 = new WeakBandit(800, 30, 20, 60, 100, 2, true);
	HealthPotion pot = new HealthPotion(500, 468, 100 / 3, 106 / 3, 30, false);
	X x = new X(600, 100, 200, 200, 1);
	Object_Manager o = new Object_Manager(p, m, kit, caveBoots, s, bayShop, ship, man, sword, b, pot, b1, b2, x);
	static boolean up = false;
	static boolean down = false;
	static boolean right = false;
	static boolean left = false;
	static boolean swordUp = true;
	static boolean swordDown;
	Font inventoryFont;
	Font menuFont;
	Font instructionsFont;
	Font healthFont;

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
	final static int mapStates[][] = {

			{ OCEAN_STATE, OCEAN_STATE, OCEAN_STATE, OCEAN_STATE, ISLAND_STATE },
			{ OCEAN_STATE, OCEAN_STATE, OCEAN_STATE, OCEAN_STATE, OCEAN_STATE },
			{ OCEAN_STATE, OCEAN_STATE, OCEAN_STATE, OCEAN_STATE, OCEAN_STATE },
			{ OCEAN_STATE, OCEAN_STATE, OCEAN_STATE, OCEAN_STATE, BAY_STATE },
			{ OCEAN_STATE, OCEAN_STATE, OCEAN_STATE, NO_PLACE, PATH2_STATE },
			{ OCEAN_STATE, OCEAN_STATE, OCEAN_STATE, NO_PLACE, PATH1_STATE },
			{ NO_PLACE, CAVE_STATE, FOREST_STATE, LAGOON_STATE, FOREST_EDGE } };

	boolean mapOpen;
	boolean updatedSpeed;
	boolean playerCanMoveLeft = true;
	boolean playerCanMoveRight = true;
	boolean playerCanMoveUp = true;
	boolean playerCanMoveDown = true;
	int currentState = MENU_STATE;

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
			PlayerSwordDownLeft = ImageIO.read(this.getClass().getResourceAsStream("Character-swordDown.Left.png"));
			PlayerSwordDownRight = ImageIO.read(this.getClass().getResourceAsStream("Character-swordDown.png"));
			PlayerSwordUpLeft = ImageIO.read(this.getClass().getResourceAsStream("Character-swordUp.Left.png"));
			PlayerSwordUpRight = ImageIO.read(this.getClass().getResourceAsStream("Character-swordUp.png"));
			enemyShip = ImageIO.read(this.getClass().getResourceAsStream("EnemyShip.png"));
			potion = ImageIO.read(this.getClass().getResourceAsStream("Potion.png"));
			emptyPotion = ImageIO.read(this.getClass().getResourceAsStream("EmptyPotion.png"));
			boots = ImageIO.read(this.getClass().getResourceAsStream("SpeedyBoots.png"));
			treasureMarker = ImageIO.read(this.getClass().getResourceAsStream("x-marks-the-spot.png"));
			RepairKitImg = ImageIO.read(this.getClass().getResourceAsStream("repairKit.png"));
			enemy = new ImageIcon(getClass().getResource("enemy.gif")).getImage();
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

		o.update();
		o.checkCollision();
		repaint();

	}

	void updateLagoonState() {

		o.update();
		o.checkCollision();
		repaint();

	}

	void updateCaveState() {

		o.update();
		o.checkCollision();
		repaint();

	}

	void updateShackState() {

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

		o.update();
		b.update();
		o.checkCollision();
		repaint();
	}

	void updatePath2State() {

		o.update();

		repaint();
	}

	void updateBayState() {

		o.update();
		o.checkCollision();
		repaint();

	}

	void updateIslandState() {
		if (ship.x > JourneyToTheLostTreasure.WIDTH / 4) {
			o.setPlayerisSailing(false);
		}
		x.update();

		o.update();
		o.checkCollision();
		repaint();

	}

	void drawForestState(Graphics g) {

		g.setColor(Color.GREEN);
		g.fillRect(0, 0, JourneyToTheLostTreasure.WIDTH, JourneyToTheLostTreasure.HEIGHT);
		g.setColor(Color.GRAY);
		if (!o.getPlayerisSailing()) {

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
		drawPlayerHealth(g);
	}

	void drawLagoonState(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(0, 0, JourneyToTheLostTreasure.WIDTH, JourneyToTheLostTreasure.HEIGHT);
		g.setColor(Color.GRAY);
		if (!o.getPlayerisSailing()) {

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
		drawPlayerHealth(g);

		if (sword.isFound) {
			sword.draw(g);
		}

	}

	void drawCaveState(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, JourneyToTheLostTreasure.WIDTH, JourneyToTheLostTreasure.HEIGHT);

		g.setColor(Color.GRAY);
		if (!o.getPlayerisSailing()) {

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

		drawPlayerHealth(g);

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
		if (!o.getPlayerisSailing()) {

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
		g.setColor(Color.red);
		drawPlayerHealth(g);

		if (sword.isFound) {
			sword.draw(g);
		}

	}

	void drawMapState(Graphics g) {

		drawMap(g);

	}

	void drawMap(Graphics g) {
		g.setColor(new Color(0x999999));
		g.fillRect(0, 0, JourneyToTheLostTreasure.WIDTH, JourneyToTheLostTreasure.HEIGHT);
		g.setColor(Color.blue);
		g.fillRect((JourneyToTheLostTreasure.WIDTH / 5), 0, (JourneyToTheLostTreasure.WIDTH / 5) * 4,
				JourneyToTheLostTreasure.HEIGHT);
		g.setColor(Color.ORANGE);
		g.fillRect((JourneyToTheLostTreasure.WIDTH / 5) * 4, 0, (JourneyToTheLostTreasure.WIDTH / 5),
				JourneyToTheLostTreasure.HEIGHT / 6);
		g.setColor(new Color(0xFFFF66));
		g.fillRect((JourneyToTheLostTreasure.WIDTH / 5) * 4, (JourneyToTheLostTreasure.HEIGHT / 7) * 3,
				(JourneyToTheLostTreasure.WIDTH / 5), JourneyToTheLostTreasure.HEIGHT / 7);
		g.setColor(new Color(0x33FF33));
		g.fillRect((JourneyToTheLostTreasure.WIDTH / 5) * 4, (JourneyToTheLostTreasure.HEIGHT / 7) * 4,
				(JourneyToTheLostTreasure.WIDTH / 5), JourneyToTheLostTreasure.HEIGHT / 7);
		g.fillRect((JourneyToTheLostTreasure.WIDTH / 5) * 4, (JourneyToTheLostTreasure.HEIGHT / 7) * 5,
				(JourneyToTheLostTreasure.WIDTH / 5), JourneyToTheLostTreasure.HEIGHT / 7);
		g.setColor(new Color(0x009900));
		g.fillRect((JourneyToTheLostTreasure.WIDTH / 5) * 4, (JourneyToTheLostTreasure.HEIGHT / 7) * 6,
				(JourneyToTheLostTreasure.WIDTH / 5), JourneyToTheLostTreasure.HEIGHT / 7);
		g.setColor(Color.cyan);
		g.fillRect((JourneyToTheLostTreasure.WIDTH / 5) * 3, (JourneyToTheLostTreasure.HEIGHT / 7) * 6,
				(JourneyToTheLostTreasure.WIDTH / 5), JourneyToTheLostTreasure.HEIGHT / 7);
		g.setColor(new Color(0x009900));
		g.fillRect((JourneyToTheLostTreasure.WIDTH / 5) * 2, (JourneyToTheLostTreasure.HEIGHT / 7) * 6,
				(JourneyToTheLostTreasure.WIDTH / 5), JourneyToTheLostTreasure.HEIGHT / 7);
		g.setColor(new Color(0x000000));
		g.fillRect((JourneyToTheLostTreasure.WIDTH / 5), (JourneyToTheLostTreasure.HEIGHT / 7) * 6,
				(JourneyToTheLostTreasure.WIDTH / 5), JourneyToTheLostTreasure.HEIGHT / 7);

		g.setColor(Color.blue);
		g.fillRect(0, 25, 100, 75);
		g.setColor(Color.BLACK);
		g.setFont(inventoryFont);
		g.drawString("OCEAN", 101, 25 + (75 / 2));
		g.setColor(Color.ORANGE);
		g.fillRect(0, 125, 100, 75);
		g.setFont(inventoryFont);
		g.setColor(Color.BLACK);
		g.drawString("ISLAND", 101, 125 + (75 / 2));
		g.setColor(new Color(0xFFFF66));
		g.fillRect(0, 225, 100, 75);
		g.setFont(inventoryFont);
		g.setColor(Color.BLACK);
		g.drawString("BAY", 101, 225 + (75 / 2));
		g.setColor(new Color((0x33FF33)));
		g.fillRect(0, 325, 100, 75);
		g.setColor(Color.BLACK);
		g.setFont(inventoryFont);
		g.drawString("PATH", 101, 325 + (75 / 2));
		g.setColor(new Color((0x009900)));
		g.fillRect(0, 425, 100, 75);
		g.setColor(Color.BLACK);
		g.setFont(inventoryFont);
		g.drawString("FOREST", 101, 425 + (75 / 2));
		g.setColor(Color.cyan);
		g.fillRect(0, 525, 100, 75);
		g.setColor(Color.BLACK);
		g.setFont(inventoryFont);
		g.drawString("LAGOON", 101, 525 + (75 / 2));
		g.setColor(Color.black);
		g.fillRect(0, 625, 100, 75);
		g.setColor(Color.BLACK);
		g.setFont(inventoryFont);
		g.drawString("CAVE", 101, 625 + (75 / 2));
	}

	void drawInShackState(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, JourneyToTheLostTreasure.WIDTH, JourneyToTheLostTreasure.HEIGHT);
		g.setColor(Color.GRAY);
		if (!o.getPlayerisSailing()) {

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
		g.setColor(Color.red);
		drawPlayerHealth(g);
		if (m.isFound) {
			m.drawInInv(g);
		}
		if (kit.isFound) {
			kit.drawInInv(g);
		}
		if (caveBoots.isFound) {
			caveBoots.drawInInv(g);
		}
		if (pot.isFound) {
			pot.drawInInv(g, pot.isDrank);
		}
		if (sword.isFound) {

			sword.draw(g);
		}

		repaint();
	}

	void drawPath2State(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(0, 0, JourneyToTheLostTreasure.WIDTH, JourneyToTheLostTreasure.HEIGHT);
		g.setColor(Color.GRAY);
		if (!o.getPlayerisSailing()) {

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
		drawPlayerHealth(g);

		if (sword.isFound) {
			sword.draw(g);
		}

	}

	void drawPath1State(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, JourneyToTheLostTreasure.WIDTH, JourneyToTheLostTreasure.HEIGHT);
		g.setColor(Color.GRAY);
		if (!o.getPlayerisSailing()) {

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
		drawPlayerHealth(g);
		if (sword.isFound) {
			sword.draw(g);
		}

	}

	void drawIslandState(Graphics g) {

		g.setColor(Color.ORANGE);

		g.fillRect(0, 0, JourneyToTheLostTreasure.WIDTH, JourneyToTheLostTreasure.HEIGHT);
		g.setColor(Color.blue);
		g.fillRect(0, 0, JourneyToTheLostTreasure.WIDTH / 4, JourneyToTheLostTreasure.HEIGHT);
		x.draw(g);
		if (!o.getPlayerisSailing()) {

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
		g.drawString("Island", JourneyToTheLostTreasure.WIDTH / 3, 50);
		ship.draw(g);
		drawPlayerHealth(g);
		if (sword.isFound) {
			sword.draw(g);
		}
		drawPlayerHealth(g);
		repaint();
	}

	void drawBayState(Graphics g) {

		g.setColor(Color.GREEN);

		g.fillRect(0, 0, JourneyToTheLostTreasure.WIDTH, JourneyToTheLostTreasure.HEIGHT);
		g.setColor(Color.blue);
		g.fillRect(0, 0, JourneyToTheLostTreasure.WIDTH / 3, JourneyToTheLostTreasure.HEIGHT);
		g.setColor(Color.GRAY);
		g.fillRect(550, 10, 290, 290);
		if (!o.getPlayerisSailing()) {

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
		drawPlayerHealth(g);
		if (sword.isFound) {
			sword.draw(g);
		}

		repaint();
	}

	public void paintComponent(Graphics g) {
		g.fillRect(0, 0, 1000000, 10000000);
		if (currentState == MENU_STATE) {
			drawMenuState(g);
		} else if (currentState == END_STATE) {
			g.setColor(Color.red);
			g.fillRect(0, 0, JourneyToTheLostTreasure.WIDTH, JourneyToTheLostTreasure.HEIGHT);
			g.setFont(menuFont);
			g.setColor(Color.black);
			g.drawString("Game Over", (JourneyToTheLostTreasure.WIDTH / 2) - 200, JourneyToTheLostTreasure.HEIGHT / 3);
			g.drawString("Press ENTER To Restart", (JourneyToTheLostTreasure.WIDTH / 2) - 200,
					(JourneyToTheLostTreasure.HEIGHT / 2));
			o.processDeath();
		} else if (!mapOpen) {

			if (p.health <= 0 || ship.health <= 0) {
				// DeathListener.death();
				System.out.println("Game Over");
				currentState = END_STATE;
			}
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
			if (mapStates[mapRow][mapColumn] == ISLAND_STATE) {
				drawIslandState(g);
				updateIslandState();
			}
			if (kit.isFound) {
				kit.drawInInv(g);
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

			if (swordDown) {
				switch (p.dir) {
				case "LEFT":
					if (!o.getPlayerisSailing()) {
						g.drawImage(GamePanel.PlayerSwordDownLeft, p.x, p.y, p.width, p.height, null);
					}
					break;
				case "RIGHT":
					if (!o.getPlayerisSailing()) {
						g.drawImage(GamePanel.PlayerSwordDownRight, p.x, p.y, p.width, p.height, null);
					}
					break;
				}
			} else if (swordUp) {
				switch (p.dir) {
				case "LEFT":
					if (!o.getPlayerisSailing()) {
						g.drawImage(GamePanel.PlayerSwordUpLeft, p.x, p.y, p.width, p.height, null);
					}
					break;

				case "RIGHT":
					if (!o.getPlayerisSailing()) {
						g.drawImage(GamePanel.PlayerSwordUpRight, p.x, p.y, p.width, p.height, null);
					}
					break;
				}
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
		} else {

			drawMapState(g);

		}
		repaint();
	}

	void updateOceanState() {

		o.update();
		o.checkCollision();
		repaint();

	}

	void drawOceanState(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(0, 0, JourneyToTheLostTreasure.WIDTH, JourneyToTheLostTreasure.HEIGHT);

		g.setColor(Color.GRAY);

		for (int i = 0; i < o.enemyShipList.size(); i++) {
			o.enemyShipList.get(i).draw(g);
		}
		ship.draw(g);

		for (PlayerCannonBall b : o.cannonballList) {
			b.draw(g);
		}
		for (EnemyCannonBall b : o.EnemycannonballList) {
			b.draw(g);
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
		g.drawString("Ocean", JourneyToTheLostTreasure.WIDTH / 3, 50);
		g.setColor(Color.red);
		g.fillOval((ship.getX() + ship.width / 2) - ((ship.health) / 2), ship.getY() + ship.height, ship.health, 5);
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

		if (p.collisionBox.y > JourneyToTheLostTreasure.HEIGHT - 60) {
			bayShop.inside = false;

			p.setY(350);
			p.setX(560);
			sword.setX(575);
			sword.setY(350);

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

		drawPlayerHealth(g);
		if (sword.isFound) {

			sword.draw(g);
		}

		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			// works

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
				mapRow = 6;
				mapColumn = 2;
				currentState = GAME_STATE;
			}
		} else if (currentState == END_STATE) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				currentState = MENU_STATE;
			}
		}

		else {
			if (e.getKeyCode() == KeyEvent.VK_W) {
				up = true;
				checkUp();
			} else if (e.getKeyCode() == KeyEvent.VK_S) {
				down = true;
				checkDown();
			} else if (e.getKeyCode() == KeyEvent.VK_A) {
				left = true;
				checkLeft();
			} else if (e.getKeyCode() == KeyEvent.VK_D) {
				right = true;
				checkRight();
			}
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				up = true;
				checkUp();
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				down = true;
				checkDown();
			} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				left = true;
				checkLeft();
			} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				right = true;
				checkRight();
			}

			if (kit.isFound) {
				if (kit.positionInInv == INVENTORY_SLOT1) {
					if (o.getPlayerisSailing()) {
						if (e.getKeyCode() == KeyEvent.VK_1) {
							System.out.println(kit.isUsed);
							if (kit.timeUntilNextUse <= 0) {
								if (ship.health < ship.maxHealth) {
									ship.health = ship.maxHealth;
									kit.timeUntilNextUse = fps * 15;
								}
							}
						}
					}
				} else if (kit.positionInInv == INVENTORY_SLOT2) {
					if (e.getKeyCode() == KeyEvent.VK_2) {
						System.out.println(kit.isUsed);
						if (kit.timeUntilNextUse <= 0) {
							if (ship.health < ship.maxHealth) {
								ship.health = ship.maxHealth;
								kit.timeUntilNextUse = fps * 15;
							}
						}
					}
				} else if (kit.positionInInv == INVENTORY_SLOT3) {
					if (e.getKeyCode() == KeyEvent.VK_3) {
						if (kit.timeUntilNextUse <= 0) {
							if (ship.health < ship.maxHealth) {
								ship.health = ship.maxHealth;
								kit.timeUntilNextUse = fps * 15;
							}
						}
					}
				} else if (kit.positionInInv == INVENTORY_SLOT4) {
					if (e.getKeyCode() == KeyEvent.VK_4) {

						if (kit.timeUntilNextUse <= 0) {
							if (ship.health < ship.maxHealth) {
								ship.health = ship.maxHealth;
								kit.timeUntilNextUse = fps * 30;
							}
						}
					}
				}
			}

			if (pot.isFound) {

				if (pot.positionInInv == INVENTORY_SLOT1) {

					if (e.getKeyCode() == KeyEvent.VK_1) {

						if (pot.isDrank == false) {
							if (p.health < 50) {
								p.health = 50;
								pot.isDrank = true;
							}
						}
					}
				} else if (pot.positionInInv == INVENTORY_SLOT2) {
					if (e.getKeyCode() == KeyEvent.VK_2) {
						if (pot.isDrank == false) {
							if (p.health < 50) {
								p.health = 50;
								pot.isDrank = true;
							}
						}
					}
				} else if (pot.positionInInv == INVENTORY_SLOT3) {
					if (e.getKeyCode() == KeyEvent.VK_3) {
						if (pot.isDrank == false) {
							if (p.health < 50) {
								p.health = 50;
								pot.isDrank = true;
							}
						}
					}
				} else if (pot.positionInInv == INVENTORY_SLOT4) {
					if (e.getKeyCode() == KeyEvent.VK_4) {
						if (pot.isDrank == false) {
							if (p.health < 50) {
								p.health = 50;
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
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (o.getPlayerisSailing()) {
				for (int i = 0; i < o.enemyShipList.size(); i++) {

					o.playerHasFired = true;

					if (PlayerShip.direction == 0) {

						o.cannonballList.add(new PlayerCannonBall(ship.getX(), ship.getY() + 80, 10, 10, 100));

					}
					if (PlayerShip.direction == 3) {
						o.cannonballList.add(new PlayerCannonBall(ship.getX() + 120, ship.getY() + 80, 10, 10, 100));

					}
					if (PlayerShip.direction == 1 || PlayerShip.direction == 4) {
						o.cannonballList.add(new PlayerCannonBall(ship.getX() + 70, ship.getY(), 10, 10, 100));

					}
					if (PlayerShip.direction == 2 || PlayerShip.direction == 5) {
						o.cannonballList.add(new PlayerCannonBall(ship.getX() + 80, ship.getY() + 120, 10, 10, 100));

					}
				}
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_W) {
			up = false;
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			down = false;
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			right = false;
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			left = false;

		}
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			up = false;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			down = false;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			right = false;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			left = false;

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
		if (kit.timeUntilNextUse > 0) {
			kit.timeUntilNextUse -= 1;
		} else {
			System.out.println("ready");
		}
		o.moveEnemyShip();
		b.setX(b.getX() - ((b.getX() - p.getX()) / 10));
		b.setY(b.getY() - ((b.getY() - p.getY()) / 10));

		b1.setX(b1.getX() - ((b1.getX() - p.getX()) / 10));
		b1.setY(b1.getY() - ((b1.getY() - p.getY()) / 10));
		b2.setX(b2.getX() - ((b2.getX() - p.getX()) / 10));
		b2.setY(b2.getY() - ((b2.getY() - p.getY()) / 10));

		o.moveBullets();

		if (!o.getPlayerisSailing())

		{
			if (up) {

				p.setY(p.getY() - p.getSpeed());
				sword.setY(sword.getY() - p.getSpeed());
			}
			if (right) {
				p.setX(p.getX() + p.getSpeed());
				sword.setX(sword.getX() + p.getSpeed());
			}
			if (left) {

				p.setX(p.getX() - p.getSpeed());
				sword.setX(sword.getX() - p.getSpeed());
			}
			if (down) {
				p.setY(p.getY() + p.getSpeed());
				sword.setY(sword.getY() + p.getSpeed());

			}
		} else if (o.getPlayerisSailing()) {

			if (up) {

				ship.setY(ship.getY() - ship.getSpeed());
				sword.setY(sword.getY() - ship.getSpeed());
			}
			if (right) {
				ship.setX(ship.getX() + ship.getSpeed());
				sword.setX(sword.getX() + ship.getSpeed());
			}
			if (left) {

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
			if (mapColumn <= 0) {
				playerCanMoveLeft = false;
			}
			if (mapColumn > 0 && mapStates[mapRow][mapColumn - 1] == NO_PLACE) {
				playerCanMoveLeft = false;
			}
			if (!o.getPlayerisSailing() && mapColumn > 0 && mapStates[mapRow][mapColumn - 1] == OCEAN_STATE) {
				playerCanMoveLeft = false;
			}
			if (playerCanMoveLeft) {
				if (!o.getPlayerisSailing()) {
					p.setX(800);
					sword.setX(815);
				} else if (o.getPlayerisSailing()) {
					ship.setX(815);
				}
				changePos(mapRow, mapColumn - 1);
			} else {
				left = false;
			}
		}

	}

	void checkRight() {
		if (p.collisionBox.x > 815 || ship.collisionBox.x > 815) {
			if (mapColumn >= LAST_COL) {
				playerCanMoveRight = false;
			}
			if (mapColumn < LAST_COL && mapStates[mapRow][mapColumn + 1] == NO_PLACE) {
				playerCanMoveRight = false;
			}
			if (!o.getPlayerisSailing() && mapColumn < LAST_COL && mapStates[mapRow][mapColumn + 1] == OCEAN_STATE) {
				playerCanMoveRight = false;
			}
			if (playerCanMoveRight) {
				changePos(mapRow, mapColumn + 1);
				if (!o.getPlayerisSailing()) {
					p.setX(10);
					sword.setX(10);
				} else if (o.getPlayerisSailing()) {
					ship.setX(10);
				}

			} else {
				right = false;
			}

		}
	}

	void checkUp() {
		if (p.collisionBox.y < 0 || ship.collisionBox.y < 0) {
			if (mapRow <= 0) {
				playerCanMoveUp = false;
			}
			if (mapRow < 0 || mapStates[mapRow - 1][mapColumn] == NO_PLACE) {
				playerCanMoveUp = false;
			}
			if (!o.getPlayerisSailing() && mapRow >= 0 && mapStates[mapRow - 1][mapColumn] == OCEAN_STATE) {
				playerCanMoveUp = false;
			}
			if (playerCanMoveUp) {
				if (!o.getPlayerisSailing()) {
					p.setY(800);
					sword.setY(815);
				} else if (o.getPlayerisSailing()) {
					ship.setY(815);
				}
				changePos(mapRow - 1, mapColumn);
			} else {
				up = false;
			}

		}
	}

	void checkDown() {
		if (p.collisionBox.y > 800 || ship.collisionBox.y > 800) {
			if (mapRow >= LAST_ROW) {
				playerCanMoveDown = false;
			}

			if (mapRow < LAST_ROW && mapStates[mapRow + 1][mapColumn] == NO_PLACE) {
				playerCanMoveDown = false;
			}
			if (!o.getPlayerisSailing() && mapRow < LAST_ROW && mapStates[mapRow + 1][mapColumn] == OCEAN_STATE) {
				playerCanMoveDown = false;
			}
			if (playerCanMoveDown) {
				if (!o.getPlayerisSailing()) {
					p.setY(10);
					sword.setY(10);
				} else if (o.getPlayerisSailing()) {
					ship.setY(10);
				}
				changePos(mapRow + 1, mapColumn);
			} else {
				down = false;
			}

		}
	}

	void drawPlayerHealth(Graphics g) {
		g.setColor(Color.red);
		g.fillOval((p.getX() + (p.width / 2)) - ((p.health * 2) / 2), p.getY() + p.height, p.health * 2, 5);
		repaint();

	}
}
