package it.univr.GameOfLife;

public class Core {
	/**
	 * Creates a new Cell array of cells.
	 */
	private Cell first[][];
	/** 
	 * This method creates a new field using:
	 * @param numOfThreads
	 * is used for calculating a new casual array
	 * @param dim
	 * is used for setting the size of the array
	 */
	public Core(int numOfThreads, int size){
		GenThreadsCasualArray gen = new GenThreadsCasualArray(numOfThreads, size);
		first = new Cell[gen.getArray().length][gen.getArray()[0].length];
		arrayToCell(gen.getArray());		
	}
	/** 
	 * This method creates a new field using:
	 * @param numOfThreads
	 * not used.
	 * @param init
	 * is used for setting the row's number of the array.
	 */
	public Core(int numOfThreads, boolean[][] init){
		first = new Cell[init.length][init[0].length];
		arrayToCell(init);
	}
	/**
	 * This method is used for taking informations about cell's death.
	 * @param y
	 * is used for knowing the position of cell.
	 * @param x
	 * is used for knowing the position of cell.
	 * @return
	 * returns cell's death status.
	 */
	public boolean isDeath(int y, int x){
		return first[y][x].isDeath();
	}
	/**
	 * This method is used for taking informations about cell's life.
	 * @param y
	 * is used for knowing the position of cell.
	 * @param x
	 * is used for knowing the position of cell.
	 * @return
	 * returns cell's life status.
	 */
	public boolean isLife(int y, int x){
		return first[y][x].isLife();
	}
	/**
	 * This method transforms an array of boolean into an array of cells.
	 * @param init
	 * is used for setting cell's life status.
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
	 * This method is used for transforming cell's status to array.
	 * @return
	 * returns cell's status into the array (true = life, false = death).
	 */
	public boolean [][] getArray(){
		return this.cellToArray(first);
	}
	/**
	 * This used for transforming a cell's array into a boolean array.
	 * @param init
	 * is used for taking information of the cells.
	 * @return
	 * returns the result of conversion (a boolean array).
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
	 * This method is used for killing a cell.
	 * @param row
	 * is used for knowing the exact cell's position.
	 * @param column
	 * is used for knowing the exact cell's position.
	 */
	public void uccidoCell(int row, int column){
		if(first[row][column] != null){
			first[row][column].setDeath(true);
			first[row][column].setLife(false);
		}
	}
	/**
	 * This method is used for killing a cell.
	 * @param row
	 * is used for knowing the exact cell's position.
	 * @param column
	 * is used for knowing the exact cell's position.
	 */
	public void killCell(int row, int column){
		first[row][column].setLife(false);
	}
	/**
	 * This method is used for adding a cell.
	 * @param row
	 * is used for knowing the exact cell's position.
	 * @param column
	 * is used for knowing the exact cell's position.
	 */
	public void addCell(int row, int column){
		setLifeFirstTrue(row,column);
	}
	/**
	 * This method is used for generating the next cell's generation.
	 * @param numOfThreads
	 * is used for calculating cell's next generation.
	 */
	public void nextGenerationThreads(int numOfThreads){
		GenThreadsNextGeneration gen = new GenThreadsNextGeneration(numOfThreads, first);
		first = gen.getFirst();
	}
	/**
	 * This method is used for resetting the main game grid.
	 */
	public void reset(){
		for(int y=0; y < first.length; y++){
			for(int x=0; x < first[0].length ; x++){
				first[y][x] = new Cell(false, false);
			}
		}
	}
	/**
	 * This method is used for adding a Ship into game grid.
	 * @param x
	 * is used for knowing the exact cell's position.
	 * @param y
	 * is used for knowing the exact cell's position.
	 */
	public void addShip(int x, int y){
		setLifeFirstTrue(y,x);
		setLifeFirstTrue(y,x-2);
		setLifeFirstTrue(y,x-1);
		setLifeFirstTrue(y-1,x);
		setLifeFirstTrue(y-2,x-1);
	}
	/**
	 * This method is used for adding a HorizontalBlinker into game grid.
	 * @param y
	 * is used for knowing the exact cell's position.
	 * @param x
	 * is used for knowing the exact cell's position.
	 */
	public void addBlinkerHorizontal(int y, int x){
		setLifeFirstTrue(y,x+1);
		setLifeFirstTrue(y,x-1);
		setLifeFirstTrue(y,x);
	}
	/**
	 * This method is used for adding a VerticalBlinker into game grid.
	 * @param y
	 * is used for knowing the exact cell's position.
	 * @param x
	 * is used for knowing the exact cell's position.
	 */
	public void addBlinkerVertical(int y, int x){		

			setLifeFirstTrue(y+1,x);
			setLifeFirstTrue(y-1,x);
			setLifeFirstTrue(y,x);
	}
	/**
	 * This method is used for adding a Toad into game grid.
	 * @param y
	 * is used for knowing the exact cell's position.
	 * @param x
	 * is used for knowing the exact cell's position.
	 */
	public void addToad(int y, int x){
			this.addBlinkerHorizontal(y, x);
			this.addBlinkerHorizontal(y-1, x-1);
	}
	/**
	 * This method is used for adding a Block into game grid.
	 * @param y
	 * is used for knowing the exact cell's position.
	 * @param x
	 * is used for knowing the exact cell's position.
	 */
	public void addBlock(int y, int x){
		/*
		 * Posiziona il blocco a partire dall'angolo in alto a sinistra
		 */
		setLifeFirstTrue(y,x);
		setLifeFirstTrue(y,x+1);
		setLifeFirstTrue(y-1,x);
		setLifeFirstTrue(y-1,x+1);
	}
	/**
	 * This method is used for adding a Beacon into game grid.
	 * @param y
	 * is used for knowing the exact cell's position.
	 * @param x
	 * is used for knowing the exact cell's position.
	 */
	public void addBeacon(int y, int x){
		
			addBlock(y,x);
			addBlock(y-2,x+2);
	}
	/**
	 * This method is used for adding a Beehive into game grid.
	 * @param y
	 * is used for knowing the exact cell's position.
	 * @param x
	 * is used for knowing the exact cell's position.
	 */
	public void addBeehive(int y, int x){
		setLifeFirstTrue(y,x-1);
		setLifeFirstTrue(y,x+2);
		setLifeFirstTrue(y-1,x);
		setLifeFirstTrue(y-1,x+1);
		setLifeFirstTrue(y+1,x);
		setLifeFirstTrue(y+1,x+1);
	}
	/**
	 * This method is used for adding a Loaf into game grid.
	 * @param y
	 * is used for knowing the exact cell's position.
	 * @param x
	 * is used for knowing the exact cell's position.
	 */
	public void addLoaf(int y, int x){
		setLifeFirstTrue(y,x-1);
		setLifeFirstTrue(y+1,x);
		setLifeFirstTrue(y-1,x);
		setLifeFirstTrue(y-1,x+1);
		setLifeFirstTrue(y-2,x+1);
		setLifeFirstTrue(y,x+2);
		setLifeFirstTrue(y-1,x+2);
	}
	/**
	 * This method is used for adding a Pulsar into game grid.
	 * @param y
	 * is used for knowing the exact cell's position.
	 * @param x
	 * is used for knowing the exact cell's position.
	 */
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
	/**
	 * This method is used for adding a LightWeight into game grid.
	 * @param y
	 * is used for knowing the exact cell's position.
	 * @param x
	 * is used for knowing the exact cell's position.
	 */
	public void addLightweight (int x, int y){	
			setLifeFirstTrue(y-1,x-2);
			setLifeFirstTrue(y-1,x+1);
			setLifeFirstTrue(y+1,x-2);
			this.addBlinkerHorizontal(y+2, x);
			this.addBlinkerVertical(y+1, x+2);

	}
	/**
	 * This method is used for adding a Pulsar into game grid
	 * @param y_
	 * is used for knowing the exact cell's position
	 * @param x_
	 * is used for knowing the exact cell's position
	 */
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
	public void addGlider(int y, int x){
		setLifeFirstTrue(y, x);
		this.addBlinkerVertical(y, x-1);
		this.addBlinkerVertical(y, x-7);
		setLifeFirstTrue(y, x-3);
		setLifeFirstTrue(y+2, x-2);
		setLifeFirstTrue(y-2, x-2);
		setLifeFirstTrue(y-3, x-4);
		setLifeFirstTrue(y-3, x-5);
		setLifeFirstTrue(y+3, x-4);
		setLifeFirstTrue(y+3, x-5);
		setLifeFirstTrue(y+2, x-6);
		setLifeFirstTrue(y-2, x-6);
		addBlock(y+1, x-17);
		addBlinkerVertical(y+2, x+3);
		addBlinkerVertical(y+2, x+4);
		setLifeFirstTrue(y, x+5);
		setLifeFirstTrue(y+4, x+5);
		setLifeFirstTrue(y+5, x+7);
		setLifeFirstTrue(y+4, x+7);
		setLifeFirstTrue(y, x+7);
		setLifeFirstTrue(y-1, x+7);
		addBlock(y+3, x+17);
	}
	/**
	 * This method is used for taking informations about the real X (column) cell's coordinate.
	 * @param column
	 * is used for knowing the exact cell's position.
	 * @return
	 * returns the real X (column) cell's coordinate.
	 */
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
	/**
	 * This method is used for taking informations about the real Y (row) cell's coordinate.
	 * @param row
	 * is used for knowing the exact cell's position.
	 * @return
	 * returns the real Y (row) cell's coordinate.
	 */
	private int realY(int row){
		if(row < 0){
			return first.length + row;
		}
		if(row >= first.length){
			return row - first.length;
		}
		return row;	
	}
	/**
	 * This method is used for setting cell's life to true, but in the real X and Y positions.
	 * @param y
	 * is used for knowing the exact cell's position.
	 * @param x
	 * is used for knowing the exact cell's position.
	 */
	private void setLifeFirstTrue(int y, int x){
		first[realY(y)][realX(x)].setLife(true);
	}
	/*
	 * metodi equals e hashCode
	 */
}
