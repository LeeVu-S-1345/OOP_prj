package group17;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class GameTLMN extends Game implements MouseListener, Runnable{
	Thread thread;
	private ArrayList<Card> chosenCard = new ArrayList<Card>();

	public GameTLMN(String title, int players, int bots) {
		// TODO Auto-generated constructor stub
		super(title, players, bots);
		maxCards = 13;
		newGame(maxCards, playersNum);
		this.addMouseListener(this);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		if(show == 0) {
			drawStartState();
		}
		else if(show == 1) {
			if(turn == 1) {
				paintCardsHorizontal(playerCard[0], width/2-(12*disX+cardWidth)/2, 25, cardWidth, cardHeight);
				drawStatePlaying();
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(btn.get(turn-1).contains(e.getPoint())) {
			show = 1;
			
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(thread != null) {
			repaint();
		}
	}
	
	public void gameStart() {
		thread = new Thread(this);
		thread.start();
	}
}
