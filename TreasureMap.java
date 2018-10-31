package gamePackage;

import java.awt.Color;
import java.awt.Graphics;

public class TreasureMap extends Game_Object {
	boolean isFound;
	boolean isAdded = false;
	int positionInInv;

	TreasureMap(int x, int y, int width, int height, int health, boolean found) {
		super(x, y, width, height, health);
		this.isFound = found;
	}

	void draw(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, width, height);

	}

	void update() {
		super.update();
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

	void drawInInv(Graphics g) {

		switch (Object_Manager.inv.size()) {
		case 1:
			if (Object_Manager.inv.contains(Object_Manager.m) == false) {
				positionInInv = GamePanel.INVENTORY_SLOT2;
				if (isAdded == false) {
					Object_Manager.inv.add(Object_Manager.m);
					isAdded = true;
				}
			}
			break;
		case 2:
			if (Object_Manager.inv.contains(Object_Manager.m) == false) {
				positionInInv = GamePanel.INVENTORY_SLOT3;
				if (isAdded == false) {
					Object_Manager.inv.add(Object_Manager.m);
					isAdded = true;
				}
			}

			break;
		case 3:
			if (Object_Manager.inv.contains(Object_Manager.m) == false) {
				positionInInv = GamePanel.INVENTORY_SLOT4;
				if (isAdded == false) {
					Object_Manager.inv.add(Object_Manager.m);
					isAdded = true;
				}
			}

			break;
		default:
			if (Object_Manager.inv.contains(Object_Manager.m) == false) {
				positionInInv = GamePanel.INVENTORY_SLOT1;
                  
				if (isAdded == false) {
					Object_Manager.inv.add(Object_Manager.m);
					isAdded = true;
				}
			}
			break;
		}
		g.setColor(Color.yellow);
		g.fillRect(900, positionInInv, 50, 50);
		/*
		 * if (Object_Manager.inv.isEmpty()) { g.setColor(Color.yellow); g.fillRect(900,
		 * GamePanel.INVENTORY_SLOT1, 50, 50); if (isAdded == false) {
		 * Object_Manager.inv.add(Object_Manager.m); isAdded = true; } } else if
		 * (Object_Manager.inv.size() == 1) { g.setColor(Color.yellow); g.fillRect(900,
		 * GamePanel.INVENTORY_SLOT2, 50, 50); if (isAdded == false) {
		 * Object_Manager.inv.add(Object_Manager.m); isAdded = true; } } else if
		 * (Object_Manager.inv.size() == 2) { g.setColor(Color.yellow); g.fillRect(900,
		 * GamePanel.INVENTORY_SLOT3, 50, 50); if (isAdded == false) {
		 * Object_Manager.inv.add(Object_Manager.m); isAdded = true; } } else if
		 * (Object_Manager.inv.size() == 3) { g.setColor(Color.yellow); g.fillRect(900,
		 * GamePanel.INVENTORY_SLOT4, 50, 50); Object_Manager.inv.add(Object_Manager.m);
		 * isAdded = true; }
		 */
	}
}
