package group17;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JPanel{
	private Mode mode = new Mode();
	private JFrame window = new JFrame();
	private int width = 700, height = 500;
	
	public MainFrame() {
		window.setSize(width, height);
		window.setTitle("Playing Card");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setVisible(true);
	}
	
	public static void main(String[] args) {
		MainFrame frame = new MainFrame();
		frame.window.add(frame.mode);
		frame.mode.game();
	}
}