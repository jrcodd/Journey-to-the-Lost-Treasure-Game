package gamePackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

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

	final static int fps = 60;
	Timer t;
	OldMan man = new OldMan(600, 75, 20, 60, 200);
	Shack s = new Shack(530, 20, 300, 300, 2000, false);
	SpeedyBoots caveBoots = new SpeedyBoots(250, 600, 10, 20, 50, false);
	Player p = new Player(100, 500, 20, 60, 100, 5);
	TreasureMap m = new TreasureMap(400, 100, 10, 10, 10, false);
	Sword sword = new Sword(115, 500, 10, 40, 100, false);
	StrongBandit b = new StrongBandit(400, 100, 20, 60, 300, 3);
	static boolean up = false;
	static boolean down = false;
	static boolean right = false;
	static boolean left = false;
	static boolean isAttacking;
	Font inventoryFont;
	Font menuFont;
	Font instructionsFont;
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
	Object_Manager o = new Object_Manager(p, m, caveBoots, s, man, sword);

	GamePanel() {
		inventoryFont = new Font("Arial", Font.PLAIN, 25);
		menuFont = new Font("Arial", Font.ITALIC, 50);
		instructionsFont = new Font("Arial", Font.PLAIN, 35);
		t = new Timer(1000 / fps, this);
		t.start();

	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(0, 0, JourneyToTheLostTreasure.WIDTH, JourneyToTheLostTreasure.HEIGHT);
		g.setColor(Color.gray);
		g.setFont(menuFont);
		g.drawString("Journey To The Lost Treasure", JourneyToTheLostTreasure.WIDTH / 7,
				JourneyToTheLostTreasure.HEIGHT / 4);
		g.setFont(instructionsFont);
		g.drawString("Press ENTER to start", JourneyToTheLostTreasure.WIDTH / 3, JourneyToTheLostTreasure.HEIGHT / 2);
	}

	void updateForestState() {
		if (p.collisionBox.y > JourneyToTheLostTreasure.HEIGHT - 60) {
			down = false;

		}
		if (p.collisionBox.y < 0) {
			up = false;

		}
		if (p.collisionBox.x < 0) {
			p.setX(800);
			sword.setX(815);

			currentState = CAVE_STATE;
		}
		if (p.collisionBox.x > JourneyToTheLostTreasure.WIDTH - 170) {
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
		}
		o.update();
		o.checkCollision();
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
		if (sword.isFound) {
			sword.draw(g);
		}
		g.setFont(menuFont);
		g.drawString("Forest", JourneyToTheLostTreasure.WIDTH / 3, 50);
		if (caveBoots.isFound()) {
			caveBoots.drawInInv(g);
		}
		if (m.found == false) {
			m.draw(g);
		}
		if (m.isFound()) {

			m.drawInInv(g);
		}
	}

	void drawLagoonState(Graphics g) {
		g.setColor(Color.CYAN);
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
		g.setFont(menuFont);
		g.drawString("Lagoon", JourneyToTheLostTreasure.WIDTH / 3, 50);
		if (sword.isFound) {
			sword.draw(g);
		}
		if (caveBoots.isFound()) {
			caveBoots.drawInInv(g);
		}
		if (m.isFound()) {
			m.drawInInv(g);
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
		g.setFont(menuFont);
		g.drawString("Cave", JourneyToTheLostTreasure.WIDTH / 3, 50);
		if (sword.isFound) {
			sword.draw(g);
		}
		if (caveBoots.isFound == false) {
			caveBoots.draw(g);
		}
		if (caveBoots.isFound()) {
			caveBoots.drawInInv(g);

		}
		if (m.isFound()) {

			m.drawInInv(g);
		}
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
		g.setFont(menuFont);
		g.setColor(Color.white);
		g.drawString("Forest Edge", JourneyToTheLostTreasure.WIDTH / 4, 50);
		if (sword.isFound) {
			sword.draw(g);
		}
		if (caveBoots.isFound()) {
			caveBoots.drawInInv(g);

		}
		if (m.isFound()) {
			// move it to the inventory
			m.drawInInv(g);
		}
	}

	void drawMapState(Graphics g) {
		g.setColor(Color.ORANGE);
		g.fillRect(0, 0, JourneyToTheLostTreasure.WIDTH, JourneyToTheLostTreasure.HEIGHT);
		System.out.println("drawing map");
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
		g.setFont(menuFont);
		g.drawString("Shack", JourneyToTheLostTreasure.WIDTH / 3, 50);
		if (sword.isFound) {

			sword.draw(g);
		}
		if (caveBoots.isFound()) {
			caveBoots.drawInInv(g);
		}
		if (m.isFound()) {
			// move it to the inventory
			m.drawInInv(g);
		}

		repaint();
	}

	void drawPath1State(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, JourneyToTheLostTreasure.WIDTH, JourneyToTheLostTreasure.HEIGHT);
		g.setColor(Color.GRAY);
		p.draw(g);

		b.draw(g);
		g.setColor(Color.GRAY);
		g.fillRect(850, 0, 150, 700);
		g.setFont(inventoryFont);
		g.setColor(Color.WHITE);
		g.drawString("1", 860, 150);
		g.drawString("2", 860, 300);
		g.drawString("3", 860, 450);
		g.drawString("4", 860, 600);
		g.setFont(menuFont);
		g.drawString("Path", JourneyToTheLostTreasure.WIDTH / 3, 50);
		if (sword.isFound) {
			sword.draw(g);
		}
		if (caveBoots.isFound()) {
			caveBoots.drawInInv(g);
		}
		if (m.isFound()) {

			m.drawInInv(g);
		}

	}

	public void paintComponent(Graphics g) {

		if (currentState == MENU_STATE) {
			drawMenuState(g);
		}
		if (currentState == FOREST_STATE) {
			sword.draw(g);
			drawForestState(g);
			updateForestState();

		}
		if (currentState == LAGOON_STATE) {
			updateLagoonState();
			drawLagoonState(g);
		}
		if (currentState == CAVE_STATE) {
			drawCaveState(g);
			updateCaveState();
		}
		if (currentState == SHACK_STATE) {
			drawShackState(g);
			updateShackState();
		}
		if (s.inside) {

			drawInShackState(g);
			updateInShackState();

		}
		if (currentState == PATH1_STATE) {
			updatePath1State();
			drawPath1State(g);

		}
		if (mapOpen) {
			drawMapState(g);
		}
		if (isAttacking) {
			sword.attack(g);
		}

		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (sword.isFound) {
			if (e.getKeyCode() == KeyEvent.VK_SPACE) {
				if (isAttacking == false) {
					isAttacking = true;
				}
			}
		}
		if (caveBoots.isFound()) {
			if (e.getKeyCode() == KeyEvent.VK_2) {
				if (mapOpen == false) {

					if (updatedSpeed == false) {
						p.speed += 3;
						updatedSpeed = true;
					}
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

		} else if (currentState > MENU_STATE) {
			if (e.getKeyCode() == KeyEvent.VK_W) {
				up = true;

			} else if (e.getKeyCode() == KeyEvent.VK_A) {
				left = true;

			} else if (e.getKeyCode() == KeyEvent.VK_S) {
				down = true;

			} else if (e.getKeyCode() == KeyEvent.VK_D) {
				right = true;

			}

		}
		if (currentState > MENU_STATE) {
			if (e.getKeyCode() == KeyEvent.VK_1) {
				if (m.isFound()) {
					mapOpen = true;

				}
			}
		}
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {

		if (e.getKeyCode() == KeyEvent.VK_W) {
			up = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_S) {
			down = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			right = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_A) {
			left = false;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			if (sword.isFound) {
				sword.width=10;
				sword.height=40;
				sword.setY(sword.getY()-33);
				sword.setY(sword.getY() - p.getSpeed());
				isAttacking = false;
				
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
