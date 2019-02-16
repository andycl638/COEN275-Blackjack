package blackjack;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.BoxLayout;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BlackjackGui {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BlackjackGui window = new BlackjackGui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public BlackjackGui() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(139, 69, 19));
		frame.setResizable(true);
		frame.setBackground(new Color(0, 128, 0));
		frame.setBounds(100, 100, 600, 419);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel dealerPanel = new JPanel();
		dealerPanel.setBackground(new Color(0, 128, 0));
		dealerPanel.setBounds(6, 6, 588, 190);
		frame.getContentPane().add(dealerPanel);
		dealerPanel.setLayout(null);
		
		JPanel deckPanel = new JPanel();
		deckPanel.setBackground(new Color(0, 128, 0));
		deckPanel.setBounds(506, 21, 73, 107);
		dealerPanel.add(deckPanel);
		
		JLabel deckLbl = new JLabel();
		deckPanel.add(deckLbl);
		deckLbl.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/cards/back.gif")).getImage()));
		
		JLabel dealerLbl = new JLabel("Dealer");
		dealerLbl.setBounds(6, 6, 60, 16);
		dealerPanel.add(dealerLbl);
		
		JPanel dealerHandPanel = new JPanel();
		dealerHandPanel.setBounds(100, 45, 365, 97);
		dealerHandPanel.setBackground(new Color(0, 128, 0));
		dealerPanel.add(dealerHandPanel);
		dealerHandPanel.setLayout(null);
		
		
		
		JLabel card2 = new JLabel();
		card2.setHorizontalAlignment(SwingConstants.TRAILING);
		card2.setBounds(27, 0, 73, 97);

		
		card2.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/cards/qs.gif")).getImage()));
		dealerHandPanel.add(card2);
		
		JLabel card3 = new JLabel();
		card3.setBounds(129, 0, 73, 97);
		card3.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/cards/tc.gif")).getImage()));
		dealerHandPanel.add(card3);
		
		JLabel card4 = new JLabel();
		card4.setBounds(214, 0, 73, 97);
		card4.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/cards/3d.gif")).getImage()));
		dealerHandPanel.add(card4);
		
		JLabel card1 = new JLabel();
		card1.setBounds(0, 0, 73, 97);
		card1.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/cards/js.gif")).getImage()));
		dealerHandPanel.add(card1);
		
		/*JLabel card5 = new JLabel();
		card5.setIcon(new ImageIcon("/Users/andy/COEN275/COEN275-Blackjack/src/Resource/cards/ah.gif"));
		dealerHandPanel.add(card5);*/
		
		JPanel playerPanel = new JPanel();
		playerPanel.setBackground(new Color(0, 128, 0));
		playerPanel.setBounds(6, 201, 588, 190);
		frame.getContentPane().add(playerPanel);
		playerPanel.setLayout(null);
		
		JPanel playerHandPanel = new JPanel();
		playerHandPanel.setBounds(100, 45, 365, 97);
		playerHandPanel.setBackground(new Color(0, 128, 0));
		playerPanel.add(playerHandPanel);
		playerHandPanel.setLayout(new BoxLayout(playerHandPanel, BoxLayout.X_AXIS));
		
		JLabel card1P = new JLabel();
		card1P.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/cards/js.gif")).getImage()));
		playerHandPanel.add(card1P);
		
		JLabel card2P = new JLabel();
		card2P.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/cards/qs.gif")).getImage()));
		playerHandPanel.add(card2P);
		
		JLabel card3P = new JLabel();
		card3P.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/cards/tc.gif")).getImage()));
		playerHandPanel.add(card3P);
		
		JLabel card4P = new JLabel();
		card4P.setIcon(new ImageIcon(new ImageIcon(getClass().getResource("/cards/3d.gif")).getImage()));
		playerHandPanel.add(card4P);
		
		/*JLabel card5P = new JLabel();
		card5P.setIcon(new ImageIcon("/Users/andy/COEN275/COEN275-Blackjack/src/Resource/cards/ah.gif"));
		playerHandPanel.add(card5P);*/
		
		JPanel optionsPanel = new JPanel();
		optionsPanel.setBackground(new Color(0, 128, 0));
		optionsPanel.setBounds(462, 22, 120, 102);
		playerPanel.add(optionsPanel);
		optionsPanel.setLayout(new GridLayout(3, 2, 0, 0));
		
		JButton standBtn = new JButton("Stand");
		standBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.out.println("clicked!");
			}
		});
		standBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		JButton hitBtn = new JButton("Hit");
		hitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("clicked!");
			}
		});
		hitBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		JButton splitBtn = new JButton("Split");
		splitBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		JButton doubleBtn = new JButton("Double");
		doubleBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		JButton surrenderBtn = new JButton("Surrender");
		surrenderBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		optionsPanel.add(standBtn);
		optionsPanel.add(hitBtn);
		optionsPanel.add(splitBtn);
		optionsPanel.add(doubleBtn);
		optionsPanel.add(surrenderBtn);
		
		
		JLabel playerLbl = new JLabel("Player: LoNG Name");
		playerLbl.setBounds(6, 6, 135, 16);
		playerPanel.add(playerLbl);
		
		JLabel balanceLbl = new JLabel("Balance: $100");
		balanceLbl.setBounds(6, 21, 99, 16);
		playerPanel.add(balanceLbl);
		
		JPanel betPanel = new JPanel();
		betPanel.setBackground(new Color(0, 128, 0));
		betPanel.setBounds(6, 82, 78, 102);
		playerPanel.add(betPanel);
		betPanel.setLayout(new GridLayout(0, 2, 0, 0));
		
		JButton oneBtn = new JButton("$1");
		oneBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		JButton fiveBtn = new JButton("$5");
		fiveBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		JButton tenBtn = new JButton("$10");
		tenBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		JButton twentyFiveBtn = new JButton("$25");
		twentyFiveBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		JButton fiftyBtn = new JButton("$50");
		fiftyBtn.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		betPanel.add(oneBtn);
		betPanel.add(fiveBtn);
		betPanel.add(tenBtn);
		betPanel.add(twentyFiveBtn);
		betPanel.add(fiftyBtn);
	}
}
