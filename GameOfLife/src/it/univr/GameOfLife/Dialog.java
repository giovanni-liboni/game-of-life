package it.univr.GameOfLife;

import java.io.*;
import javax.swing.*;

public class Dialog{
	/**
	 * This method is used for creating a new frame which asks for the desidered number of thread to use.
	 */
	public void numOfThreads(){
		String str;
		int numOfThreads=2;
		
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
				catch(NumberFormatException e){}
				finally{;}
			}
			while(numOfThreads < 0);
		
		
		Finestra.numOfThreads = numOfThreads;
	}
	/**
	 * This method is used for creating the frame which will asks for file name for saving the current game grid.
	 * @param campo
	 * Used for obtaining the Game field to use.
	 * @param contGen
	 * Used for obtaining the number of generation passed since this frame is called.
	 * @param numOfThreads
	 * Used for obtaining the number of threads used in the game.
	 */
	public void fileSave(Core campo,int contGen, int numOfThreads ){
		
		String
			fileName = "";
		
		JFileChooser c = new JFileChooser();
		
		int val = c.showSaveDialog(null);
		if(val == JFileChooser.APPROVE_OPTION) {
			fileName = c.getSelectedFile().getName();
		}
		if(val == JFileChooser.CANCEL_OPTION) {
			return;
		}
		File f = c.getSelectedFile();
		
		try {
		
			DataOutputStream outStream = new DataOutputStream(new FileOutputStream(f));
			
			int max = campo.getArray().length;
			
			outStream.writeInt(-1);
			
			outStream.writeInt(max);
			
			outStream.writeInt(contGen);
			
			outStream.writeInt(numOfThreads);
			
			for(int y=0; y< max;y++){
				for(int x=0; x < max;x++){
					
					outStream.writeBoolean(campo.getArray()[y][x]);
					outStream.writeBoolean(campo.isDeath(y, x));
				}
			}
			
			outStream.close();
			
		}
		catch (IOException e) {
			JOptionPane.showConfirmDialog(null, "Salvataggio non riuscito", "Attenzione!", JOptionPane.ERROR_MESSAGE);
			
		}
		finally{;}
	}
	/**
	 * This method is used for creating the frame which will asks for file name for opening a saved game grid.
	 */
	public void fileOpen(){
		boolean statusFile = false;
		int temp;
		String
			fileName = "";
		
		JFileChooser c = new JFileChooser();
		
		int val = c.showOpenDialog(null);
		if(val == JFileChooser.APPROVE_OPTION) {
			fileName = c.getSelectedFile().getName();
		
		}
		if(val == JFileChooser.CANCEL_OPTION) {
			return;
		}
		File f = c.getSelectedFile();
		DataInputStream inStream;
		try {
				
			inStream = new DataInputStream(new FileInputStream(f));
	
			Core campo;
			boolean [][] array = null;
			boolean [][] death = null;
			int max;
			try{
				
				temp = inStream.readInt();
				
				if(temp<0){
					max = inStream.readInt();
					statusFile = true;
				}
				else
					max = temp;
				if(!statusFile)
					inStream.readChar();

				Finestra.contGen = inStream.readInt();
				if(!statusFile)
					inStream.readChar();
				
				Finestra.numOfThreads = inStream.readInt();
				if(!statusFile)
					inStream.readChar();
				
				array = new boolean[max][max];
				death = new boolean[max][max];
				
				for(int y=0; y< max;y++){
					for(int x=0; x < max;x++){
						array[y][x] = inStream.readBoolean();
						if(statusFile)
							death[y][x] = inStream.readBoolean();
						
						if(!statusFile)
							inStream.readChar();
					}
				}
				
				campo = new Core(Finestra.numOfThreads, array);
				
				
				if(statusFile){
				for(int y=0; y< max;y++){
					for(int x=0; x < max;x++){
						if(death[y][x])
							campo.uccidoCell(y, x);
					}
				}
				}
				Finestra.cont.remove(Finestra.panel);
				Finestra.panel = new Griglia(campo);
				Finestra.cont.add(Finestra.panel);
				Finestra.panel.setVisible(false);
				Finestra.panel.setVisible(true);          
				Finestra.gameStatus = false;
				
			    Finestra.frame.setContGenLabel(String.valueOf(Finestra.contGen));
				Finestra.frame.setNumOfThreadLabel(String.valueOf(Finestra.numOfThreads));
				
				
			}
			finally{;}
			inStream.close();
			
		} 
		catch (FileNotFoundException e) {
			JOptionPane.showConfirmDialog(null, "File non trovato", "Attenzione!", JOptionPane.ERROR_MESSAGE);
		} 
		catch (IOException e) {
			JOptionPane.showConfirmDialog(null, "Caricamento non riuscito", "Attenzione!", JOptionPane.DEFAULT_OPTION);
			
		}
		catch (Exception e) {
		JOptionPane.showConfirmDialog(null, "Caricamento fallito", "Attenzione!", JOptionPane.DEFAULT_OPTION);
		}
		finally{;}
	}
	/**
	 * This method is used for setting the game grid size.
	 */
	public void dimSet(){
		String str;
		int dim = 0;
		
		do{
			str = JOptionPane.showInputDialog ("Insert square's length side:");
				try{
					int temp = Integer.parseInt(str);
					if(temp > 0){
						dim = temp;
					}
					else if(temp < 0)
						JOptionPane.showMessageDialog(null, "Attenzione valore inserito non valido!\n"+
								"Inserire numero maggiore di 0.", "Attenzione!", JOptionPane.WARNING_MESSAGE);
				}
				catch(NumberFormatException e){;}
				finally{;}
				
			}
			while(dim < 0);
			if(str!=null)
			{
			Finestra.dim = dim;
			Finestra.cont.remove(Finestra.panel);
			Finestra.panel = new Griglia(new Core(Finestra.numOfThreads,dim));
			Finestra.cont.add(Finestra.panel);
			Finestra.panel.setVisible(false);
		 	Finestra.panel.setVisible(true);
			Finestra.frame.repaint();

			Finestra. contGen = 0;
			Finestra.frame.setContGenLabel(String.valueOf(Finestra.contGen));
			Finestra. gameStatus = false;
			}
			else
			{}
	}
	/**
	 * This method is used for showing informations about the author of this game.
	 */
	public void aboutAuthors(){
		JOptionPane.showMessageDialog(null, "<html><center>Giovanni Liboni & Pinna Cristian<br>March 2013</center></html>", "About the authors...", JOptionPane.PLAIN_MESSAGE);
	}
}