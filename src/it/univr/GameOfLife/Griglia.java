package it.univr.GameOfLife;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Griglia extends JPanel {
	/**
	 * Instantiates the main game boolean array.
	 */
	private boolean[][] array;
	/**
	 * Instantiates main game's field.
	 */
	private Core campo;
	/**
	 * Instantiates the maximum game field bound.
	 */
	private int max;
	/**
	 * Instantiates the main grid's button field.
	 */
	private Button[][] buttons;
	/**
	 * Instantiates the default actionListener (kill Mode)
	 */
	private int actionListenerMode = -1;
	/**
	 * Instantiates the default life grid color. (blue)
	 */
	private Color lifeColor = Color.BLUE;
	/**
	 * Instantiates the default nolife grid color. (white)
	 */
	private Color noLifeColor = Color.WHITE;
	/**
	 * Instantiates the default dead grid color. (red)
	 */
	private Color deathColor = Color.RED;
	/**
	 * This method sets the value into ActionListenerMode, used for adding or killing cells.
	 * @param actionListenerMode
	 * The value -1 is for kill, 1~11 is for adding.
	 */
	public void setActionListenerMode(int actionListenerMode) {
		this.actionListenerMode = actionListenerMode;
	}
	/**
	 * This method is invoked for killing is adding cells, it uses
	 * actionListenerMode that was set by setActionListenerMode(int actionListenerMode).
	 */
	private ActionListener kill = new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					if(e.getSource() instanceof Button){
						
						int y = ((Button)e.getSource()).getRow();
						int x = ((Button)e.getSource()).getColumn();
						
						switch(actionListenerMode){
							case 1:{
									campo.addBlock(y, x);
									array = campo.getArray();
									setColor();
								
								break;	
							}
							case 2:{ 
									campo.addBeehive(y, x);
									array = campo.getArray();
									setColor();
								break;	
							}
							case 3:{
									campo.addLoaf(y, x);
									array = campo.getArray();
									setColor();
								
								break;	
							}
							case 4:{
									campo.addBlinkerHorizontal(y, x);
									array = campo.getArray();
									setColor();
								break;	
							}
							case 5:{  
									campo.addToad(y, x);
									array = campo.getArray();
									setColor();
								break;	
							}
							case 6:{
									campo.addBeacon(y, x);
									array = campo.getArray();
									setColor();
								break;	
							}
							case 7:{
									campo.addPulsar(y, x);
									array = campo.getArray();
									setColor();
								break;	
							}
							case 8:{
								  
									campo.addGlider(y, x);
									array = campo.getArray();
									setColor();
								
								break;	
							}
							case 9:{
								
									campo.addLightweight(y, x);
									array = campo.getArray();
									setColor();
								
								break;	
							}
							case 10:{
								  
									campo.addShip(y, x);
									array = campo.getArray();
									setColor();
								
								break;	
							}
							case 11:{
								  
								campo.addCell(y, x);
								array = campo.getArray();
								setColor();
							
							break;	
						}
							default:
								  
									((Button)e.getSource()).setBackground(deathColor);
									campo.uccidoCell(y,x);
								break;
								
						}
					}
				}
	};
	/**
	 * This constructor builds the grid.
	 * @param campo
	 * is used for taking information about length and settings of current game field.
	 */
	public Griglia(Core campo){
		
		this.campo = campo;
		this.array = this.campo.getArray();
		
		
		max = campo.getArray().length;
		
		buttons = new Button[max][max];
		this.setLayout(new GridLayout(max,max));
		
		for(int y=0; y< max;y++){
			for(int x=0; x < max;x++){
				buttons[y][x] = new Button(y,x);
				buttons[y][x].addActionListener(kill);
				
				if(campo.isDeath(y, x)){
					buttons[y][x].setBackground(deathColor);
				}
				else
					buttons[y][x].setBackground((array[y][x])? lifeColor : noLifeColor);
				
				this.add(buttons[y][x]);
			}
		}
	}
	/**
	 * This method is used for setting the game field.
	 * @param campo
	 * is used for taking information about length and settings of current game field.
	 */
	public void setCampo(Core campo){
		this.campo = campo;
		array = campo.getArray();
		max = campo.getArray().length;
	}
	/**
	 * This method sets the color of the current grid, setting white on cells without life,
	 * blue for cells with life and red for dead cells.
	 */
	public void setColor(){		
		for(int y=0; y< max;++y){
			for(int x=0; x < max;++x){
				if(campo.isDeath(y, x)){
					buttons[y][x].setBackground(deathColor);
				}
				else
					buttons[y][x].setBackground((array[y][x])? lifeColor : noLifeColor);
				}
		}
	}
	/**
	 * This method is used for resetting the current field, setting a clear grid.
	 */
	public void reset(){
		this.campo.reset();
		this.array = campo.getArray();
			for(Button[] button: buttons){
				for(Button button_: button){
					button_.setBackground(noLifeColor);
				}
			}
	}
	/**
	 * This method is used for taking the next generation of current field.
	 * @param numOfThreads
	 * is used for setting the number of threads used for calculating the next generation.
	 */
	public void nextGen(int numOfThreads){
		campo.nextGenerationThreads(numOfThreads);
		this.array = campo.getArray();
	}
	/**
	 * This method is used for getting the current grid color.
	 * @param whatColor
	 * used for asking for a specific color (0=life,1=nolife,2=death)
	 * @return
	 * the default color requested.
	 */
	public Color getColor(int whatColor){
		switch(whatColor){
			case 0: return this.lifeColor;
			case 1: return this.noLifeColor;
			case 2: return this.deathColor;
			default: return null;
		}
	}
	/**
	 * This method is used for setting the selected grid color.
	 * @param whatColor
	 * used for setting a specific color (0=life,1=nolife,2=death)
	 * @param setSelectedColor
	 * used for setting the specific color (rgb).
	 */
	public void setGridColor(int whatColor, Color setSelectedColor){
		switch(whatColor){
			case 0: this.lifeColor = setSelectedColor;
			case 1: this.noLifeColor = setSelectedColor;
			case 2: this.deathColor = setSelectedColor;
		}
	}
	/**
	 * This method is used for taking information about current game field.
	 * @return
	 * returns the current field.
	 */
	public Core getCampo(){
		return campo;
	}
}