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
	private SingleThread[] createThreads(int numOfThreads){
			SingleThread[] slaves = new SingleThread[numOfThreads];
			for(int pos=0; pos < numOfThreads; pos++){
				(slaves[pos] = new SingleThread()).start();
			}
			return slaves;
		}
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