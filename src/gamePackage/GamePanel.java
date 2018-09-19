package gamePackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Font menuFont;
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

	GamePanel() {
		menuFont = new Font("Arial", Font.BOLD, 50);
	}

	public void paintComponent(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(0, 0, JourneyToTheLostTreasure.WIDTH, JourneyToTheLostTreasure.HEIGHT);
	}
}
