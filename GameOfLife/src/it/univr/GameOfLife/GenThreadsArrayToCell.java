package it.univr.GameOfLife;

public class GenThreadsArrayToCell{
	/**
	 * Instantiates the boolean game field array.
	 */
//	private boolean[][] array;
	/**
	 * Instantiates the Cell array of game field.
	 */
//	private Cell[][] campo;
	/**
	 * This instantiates the length of square's game grid side.
	 */
//	private int y=0;
	/**
	 * This method creates the thread's array and waits for every threads to stop
	 * @param numOfThreads
	 * is the number of threads
	 * @param array
	 * is a boolean array.
	 */
//	public GenThreadsArrayToCell(int numOfThreads, boolean[][] array){
//			this.array = array;
//			this.campo = new Cell[array.length][array[0].length];
//			SingleThread[] slaves = createThreads(numOfThreads);
//			waitForThreadsToFinish(slaves);
//		}
	/**
	 * This method is used for obtaining the game field.
	 * @return
	 * returns the current field.
	 */
//	public Cell[][] getCampo(){
//		return this.campo;
//	}
	/**
	 * This method creates the threads and starts them.
	 * @param numOfThreads
	 * is the number of threads to use.
	 * @return
	 * returns the treads generated.
	 */
//	private SingleThread[] createThreads(int numOfThreads){
//			SingleThread[] slaves = new SingleThread[numOfThreads];
//			for(int pos=0; pos < numOfThreads; pos++){
//				(slaves[pos] = new SingleThread()).start();
//			}
//			return slaves;
//		}
	/**
	 * This method is used for waiting every threads to finish their work.
	 * @param slaves
	 * is the array which contains the threads
	 */
//	private void waitForThreadsToFinish(SingleThread[] slaves) {
//			// aspettiamo che abbiano finito di lavorare
//			for (SingleThread slave: slaves)
//				try {
//					slave.join();
//				}
//				catch (InterruptedException e) {
//					// qualcuno ci ha interrotti mentre aspettavamo
//				}
//				finally{;}
//		}
	/**
	 * This method is used for starting the work of every thread.
	 */
//	private class SingleThread extends Thread{
//			private int yThread;
//			@Override
//			public void run(){
//				do{
//					synchronized(GenThreadsArrayToCell.this){
//						yThread=y;
//						y++;
//					}
//					if(yThread < array.length)
//						for(int x=0; x < array[0].length;++x){
//							campo[yThread][x] = new Cell();
//							campo[yThread][x].setLife(array[yThread][x]);
//					}
//				}
//				while(yThread < array.length);
//			}
//		}
}