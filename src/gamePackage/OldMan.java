package gamePackage;

import java.awt.Graphics;

public class OldMan extends Game_Object {

	boolean hasTalked;

	OldMan(int x, int y, int width, int height, int health) {
		super(x, y, width, height, health);

	}

	void draw(Graphics g) {
		g.drawImage(GamePanel.OldMan, x, y, width, height, null);
	}

	void talk() {
		Object_Manager.currentMessage1 = "Here is a Ship Repair Kit. Be careful out there traveler.";
		Object_Manager.currentMessage2 = "The Ship Repair kit can be used to heal the ship to full health.";
		Object_Manager.currentMessage3 = "It has a cooldown.";
	}

}
