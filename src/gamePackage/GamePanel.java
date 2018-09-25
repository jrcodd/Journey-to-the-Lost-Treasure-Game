package gamePackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		//currentState = MENU_STATE;
	}

	void drawMenuState(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(0, 0, JourneyToTheLostTreasure.WIDTH, JourneyToTheLostTreasure.HEIGHT);
		g.setColor(Color.gray);
		g.setFont(menuFont);
		g.drawString("Journey To The Lost Treasure", JourneyToTheLostTreasure.WIDTH / 7,
				JourneyToTheLostTreasure.HEIGHT / 4);
		g.setFont(instructionsFont);
		g.drawString("Press ENTER to start", JourneyToTheLostTreasure.WIDTH/5,  JourneyToTheLostTreasure.HEIGHT/2);
	}

	void drawForestState(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, JourneyToTheLostTreasure.WIDTH, JourneyToTheLostTreasure.HEIGHT);
	}

	public void paintComponent(Graphics g) {
		if (currentState == MENU_STATE) {
			drawMenuState(g);
		} if (currentState == FOREST_STATE) {
			drawForestState(g);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (currentState == MENU_STATE) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				System.out.println("ENTER was pressed");
				currentState = FOREST_STATE;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	
}
