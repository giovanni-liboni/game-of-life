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
	public Core(int numOfThreads, boolean[][] init){
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
	 * @param row Rows
	 * @param column Columns
	 */
	public void uccidoCell(int row, int column){
		if(first[row][column] != null){
			first[row][column].setDeath(true);
			first[row][column].setLife(false);
		}
	}
	public void killCell(int row, int column){
		first[row][column].setLife(false);
	}
	
	public void addCell(int row, int column){
		setLifeFirstTrue(row,column);
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
		setLifeFirstTrue(y,x);
		setLifeFirstTrue(y,x-2);
		setLifeFirstTrue(y,x-1);
		setLifeFirstTrue(y-1,x);
		setLifeFirstTrue(y-2,x-1);
	}
	public void addBlinkerHorizontal(int y, int x){
		setLifeFirstTrue(y,x+1);
		setLifeFirstTrue(y,x-1);
		setLifeFirstTrue(y,x);
	}
	public void addBlinkerVertical(int y, int x){		

			setLifeFirstTrue(y+1,x);
			setLifeFirstTrue(y-1,x);
			setLifeFirstTrue(y,x);
	}
	public void addToad(int y, int x){
			this.addBlinkerHorizontal(y, x);
			this.addBlinkerHorizontal(y-1, x-1);
	}
	public void addBlock(int y, int x){
		/*
		 * Posiziona il blocco a partire dall'angolo in alto a sinistra
		 */
		setLifeFirstTrue(y,x);
		setLifeFirstTrue(y,x+1);
		setLifeFirstTrue(y-1,x);
		setLifeFirstTrue(y-1,x+1);
	}
	public void addBeacon(int y, int x){
		
			addBlock(y,x);
			addBlock(y-2,x+2);
	}
	public void addBeehive(int y, int x){
		setLifeFirstTrue(y,x-1);
		setLifeFirstTrue(y,x+2);
		setLifeFirstTrue(y-1,x);
		setLifeFirstTrue(y-1,x+1);
		setLifeFirstTrue(y+1,x);
		setLifeFirstTrue(y+1,x+1);
	}
	public void addLoaf(int y, int x){
		setLifeFirstTrue(y,x-1);
		setLifeFirstTrue(y+1,x);
		setLifeFirstTrue(y-1,x);
		setLifeFirstTrue(y-1,x+1);
		setLifeFirstTrue(y-2,x+1);
		setLifeFirstTrue(y,x+2);
		setLifeFirstTrue(y-1,x+2);		
	}
	public void addMyPulsar(int y, int x){
			
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
	public void addLightweight (int x, int y){			
			setLifeFirstTrue(y-1,x-2);
			setLifeFirstTrue(y-1,x+1);
			setLifeFirstTrue(y+1,x-2);
			this.addBlinkerHorizontal(y+2, x);
			this.addBlinkerVertical(y+1, x+2);

	}
	public void addPulsar(int y_, int x_){

			this.addBlinkerHorizontal(y_-1, x_+3);
			this.addBlinkerHorizontal(y_-6, x_+3);
			this.addBlinkerVertical(y_-3, x_+1);
			this.addBlinkerVertical(y_-3, x_+6);
			
			this.addBlinkerHorizontal(y_+1, x_+3);
			this.addBlinkerHorizontal(y_+6, x_+3);
			this.addBlinkerVertical(y_+3, x_+1);
			this.addBlinkerVertical(y_+3, x_+6);
			
			this.addBlinkerHorizontal(y_-1, x_-3);
			this.addBlinkerHorizontal(y_-6, x_-3);
			this.addBlinkerVertical(y_-3, x_-1);
			this.addBlinkerVertical(y_-3, x_-6);
			
			this.addBlinkerHorizontal(y_+1, x_-3);
			this.addBlinkerHorizontal(y_+6, x_-3);
			this.addBlinkerVertical(y_+3, x_-1);
			this.addBlinkerVertical(y_+3, x_-6);
	}
	// columns
	private int realX(int column){
		if(column < 0){
			return first[0].length + column;
		}
		if(column >= first[0].length){
			return column - first[0].length;
		}
		return column;
	}
	// rows
	private int realY(int row){
		if(row < 0){
			return first.length + row;
		}
		if(row >= first.length){
			return row - first.length;
		}
		return row;	
	}
	private void setLifeFirstTrue(int y, int x){
		first[realY(y)][realX(x)].setLife(true);
	}
	/*
	 * metodi equals e hashCode
	 */
}
