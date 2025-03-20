package group17;

import java.awt.Graphics;
import java.awt.Graphics2D;

public class GameBaCay extends Game{

	public GameBaCay(String title, int players, int bots) {
		super(title, players, bots);
		// TODO Auto-generated constructor stub
		maxCards = 3;
		newGame(maxCards, playersNum);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		drawStartState();
	}
}
