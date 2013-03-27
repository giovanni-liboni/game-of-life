package my.GameOfLife;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

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
				catch(NumberFormatException e){
				}
			}
			while(numOfThreads < 0);
		Finestra.numOfThreads = numOfThreads;
	}
	/**
	 * This method is used for creating the frame which will asks for file name for saving the current game grid ;)
	 * @param campo
	 * @param contGen
	 * @param numOfThreads
	 */
	public void fileSave(Core campo,int contGen, int numOfThreads ){
		
		String
			fileName = "";
		
		JFileChooser c = new JFileChooser();
		
		int rVal = c.showSaveDialog(null);
		if(rVal == JFileChooser.APPROVE_OPTION) {
			fileName = c.getSelectedFile().getName();
		}
		if(rVal == JFileChooser.CANCEL_OPTION) {
			fileName = "";
		}
		if(fileName == null){
//			dispose();
		}
		File f = c.getSelectedFile();
		
		try {
		
			DataOutputStream outStream = new DataOutputStream(new FileOutputStream(f));
			
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
					outStream.writeChar(';');
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

		String
			fileName = "";
		
		JFileChooser c = new JFileChooser();
		
		int rVal = c.showOpenDialog(null);
		if(rVal == JFileChooser.APPROVE_OPTION) {
			fileName = c.getSelectedFile().getName();
		
		}
		if(rVal == JFileChooser.CANCEL_OPTION) {
			fileName = "";
			
		}
		if(fileName == null){
			JOptionPane.showConfirmDialog(null, "File non trovato", "Attenzione!", JOptionPane.ERROR_MESSAGE);
			
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
				
				// contGen
				Finestra.contGen = inStream.readInt();
				inStream.readChar();
				
				//numOfThreads
				Finestra.numOfThreads = inStream.readInt();
				inStream.readChar();
				
				array = new boolean[max][max];
				
				for(int y=0; y< max;y++){
					for(int x=0; x < max;x++){
						array[y][x] = inStream.readBoolean();
						inStream.readChar();
					}
				}
				
				campo = new Core(Finestra.numOfThreads, array);
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

}
