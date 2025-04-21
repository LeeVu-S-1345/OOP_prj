package ui;

import javax.swing.*;

import game.TienLenMienNam;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import utilities.Card;
import utilities.ControlGame;
import utilities.Deck;
import utilities.Player;

public class GraphicalMode extends JPanel implements GameUI {
	
    private static final int RAISE_HEIGHT = HEIGHT_CARD/7; // Vertical raise amount
    private static final int SHIFT_HORIZONTAL = WIDTH_CARD/3; // Horizontal shift amount
    private Image backgroundImage;
    private Map<Card, Rectangle>[] mapCard;
    private ArrayList<Integer>[] cardState;
    private Graphics2D g2;
    private int turn = 0;
    private ControlGame ctrl = new TienLenMienNam(4, 0);
    private Player player;

    public GraphicalMode() {
    	backgroundImage = new ImageIcon(getClass().getResource("/images/table_background.jpg")).getImage();
    	ctrl.getPlayerCards();
    	player = ctrl.getPlayer(0);
    	cardState = ctrl.setCardButton();
    	mapCard = ctrl.setMapCard();
        this.addMouseListener(ctrl.handleInput(1, turn));
    }
    
    public ImageIcon getImageCard(String image) {
        String filepath = "/images/" + image + ".png";
        return new ImageIcon(getClass().getResource(filepath));
    }
    
    @Override
	public void drawCardsVertical(Player player, int x) {
    	int y = (player.getNumCards() - 1)*OFFSET_Y + HEIGHT_CARD;
		y = getHeight()/2 - y/2;
		for(int i = 0; i < player.getCards().size(); i++) {
			if(cardState[turn].get(i) == 1) {
				g2.drawImage(getImageCard(player.getCard(i).getValue()).getImage(), x + (turn - 2)*SHIFT_HORIZONTAL, y+i*OFFSET_Y, WIDTH_CARD, HEIGHT_CARD, null);
				if(i < player.getCards().size() - 1) {
					mapCard[turn].put(player.getCard(i), new Rectangle(x + (turn - 2)*SHIFT_HORIZONTAL, y+i*OFFSET_Y, WIDTH_CARD, OFFSET_Y));
				}
				else {
					mapCard[turn].put(player.getCard(i), new Rectangle(x + (turn - 2)*SHIFT_HORIZONTAL, y+i*OFFSET_Y, WIDTH_CARD, HEIGHT_CARD));
				}
			}
			else{
				g2.drawImage(getImageCard(player.getCard(i).getValue()).getImage(), x, y+i*OFFSET_Y, WIDTH_CARD, HEIGHT_CARD, null);
				if(i < player.getCards().size() - 1) {
					mapCard[turn].put(player.getCard(i), new Rectangle(x, y+i*OFFSET_Y, WIDTH_CARD, OFFSET_Y));
				}
				else {
					mapCard[turn].put(player.getCard(i), new Rectangle(x, y+i*OFFSET_Y, WIDTH_CARD, HEIGHT_CARD));
				}
			}
		}
	}

	@Override
	public void drawCardsHorizontal(Player player, int y) {
		int x = (player.getNumCards() - 1)*OFFSET_X + WIDTH_CARD;
		x = getWidth()/2 - x/2;
		for(int i = 0; i < player.getCards().size(); i++) {
			if(cardState[turn].get(i) == 1) {
				g2.drawImage(getImageCard(player.getCard(i).getValue()).getImage(), x+i*OFFSET_X, y - RAISE_HEIGHT, WIDTH_CARD, HEIGHT_CARD, null);
				if(i < player.getCards().size() - 1) {
					mapCard[turn].put(player.getCard(i), new Rectangle(x+i*OFFSET_X, y - RAISE_HEIGHT, OFFSET_X, HEIGHT_CARD));
				}
				else {
					mapCard[turn].put(player.getCard(i), new Rectangle(x+i*OFFSET_X, y - RAISE_HEIGHT, WIDTH_CARD, HEIGHT_CARD));
				}
			}
			else{
				g2.drawImage(getImageCard(player.getCard(i).getValue()).getImage(), x+i*OFFSET_X, y, WIDTH_CARD, HEIGHT_CARD, null);
				if(i < player.getCards().size() - 1) {
					mapCard[turn].put(player.getCard(i), new Rectangle(x+i*OFFSET_X, y, OFFSET_X, HEIGHT_CARD));
				}
				else {
					mapCard[turn].put(player.getCard(i), new Rectangle(x+i*OFFSET_X, y, WIDTH_CARD, HEIGHT_CARD));
				}
			}
		}
	}

	@Override
	public void drawChosenCards(ArrayList<Card> chosenCard) {
		int x = (chosenCard.size() - 1)*OFFSET_X + WIDTH_CARD;
		x = getWidth()/2 - x/2;
		int y = (chosenCard.size() - 1)*OFFSET_Y + HEIGHT_CARD;
		y = getHeight()/2 - y/2;
		for(int i = 0; i < chosenCard.size(); i++) {
			g2.drawImage(getImageCard(chosenCard.get(i).getValue()).getImage(), x+i*OFFSET_X, y, WIDTH_CARD, HEIGHT_CARD, null);
		}
	}

