package it.univr.GameOfLife;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class GameX extends JFrame {
	
	private JPanel up, down;
	private JMenuBar barraDeiMenu;
	private JSlider slider;
	private JLabel numOfThreadLabel,contGenLabel;
	/*
	 * Bisogna rendere private i menu
	 * - Aggiungere i metodi necessari
	 */
	/**
	 * This Method Creates an array of JMenu[], which is used in JMenuBar.
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
	 * This method creates an array of JMenuItem that will be included in JMenu's array.
	 */
	final JMenuItem[] 
		menuItem0 ={
				new JMenuItem("Open..."),//0
				new JMenuItem("Save..."),//1
				new JMenuItem("Modifica dimensioni(solo per sviluppo)"),//2
				new JMenuItem("Modifica numero di Threads"),//3
				new JMenuItem("Esci")//4
		},
		menuItem1 ={
				new JMenuItem("Campo casuale"),//5
				new JMenuItem("Change Colour"),//6
		},
		menuItem3 = {
				new JMenuItem("Blocks"),//7
				new JMenuItem("Beehive"),//8
				new JMenuItem("Loaf"),//9
				new JMenuItem("Boat")//10
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
	 * This method creates the button, that will be under JMenuBar, exactly in the north section of
	 * main frame's BorderLayout
	 */
	final JButton[] buttons = {
				new JButton("Start"),
				new JButton("Pause"),
				new JButton("Reset"),
				new JButton("Next")
	};
	/**
	 * This method is used for creating main game's frame (game grid).
	 * @param numOfThreads
	 * is used for parsing the number of threads used for creating the main game grid.
	 */
	public GameX(int numOfThreads){

		setTitle("GameOfLife");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Toolkit mioToolkit = Toolkit.getDefaultToolkit();
	
		Dimension dimensioniSchermo = mioToolkit.getScreenSize();

		int larghezzaFrame;
		
		larghezzaFrame = (int) (dimensioniSchermo.getWidth()/2+50);

		setSize(larghezzaFrame, larghezzaFrame);

		
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
		
		/*Creo i componenti per la parte sud*/
		//Creo lo slider
		
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
	 * This method obtains the number of threads of the label (situated in the south section of
	 * main game's frame)
	 * @return
	 * returns the number of threads of the label
	 */
	public JLabel getNumOfThreadLabel() {
		return numOfThreadLabel;
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
	 * This method is used for getting the population's current counter of JLabel.
	 * @return
	 * returns the current generation Label.
	 */
	public JLabel getContGenLabel() {
		return contGenLabel;
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