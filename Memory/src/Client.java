import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.Timer;

public class Client extends JFrame {
	private static final long serialVersionUID = 1L;
	private Socket socket = null;
	private final int port = 3345;
	private JMenuItem newGame = new JMenuItem("Новая игра");
	private JMenuItem easy = new JMenuItem("Новичок");
	private JMenuItem medium = new JMenuItem("Любитель");
	private JMenuItem hard = new JMenuItem("Профессионал");
	private JMenuItem special = new JMenuItem("Особый");
	private JMenuItem exit = new JMenuItem("Выход");
	private GridLayout grid = new GridLayout(ClientMain.height, ClientMain.width, 5, 5);
	private JButton[] buttons = new JButton[ClientMain.width * ClientMain.height];
	private ImageIcon[] cardIcons = new ImageIcon[ClientMain.width * ClientMain.height];
	private boolean[] blockedCards;
	ImageIcon cardBack = new ImageIcon(this.getClass().getResource("source/card_back.png"));
	private JPanel game;
	
	public Client() throws UnknownHostException, IOException, InterruptedException {
		super("Пазл");
		
		this.setBounds(150, 150, 100 * ClientMain.width, 100 * ClientMain.height);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		addComponentsToPane(this.getContentPane());
	}
	
	public void addComponentsToPane(Container pane) throws IOException, InterruptedException {
		JMenu menu = new JMenu("Игра");
		game = new JPanel();
		JMenuBar bar = new JMenuBar();
		game.setLayout(grid);
		bar.setLayout(new FlowLayout(FlowLayout.LEADING));
		for(int i = 0; i < ClientMain.width * ClientMain.height; i++) {
			buttons[i] = new JButton();
			buttons[i].setIcon(cardBack);
			buttons[i].addActionListener(new ButtonListener());
			game.add(buttons[i]);
		}
		easy.addActionListener(new MenuListener(4, 4));
		medium.addActionListener(new MenuListener(6, 6));
		hard.addActionListener(new MenuListener(8, 8));
		special.addActionListener(new SettingsListener(this));
		exit.addActionListener(new ExitListener(this));
		newGame.addActionListener(new NewGameListener());
		menu.add(newGame);
		menu.add(easy);
		menu.add(medium);
		menu.add(hard);
		menu.add(special);
		menu.add(exit);
		bar.add(menu);

		pane.add(bar, BorderLayout.PAGE_START);
		pane.add(game, BorderLayout.CENTER);
		startNewGame();
	}
	
	
	public void startNewGame() throws IOException, InterruptedException {
		socket = new Socket("localhost", port);
		System.out.println("Client connected to socket.");
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		DataInputStream in = new DataInputStream(socket.getInputStream());
		
		out.writeInt(ClientMain.width);
		out.writeInt(ClientMain.height);
		out.flush();
		Thread.sleep(100);
		grid = new GridLayout(ClientMain.height, ClientMain.width, 5, 5);
		buttons = new JButton[ClientMain.width * ClientMain.height];
		cardIcons = new ImageIcon[ClientMain.width * ClientMain.height];
		blockedCards = new boolean[ClientMain.width * ClientMain.height];
		this.setBounds(150, 150, 100 * ClientMain.width, 100 * ClientMain.height);
		this.getContentPane().remove(game);
		game.setLayout(grid);
		game.removeAll();
		buttons = new JButton[ClientMain.width * ClientMain.height];
		cardIcons = new ImageIcon[ClientMain.width * ClientMain.height];
		for(int i = 0; i < ClientMain.width * ClientMain.height; i++) {
			blockedCards[i] = false;
			cardIcons[i] = new ImageIcon(this.getClass().getResource(in.readUTF()));
			buttons[i] = new JButton();
			buttons[i].setIcon(cardBack);
			buttons[i].addActionListener(new ButtonListener());
			game.add(buttons[i]);
		}
		this.getContentPane().add(game);
		in.close();
		out.close();
		socket.close();
	}
	
