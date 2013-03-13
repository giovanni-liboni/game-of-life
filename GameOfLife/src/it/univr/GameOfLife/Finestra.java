package it.univr.GameOfLife;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.io.*;

@SuppressWarnings("serial")
public class Finestra extends JFrame{
	
	private String
		fileName = "",
		str;

	private boolean gameStatus; // if true -> start else pause
	private GameX frame;
	private Griglia panel;
	private int 
		numOfThreads=1,
		X=50, 
		Y=50,
		contGen=0;
	private Container cont;
	
	public Finestra(){

		/* Creo la finestra iniziale e aggiorno il numero di threads
		 * da usare
		 */
		do{
		str = JOptionPane.showInputDialog (
				   "Numero di Threads (default 2)", 2);
			try{
				int temp = Integer.parseInt(str);
				if(temp > 0){
					numOfThreads = temp;
				}
				else if(temp < 0)
					JOptionPane.showMessageDialog(null, "Attenzione valore inserito non valido!\n"+
							"Inserire numero maggiore di 0.", "Attenzione!", JOptionPane.WARNING_MESSAGE);
				
			}
			catch(NumberFormatException e){
				numOfThreads = -1;
				JOptionPane.showMessageDialog(null, "Attenzione valore inserito non valido!\n" +
						"Inserire un intero maggiore di 0.", "Attenzione!", JOptionPane.WARNING_MESSAGE);
			}
		}
		while(numOfThreads < 0);
		/* Creo il core */
		
	
		
		/* Creo il frame*/
		
		frame = new GameX(numOfThreads);
		
		// aggiorno il numero di threads per l'interfaccia
		
		frame.setNumOfThreadLabel(String.valueOf(numOfThreads));
				

		
		/*Creo il JPanel per il campo delle cellule	 */
		
		cont = frame.getContentPane();
		panel = new Griglia(new Core(numOfThreads,Y,X));
		cont.add(panel);
		
		frame.setVisible(true);
		
		/* Creo un array listener per il men�*/
		
		ActionListener[] 
				ActionListenerMenuItem0 = {
				
			//apri un file - 1
				
					new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent arg0) {
							JFileChooser c = new JFileChooser();
							
							int rVal = c.showOpenDialog(Finestra.this);
							if(rVal == JFileChooser.APPROVE_OPTION) {
								fileName = c.getSelectedFile().getName();
							
							}
							if(rVal == JFileChooser.CANCEL_OPTION) {
								fileName = "";
								
							}
							if(fileName == null){
								dispose();
							}
							File f = c.getSelectedFile();
							DataInputStream inStream;
							try {
								
								inStream = new DataInputStream(new FileInputStream(f));
			
							Core campo;
							boolean [][] array = null;
							int max;
							try{
								
								max = inStream.readInt();
								inStream.readChar();
		
								contGen = inStream.readInt();
								inStream.readChar();
								
								numOfThreads = inStream.readInt();
								inStream.readChar();
								
								array = new boolean[max][max];
								for(int y=0; y< max;y++){
									for(int x=0; x < max;x++){
										array[y][x] = inStream.readBoolean();
										inStream.readChar();
									}
								}
								campo = new Core(array);
								panel.setCampo(campo);
							    frame.setContGenLabel(String.valueOf(contGen));
								frame.setNumOfThreadLabel(String.valueOf(numOfThreads));
			                    gameStatus = false;
								disegna();
							}catch(EOFException e){
								JOptionPane.showConfirmDialog(getParent(), "Caricamento completato");
								
							}
		
							inStream.close();
							} catch (FileNotFoundException e) {
								JOptionPane.showConfirmDialog(getParent(), "File non trovato", "Attenzione!", ERROR, ERROR);
							} catch (IOException e) {
								JOptionPane.showConfirmDialog(getParent(), "Caricamento non riuscito", "Attenzione!", ERROR, ERROR);
								
							}
								catch (Exception e) {
							JOptionPane.showConfirmDialog(getParent(), "Caricamento fallito", "Attenzione!", ERROR, ERROR);
							}
					}
				},
			
				// salva file - 2
				new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						JFileChooser c = new JFileChooser();
						
						int rVal = c.showSaveDialog(Finestra.this);
						if(rVal == JFileChooser.APPROVE_OPTION) {
							fileName = c.getSelectedFile().getName();
						}
						if(rVal == JFileChooser.CANCEL_OPTION) {
							fileName = "";
						}
						
						File f = c.getSelectedFile();
						
						try {
						
							DataOutputStream outStream = new DataOutputStream(new FileOutputStream(f));
							Core campo = panel.getCampo();
							int max = campo.getArray().length;
							
							outStream.writeInt(max);
							outStream.writeChar(':');
							
							outStream.writeInt(contGen);
							outStream.writeChar(':');
							
							outStream.writeInt(numOfThreads);
							outStream.writeChar(':');
							
							for(int y=0; y< max;y++){
								for(int x=0; x < max;x++){
									
									outStream.writeBoolean(campo.getArray()[y][x]);
									outStream.writeChars(";");
								}
							}
							
							outStream.close();
							
						} catch (FileNotFoundException e) {
							JOptionPane.showConfirmDialog(getParent(), "File non trovato", "Attenzione!", ERROR, ERROR);
						} catch (IOException e) {
							JOptionPane.showConfirmDialog(getParent(), "Salvataggio non riuscito", "Attenzione!", ERROR, ERROR);
							
						}
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
					
						do{
							str = JOptionPane.showInputDialog (
									   "Numero di Threads (default 2)", 2);
								try{
									int temp = Integer.parseInt(str);
									if(temp > 0){
										numOfThreads = temp;
									}
									else
										JOptionPane.showMessageDialog(null, "Attenzione valore inserito non valido!\n"+
												"Inserire numero maggiore di 0.", "Attenzione!", JOptionPane.WARNING_MESSAGE);
									
								}
								catch(Exception e){
									numOfThreads = -1;
									JOptionPane.showMessageDialog(null, "Attenzione valore inserito non valido!\n" +
											"Inserire un intero maggiore di 0.", "Attenzione!", JOptionPane.WARNING_MESSAGE);
								}
							}
							while(numOfThreads < 0);
						// aggiornamento numero di threads usate stampate
						frame.setNumOfThreadLabel(str);
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

	final Runnable doNextGen = new Runnable() {
	     public void run() {
	         nextGen();
	     }
	 };
	 
	private void start(){
		gameStatus=true;
		Thread appThread = new Thread() {
		     public void run() {
		    	 while(gameStatus){
		         try {
		        	 ++contGen;
		        	 frame.setContGenLabel(String.valueOf(contGen));
		        	 sleepFor(frame.getSlider().getValue());
		             SwingUtilities.invokeAndWait(doNextGen);
		         }
		         catch (Exception e) {}
		    	 }
		     }
		 }; appThread.start();
	}
	private void nextGen(){
		panel.nextGen(numOfThreads);
		disegna();
	}
	private void reset(){
		panel.reset();
		panel.repaint();
	}
	private void disegna(){
		panel.setColor();
		panel.repaint();
	}
	private static void sleepFor(int milliseconds) {
		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {}
	}
}