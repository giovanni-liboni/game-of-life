package my.GameOfLife;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;


public class GenThreadsPaintComponent {
	boolean[][] array;
	static int y=0;
	private Shape [][] figure;
	Graphics2D g;
	int cellDim=10; // da cambiare
	int maxRows;
	int maxColumns;
	/**
	 * This constructor is used for generating the threads which will be used for painting the game grid.
	 * @param numOfThreads
	 * is the number of thread used for painting the game grid.
	 * @param campo
	 * is used for setting the field where painting will be applied.
	 * @param g
	 * is the graphic under the grid.
	 */
	public GenThreadsPaintComponent(int numOfThreads,it.univr.GameOfLife.Core campo, Graphics g){
		
		this.g= (Graphics2D) g;
		maxRows = campo.getArray().length;
		maxColumns = campo.getArray()[0].length;
		figure = new Shape[maxRows][maxColumns];
		
		this.array = new boolean[maxRows][maxColumns];
		this.array = campo.getArray();
		
		SingleThread[] slaves = createThreads(numOfThreads);
		
		waitForThreadsToFinish(slaves);
		
	}
	/**
	 * This method is used for creating the threads which will be used for the game.
	 * @param numOfThreads
	 * is the number of threads used for this method.
	 * @return
	 * returns the threads ready for working.
	 */
	private SingleThread[] createThreads(int numOfThreads){
			SingleThread[] slaves = new SingleThread[numOfThreads];
			for(int pos=0; pos < numOfThreads; pos++){
				(slaves[pos] = new SingleThread()).start();
			}
			return slaves;
		}
	/**
	 * This constructor is used for waiting others threads to finish their work.
	 * @param slaves
	 * are the threads used for working.
	 */
	private void waitForThreadsToFinish(SingleThread[] slaves) {
			// aspettiamo che abbiano finito di lavorare
			for (SingleThread slave: slaves)
				try {
					slave.join();
				}
				catch (InterruptedException e) {
					// qualcuno ci ha interrotti mentre aspettavamo
				}
		}
	/**
	 * This constructor is used for starting every single thread for the rispective work.
	 */
	private class SingleThread extends Thread{
			int yThread;
			@Override
			public void run(){
				do{
					synchronized(GenThreadsPaintComponent.this){
							yThread=y;
							y++;
					}
					if(yThread < maxRows){
							for(int x=0; x < maxColumns; x++){
								//cambiare qui l'algoritmo
								figure [yThread][x] = new Rectangle2D.Double(yThread*cellDim,x*cellDim,cellDim,cellDim);
								synchronized(GenThreadsPaintComponent.this){
									g.fill(figure [yThread][x]);
									g.setColor((array[yThread][x] == true) ? Color.BLACK : Color.WHITE);
								}
							}
				}
				
			}
			while(yThread < maxRows);
		}
	}
}