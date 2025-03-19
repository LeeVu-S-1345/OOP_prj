package group17;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JPanel implements MouseListener, Runnable{
	private JFrame window = new JFrame();
	private int height = 670, width = 900;
	private Color background = new Color(4, 191, 29);
	private int cardHeight = 140, cardWidth = 89;
	private int disX = 30, disY = 30;
	Graphics2D g2;
	private Map<Card, Rectangle> mapCard = new HashMap<Card, Rectangle>();
	Card selected;
	private ArrayList<Card> ply = new ArrayList<Card>();
	
	public Game(String title) {
		window.setTitle(title);
		window.setSize(width, height);
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
		this.setBackground(background);
		window.add(this);
	}
	
	public void getCollectionCards() {
		ply.add(new Card("H", "K"));
		ply.add(new Card("S", "10"));
		ply.add(new Card("D", "A"));
	}
	
	public ImageIcon getImageCard(String image) {
		String filepath = "/images/" + image + ".png";
		return new ImageIcon(getClass().getResource(filepath));
	}
	
	public void paintCard(Card card, int x, int y, int w, int h) {
		mapCard.put(card, new Rectangle(x, y, w, h));
		g2.drawImage(getImageCard(card.getValue()).getImage(), x, y, w, h, null);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;
//		for(int i = 0; i < ply.size(); i++) {
//			paintCard(ply.get(i), 170 + disX*i, 480, cardWidth, cardHeight);
//		}
//		
//		for(int i = 0; i < 13; i++) {
//			g.drawImage(getImageCard("3C").getImage(), 210+disX*i, 30, cardWidth, cardHeight, null);
//		}
//		
//		for(int i = 0; i < 13; i++) {
//			g.drawImage(getImageCard("3C").getImage(), 20, 20+disY*i, cardWidth, cardHeight, null);
//		}
		drawPlayerPanelHorizontal(10, 320, 20, 250, 175, "Player 1");
		drawPlayerPanelHorizontal(13, 320, 445, 250, 175, "Bot 1");
		drawPlayerPanelVertical(8, 15, 180, 120, 270, "Player 3");
		drawPlayerPanelVertical(8, 750, 180, 120, 270, "Player 3");
	}
	
	public void drawPlayerPanelHorizontal(int cardsNum, int x, int y, int w, int h, String player) {
		drawButton(x, y, w, h);
		g2.drawImage(getImageCard("back").getImage(), x+10, y+15, cardWidth, cardHeight, null);
		g2.setFont(getFont().deriveFont(28F));
		int length = (int)g2.getFontMetrics().getStringBounds(player, g2).getWidth();
		g2.drawString(player, x+(w+cardWidth+10)/2-length/2, y+40);
		drawButton(x+125, y+60, 80, 30, "show", 20);
		g2.setFont(getFont().deriveFont(32F));
		g2.drawString(Integer.toString(cardsNum), x+150, y+145);
	}
	
	public void drawPlayerPanelVertical(int cardsNum, int x, int y, int w, int h, String player) {
		drawButton(x, y, w, h);
		g2.drawImage(getImageCard("back").getImage(), x+14, y+120, cardWidth, cardHeight, null);
		g2.setFont(getFont().deriveFont(28F));
		int length = (int)g2.getFontMetrics().getStringBounds(player, g2).getWidth();
		g2.drawString(player, w/2-length/2+x, y+30);
		drawButton(x+20, y+45, 80, 30, "show", 20);
		g2.setFont(getFont().deriveFont(32F));
		g2.drawString(Integer.toString(cardsNum), x+50, y+110);
	}
	
	public void drawButton(int x, int y, int w, int h) {
		g2.setColor(new Color(0, 0, 0, 130));
		g2.fillRoundRect(x, y, w, h, 35, 35);
		
		g2.setColor(new Color(255, 255, 255));
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x, y, w, h, 25, 25);
	}
	
	public void drawButton(int x, int y, int w, int h, String btn, float size) {
		g2.setColor(new Color(0, 0, 0, 130));
		g2.fillRoundRect(x, y, w, h, 0, 0);
		
		g2.setColor(new Color(255, 255, 255));
		g2.setStroke(new BasicStroke(3));
		g2.drawRoundRect(x, y, w, h, 0, 0);
		g2.setFont(getFont().deriveFont(size));
		g2.drawString(btn, x+18, y+21);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		for(Card card : ply) {
			Rectangle bound = mapCard.get(card);
			if(bound.contains(e.getPoint())) {
				selected = card;
				break;
			}
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}