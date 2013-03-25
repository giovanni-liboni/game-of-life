package it.univr.GameOfLife;

import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

import javax.swing.*;

@SuppressWarnings("serial")
public class Finestra extends JFrame{
	
	private Dialog dialog;
	protected static boolean gameStatus; // if true -> start else pause
	protected static GameX frame;
	protected static Griglia panel;
	protected static int 
		numOfThreads=1,
		X=80, 
		Y=80,
		contGen=0;
	protected static Container cont;
	final Runnable doNextGen = new Runnable() {
	     public void run() {
	         nextGen();
	     }
	 };
	/**
	 * This method is used for showing threads setter frame,
	 * and then creates game main window
	 */
	public Finestra(){
		
		dialog = new Dialog();
		
		/* Creo la finestra iniziale e aggiorno il numero di threads
		 * da usare
		 */
		dialog.numOfThreads();
		
		/* Creo il frame*/
		
		frame = new GameX(numOfThreads);
		
		// aggiorno il numero di threads per l'interfaccia
		
		frame.setNumOfThreadLabel(String.valueOf(numOfThreads));
				
		/*Creo il JPanel per il campo delle cellule	 */

		cont = frame.getContentPane();
		panel = new Griglia(new Core(numOfThreads,Y,X));
		cont.add(panel);
			
	// Assegno i menù
		assegnaMenu();
			
		frame.setVisible(true);
		
	} // fine costruttore Finestra
	/**
	 * Creates ActionListener and sets it on every button and menu item
	 */
	public void assegnaMenu(){
		
		/* Creo un array listener per il menù*/
		
		ActionListener[] 
				ActionListenerMenuItem0 = {
				
			//apri un file - 1
				
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							
						dialog.fileOpen();		
						disegna();
					}
				},
			
				// salva file - 2
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						dialog.fileSave(panel.getCampo(), contGen, numOfThreads);
					}
				},
				
			
				//modifica la dimensione del campo (solo sviluppo) - 2
				
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						int[] dati = new DialogDimSet("Immettere le dimensioni").getData();
						
						if(dati[0]>0 && dati[1]>0){
							Y = dati[1];
							X = dati[0];
							cont.remove(panel);
							panel = new Griglia(new Core(numOfThreads,Y,X));
							cont.add(panel);
							panel.setVisible(false);
							panel.setVisible(true);
							frame.repaint();
							disegna();
						    contGen = 0;
	                        frame.setContGenLabel(String.valueOf(contGen));
	                        gameStatus = false;
						}
					}
				},
			
				//Modifica numero di threads dal menu - 3
				
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
							dialog.numOfThreads();
					}
				},
				
				// chiude il programma	- 4
						
				new ActionListener(){
							@Override
							public void actionPerformed(ActionEvent arg0) {
								frame.dispose();
							}
						}
		},
		
		ActionListenerMenuItem1 = {
			//nuovo campo casuale - 6
				
			new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					contGen = 0;
					frame.setContGenLabel(String.valueOf(contGen));
					panel.setCampo((new Core(numOfThreads,Y,X)));
					disegna();
					}
			}
		},
		
		ActionListenerMenuItem3 = {
				
					//add-stillLifes-block
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							panel.setActionListenerMode(1);
						}
					},
					//add-stillLifes-Beehive
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							panel.setActionListenerMode(2);
						}
					},
					//add-stillLifes-Loaf
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							panel.setActionListenerMode(3);
						}
					},
					//add-stillLifes-Boat
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							panel.setActionListenerMode(4);
						}
					},
		},
		ActionListenerMenuItem4 = {
					//add-oscillators-blinker
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							panel.setActionListenerMode(5);	
						}
					},
					//add-oscillators-toad
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							panel.setActionListenerMode(6);				
						}
					},
					//add-oscillators-beacon
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							panel.setActionListenerMode(7);						
						}
					},
					//add-oscillators-pulsar
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							panel.setActionListenerMode(8);							
						}
					},
		},
		ActionListenerMenuItem5 = {			
					//add-spaceships-glider
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							panel.setActionListenerMode(9);					
						}
					},
					//add-spaceships-lightweight spaceship
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							panel.setActionListenerMode(10);							
						}
					},
					//add-spaceships-spaceship
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							panel.setActionListenerMode(11);							
						}
					}
			},
			
			ActionListenerMenuItem6 = {
					
					// ? - 7
						
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							
							}
					},
			},
			ActionListenerButtons = {
					// pulsante start - 8
						
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							
							start();
							}
					},
			
					// pulsante pause - 9
					
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							gameStatus = false;
						}
					},
			
					// pulsante reset - 10
					
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							contGen = 0;
							frame.setContGenLabel(String.valueOf(contGen));
							gameStatus = false;
							reset();
							
						}
					},
			
					// pulsante next - 11
			
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							 ++contGen;
							 frame.setContGenLabel(String.valueOf(contGen));
							nextGen();				
						}
					}
			};
		
		// Assegno i listener al menù
		
		for (int i = 0; i < ActionListenerMenuItem0.length; ++i){
			frame.menuItem0[i].addActionListener(ActionListenerMenuItem0[i]);
		}
		for (int i = 0; i < ActionListenerMenuItem1.length; ++i){
			frame.menuItem1[i].addActionListener(ActionListenerMenuItem1[i]);
		}
		for (int i = 0; i < ActionListenerMenuItem3.length; ++i){
			frame.menuItem3[i].addActionListener(ActionListenerMenuItem3[i]);
		}
		for (int i = 0; i < ActionListenerMenuItem4.length; ++i){
			frame.menuItem4[i].addActionListener(ActionListenerMenuItem4[i]);
		}
		for (int i = 0; i < ActionListenerMenuItem5.length; ++i){
			frame.menuItem5[i].addActionListener(ActionListenerMenuItem5[i]);
		}
		for (int i = 0; i < ActionListenerMenuItem6.length; ++i){
			frame.menuItem6[i].addActionListener(ActionListenerMenuItem6[i]);
		}
		for (int i = 0; i < ActionListenerButtons.length; ++i){
			frame.buttons[i].addActionListener(ActionListenerButtons[i]);
		}
	}
	/**
	 * Starting of game.
	 * Sets gameStatus true, starts a new thread that contain
	 * a runnable with a while cycle (while condition = gameStatus), increments contGen
	 * updating setContGenLabel, sets sleep taking information from the slider and then
	 * invokes doNextGen
	 */
	private void start(){
		gameStatus=true;
		Thread appThread = new Thread() {
		     public void run() {
		    	 while(gameStatus){
		         try {
		        	 ++contGen;
		        	 frame.setContGenLabel(String.valueOf(contGen));
		        	 sleepFor((500 - frame.getSlider().getValue()));
		             SwingUtilities.invokeAndWait(doNextGen);
		         }
		         catch (Exception e) {}
		    	 }
		     }
		 }; appThread.start();
	}
	/**
	 * This method generate the new generation on the current panel and then invokes the
	 * method disegna()
	 */
	private void nextGen(){
		panel.nextGen(numOfThreads);
		disegna();
	}
	/**
	 * This method is used for resetting the current panel, clearing it all.
	 * It invokes reset() and repaint() on current panel
	 */
	private void reset(){
		panel.reset();
		panel.repaint();
	}
	/**
	 * This method is used for drawing the current panel, using setColor() and repaint()
	 * methods on current panel
	 */
	protected void disegna(){
		panel.setColor();
		panel.repaint();
	}
	/**
	 * This method is used for taking time on creating nextGen()
	 * @param milliseconds
	 * is taken from current value setted on slider
	 */
	private static void sleepFor(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {}
	}
}