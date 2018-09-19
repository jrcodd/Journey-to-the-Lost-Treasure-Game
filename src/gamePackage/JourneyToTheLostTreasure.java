package gamePackage;

import javax.swing.JFrame;

public class JourneyToTheLostTreasure {
	static final int HEIGHT = 700;
	static final int WIDTH = 1000;
	JFrame f;

	public static void main(String[] args) {
		JourneyToTheLostTreasure journey = new JourneyToTheLostTreasure();
		journey.setup();
	}

	JourneyToTheLostTreasure() {
		f = new JFrame();
	}

	void setup() {

		f.setSize(WIDTH, HEIGHT);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		GamePanel gp = new GamePanel();
		f.add(gp);

	}
}
