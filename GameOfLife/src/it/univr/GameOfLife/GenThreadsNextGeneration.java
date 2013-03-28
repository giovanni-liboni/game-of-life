package it.univr.GameOfLife;

public class GenThreadsNextGeneration{
	/**
	 * Instantiates the current row.
	 */
	private int y=0;
	/**
	 * Instantiates the main boolean Cell array.
	 */
	private Cell[][] first;
	/**
	 * Instantiates the auxiliary array that will be used for calculating next generation.
	 */
	private Cell[][] last;
	/**
	 * This constructor initializes the "first" and "last" array, copies other in first, creates the threads and waits their finish. Copies the temporary array into first.
	 * @param numOfThreads
	 * is the number of threads used for the operations.
	 * @param other
	 * is the array of current cell's array.
	 */
	public GenThreadsNextGeneration(int numOfThreads, Cell[][] other){
		/*
		 * Inizializzo i due array
		 */
		first = new Cell[other.length][other[0].length];
		last = new Cell[other.length][other[0].length];
		/*
		 * Inizializzo ogni singola cella
		 */
		for(int y_=0; y_ < other.length; ++y_){
			for(int x_=0; x_ < other[0].length ; ++x_){
				first[y_][x_] = new Cell();
				last[y_][x_] = new Cell();
			}
		}
		/*
		 * Copio i due array
		 */
		first = other;
		/*
		 * Creo le threads per il lavoro
		 */
		SingleThread[] slaves = createThreads(numOfThreads);
		/*
		 * Aspetto che le thread abbiano finito
		 */
		waitForThreadsToFinish(slaves);
		/*
		 * Copio i due array, in questo modo la versione definitiva
		 * sar� in first
		 */
		first = last;
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
		for(int pos=0; pos < numOfThreads; ++pos){
			(slaves[pos] = new SingleThread()).start();
		}
		return slaves;
	}
	/**
	 * This method is used for waiting others threads to finish their work.
	 * @param slaves
	 * are the threads used for working.
	 */
	private void waitForThreadsToFinish(SingleThread[] slaves) {
		
		for (SingleThread slave: slaves)
			try {
				slave.join();
			}
			catch (InterruptedException e) {}
			finally{;}
	}
	/**
	 * This method is used for starting every single thread for the rispective work.
	 */
	private class SingleThread extends Thread{
		int yThread;
		@Override
		public void run(){
			do{
				synchronized(GenThreadsNextGeneration.this){
					yThread=y;
					y++;
				}
				if(yThread < first.length)
					calcoloRiga(yThread);
			}
			while(yThread < first.length);
		}
	}
	/**
	 * This method is used for calculating the number of rows.
	 * @param y
	 * is the number of rows.
	 */
	private void calcoloRiga(int y){
		for(int x= 0; x<first[0].length;++x){
			if(first[y][x].isDeath()){
				last[y][x] = new Cell(false, true);
			}
			else{
				int i = this.contCellule(y, x);
				if((i == 3) || (i==2 && first[y][x].isLife())){
					last[y][x].setLife(true);

				}
				else {
					last[y][x].setLife(false);
				}
			}
		}
	}
	/**
	 * This method is used for calculating the number of cells into the main game grid.
	 * @param rows
	 * is the number of rows used for calculating the number of cells.
	 * @param columns
	 * is the number of columns used for calculating the number of cells.
	 * @return
	 * returns the number of cells into the main game grid.
	 */
	private int contCellule(int rows, int columns){
		int res=0;
		
		int dx = (columns >= first[0].length-1) ? 0 : columns+1;
		int sx = (columns <= 0) ? first[0].length-1 : columns-1;
		
		int up = (rows <= 0) ? first.length-1 : rows-1;
		int down = (rows >= first.length-1) ? 0 : rows+1;
		
		if(first[up][columns].isLife()) 
			res++;
		if(first[up][sx].isLife()) 
			res++;
		if(first[rows][sx].isLife()) 
			res++;
		if(first[down][sx].isLife()) 
			res++;
		if(first[down][columns].isLife()) 
			res++;
		if(first[down][dx].isLife()) 
			res++;
		if(first[rows][dx].isLife()) 
			res++;
		if(first[up][dx].isLife())
			res++;
		
		return res;
	}
	/**
	 * This method is used for getting the first cell array.
	 * @return
	 * returns the first cell array.
	 */
	public Cell[][] getFirst() {
		return first;
	}
}