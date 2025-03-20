package group17;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class GameTLMB extends Game{

	public GameTLMB(String title, int players, int bots) {
		super(title, players, bots);
		// TODO Auto-generated constructor stub
		maxCards = 13;
		newGame(maxCards, playersNum);
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		drawStartState();
	}
}
