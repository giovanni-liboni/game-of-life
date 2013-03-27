package it.univr.GameOfLife;

import java.util.Random;

public class GenThreadsCasualArray{
	private Random random = new Random();
	public boolean array[][];
	private int y=0;
	/**
	 * This constructor is used for creating a random game field.
	 * @param numOfThreads
	 * is the number of threads used for creating the field.
	 * @param y
	 * @param x
	 */
	public GenThreadsCasualArray(int numOfThreads, int size){
			array = new boolean[size][size];
			SingleThread[] slaves = createThreads(numOfThreads);
			waitForThreadsToFinish(slaves);
		}
	/**
	 * This method is used for getting the current game grid's array.
	 * @return
	 * returns the array of current game.
	 */
	public boolean[][] getArray(){
		return this.array;
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
				finally{;}
		}
	/**
	 * This constructor is used for starting every single thread for the rispective work.
	 */
	private class SingleThread extends Thread{
			private int yThread;
			@Override
			public void run(){
				do{
					synchronized(GenThreadsCasualArray.this){
						yThread=y;
						y++;
					}
					if(yThread < array.length)
						for(int x=0; x < array[0].length;x++){
							array[yThread][x] = random.nextBoolean();
					}
				}
				while(yThread < array.length);
			}
		}
}
