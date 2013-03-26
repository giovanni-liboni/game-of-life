package it.univr.GameOfLife;

public class GenThreadsArrayToCell{
	private boolean[][] array;
	private Cell[][] campo;
	private int y=0;
		
	public GenThreadsArrayToCell(int numOfThreads, boolean[][] array){
			this.array = array;
			this.campo = new Cell[array.length][array[0].length];
			SingleThread[] slaves = createThreads(numOfThreads);
			waitForThreadsToFinish(slaves);
		}
	public Cell[][] getCampo(){
		return this.campo;
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
				finally{;}
		}
	private class SingleThread extends Thread{
			private int yThread;
			@Override
			public void run(){
				do{
					synchronized(GenThreadsArrayToCell.this){
						yThread=y;
						y++;
					}
					if(yThread < array.length)
						for(int x=0; x < array[0].length;++x){
							campo[yThread][x] = new Cell();
							campo[yThread][x].setLife(array[yThread][x]);
					}
				}
				while(yThread < array.length);
			}
		}
}
