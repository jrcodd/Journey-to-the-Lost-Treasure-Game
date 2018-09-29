package gamePackage;

import java.awt.Color;
import java.awt.Graphics;

public class SpeedyBoots extends Game_Object{
	boolean isFound;

SpeedyBoots(int x, int y, int width, int height, int health, boolean isFound){
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
	g.fillRect(x+20, y, width, height);
}
void drawInInv(Graphics g) {
	g.setColor(Color.YELLOW);
	g.fillRect(900, 350, width, height);
	g.fillRect(920, 350, width, height);
	
}

}
