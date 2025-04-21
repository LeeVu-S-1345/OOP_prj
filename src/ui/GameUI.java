package ui;

import java.util.ArrayList;
import utilities.Card;
import utilities.Player;

public interface GameUI {
	
	public static final int WIDTH_WINDOW = 1000;
	public static final int HEIGHT_WINDOW = 650;
	public static final int WIDTH_CARD = 89;
	public static final int HEIGHT_CARD = 140;
	public static final int OFFSET_X = WIDTH_CARD/3;
    public static final int OFFSET_Y = HEIGHT_CARD/5;
	
	public void drawCardsVertical(Player player, int x);
	public void drawCardsHorizontal(Player player, int y);
	public void drawChosenCards(ArrayList<Card> chosenCard);
	public void drawHidingStateVertical(Player player, int x);
	public void drawHidingStateHorizontal(Player player, int y);
}
