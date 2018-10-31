package gamePackage;

import java.awt.Color;
import java.awt.Graphics;

public class Shack  extends Game_Object{
	boolean inside = false;
Shack(int x, int y, int width, int height, int health, boolean inside){
	super(x, y, width, height, health);
	this.inside = inside;
}
	void draw(Graphics g) {
		g.setColor(Color.DARK_GRAY);
		g.fillRect(530, 20, 300, 300);
	}


}
