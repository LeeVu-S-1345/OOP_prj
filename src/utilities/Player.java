package utilities;

import java.util.ArrayList;
import utilities.Card;

public class Player {
	
	private String name = "vu";
	private ArrayList<Card> cards;
	private boolean isPass = false;
	
	public Player() {
		cards = new ArrayList<Card>();
	}
	
	public int getNumCards() {
		return cards.size();
	}
	
	public void removeCard(int i) {
		cards.remove(i);
	}

	public String getName() {
		return name;
	}
	
	public Card getCard(int i) {
		return cards.get(i);
	}

	public boolean isPass() {
		return isPass;
	}

	public void setPass(boolean isPass) {
		this.isPass = isPass;
	}
	
	public void add(Card c) {
		cards.add(c);
	}
	
	public ArrayList<Card> getCards(){
		return cards;
	}
}
