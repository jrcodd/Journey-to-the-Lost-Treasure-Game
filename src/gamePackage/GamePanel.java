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
	Player p = new Player(100, 500, 20, 60, 100, 5);
	TreasureMap m = new TreasureMap(400, 100, 10, 10, 10, false);
	boolean up = false;
	boolean down = false;
	boolean right = false;
	boolean left = false;
	Font menuFont;
	Font instructionsFont;
	final int MENU_STATE = 0;
	final int FOREST_STATE = 1;
	final int LAGOON_STATE = 2;
	final int CAVE_STATE = 3;
	final int SHACK_STATE = 4;
	final int PATH1_STATE = 5;
	// path2 has bandits
	final int PATH2_STATE = 6;
	final int BAY_STATE = 7;
	final int OCEAN_STATE = 8;
	final int ISLAND_STATE = 9;
	// ocean2 and bay2 are after treasure is found
	final int OCEAN2_STATE = 10;
	final int BAY2_STATE = 11;
	final int CREDITS_STATE = 12;
	int currentState = MENU_STATE;

	GamePanel() {
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

	}

	void drawForestState(Graphics g) {

		g.setColor(Color.GREEN);
		g.fillRect(0, 0, JourneyToTheLostTreasure.WIDTH, JourneyToTheLostTreasure.HEIGHT);
		g.setColor(Color.GRAY);
		p.draw(g);
		if (m.found == false) {
			m.draw(g);
		}

	}

	public void paintComponent(Graphics g) {
		if (currentState == MENU_STATE) {
			drawMenuState(g);
		}
		if (currentState == FOREST_STATE) {
			drawForestState(g);
		}
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		if (currentState == MENU_STATE) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				currentState = FOREST_STATE;

			}

		} else if (currentState == FOREST_STATE) {
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
		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
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
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (up) {

			p.setY(p.getY() - p.getSpeed());
		}
		if (right) {
			p.setX(p.getX() + p.getSpeed());
		}
		if (left) {
			p.setX(p.getX() - p.getSpeed());
		}
		if (down) {
			p.setY(p.getY() + p.getSpeed());
		}

		repaint();
	}

}