	public void restart() throws UnknownHostException, IOException {
		socket = new Socket("localhost", port);
		System.out.println("Client connected to socket.");
		DataOutputStream out = new DataOutputStream(socket.getOutputStream());
		DataInputStream in = new DataInputStream(socket.getInputStream());
		
		out.writeInt(ClientMain.width);
		out.writeInt(ClientMain.height);
		out.flush();
		for(int i = 0; i < ClientMain.width * ClientMain.height; i++) {
			blockedCards[i] = false;
			cardIcons[i] = new ImageIcon(this.getClass().getResource(in.readUTF()));
			buttons[i].setIcon(cardBack);
		}
		in.close();
		out.close();
		socket.close();
	}

	
	private int currentIndex, firstIndex;
	private boolean firstTurned = false, secondTurned = false;
	
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(secondTurned) {
				buttons[currentIndex].setIcon(cardBack);
				buttons[firstIndex].setIcon(cardBack);
				currentIndex = -1;
				firstIndex = -1;
				secondTurned = false;
				firstTurned = false;
			}
			
			if(!firstTurned) {
				for(int i = 0; i < ClientMain.width * ClientMain.height; i++) {
					if(e.getSource() == buttons[i]) {
						if(!blockedCards[i]) {
							buttons[i].setIcon(cardIcons[i]);
							firstIndex = i; 
							firstTurned = true;
						}
					}
				}
			} else {
				for(int i = 0; i < ClientMain.width * ClientMain.height; i++) {
					if(e.getSource() == buttons[i]) {
						if(!blockedCards[i]) {
							buttons[i].setIcon(cardIcons[i]);
							currentIndex = i; 
							secondTurned = true;
						}
						
					}
				}
				
				if(currentIndex == firstIndex) {
					buttons[currentIndex].setIcon(cardBack);
					firstTurned = false;
				} else if (cardIcons[currentIndex].getDescription().equals(cardIcons[firstIndex].getDescription())) {
					blockedCards[currentIndex] = true;
					blockedCards[firstIndex] = true;
					currentIndex = -1;
					firstIndex = -1;
					secondTurned = false;
					firstTurned = false;
					try {
						checkCards();
					} catch (IOException | InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			
			
		}

		private void checkCards() throws IOException, InterruptedException {
			for(int i = 0; i < blockedCards.length; i++) {
				if(!blockedCards[i]) {
					return;
				}
			}
			JOptionPane.showMessageDialog(null, "Поздравляю! Вы победили!");
			startNewGame();
		}

	}
	
	class MenuListener implements ActionListener{
		private int width, height;
		
		public MenuListener(int width, int height) {
			this.width = width;
			this.height = height;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			if(ClientMain.width != this.width && ClientMain.height != this.height) {
				ClientMain.width = this.width;
				ClientMain.height = this.height;
				try {
					startNewGame();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, e1.toString());
				} catch (InterruptedException e1) {
					JOptionPane.showMessageDialog(null, e1.toString());
				}
			} else {
				try {
					restart();
				} catch (IOException e1) {
					JOptionPane.showMessageDialog(null, e1.toString());
				}
			}
			
		}}
	
	class NewGameListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				restart();
			} catch (IOException e1) {
				JOptionPane.showMessageDialog(null, e1.toString());
			}
			
		}

	}
	
	class SettingsListener implements ActionListener{
		private Client cli;
		public SettingsListener(Client cli) {
			this.cli = cli;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				Settings s = new Settings(cli);
			} catch (IOException | InterruptedException e1) {
				e1.printStackTrace();
			}
			
		}

	}
	
	class ExitListener implements ActionListener{
		private Client cli;
		public ExitListener(Client cli) {
			this.cli = cli;
		}
		@Override
		public void actionPerformed(ActionEvent e) {
				cli.dispose();
		}

	}
	
}
