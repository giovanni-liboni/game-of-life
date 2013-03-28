package it.univr.GameOfLife;


public class Cell {
	/**
	 * Used for setting life and death status of a cell.
	 */
	private boolean life, death;
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
}