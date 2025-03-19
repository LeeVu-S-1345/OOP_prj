package group17;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main extends JPanel{
	private Mode mode = new Mode();
	private JFrame window = new JFrame();
	private int width = 700, height = 500;
	private int widthGame = 900, heightGame = 670;
	private Color background = new Color(4, 191, 29);
	private static int gameState = 0;
	
	public Main() {
		window.setSize(width, height);
		window.setTitle("Playing Card");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setVisible(true);
	}
	
	public static void setGameState(int state) {
		gameState = state;
	}
	
	public void getNewWindow() {
		window.resize(widthGame, heightGame);
		window.setBackground(background);
		window.setTitle(mode.getTypeGame());
	}
	
	public static void main(String[] args) {
		Main frame = new Main();
		frame.window.add(frame.mode);
		frame.mode.game();
	}
}