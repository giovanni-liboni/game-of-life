package it.univr.GameOfLife;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Dialog{
	
	/*
	 * Finestra che ritorna il numero di threads inserito
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
				catch(NumberFormatException e){
			}
			}
			while(numOfThreads < 0);
		
		// modifico il numero di threads
		Finestra.numOfThreads = numOfThreads;
	}
	public void fileSave(Core campo,int contGen, int numOfThreads ){
		
		String
			fileName = "";
		
		JFileChooser c = new JFileChooser();
		
		int rVal = c.showSaveDialog(null);
		if(rVal == JFileChooser.APPROVE_OPTION) {
			fileName = c.getSelectedFile().getName();
		}
		if(rVal == JFileChooser.CANCEL_OPTION) {
			return;
		}
		if(fileName == null){
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
			
		} catch (FileNotFoundException e) {
			JOptionPane.showConfirmDialog(null, "File non trovato", "Attenzione!", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showConfirmDialog(null, "Salvataggio non riuscito", "Attenzione!", JOptionPane.ERROR_MESSAGE);
			
		}
	}
	public void fileOpen(){
		boolean statusFile = false;
		int temp;
		String
			fileName = "";
		
		JFileChooser c = new JFileChooser();
		
		int rVal = c.showOpenDialog(null);
		if(rVal == JFileChooser.APPROVE_OPTION) {
			fileName = c.getSelectedFile().getName();
		
		}
		if(rVal == JFileChooser.CANCEL_OPTION) {
			return;
		}
		if(fileName == null){
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
				// contGen
				Finestra.contGen = inStream.readInt();
				if(!statusFile)
					inStream.readChar();
				
				//numOfThreads
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
				
				// uccido le cellule
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
			catch(EOFException e){
				JOptionPane.showConfirmDialog(null, "Caricamento completato");		
			}
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
	}
	public void dimSet(){
		String str;
		int dim = 50;
		
		do{
			str = JOptionPane.showInputDialog (
					   "Insert square's length side:", 50);
				try{
					int temp = Integer.parseInt(str);
					if(temp > 0){
						dim = temp;
					}
					else if(temp < 0)
						JOptionPane.showMessageDialog(null, "Attenzione valore inserito non valido!\n"+
								"Inserire numero maggiore di 0.", "Attenzione!", JOptionPane.WARNING_MESSAGE);
					
				}
				catch(NumberFormatException e){
			}
			}
			while(dim < 0);
		
			Finestra.X = dim;
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
}
