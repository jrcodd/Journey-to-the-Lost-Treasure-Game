package gamePackage;

import javax.swing.JFrame;

public class JourneyToTheLostTreasure {
	static final int HEIGHT = 700;
	static final int WIDTH = 1000;

	JFrame f;
	GamePanel gp;

	public static void main(String[] args) {
		// JourneyToTheLostTreasure journey = new JourneyToTheLostTreasure();

		new JourneyToTheLostTreasure();

		// journey.setup();
	}

	JourneyToTheLostTreasure() {
		f = new JFrame();
		gp = new GamePanel();
		setup();

	}

	void setup() {

		f.add(gp);
		f.addKeyListener(gp);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(WIDTH + 18, HEIGHT + 247);
		f.setResizable(false);
	}

}
