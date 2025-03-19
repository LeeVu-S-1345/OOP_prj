package group17;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class GameTLMN extends Game implements Runnable{
	Thread thread;

	public GameTLMN(String title, int players, int bots) {
		// TODO Auto-generated constructor stub
		super(title, players, bots);
		deck = host.createDeck();
		maxCards = 13;
		newGame(maxCards, playersNum);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		drawStartState();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(thread != null) {
			repaint();
		}
	}
	
	public void gamestart() {
		thread = new Thread(this);
		thread.start();
	}
}
