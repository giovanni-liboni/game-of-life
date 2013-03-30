package it.univr.GameOfLife;

import javax.swing.*;
import java.awt.*;

public class GameX extends JFrame {
	/**
	 * Instantiates two panels in main game window.
	 */
	private JPanel up, down;
	/**
	 * Instantiates the JMenuBar.
	 */
	private JMenuBar barraDeiMenu;
	/**
	 * Instantiates the JSlider that will configure game speed.
	 */
	private JSlider slider;
	/**
	 * Instantiate the JLabels that will be in the south panel.
	 */
	private JLabel numOfThreadLabel,contGenLabel;
	/**
	 * This is an array of JMenu[], which is used in JMenuBar.
	 */
	final JMenu[] menu = {
			new JMenu("Menu"),//0
			new JMenu("Game"),//1
			new JMenu("Add"),//2
				new JMenu("Still Lifes"),//3
				new JMenu("Oscillators"),//4
				new JMenu("Spaceships"),//5
			new JMenu("?")//6
	};
	/**
	 * This is an array of JMenuItem that will be included in JMenu's array.
	 */
	final JMenuItem[] 
		menuItem0 ={
				new JMenuItem("Open..."),//0
				new JMenuItem("Save..."),//1
				new JMenuItem("Change size"),//2
				new JMenuItem("Change numbers of Threads"),//3
				new JMenuItem("Exit")//4
		},
		menuItem1 ={
				new JMenuItem("Casual game"),//5
				new JMenuItem("Change Colour"),//6
				new JMenuItem("Kill Mode"),
		},
		menuItem3 = {
				new JMenuItem("Blocks"),//7
				new JMenuItem("Beehive"),//8
				new JMenuItem("Loaf"),//9
				new JMenuItem("Single Cell")//10
		},
		menuItem4 = {		
				new JMenuItem("Blinker"),//11
				new JMenuItem("Toad"),//12
				new JMenuItem("Beacon"),//13
				new JMenuItem("Pulsar")//14
		},
		menuItem5 = {
				new JMenuItem("Glider"),//15
				new JMenuItem("Lightweight Spaceship"),//16
				new JMenuItem("Spaceship") //17
		},
		menuItem6 = {
				new JMenuItem("?")//18
		};
	/**
	 * This is an array of buttons, that will be under JMenuBar, exactly in the north section of
	 * main frame's BorderLayout
	 */
	final JButton[] buttons = {
				new JButton("Start"),
				new JButton("Pause"),
				new JButton("Reset"),
				new JButton("Next")
	};
	/**
	 * This constructor is used for creating main game's frame (game grid).
	 * @param numOfThreads
	 * is used for parsing the number of threads used for creating the main game grid.
	 */
	public GameX(int numOfThreads){

		setTitle("GameOfLife");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Toolkit mioToolkit = Toolkit.getDefaultToolkit();
	
		Dimension dimensioniSchermo = mioToolkit.getScreenSize();

		int dimFrame;
		
		dimFrame = (int) (dimensioniSchermo.getWidth()/2+50);

		setSize(dimFrame, dimFrame);

		
		/* Creo il menu */
		
		barraDeiMenu = new JMenuBar();
		
		
		/* Creo un array di menuItem*/
		for(int i=0; i < menuItem0.length; ++i){
			menu[0].add(menuItem0[i]);
		}
		for(int i=0; i < menuItem1.length; ++i){
			menu[1].add(menuItem1[i]);
		}
		for(int i=0; i < menuItem3.length; ++i){
			menu[3].add(menuItem3[i]);
		}
		for(int i=0; i < menuItem4.length; ++i){
			menu[4].add(menuItem4[i]);
		}
		for(int i=0; i < menuItem5.length; ++i){
			menu[5].add(menuItem5[i]);
		}
		for(int i=0; i < menuItem6.length; ++i){
			menu[6].add(menuItem6[i]);
		}

		menu[2].add(menu[3]);
		menu[2].add(menu[4]);
		menu[2].add(menu[5]);
		
		barraDeiMenu.add(menu[0]);
		barraDeiMenu.add(menu[1]);
		barraDeiMenu.add(menu[2]);
		barraDeiMenu.add(menu[6]);
		
		setJMenuBar(barraDeiMenu);
		
		/*Disegno i bottoni e le varie cose sotto e sopra */
		
		up = new JPanel();
		
		up.add(buttons[0], JPanel.CENTER_ALIGNMENT);
		up.add(buttons[1], JPanel.CENTER_ALIGNMENT);
		up.add(buttons[2], JPanel.CENTER_ALIGNMENT);
		up.add(buttons[3], JPanel.CENTER_ALIGNMENT);
		add(up, BorderLayout.NORTH);
	
		slider = new JSlider(JSlider.HORIZONTAL,0,500,50);
		slider.setPaintLabels(true);
		slider.setValue(250);
		
		// Creo il Label per la speed
		
		down = new JPanel();
		down.add(new JLabel("Speed"));
		
		// Aggiungo lo slider
		
		down.add(slider);
		down.add(new JLabel("                   "));
		
		// Creo il Label per le threads
		
		down.add(new JLabel("Numero di threads: "));
		numOfThreadLabel = new JLabel(String.valueOf(numOfThreads));
		down.add(numOfThreadLabel);
		down.add(new JLabel("   "));
		
		// Creo il Label per il conteggio delle generazioni
		
		down.add(new JLabel("Generations: "));
		contGenLabel = new JLabel(String.valueOf(0));
		down.add(contGenLabel);
		
		/*Disegno la parte sud */
				
		add(down, BorderLayout.SOUTH);
	}
	/**
	 * This method obtains the slider
	 * @return
	 * returns the slider.
	 */
	public JSlider getSlider() {
		return slider;
	}
	/**
	 * This method sets the number of threads in the JLabel.
	 * @param str
	 * is the number of threads to be written in JLabel.
	 */
	public void setNumOfThreadLabel(String str) {
		this.numOfThreadLabel.setText(str);
	}
	/**
	 * This method sets the generation counter in JLabel.
	 * @param str
	 * is the number of current population.
	 */
	public void setContGenLabel(String str) {
		this.contGenLabel.setText(str);
	}
}