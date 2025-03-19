package group17;

import java.util.ArrayList;
import java.util.Random;

public class Card {
	private String[] suit = {"S", "C", "D", "H"};
	private String[] val = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
	private String type, rank;
	private String value;
	
	public Card() {
		//default constructor
	}
	
	public Card(String suit, String rank) {
		this.type = suit;
		this.rank = rank;
		value = rank+suit;
		toString();
	}
	
	public String toString(){
        return rank+type;
    }
	
	public String getValue() {
		return value;
	}
	
	public ArrayList<Card> createDeck() {
		ArrayList<Card> deck = new ArrayList<Card> ();
		for(int i = 0; i < val.length; i++) {
			for(int j = 0; j < suit.length; j++) {
				Card card = new Card(suit[j], val[i]);
				deck.add(card);
			}
		}
		return deck;
	}
	
	public ArrayList<Card> shuffleDeck(ArrayList<Card> deck){
		Random random = new Random();
		for(int i = 0; i < 2*deck.size(); i++) {
			int j = random.nextInt(deck.size());
			Card currCard = deck.get(i%deck.size());
			Card randomCard = deck.get(j);
			deck.set(i%deck.size(), randomCard);
			deck.set(j, currCard);
		}
		return deck;
	}
	
	public ArrayList<Card>[] getPlayerCard(ArrayList<Card> deck, int players, int maxCards){
		ArrayList<Card>[] playerCard = new ArrayList[players];
		for(int i = 0; i < players; i++) {
			playerCard[i] = new ArrayList<Card>();
		}
		int cardsNum = 0, idx = 0;
		while(cardsNum < maxCards) {
			for(int i = 0; i < players; i++) {
				playerCard[i].add(deck.get(idx));
				idx++;
			}
			cardsNum++;
		}
		return playerCard;
	}
	
	public static void main(String[] args) {
		Card card = new Card();
		ArrayList<Card> cards = card.shuffleDeck(card.createDeck());
		ArrayList<Card>[] player = card.getPlayerCard(cards, 4, 13);
		for(int i=0; i<4; i++) {
			player[i].sort(null);
			System.out.println(player[i]);
		}
	}
}
