package game;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;

import utilities.Card;
import utilities.ControlGame;
import utilities.Player;

public class TienLenMienNam extends ControlGame{

	public static JButton btnPass = new JButton ("Pass");

	public TienLenMienNam(int numPlayers, int numBots) {
		super(numPlayers, numBots);
		setMaxCards(13);
		getPlayerCards();
	}

	@Override
	public MouseAdapter handleInput(int show, int turn) {
		return new MouseAdapter() {
			int numChosen = 0;
			@Override
			public void mouseClicked(MouseEvent e) {
				Player player = getPlayer(turn);
				if(show == 1) {
					for(int i = 0; i < player.getNumCards(); i++) {
						if(mapCard[turn].get(player.getCard(i)).contains(e.getPoint())) {
							if(cardState[turn].get(i) == 0) {
								numChosen++;
								cardState[turn].set(i, 1);
							}
							else if(cardState[turn].get(i) == 1) {
								numChosen--;
								cardState[turn].set(i, 0);
							}
						}
					}
					if(numChosen == 0) {
						btnPlay.setEnabled(false);
						btnPass.setEnabled(true);
					}
					else if(numChosen > 0) {
						int i = 0;
						chosenCard.clear();
						while(i < player.getNumCards()) {
							if(cardState[turn].get(i) == 1) {
								chosenCard.add(player.getCard(i));
							}
							i++;
						}
						//rule();
						btnPass.setEnabled(false);
					}
				}
			}
		};
		
	}
	
	public void setFunctionButtonPlay() {
		btnPlay.addActionListener(e -> {
			int i = 0;
			previousCard = new ArrayList<Card>(chosenCard);
			if(chosenCard.size() != 0) {
				chosenCard.clear();
			}
			while(i < player[getTurn()].getNumCards()) {
				if(cardState[getTurn()].get(i) == 1) {
					cardState[getTurn()].remove(i);
					player[getTurn()].removeCard(i);
				}
				else {
					i++;
				}
			}
			btnPlay.setEnabled(false);
		});
	}
	
	public void setFunctionButtonPass() {
//		skip[turn] = true;
//		numOfSkip++;
//		do{
			setTurn((getTurn() + 1) % getNumPlayers());
//		}while(skip[turn]);
//		if(numOfSkip == playersNum - 1) {
//			newRound();
//			pre.clear();
//			numOfSkip = 0;
//		}
//		show = 0;
		btnPass.setEnabled(false);
	}
	
	@Override
	public JPanel controlPanel() {
		JPanel panel = new JPanel(new GridLayout(2, 1, 5, 5));
		setFunctionButtonPlay();
		setFunctionButtonPass();
		btnPlay.setEnabled(false);
		btnPass.setEnabled(false);
		panel.add(btnPlay);
		panel.add(btnPass);
		JPanel board = new JPanel();
		board.add(panel, BorderLayout.PAGE_START);
		return board;
	}
}
