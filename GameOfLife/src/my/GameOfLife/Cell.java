package my.GameOfLife;


public class Cell {
	/**
	 * Used for setting life and death status of a cell.
	 */
	private boolean life, death;
	/**
	 * Used for counting the number of cell's neighbors.
	 */
	private int vicini=0;
	/**
	 * This constructor is used for creating a new cell setting the life and death parameters.
	 * @param life
	 * is used for setting life value (true = life, false = no life).
	 * @param death
	 * is used for setting death value (true = death, false = no death).
	 */
	public Cell(boolean life, boolean death){
		this.life = life;
		this.death = death;
	}
	/**
	 * Contructor's default values (life & death set to false).
	 */
	public Cell(){
		life = false;
		death = false;
	}
	/**
	 * This method is used for taking informations about cell's life status.
	 */
	public boolean isLife() {
		return life;
	}
	/**
	 * This method sets the life status of a cell.
	 * @param life
	 * is used for set life status (true = life, false = no life).
	 */
	public void setLife(boolean life) {
		this.life = life;
	}
	/**
	 * This method is used for taking information about cell's death.
	 */
	public boolean isDeath() {
		return death;
	}
	/**
	 * This method sets the death status of a cell.
	 * @param death
	 * is used for set death status (true = death, false = no death).
	 */
	public void setDeath(boolean death) {
		this.death = death;
	}
	/**
	 * Copia i campi della cellula other in this
	 */
	/**
	 * This method is used for taking informations about cell's neighbors
	 * @return
	 * returns the number of cell's neighbors
	 */
	public int getVicini() {
		return vicini;
	}
	/**
	 * This method is used for setting the number of cell's neighbors
	 * @param i
	 * is used for setting the number of cell's neighbors
	 */
	public void setVicini(int i){
		vicini=i;
	}
	/**
	 * This method is used for setting informations about cell's life status
	 * @param other
	 * is used for taking informations from "other" cell (life, death and neighbors)
	 */
	public void copy(Cell other){

		life = other.isLife();
		death = other.isDeath();
		vicini = other.getVicini();
	}
	
}
