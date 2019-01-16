package gamePackage;

import java.awt.Color;
import java.awt.Graphics;

public class OldMan extends Game_Object {

	boolean hasTalked;

	OldMan(int x, int y, int width, int height, int health) {
		super(x, y, width, height, health);

	}

	void draw(Graphics g) {
		g.setColor(Color.GRAY);
		g.fillRect(x, y, width, height);
	}

	void talk() {
		Object_Manager.currentMessage1 = "Here is a Ship Repair Kit. Be careful out there traveler.";
		Object_Manager.currentMessage2 = "The Ship Repair kit can be used to heal the ship to full health. It has a cooldown.";
		Object_Manager.currentMessage3 = "";
	}

}
