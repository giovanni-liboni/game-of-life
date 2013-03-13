package my.GameOfLife;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


@SuppressWarnings("serial")
public class Griglia extends JPanel {
	
	private boolean[][] array;
	private Core campo;
	private int max;
	private Button[][] buttons;
	private int actionListenerMode = -1;
	private Color gridColor = Color.BLUE;
	private Color lifeColor = Color.RED;
	private Color deathColor = Color.WHITE;
	
	public void setGridColor(int type, Color colorToSet){
		switch(type){
		case 1: this.gridColor = colorToSet;
		case 2: this.lifeColor = colorToSet;
		default: this.deathColor = colorToSet;
		}
	}
	
	// implemento l'action listener per i pulsanti

	public void setActionListenerMode(int actionListenerMode) {
		this.actionListenerMode = actionListenerMode;
	}
	private ActionListener kill = new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					
					int y = ((Button)e.getSource()).getRow();
					int x = ((Button)e.getSource()).getColumn();
					switch(actionListenerMode){
					case 1:{
						if( e.getSource() instanceof Button) {   
							campo.addBlock(y, x);
							array = campo.getArray();
							setColor();
						}
						break;	
					}
					case 2:{
						if( e.getSource() instanceof Button) {   
							campo.addBeehive(y, x);
							array = campo.getArray();
							setColor();
						}
						break;	
					}
					case 3:{
						if( e.getSource() instanceof Button) {   
							campo.addLoaf(y, x);
							array = campo.getArray();
							setColor();
						}
						break;	
					}
					case 4:{
						if( e.getSource() instanceof Button) {   
							//add boat
							array = campo.getArray();
							setColor();
						}
						break;	
					}
					case 5:{
						if( e.getSource() instanceof Button) {   
							campo.addBlinkerHorizontal(y, x);
							array = campo.getArray();
							setColor();
						}
						break;	
					}
					case 6:{
						if( e.getSource() instanceof Button) {   
							campo.addToad(y, x);
							array = campo.getArray();
							setColor();
						}
						break;	
					}
					case 7:{
						if( e.getSource() instanceof Button) {   
							campo.addBeacon(y, x);
							array = campo.getArray();
							setColor();
						}
						break;	
					}
					case 8:{
						if( e.getSource() instanceof Button) {   
							campo.addPulsar(y, x);
							array = campo.getArray();
							setColor();
						}
						break;	
					}
					case 9:{
						if( e.getSource() instanceof Button) {   
							//add glider
							array = campo.getArray();
							setColor();
						}
						break;	
					}
					case 10:{
						if( e.getSource() instanceof Button) {   
							campo.addLightweight(x, y);
							array = campo.getArray();
							setColor();
						}
						break;	
					}
					case 11:{
						if( e.getSource() instanceof Button) {   
							campo.addShip(x, y);
							array = campo.getArray();
							setColor();
						}
						break;	
					}
					default:
						if( e.getSource() instanceof Button) {   
							((Button)e.getSource()).setBackground(deathColor);
							campo.uccidoCell(y,x);
						break;
						}
					}
					
				}
	};
	/**
	 * Costruttore classe Grilia
	 * @param campo Di tipo Core 
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
					buttons[y][x].setBackground((array[y][x])? lifeColor : gridColor);
				
				this.add(buttons[y][x]);
			}
		}
	}
	/**
	 * Setta il campo interno alla griglia con un campo.
	 * Deve essere un'istanza di Core
	 * @param campo
	 */
	public void setCampo(Core campo){
		this.campo = campo;
		array = campo.getArray();
		max = campo.getArray().length;
	}
	/**
	 * Aggiorna il colore del campo
	 */
	public void setColor(){
		
		for(int y=0; y< max;y++){
			for(int x=0; x < max;x++){
				if(campo.isDeath(y, x)){
					buttons[y][x].setBackground(deathColor);
				}
				else
					buttons[y][x].setBackground((array[y][x])? lifeColor : gridColor);
				}
		}
	}
	/**
	 * Metodo di reset del campo
	 */
	public void reset(){
		this.campo.reset();
		this.array = campo.getArray();
			for(Button[] button: buttons){
				for(Button button_: button){
					button_.setBackground(gridColor);
				}
			}
	}
	public void nextGen(int numOfThreads){
		campo.nextGenerationThreads(numOfThreads);
		this.array = campo.getArray();
	}
	public Core getCampo(){
		return campo;
	}

}
