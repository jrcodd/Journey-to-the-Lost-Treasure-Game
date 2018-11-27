package gamePackage;

import java.awt.Graphics;

public class ShipRepairKit extends Game_Object {
	boolean isFound;
	boolean isUsed = false;
	boolean isAdded = false;
	int positionInInv;
	int timeUntilNextUse = (GamePanel.fps)*30;

	ShipRepairKit(int x, int y, int width, int height, int health, boolean found) {
		super(x, y, width, height, health);
		this.isFound = found;
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
			if (Object_Manager.inv.contains(Object_Manager.kit) == false) {
				positionInInv = GamePanel.INVENTORY_SLOT2;
				if (isAdded == false) {
					Object_Manager.inv.add(Object_Manager.kit);
					isAdded = true;
				}
			}
			break;
		case 2:
			if (Object_Manager.inv.contains(Object_Manager.kit) == false) {
				positionInInv = GamePanel.INVENTORY_SLOT3;
				if (isAdded == false) {
					Object_Manager.inv.add(Object_Manager.kit);
					isAdded = true;
				}
			}

			break;
		case 3:
			if (Object_Manager.inv.contains(Object_Manager.kit) == false) {
				positionInInv = GamePanel.INVENTORY_SLOT4;
				if (isAdded == false) {
					Object_Manager.inv.add(Object_Manager.kit);
					isAdded = true;
				}
			}

			break;
		default:
			if (Object_Manager.inv.contains(Object_Manager.kit) == false) {
				positionInInv = GamePanel.INVENTORY_SLOT1;

				if (isAdded == false) {
					Object_Manager.inv.add(Object_Manager.kit);
					isAdded = true;
				}
			}
			break;
		}

		g.drawImage(GamePanel.RepairKitImg, 900, positionInInv, 50, 50, null);
		g.fillOval((900 + (50/ 2)) - ((timeUntilNextUse /20) / 2), positionInInv + 50, timeUntilNextUse/20, 5);
		/*
		 * if (Object_Manager.inv.isEmpty()) { g.setColor(Color.yellow); g.fillRect(900,
		 * GamePanel.INVENTORY_SLOT1, 50, 50); if (isAdded == false) {
		 * Object_Manager.inv.add(Object_Manager.kit); isAdded = true; } } else if
		 * (Object_Manager.inv.size() == 1) { g.setColor(Color.yellow); g.fillRect(900,
		 * GamePanel.INVENTORY_SLOT2, 50, 50); if (isAdded == false) {
		 * Object_Manager.inv.add(Object_Manager.kit); isAdded = true; } } else if
		 * (Object_Manager.inv.size() == 2) { g.setColor(Color.yellow); g.fillRect(900,
		 * GamePanel.INVENTORY_SLOT3, 50, 50); if (isAdded == false) {
		 * Object_Manager.inv.add(Object_Manager.kit); isAdded = true; } } else if
		 * (Object_Manager.inv.size() == 3) { g.setColor(Color.yellow); g.fillRect(900,
		 * GamePanel.INVENTORY_SLOT4, 50, 50);
		 * Object_Manager.inv.add(Object_Manager.kit); isAdded = true; }
		 */
	}
}
