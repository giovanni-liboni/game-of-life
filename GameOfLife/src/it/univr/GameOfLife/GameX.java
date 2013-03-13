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
	
	JMenuItem[] menuItem ={
			new JMenuItem("Open"),//0
			new JMenuItem("Save"),//1
			new JMenuItem("Modifica dimensioni(solo per sviluppo)"),//2
			new JMenuItem("Modifica numero di Threads"),//3
			new JMenuItem("Esci"),//4
			new JMenuItem("Aggiungi per giovanni prova"),//5
			new JMenuItem("Campo casuale"),//6
			new JMenuItem("Blocks"),//7
			new JMenuItem("Beehive"),//8
			new JMenuItem("Loaf"),//9
			new JMenuItem("Boat"),//10
			new JMenuItem("Blinker"),//11
			new JMenuItem("Toad"),//12
			new JMenuItem("Beacon"),//13
			new JMenuItem("Pulsar"),//14
			new JMenuItem("Glider"),//15
			new JMenuItem("Lightweight Spaceship"),//16
			new JMenuItem("Spaceship"), //17
			new JMenuItem("?"),//18
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
		
		menu[0].add(menuItem[0]);
		menu[0].add(menuItem[1]);
		menu[0].add(menuItem[2]);
		menu[0].add(menuItem[3]);
		menu[0].add(menuItem[4]);
		menu[1].add(menuItem[5]);
		menu[1].add(menuItem[6]);
		menu[3].add(menuItem[7]);
		menu[3].add(menuItem[8]);
		menu[3].add(menuItem[9]);
		menu[3].add(menuItem[10]);
		menu[4].add(menuItem[11]);
		menu[4].add(menuItem[12]);
		menu[4].add(menuItem[13]);
		menu[4].add(menuItem[14]);
		menu[5].add(menuItem[15]);
		menu[5].add(menuItem[16]);
		menu[5].add(menuItem[17]);
		menu[6].add(menuItem[18]);
		
		for(int pos=0;pos<menu.length;++pos){
			if(pos==2||pos==3||pos==4||pos==5){
				if(pos==5)
					menu[2].add(menu[3]);
					menu[2].add(menu[4]);
					menu[2].add(menu[5]);
					barraDeiMenu.add(menu[2]);
			}
			else
				barraDeiMenu.add(menu[pos]);
		}

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