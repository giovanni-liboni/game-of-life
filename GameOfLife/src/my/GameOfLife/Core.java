package my.GameOfLife;

public class Core {
	private int cellOff=0;
	private Cell first[][];
	/** 
	 * Crea un campo con n° threads, righe e colonne specificate come argomento
	 * @param numOfThreads
	 * @param rows
	 * @param columns
	 */
	public Core(int numOfThreads, int rows, int columns){
		GenThreadsCasualArray gen = new GenThreadsCasualArray(numOfThreads, rows, columns);
		first = new Cell[gen.getArray().length][gen.getArray()[0].length];
		arrayToCell(gen.getArray());
		
	}
	/**
	 * Costruttore con parametro un array di boolean
	 * @param init
	 */
	public Core(boolean[][] init){
		first = new Cell[init.length][init[0].length];
		arrayToCell(init);
	}
	/**
	 * Restituisce il numero di cellule morte
	 */
	public int getCellOff(){
		return cellOff;
	}
	/**
	 * Ritorna true solo se la cellula è morta definitivamente.
	 * @param y rows
	 * @param x columns
	 * @return boolean 
	 *
	 */
	public boolean isDeath(int y, int x){
		return first[y][x].isDeath();
	}
	/**
	 * Ritorna true solo se la cellula è viva.
	 * @param y rows
	 * @param x columns
	 * @return boolean 
	 *
	 */
	public boolean isLife(int y, int x){
		return first[y][x].isLife();
	}

