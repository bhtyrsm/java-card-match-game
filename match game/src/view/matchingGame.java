package view;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import controller.cardController;
import model.TurnCounter;
import model.Card;

public class matchingGame implements ActionListener{
	
	private JFrame mainFrame;
	private Container mainContentPane;
	private TurnCounter turnCounterLabel;
	private ImageIcon cardIcon[];
	private String ImageDirectory="default";
	private int mod=0;
	private int size=16;
	private ArrayList<File> files;
	
	
	private static void newMenuItem(String text, JMenu menu, ActionListener listener)
	{
		JMenuItem newItem = new JMenuItem(text);
		newItem.setActionCommand(text);
		newItem.addActionListener(listener);
		menu.add(newItem);
	}
	
	private ImageIcon[] loadCardIcons()
	{
		
		// allocate array to store icons
		ImageIcon icon[] = new ImageIcon[size+1];
		// for each icon
		
		
		for(int i = 0; i < size/2+1; i++ )
		{
			String fileName;
			// make a new icon from a cardX.gif file
			
			 if(ImageDirectory=="default")
				fileName =  "images/"+ImageDirectory+"/card"+i+".jpg";
			else if(ImageDirectory=="car")
				fileName =  "images/"+ImageDirectory+"/car"+i+".jpg";
			else
				break;
			
			icon[i] = new ImageIcon(fileName);
			// unable to load icon
			if(icon[i].getImageLoadStatus() == MediaTracker.ERRORED)
			{
				// inform the user of the error and then quit
				JOptionPane.showMessageDialog(this.mainFrame
					, "The image " + fileName + " could not be loaded."
					, "Matching Game Error", JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}
			
		}
		return icon;
	}
	
	public matchingGame()
	{
		
		this.mainFrame = new JFrame("Matching Game");
		this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.mainFrame.setSize(400,500);
		this.mainContentPane = this.mainFrame.getContentPane();
		this.mainContentPane.setLayout(new BoxLayout(this.mainContentPane, BoxLayout.PAGE_AXIS)); 
		// make counter label
		this.turnCounterLabel = new TurnCounter();
		
		
		// Menu bar
		JMenuBar menuBar = new JMenuBar();
		this.mainFrame.setJMenuBar(menuBar);
		// Game menu
		JMenu gameMenu = new JMenu("Game");
		JMenu categoriesMenu = new JMenu(" Select Card Type");
		
		menuBar.add(gameMenu);
		menuBar.add(categoriesMenu);
		newMenuItem("New Game", gameMenu, this);
		newMenuItem("Exit", gameMenu, this);
		
		File f = new File("images/");
		files = new ArrayList<File>(Arrays.asList(f.listFiles()));
		
		for(int i=0;i<files.size();i++)
			newMenuItem(files.get(i).getName(), categoriesMenu, this);

		this.cardIcon = loadCardIcons(); 
	}
	

	
	public void newGame() {
		// TODO Auto-generated method stub
		
		// reset the turn counter to zero
		this.turnCounterLabel.NewGameReset();
		// clear out the content pane (removes turn counter label and card field)
		this.mainContentPane.removeAll();
		// make a new card field with cards, and add it to the window
		this.mainContentPane.add(makeCards());
		// add the turn counter label back in again
		this.mainContentPane.add(this.turnCounterLabel);
		
		// show the window (in case this is the first game)
		this.mainFrame.setVisible(true);
		this.mainContentPane.setVisible(true);
		
	}


	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		dprintln("actionPerformed " + e.getActionCommand());
		if(e.getActionCommand().equals("New Game")) newGame();
		else if(e.getActionCommand().equals("default"))
			ImageDirectory="default";
		else if(e.getActionCommand().equals("car"))
			ImageDirectory="car";
		else if(e.getActionCommand().equals("Exit")) System.exit(0);
		
	}

	private void dprintln(String message) {
		
		System.out.println( message );
	}
	public static void randomizeIntArray(int[] a)
	{
		Random randomizer = new Random();
		// iterate over the array
		for(int i = 0; i < a.length; i++ )
		{
			
			int d = randomizer.nextInt(a.length);
			// swap the entries
			int t = a[d];
			a[d] = a[i];
			a[i] = t;
		}
	}
	
	public JPanel makeCards()
	{
		cardIcon=loadCardIcons();
		// make the panel to hold all of the cards
		JPanel panel = new JPanel(new GridLayout(4, 4));
		
		cardController turnedManager = new cardController(this.turnCounterLabel,this.mainContentPane);
		// all cards have same back                                             
		ImageIcon backIcon = this.cardIcon[8];
		
		// make an array of card numbers: 0, 0, 1, 1, 2, 2, ..., 7, 7
		
		int cardsToAdd[];
		
		
		if (mod==0)
		{
			size = 16;
			this.mainFrame.setSize(400,500);
		}
			
		
		else if (mod==1)
		{
			size = 24;
			this.mainFrame.setSize(540,640);
		}
		
		else if (mod==2)
		{
			size = 32;
			this.mainFrame.setSize(800,500);
		}
		
		cardsToAdd = new int[size];
		
		for(int i = 0; i < size/2; i++)
		{
			cardsToAdd[2*i] = i;
			cardsToAdd[2*i + 1] = i;
		}
		// randomize the order of the array
		randomizeIntArray(cardsToAdd);
		
		// make each card object
		for(int i = 0; i < cardsToAdd.length; i++)
		{
			// number of the card, 0-7, randomized
			int num = cardsToAdd[i];
			// make the card object
			Card newCard = new Card(turnedManager, this.cardIcon[num], backIcon, num);
			// add it to the panel
			panel.add(newCard);
		}
		// return the filled panel
		return panel;
	}
}