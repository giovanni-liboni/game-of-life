package it.univr.GameOfLife;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Button extends JButton{
	
	private int row, column;
	/**
	 * This method is used for taking information from the button pressed
	 * @param y
	 * is used for setting the button into the panel
	 * @param x
	 * is used for setting the button into the panel
	 */
	public Button(int y, int x){
		this.row = y;
		this.column = x;
		this.setVisible(true);
	}

	/**
	 * This method is used for taking information of current row.
	 * @return
	 * returns current row.
	 */
	public int getRow() {
		return row;
	}


	/**
	 * This method is used for taking information of current column
	 * @return
	 * returns current column
	 */
	public int getColumn() {
		return column;
	}

	
}