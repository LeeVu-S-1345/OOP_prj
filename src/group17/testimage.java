package group17;

public class testimage {
	public static void main(String[] args) {
		GameTLMB game = new GameTLMB("Tiến lên miền bắc", 0, 1);
		game.playerCard[0].clear();
		game.playerCard[0].add(new Card("S", "3"));
		game.playerCard[0].add(new Card("C", "3"));
		game.playerCard[0].add(new Card("D", "3"));
		game.playerCard[0].add(new Card("S", "4"));
		game.playerCard[0].add(new Card("S", "5"));
		game.playerCard[0].add(new Card("C", "5"));
		game.playerCard[0].add(new Card("D", "2"));
		
		game.playerCard[1].clear();
		game.playerCard[1].add(new Card("D", "6"));
		game.playerCard[1].add(new Card("H", "6"));
		game.playerCard[1].add(new Card("S", "7"));
		game.playerCard[1].add(new Card("C", "7"));
		game.playerCard[1].add(new Card("S", "8"));
		game.playerCard[1].add(new Card("C", "8"));
		game.playerCard[1].add(new Card("S", "9"));
		game.playerCard[1].add(new Card("C", "9"));
//		game.playerCard[1].add(new Card("S", "10"));
//		game.playerCard[1].add(new Card("C", "10"));
		game.playerCard[1].add(new Card("S", "J"));
//		game.playerCard[1].add(new Card("S", "2"));
//		game.playerCard[1].add(new Card("C", "2"));
		game.gameStart();
	}
}