package group17;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

public class GameTLMN extends Game implements MouseListener, Runnable{
	private Thread thread;
	private ArrayList<Card> chosenCard = new ArrayList<Card>();
	private JButton btnPlay = new JButton("Play");
	private JButton btnSkip = new JButton("SKip");
	private JButton btnHide = new JButton("Hide");

	public GameTLMN(String title, int players, int bots) {
		// TODO Auto-generated constructor stub
		super(title, players, bots);
		maxCards = 13;
		newGame(maxCards, playersNum);
		setCardButton();
		this.addMouseListener(this);
		window.add(setPanelPlay(), BorderLayout.EAST);
	}
	
	public JPanel setPanelPlay() {
		JPanel panel = new JPanel(new GridLayout(3, 1, 5, 5));
		btnPlay.setActionCommand("Play");
		btnPlay.addActionListener(this);
		btnPlay.setEnabled(false);
		
		btnSkip.setActionCommand("Skip");
		btnSkip.addActionListener(this);
		btnSkip.setEnabled(false);

		btnHide.setActionCommand("Hide");
		btnHide.addActionListener(this);
		panel.add(btnPlay);
		panel.add(btnSkip);
		panel.add(btnHide);
		JPanel board = new JPanel();
		board.add(panel, BorderLayout.PAGE_START);
		return board;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		if(show == 0) {
			drawStartState();
			btnHide.setEnabled(false);
		}
		else if(show == 1) {
			if(turn == 0) {
				paintCardsHorizontal(playerCard[0], 0, width/2-(12*disX+cardWidth)/2, 25, cardWidth, cardHeight);
				drawStatePlaying();
			}
			else if(turn == 1 && turn <= players) {
				paintCardsVertical(playerCard[1], 1, width - (cardWidth + 95), height/2-(12*disY+cardHeight)/2, cardWidth, cardHeight);
				drawStatePlaying();
			}
			else if(turn == 2 && turn <= players) {
				paintCardsHorizontal(playerCard[2], 2, width/2-(12*disX+cardWidth)/2, height - (cardHeight+50), cardWidth, cardHeight);
				drawStatePlaying();
			}
			else if(turn == 3 && turn <= players) {
				paintCardsVertical(playerCard[3], 3, 30, height/2-(12*disY+cardHeight)/2, cardWidth, cardHeight);
				drawStatePlaying();
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(btn.get(turn).contains(e.getPoint())) {
			show = 1;
			btnSkip.setEnabled(true);
		}
		else if(show == 1) {
			for(int i = 0; i < playerCard[turn].size(); i++) {
				if(mapCard[turn].get(playerCard[turn].get(i)).contains(e.getPoint())) {
					if(cardState[turn].get(i) == 0) {
						cardState[turn].set(i, 1);
						if(turn == 0 || turn == 2) {
							mapCard[turn].get(playerCard[turn].get(i)).translate(0, -20);
						}
						else if(turn == 1 || turn == 3) {
							mapCard[turn].get(playerCard[turn].get(i)).translate(-20, 0);
						}
					}
					else if(cardState[turn].get(i) == 1) {
						cardState[turn].set(i, 0);
						if(turn == 0 || turn == 2) {
							mapCard[turn].get(playerCard[turn].get(i)).translate(0, 20);
						}
						else if(turn == 1 || turn == 3) {
							mapCard[turn].get(playerCard[turn].get(i)).translate(20, 0);
						}
					}
				}
			}
			int sum = 0;
			for(int i = 0; i < cardState[turn].size(); i++) {
				sum += cardState[turn].get(i);
				if(sum > 0) {
					btnPlay.setEnabled(true);
					btnSkip.setEnabled(false);
					break;
				}
			}
			if(sum == 0) {
				btnPlay.setEnabled(false);
				btnSkip.setEnabled(true);
			}
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();
		if(cmd.equals("Play")) {
			btnHide.setEnabled(true);
			int i = 0;
			while(i < playerCard[turn].size()) {
				if(cardState[turn].get(i) == 1) {
					cardState[turn].remove(i);
					playerCard[turn].remove(i);
				}
				else {
					i++;
				}
			}
			btnPlay.setEnabled(false);
		}
		else if(cmd.equals("Skip")) {
			btnHide.setEnabled(true);
			turn = (turn + 1) % playersNum;
			show = 0;
			btnSkip.setEnabled(false);
		}
		else if(cmd.equals("Hide")) {
			turn = (turn + 1) % playersNum;
			show = 0;
		}
	}
	
	public void gameStart() {
		thread = new Thread(this);
		thread.start();
	}
}
