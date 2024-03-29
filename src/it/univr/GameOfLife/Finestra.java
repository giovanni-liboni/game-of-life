package it.univr.GameOfLife;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Finestra extends JFrame{
	/**
	 * This is an object containing the external windows.
	 */
	private Dialog dialog;
	/**
	 * It's used for setting the status of the game. If true the game starts, else do nothing.
	 */
	private static boolean startFlag=true;
	/**
	 * It's used for setting the status of the game. If true the game starts, else stops.
	 */
	protected static boolean gameStatus; // if true -> start else pause
	/**
	 * This is the object of the window.
	 */
	protected static GameX frame;
	/**
	 * This is the object containing the world.
	 */
	protected static Griglia panel;
	/**
	 * Variables used for number of threads, dimension and game generation counter.
	 */
	protected static int 
		numOfThreads=1,
		dim=60,
		contGen=0;
	/**
	 * This is the windows's container.
	 */
	protected static Container cont;

	/**
	 * This constructor is used for showing threads setter frame,
	 * and then creates game main window
	 */
	public Finestra(){
		
		dialog = new Dialog();
		
		dialog.numOfThreads();
		
		frame = new GameX(numOfThreads);
		
		frame.setNumOfThreadLabel(String.valueOf(numOfThreads));

		cont = frame.getContentPane();
		panel = new Griglia(new Core(numOfThreads,dim));
		cont.add(panel);
		frame.setVisible(true);
		
		assegnaMenu();
		
	}
	/**
	 * Creates ActionListener and sets it on every button and menu item
	 */
	public void assegnaMenu(){
		
		ActionListener[] 
				ActionListenerMenuItem0 = {
				
			//apri un file - 1
				
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							pause();
							dialog.fileOpen();		
							disegna();
					}
				},
			
				// salva file - 2
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						pause();
						dialog.fileSave(panel.getCampo(), contGen, numOfThreads);
					}
				},
				
			
				//modifica la dimensione del campo (solo sviluppo) - 2
				
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						pause();
						dialog.dimSet();
						disegna();
					}
				},
			
				//Modifica numero di threads dal menu - 3
				
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						pause();
						dialog.numOfThreads();
						// aggiorno il numero di threads per l'interfaccia
						frame.setNumOfThreadLabel(String.valueOf(numOfThreads));
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
					pause();
					frame.setContGenLabel(String.valueOf(contGen));
					panel.setCampo((new Core(numOfThreads,dim)));
					disegna();
					}
			},
			// change color
			new ActionListener(){
				public void actionPerformed(ActionEvent arg0) {
					new ColorChanger(panel);
					}
			},
			new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent arg0) {
					panel.setActionListenerMode(-1);			
				}
			},
			
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
					//add-stillLifes-SingleCell
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							panel.setActionListenerMode(11);
						}
					},
		},
		ActionListenerMenuItem4 = {
					//add-oscillators-blinker
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							panel.setActionListenerMode(4);	
						}
					},
					//add-oscillators-toad
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							panel.setActionListenerMode(5);				
						}
					},
					//add-oscillators-beacon
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							panel.setActionListenerMode(6);						
						}
					},
					//add-oscillators-pulsar
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							panel.setActionListenerMode(7);							
						}
					},
		},
		ActionListenerMenuItem5 = {			
					//add-spaceships-glider
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							panel.setActionListenerMode(8);					
						}
					},
					//add-spaceships-lightweight spaceship
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							panel.setActionListenerMode(9);							
						}
					},
					//add-spaceships-spaceship
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							panel.setActionListenerMode(10);							
						}
					}
			},
			
			ActionListenerMenuItem6 = {
					
					// ? - 7
						
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
								dialog.aboutAuthors();
							}
					},
			},
			ActionListenerButtons = {
					// pulsante start - 8
						
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							if(startFlag){
								startFlag=false;
								start();	
							}
							}
					},
			
					// pulsante pause - 9
					
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							pause();
						}
					},
			
					// pulsante reset - 10
					
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							contGen = 0;
							frame.setContGenLabel(String.valueOf(contGen));
							pause();
							reset();
							
						}
					},
			
					// pulsante next - 11
			
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							 ++contGen;
							 frame.setContGenLabel(String.valueOf(contGen));
							 panel.nextGen(numOfThreads);	
							 disegna();
						}
					}
			};
		
		// Assegno i listener al men�
		
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
		        	 synchronized (this) {
		        		  SwingUtilities.invokeAndWait(doNextGen);
					}
		           
		         }
		         catch (Exception e) {}
		         finally{;}
		        }
		     }
		 }; appThread.start();
	}
	/**
	 * Is used for setting the runnable mode of the game and generate the new generation on the current panel and then invokes the
	 * method disegna()
	 */
	final Runnable doNextGen = new Runnable() {
	     public void run() {
	    	 if(gameStatus){
	    			panel.nextGen(numOfThreads);
	    			disegna();
	    	 }
	     }
	 };
	/**
	 * This method is used for resetting the current panel, clearing it all.
	 * It invokes reset() on current panel
	 */
	private void reset(){
		panel.reset();
	}
	/**
	 * This method is used for drawing the current panel, using setColor()
	 * methods on current panel
	 */
	private void disegna(){
		panel.setColor();
	}
	/**
	 * This method pauses the game;
	 */
	protected static void pause(){
		gameStatus=false;
		startFlag=true;
	}
	/**
	 * This method is used for taking time on creating nextGen()
	 * @param milliseconds
	 * is taken from current value set on slider
	 */
	private static void sleepFor(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {}
		finally{;}
	}
}