	/**
	 * Trasforma un array di boolean in un array di cellule
	 * @param init
	 */
	private void arrayToCell(boolean[][] init){
		for(int y=0; y < init.length; y++){
			for(int x=0; x < init[0].length ; x++){
				first[y][x] = new Cell();
				first[y][x].setLife(init[y][x]);
			}
		}
	}
	/**
	 * @return Rapprensentazione tramite array si boolean delle cellule vive
	 * ,dove true -> cellula viva,
	 * false -> cellula morta
	 * 
	 */
	public boolean [][] getArray(){
		return this.cellToArray(first);
	}
	/**
	 * Metodo per trasformare un array di Cell in un array di boolean
	 * @param Cell [][] init
	 * @return boolean[][]
	 */
	private boolean[][] cellToArray(Cell[][] init){
		boolean[][] res = new boolean [init.length][init[0].length];
		for(int y=0; y < init.length; y++){
			for(int x=0; x < init[0].length ; x++){
				
				if(!init[y][x].isDeath())
					res[y][x] = init[y][x].isLife();
				else{
					res[y][x] = false;
				}
			}
		}
		return res;
	}
	/**
	 * Funzione per l'uccisione della cellula
	 * Se si vuole potrebbe ritornare un boolean se la cellula è già morta.
	 * @param y Rows
	 * @param x Columns
	 */
	public void uccidoCell(int y, int x){
		if(first[y][x] != null){
			first[y][x].setDeath(true);
			first[y][x].setLife(false);
		}
	}
	public void nextGenerationThreads(int numOfThreads){
		cellOff=0;
		GenThreadsNextGeneration gen = new GenThreadsNextGeneration(numOfThreads, first);
		cellOff = gen.getCellOff();
		first = gen.getFirst();
	}
	/**
	 * Funzione per il reset della matrice principale
	 */
	public void reset(){
		for(int y=0; y < first.length; y++){
			for(int x=0; x < first[0].length ; x++){
				first[y][x] = new Cell(false, false);
			}
		}
	}
	public void addShip(int x, int y){
		if((y-2 >= 0 && y < first.length) && (x-2 >= 0 && x < first[0].length)){
			
			first[y][x].setLife(true);
			first[y][x-2].setLife(true);
			first[y][x-1].setLife(true);
			first[y-1][x].setLife(true);
			first[y-2][x-1].setLife(true);
		}
	}
	public void addBlinkerHorizontal(int y, int x){
		if((y >= 0 && y < first.length) && (x-1 >= 0 && x+1 < first[0].length)){
			first[y][x].setLife(true);
			first[y][x-1].setLife(true);
			first[y][x+1].setLife(true);
		}
	}
	public void addBlinkerVertical(int y, int x){
		if((y-1 >= 0 && y+1 < first.length) && (x >= 0 && x < first[0].length)){
			first[y][x].setLife(true);
			first[y-1][x].setLife(true);
			first[y+1][x].setLife(true);
		}
	}
	public void addToad(int y, int x){
		if((y-1 >= 0 && y < first.length) && (x-1 >= 0 && x < first[0].length)){
			this.addBlinkerHorizontal(y, x);
			this.addBlinkerHorizontal(y-1, x-1);
		}
	}
	public void addBlock(int y, int x){
		/*
		 * Posiziona il blocco a partire dall'angolo in alto a sinistra
		 */
		if((y-1 >= 0 && y < first.length) && (x >= 0 && x+1 < first[0].length)){
			first[y][x].setLife(true);
			first[y][x+1].setLife(true);
			first[y-1][x].setLife(true);
			first[y-1][x+1].setLife(true);
		}
	}
	public void addBeacon(int y, int x){
		if((y-2 >= 0 && y < first.length) && (x >= 0 && x+2 < first[0].length)){
			
			addBlock(y,x);
			addBlock(y-2,x+2);
		}
	}
	public void addBeehive(int y, int x){
		if((y-1 >= 0 && y+1 < first.length) && (x-1 >= 0 && x+2 < first[0].length)){
			
			first[y][x-1].setLife(true);
			first[y][x+2].setLife(true);
			first[y-1][x].setLife(true);
			first[y-1][x+1].setLife(true);
			first[y+1][x].setLife(true);
			first[y+1][x+1].setLife(true);
		}
	}
	public void addLoaf(int y, int x){
		if((y-2 >= 0 && y+1 < first.length) && (x-1 >= 0 && x+2 < first[0].length)){
			
			first[y][x-1].setLife(true);
			first[y+1][x].setLife(true);
			first[y-1][x].setLife(true);
			first[y-1][x+1].setLife(true);
			first[y-2][x+1].setLife(true);
			first[y][x+2].setLife(true);
			first[y-1][x+2].setLife(true);
			
		}
	}
	public void addMyPulsar(int y, int x){
		if((y-6 >= 0 && y+6 < first.length) && (x-6 >= 0 && x+6 < first[0].length)){
			
			this.addBlinkerHorizontal(y-1, x+3);
			this.addBlinkerHorizontal(y-6, x+3);
			this.addBlinkerVertical(y-3, x+1);
			this.addBlinkerVertical(y-3, x+5);
			
			this.addBlinkerHorizontal(y+1, x+3);
			this.addBlinkerHorizontal(y+6, x+3);
			this.addBlinkerVertical(y+3, x+1);
			this.addBlinkerVertical(y+3, x+5);
			
			this.addBlinkerHorizontal(y-1, x-3);
			this.addBlinkerHorizontal(y-6, x-3);
			this.addBlinkerVertical(y-3, x-1);
			this.addBlinkerVertical(y-3, x-5);
			
			this.addBlinkerHorizontal(y+1, x-3);
			this.addBlinkerHorizontal(y+6, x-3);
			this.addBlinkerVertical(y+3, x-1);
			this.addBlinkerVertical(y+3, x-5);
			
			
		}
	}
	public void addLightweight (int x, int y){
		if((y-2 >= 0 && y+1 < first.length) && (x-1 >= 0 && x+2 < first[0].length)){
			
			first[y-1][x-2].setLife(true);
			first[y-1][x+1].setLife(true);
			first[y+1][x-2].setLife(true);
			this.addBlinkerHorizontal(y+2, x);
			this.addBlinkerVertical(y+1, x+2);
			
		}
	}
	public void addPulsar(int y, int x){
		
		if((y-6 >= 0 && y-6 < first.length) && (x-6 >= 0 && x+6 < first[0].length)){
			this.addBlinkerHorizontal(y-1, x+3);
			this.addBlinkerHorizontal(y-6, x+3);
			this.addBlinkerVertical(y-3, x+1);
			this.addBlinkerVertical(y-3, x+6);
			
			this.addBlinkerHorizontal(y+1, x+3);
			this.addBlinkerHorizontal(y+6, x+3);
			this.addBlinkerVertical(y+3, x+1);
			this.addBlinkerVertical(y+3, x+6);
			
			this.addBlinkerHorizontal(y-1, x-3);
			this.addBlinkerHorizontal(y-6, x-3);
			this.addBlinkerVertical(y-3, x-1);
			this.addBlinkerVertical(y-3, x-6);
			
			this.addBlinkerHorizontal(y+1, x-3);
			this.addBlinkerHorizontal(y+6, x-3);
			this.addBlinkerVertical(y+3, x-1);
			this.addBlinkerVertical(y+3, x-6);
			
		}
	}
	/*
	 * metodi equals e hashCode
	 */
}
