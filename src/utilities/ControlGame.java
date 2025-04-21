package utilities;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JPanel;
import utilities.Card;

public abstract class ControlGame extends JPanel {
	
	public static JButton btnPlay = new JButton("Play");
	private int numPlayers;
	private int numBots;
	private int mode;
	private int maxCards;
	private int turn = 0;
	protected Player[] player;
	protected Map<Card, Rectangle>[] mapCard;
    protected ArrayList<Integer>[] cardState;
    protected ArrayList<Card> chosenCard = new ArrayList<Card>();
    protected ArrayList<Card> previousCard = new ArrayList<Card>();
    
    public ControlGame(int numPlayers, int numBots) {
    	this.numPlayers = numPlayers;
    	this.numBots = numBots;
    	setCardButton();
    	setMapCard();
    }
	
	public ArrayList<Integer>[] setCardButton() {
		ArrayList<Integer>[] cardState = new ArrayList[numPlayers];
		for(int i = 0; i < numPlayers; i++) {
			cardState[i] = new ArrayList<Integer>();
			for(int j = 0; j < maxCards; j++) {
				cardState[i].add(0);
			}
		}
		return cardState;
	}
	
	public Map<Card, Rectangle>[] setMapCard(){
		Map<Card, Rectangle>[] mapCard = new HashMap[numPlayers];
		for(int i = 0; i < numPlayers; i++) {
			mapCard[i] = new HashMap<Card, Rectangle>();
		}
		return mapCard;
	}
	
	public void getPlayerCards() {
		Deck deckObj = new Deck();
        ArrayList<Card> deck = deckObj.createDeck();
        deck = deckObj.shuffleDeck(deck);
        player = deckObj.dealer(deck, numPlayers + numBots, maxCards);
	}
	
	public Player getPlayer(int turn) {
		return player[turn];
	}
	
	public void deleteChosenCards(){
		int i = 0;
		while(i < player[turn].getCards().size()) {
			if(cardState[turn].get(i) == 1) {
				cardState[turn].remove(i);
				player[turn].getCards().remove(i);
				mapCard[turn].remove(player[i].getCard(i));
			}
			else {
				i++;
			}
		}
	}
	
	public int getTurn() {
		return turn;
	}
	
	public void setTurn(int turn) {
		this.turn = turn;
	}
	
	public int getNumPlayers() {
		return numPlayers;
	}
	
	public void setMaxCards(int numCards) {
		this.maxCards = numCards;
	}
	
	public abstract MouseAdapter handleInput(int show, int turn);
	public abstract JPanel controlPanel();
}