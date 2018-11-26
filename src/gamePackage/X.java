package gamePackage;

import java.awt.Graphics;

public class X extends Game_Object{
boolean hasGivenTreasure;
	X(int x, int y, int width, int height, int health) {
		super(x, y, width, height, health);
	}
void draw(Graphics g) {
	g.drawImage(GamePanel.treasureMarker, x, y, width, height, null);	
}
void update() {
	super.update();
}
}
