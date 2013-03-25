package it.univr.GameOfLife;

import java.util.Random;

public class GenThreadsCasualArray{
	private Random random = new Random();
	public boolean array[][];
	private int y=0;
		
	public GenThreadsCasualArray(int numOfThreads, int size){
			array = new boolean[size][size];
			SingleThread[] slaves = createThreads(numOfThreads);
			waitForThreadsToFinish(slaves);
		}
	public boolean[][] getArray(){
		return this.array;
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
