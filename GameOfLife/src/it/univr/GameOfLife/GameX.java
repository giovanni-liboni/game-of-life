package it.univr.GameOfLife;

import javax.swing.*;

import java.awt.*;

@SuppressWarnings("serial")
public class GameX extends JFrame {
	
	private JPanel up, down;
	private JMenuBar barraDeiMenu;
	private JSlider slider;
	JLabel numOfThreadLabel,contGenLabel;
	
	JMenu[] menu = {
			new JMenu("Menu"),//0
			new JMenu("Game"),//1
			new JMenu("Add"),//2
				new JMenu("Still Lifes"),//3
				new JMenu("Oscillators"),//4
				new JMenu("Spaceships"),//5
			new JMenu("?")//6
	};
	
	JMenuItem[] 
		menuItem0 ={
				new JMenuItem("Open"),//0
				new JMenuItem("Save"),//1
				new JMenuItem("Modifica dimensioni(solo per sviluppo)"),//2
				new JMenuItem("Modifica numero di Threads"),//3
				new JMenuItem("Esci")//4
		},
		menuItem1 ={
				new JMenuItem("Campo casuale")//6
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
	
	JButton[] buttons = {
			new JButton("Start"),
			new JButton("Pause"),
			new JButton("Reset"),
			new JButton("Next")
	};
	
	

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

	public JSlider getSlider() {
		return slider;
	}

	public JLabel getNumOfThreadLabel() {
		return numOfThreadLabel;
	}

	public void setNumOfThreadLabel(String str) {
		this.numOfThreadLabel.setText(str);
	}

	public JLabel getContGenLabel() {
		return contGenLabel;
	}

	public void setContGenLabel(String str) {
		this.contGenLabel.setText(str);
	}
}