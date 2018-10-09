package gamePackage;

import java.awt.Color;
import java.awt.Graphics;

public class HealthPotion extends Game_Object {
	boolean isFound;
	boolean isAdded = false;
	int positionInInv;
	boolean isDrank;

	HealthPotion(int x, int y, int width, int height, int health, boolean isFound) {
		super(x, y, width, height, health);

	}

	void draw(Graphics g) {

		g.setColor(Color.CYAN);
		g.fillRect(x, y, width, height);
	}

	void update() {
		super.update();

	}

	void drawInInv(Graphics g, boolean isEmpty) {

		switch (Object_Manager.inv.size()) {
		case 1:
			if (Object_Manager.inv.contains(Object_Manager.pot) == false) {
				positionInInv = GamePanel.INVENTORY_SLOT2;
				if (isAdded == false) {
					Object_Manager.inv.add(Object_Manager.pot);
					isAdded = true;
				}
			}
			break;
		case 2:
			if (Object_Manager.inv.contains(Object_Manager.pot) == false) {
				positionInInv = GamePanel.INVENTORY_SLOT3;
				if (isAdded == false) {
					Object_Manager.inv.add(Object_Manager.pot);
					isAdded = true;
				}
			}
			break;
		case 3:
			if (Object_Manager.inv.contains(Object_Manager.pot) == false) {
				positionInInv = GamePanel.INVENTORY_SLOT4;
				if (isAdded == false) {
					Object_Manager.inv.add(Object_Manager.pot);
					isAdded = true;
				}
			}

			break;
		default:
			if (Object_Manager.inv.contains(Object_Manager.pot) == false) {

				positionInInv = GamePanel.INVENTORY_SLOT1;

				if (isAdded == false) {
					Object_Manager.inv.add(Object_Manager.pot);
					isAdded = true;
					break;
				}
			}
		}
		if(isEmpty == false) {
		g.setColor(Color.cyan);
		g.fillRect(900, positionInInv, 50, 50);
		}
		else if(isEmpty == true) {
			g.drawRect(900, positionInInv, 50, 50);
		}
		/*
		 * if (Object_Manager.inv.isEmpty()) { g.setColor(Color.cyan); g.fillRect(900,
		 * GamePanel.INVENTORY_SLOT1, 50, 50); if (isAdded == false) {
		 * Object_Manager.inv.add(Object_Manager.pot); isAdded = true; } } else if
		 * (Object_Manager.inv.size() == 1) { g.setColor(Color.cyan); g.fillRect(900,
		 * GamePanel.INVENTORY_SLOT2, 50, 50); if (isAdded == false) {
		 * Object_Manager.inv.add(Object_Manager.pot); isAdded = true; } } else if
		 * (Object_Manager.inv.size() == 2) { g.setColor(Color.cyan); g.fillRect(900,
		 * GamePanel.INVENTORY_SLOT3, 50, 50); if (isAdded == false) {
		 * Object_Manager.inv.add(Object_Manager.pot); isAdded = true; } } else if
		 * (Object_Manager.inv.size() == 3) { g.setColor(Color.cyan); g.fillRect(900,
		 * GamePanel.INVENTORY_SLOT4, 50, 50); if (isAdded == false) {
		 * Object_Manager.inv.add(Object_Manager.pot); isAdded = true; } }
		 */
	}

}
