package gamePackage;

import java.awt.Color;
import java.awt.Graphics;

public class SpeedyBoots extends Game_Object {
	boolean isFound;
	boolean isAdded = false;
    int positionInInv;
	SpeedyBoots(int x, int y, int width, int height, int health, boolean isFound) {
		super(x, y, width, height, health);
		this.isFound = isFound;
	}

	int getX() {
		return x;
	}

	int getY() {
		return y;
	}

	int getHeight() {
		return height;
	}

	int getWidth() {
		return width;

	}

	boolean isFound() {
		return isFound;
	}

	void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, width, height);
		g.fillRect(x + 20, y, width, height);
	}

	void drawInInv(Graphics g) {

		switch (Object_Manager.inv.size()) {
		case 1:
			if (Object_Manager.inv.contains(Object_Manager.caveBoots) == false) {
				positionInInv = GamePanel.INVENTORY_SLOT2;
				if (isAdded == false) {
					Object_Manager.inv.add(Object_Manager.caveBoots);
					isAdded = true;
				}
			}
			break;
		case 2:
			if (Object_Manager.inv.contains(Object_Manager.caveBoots) == false) {
				positionInInv = GamePanel.INVENTORY_SLOT3;
				if (isAdded == false) {
					Object_Manager.inv.add(Object_Manager.caveBoots);
					isAdded = true;
				}
			}
			break;
		case 3:
			if (Object_Manager.inv.contains(Object_Manager.caveBoots) == false) {
				positionInInv = GamePanel.INVENTORY_SLOT4;
				if (isAdded == false) {
					Object_Manager.inv.add(Object_Manager.caveBoots);
					isAdded = true;
				}
			}

			break;
		default:
			if (Object_Manager.inv.contains(Object_Manager.caveBoots) == false) {
				positionInInv = GamePanel.INVENTORY_SLOT1;

				if (isAdded == false) {
					Object_Manager.inv.add(Object_Manager.caveBoots);
					isAdded = true;
				}
			}
			break;
          
		}
		g.setColor(Color.black);
		g.fillRect(900, positionInInv, 50, 50);
		/*
		 * if (Object_Manager.inv.isEmpty()) { g.setColor(Color.YELLOW); g.fillRect(900,
		 * GamePanel.INVENTORY_SLOT1, 50, 50);
		 * 
		 * if (isAdded == false) { Object_Manager.inv.add(Object_Manager.caveBoots);
		 * isAdded = true;
		 * 
		 * } } else if (Object_Manager.inv.size() == 1) { g.setColor(Color.YELLOW);
		 * g.fillRect(900, GamePanel.INVENTORY_SLOT2, 50, 50); if (isAdded == false) {
		 * Object_Manager.inv.add(Object_Manager.caveBoots); isAdded = true; } } else if
		 * (Object_Manager.inv.size() == 2) { g.setColor(Color.YELLOW); g.fillRect(900,
		 * GamePanel.INVENTORY_SLOT3, 50, 50); if (isAdded == false) {
		 * Object_Manager.inv.add(Object_Manager.caveBoots); isAdded = true; } } else if
		 * (Object_Manager.inv.size() == 3) { g.setColor(Color.YELLOW); g.fillRect(900,
		 * GamePanel.INVENTORY_SLOT4, 50, 50); if (isAdded == false) {
		 * Object_Manager.inv.add(Object_Manager.caveBoots); isAdded = true; } }
		 */
	}

}
