package utilities;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
	
	private static String[] suit = {"S", "C", "D", "H"}; // ♠, ♣, ♦, ♥
    private static String[] val = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};
	// Create deck of 52 cards
	public ArrayList<Card> createDeck() {
        ArrayList<Card> deck = new ArrayList<Card>();
        for (int i = 0; i < val.length; i++) {
            for (int j = 0; j < suit.length; j++) {
                Card card = new Card(suit[j], val[i]);
                deck.add(card);
            }
        }
        return deck;
    }
	
	// Shuffle the deck randomly
    public ArrayList<Card> shuffleDeck(ArrayList<Card> deck) {
        Random random = new Random();
        for (int i = 0; i < 2 * deck.size(); i++) {
            int j = random.nextInt(deck.size());
            Card currCard = deck.get(i % deck.size());
            Card randomCard = deck.get(j);
            deck.set(i % deck.size(), randomCard);
            deck.set(j, currCard);
        }
        return deck;
    }

    // Distribute cards to players
    public Player[] dealer(ArrayList<Card> deck, int players, int maxCards) {
        Player player[] = new Player[players];
        for(int i = 0; i < players; i++) {
        	player[i] = new Player();
        }
        int idx = 0;
        for (int i = 0; i < maxCards; i++) {
            for (int j = 0; j < players; j++) {
                player[j].add(deck.get(idx));
                idx++;
            }
        }
        for (int i = 0; i < players; i++) {
            player[i].getCards().sort(null);  // Sorts based on compareTo method
        }
        return player;
    }
    
    public static void main(String[] args) {
    	Deck deckObj = new Deck();
        ArrayList<Card> deck = deckObj.createDeck();
        deck = deckObj.shuffleDeck(deck);
        
        Player player = new Player();
        player.add(deck.get(0));
        System.out.println(player.getCard(0));
    }
}