	@Override
	public void drawHidingStateVertical(Player player, int x) {
		// Length of player's name
		int length = (int)g2.getFontMetrics(getFont().deriveFont(35F)).stringWidth(player.getName());
		int w = (WIDTH_CARD > length ? WIDTH_CARD : length) + 50;
		int h = 210;
		int y = getHeight()/2 - h/2;
		g2.setColor(new Color(0, 0, 0, 130));
		g2.fillRoundRect(x, y, w, h, 35, 35);
		g2.setColor(new Color(255, 255, 255));
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x, y, w, h, 25, 25);
		// Draw player's name
		g2.setFont(getFont().deriveFont(35F));
		g2.drawString(player.getName(), (w-length)/2+x, y+35);
		// Draw back card
		g2.drawImage(getImageCard("cardback").getImage(), x+w/2-WIDTH_CARD/2, y+45, WIDTH_CARD, HEIGHT_CARD, null);
		// Draw the number of cards remaining
		g2.setFont(getFont().deriveFont(28F));
		length = (int)g2.getFontMetrics(getFont().deriveFont(28F)).stringWidth(player.getNumCards()+"");
		g2.drawString(player.getNumCards()+"", w/2-length/2+x, y+h/2);
	}

	@Override
	public void drawHidingStateHorizontal(Player player, int y) {
		// length of player's name
		int length = (int)g2.getFontMetrics(getFont().deriveFont(35F)).stringWidth(player.getName());
		int w = length + WIDTH_CARD + 60;
		int h = HEIGHT_CARD + 30;
		int x = getWidth()/2 - w/2;
		g2.setColor(new Color(0, 0, 0, 130));
		g2.fillRoundRect(x, y, w, h, 35, 35);
		g2.setColor(new Color(255, 255, 255));
		g2.setStroke(new BasicStroke(5));
		g2.drawRoundRect(x, y, w, h, 25, 25);
		// Draw player's name
		g2.setFont(getFont().deriveFont(35F));
		g2.drawString(player.getName(), (w+15+WIDTH_CARD-length)/2+x, y+h/2);
		// Draw back card
		g2.drawImage(getImageCard("cardback").getImage(), x+15, y+(h-HEIGHT_CARD)/2, WIDTH_CARD, HEIGHT_CARD, null);
		// Draw the number of cards remaining
		g2.setFont(getFont().deriveFont(28F));
		length = (int)g2.getFontMetrics(getFont().deriveFont(28F)).stringWidth(player.getNumCards()+"");
		g2.drawString(player.getNumCards()+"", WIDTH_CARD/2-length/2+x+15, y+h/2);
	}
	
	public void drawHideState(Player player, int x, int y) {
		drawHidingStateVertical(player, x);
        drawHidingStateHorizontal(player, y);
        int w = (int)g2.getFontMetrics(getFont().deriveFont(35F)).stringWidth(player.getName());
        w = (WIDTH_CARD > w ? WIDTH_CARD : w) + 75;
        drawHidingStateVertical(player, getWidth()-w);
        drawHidingStateHorizontal(player, getHeight()-195);
	}

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2 = (Graphics2D) g;
        // Vẽ hình nền
        g2.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        // Hiển thị thông tin về người chơi hiện tại
        g2.setColor(Color.WHITE);  // Thay đổi màu chữ để thấy rõ trên nền
        g2.setFont(new Font("Arial", Font.BOLD, 20));
        g2.drawString("Current turn: " + 1, 20, 20);
        
        //drawHideState(player, 20, 20);
        drawCardsHorizontal(player, 25);
        drawCardsVertical(player, getWidth() - WIDTH_CARD - 30);
        drawCardsHorizontal(player, getHeight() - HEIGHT_CARD - 25);
        drawCardsVertical(player, 30);
    }
    
    // Phương thức vẽ highlight cho người chơi hiện tại
    private void drawPlayerHighlight(Player player) {
        g2.setColor(new Color(255, 255, 0, 100)); // Màu vàng nhạt, semi-transparent
        
        int x, y, width, height;
        switch(turn) {
            case 0: // Người chơi trên cùng
            	x = (player.getNumCards() - 1)*OFFSET_X + WIDTH_CARD;
        		x = getWidth()/2 - x/2 - 25;
                y = 20;
                width = (player.getNumCards() - 1)*OFFSET_X + WIDTH_CARD + 50;
                height = HEIGHT_CARD + 10;
                break;
            case 1: // Người chơi bên trái
            	x = getWidth() - WIDTH_CARD - 40;
                y = (player.getNumCards() - 1)*OFFSET_Y + HEIGHT_CARD;
        		y = getHeight()/2 - y/2 - 25;
                width = WIDTH_CARD + 20;
                height = (player.getNumCards() - 1)*OFFSET_Y + HEIGHT_CARD + 50;
                break;
            case 2: // Người chơi dưới cùng
            	x = (player.getNumCards() - 1)*OFFSET_X + WIDTH_CARD;
        		x = getWidth()/2 - x/2 - 25;
                y = getHeight() - HEIGHT_CARD - 30;
                width = (player.getNumCards() - 1)*OFFSET_X + WIDTH_CARD + 50;
                height = HEIGHT_CARD + 10;
                break;
            case 3: // Người chơi dưới cùng
                x = 20;
                y = (player.getNumCards() - 1)*OFFSET_Y + HEIGHT_CARD;
        		y = getHeight()/2 - y/2 - 25;
                width = WIDTH_CARD + 20;
                height = (player.getNumCards() - 1)*OFFSET_Y + HEIGHT_CARD + 50;
                break;
            default:
                return;
        }
        
        // Vẽ hình chữ nhật highlight
        g2.fillRoundRect(x, y, width, height, 20, 20);
        
        // Vẽ viền cho highlight
        g2.setColor(Color.YELLOW);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(x, y, width, height, 20, 20);
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tiến Lên Miền Nam");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLocationRelativeTo(null);

        GraphicalMode graphicPanel = new GraphicalMode();
        
        frame.setLayout(new BorderLayout());
        frame.add(graphicPanel.ctrl.controlPanel(), BorderLayout.EAST);
        frame.add(graphicPanel, BorderLayout.CENTER);
        frame.setSize(1000, 650);
        frame.setVisible(true);
    }
}
