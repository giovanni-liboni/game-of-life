package it.univr.GameOfLife;

/**
 * http://www.drdobbs.com/jvm/an-algorithm-for-compressing-space-and-t/184406478
 * Da implementare
 * @author Giovanni
 *
 */
public class GenThreadsNextGeneration{
	/**
	 * Intero per la memoriazzazione della riga corrente
	 */
	private int y=0;
	/**
	 * Array di Cell primario
	 */
	private Cell[][] first;
	/**
	 * Intero per conteggiare le cellule nello stato false
	 */
	private int cellOff=0;
	/**
	 * Array di supporto per il calcolo della Next Generation
	 */
	private Cell[][] last;
	/**
	 * Inizializza gli arrays first e last, copia other in first, crea le threads e aspetta la loro fine. Copia l'array temporaneo in first.
	 * @param numOfThreads
	 * @param other
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
		 * sarà in first
		 */
		first = last;
	}
	private SingleThread[] createThreads(int numOfThreads){
		SingleThread[] slaves = new SingleThread[numOfThreads];
		for(int pos=0; pos < numOfThreads; ++pos){
			(slaves[pos] = new SingleThread()).start();
		}
		return slaves;
	}
	private void waitForThreadsToFinish(SingleThread[] slaves) {
		
		for (SingleThread slave: slaves)
			try {
				slave.join();
			}
			catch (InterruptedException e) {}
	}
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
	private void calcoloRiga(int y){
		for(int x= 0; x<first[0].length;++x){
			if(first[y][x].isDeath()){
				last[y][x] = new Cell(false, true);
				synchronized(GenThreadsNextGeneration.this){
					cellOff++;
				}
			}
			else{
				int i = this.contCellule(y, x);
				if((i == 3) || (i==2 && first[y][x].isLife())){
					last[y][x].setLife(true);

				}
				else {
					last[y][x].setLife(false);
					synchronized(GenThreadsNextGeneration.this){
						cellOff++;
					}
				}
			}
		}
	}
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
	public Cell[][] getFirst() {
		return first;
	}
	public int getCellOff() {
		return cellOff;
	}

}
