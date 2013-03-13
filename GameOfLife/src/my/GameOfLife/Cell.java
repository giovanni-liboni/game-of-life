package my.GameOfLife;


public class Cell {
	private boolean life, death;
	private int vicini=0;
	/**
	 * Costruttore per creare una nuova cellula impostando i parametri
	 * life e death
	 */
	public Cell(boolean life, boolean death){
		this.life = life;
		this.death = death;
	}
	/**
	 * Costruttore di default
	 */
	public Cell(){
		life = false;
		death = false;
	}
	/**
	 * Metodo che ritorna lo stato della cellula
	 * true  -> la cellula è viva
	 * false -> la cellula è morta
	 */
	public boolean isLife() {
		return life;
	}
	/**
	 * Setta lo stato della cellula
	 */
	public void setLife(boolean life) {
		this.life = life;
	}
	/**
	 * Metodo per sapere se una cellula è morta
	 * se posto a true allora la cellula è morta definitivamente
	 */
	public boolean isDeath() {
		return death;
	}
	/**
	 * Setta un boolean sulla morte della cellula
	 * true -> la cellula è morta definitivamente
	 * false-> la cellula non è morta
	 */
	public void setDeath(boolean death) {
		this.death = death;
	}
	/**
	 * Copia i campi della cellula other in this
	 */
	public int getVicini() {
		return vicini;
	}
	public void setVicini(int i){
		vicini=i;
	}
	public void copy(Cell other){

		life = other.isLife();
		death = other.isDeath();
		vicini = other.getVicini();
	}
	
}
