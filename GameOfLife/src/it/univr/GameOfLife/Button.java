package it.univr.GameOfLife;

import javax.swing.JButton;

@SuppressWarnings("serial")
public class Button extends JButton{
	
	private int row, column;
	
	public Button(int y, int x){
		this.row = y;
		this.column = x;
		this.setVisible(true);
	}


	public int getRow() {
		return row;
	}


	public void setRow(int row) {
		this.row = row;
	}


	public int getColumn() {
		return column;
	}


	public void setColumn(int column) {
		this.column = column;
	}
	
}
