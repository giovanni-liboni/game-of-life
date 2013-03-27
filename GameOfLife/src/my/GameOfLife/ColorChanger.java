package my.GameOfLife;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class ColorChanger extends JFrame{
	/**
	 * Used for creating a new frame with a custom title.
	 */
	private JFrame changeColor = new JFrame("Choose your favorite color!");
	/**
	 * Used for taking the ContentPane from the new frame.
	 */
	private Container changeContainer = changeColor.getContentPane();
	/**
	 * This method is used for creating a new frame, which contains the color selection for life, no-life and death status.
	 * There's also a random button, which choose randomly a new color set (and meanwhile sets it to the game frame), a set button
	 * (self-understanding, it also close ColorChanger's frame), and a default button that sets cell's colors to the 
	 * default value (blue to life, white to no-life and red to death).
	 * @param panel
	 * is used for taking information and setting values about Cell's color.
	 */
	public ColorChanger(final Griglia panel){
		changeColor.setBounds(50, 50, 300, 200);
		changeColor.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		changeContainer.setLayout(new GridLayout(3, 3));
		/**
		 * Creates a new array of JLabel that will be used in the frame.
		 */
		JLabel[] chooseLabels = {new JLabel("Life",JLabel.CENTER), new JLabel("No Life",JLabel.CENTER), new JLabel("Death",JLabel.CENTER)};
		/**
		 * Creates a new array of JButton that will be used in the frame.
		 */
		final JButton[] chooseButtons = {new JButton(), new JButton(), new JButton(), new JButton("Random"), new JButton("Set"), new JButton("Default")};
		for(int pos=0;pos<chooseLabels.length;pos++)
			changeContainer.add(chooseLabels[pos]);
		for(int pos=0;pos<3;pos++){
			changeColor.add(chooseButtons[pos]);
			final int pos3 = pos;
			chooseButtons[pos].setBackground(panel.getColor(pos));
			chooseButtons[pos].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Color selectedColor = JColorChooser.showDialog(changeColor, "Pick your favorite color", panel.getColor(pos3));
					if (selectedColor != null)
	                {
						chooseButtons[pos3].setBackground(selectedColor);
						for(int pos4=0;pos4<3;pos4++){
						panel.setGridColor(pos4, chooseButtons[pos4].getBackground());
						panel.setColor();
						panel.repaint();}
	                }
				}
			});
			changeContainer.add(chooseButtons[3]);
			chooseButtons[3].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Random random = new Random();
					for(int pos=0; pos<3;pos++){
						int r=random.nextInt(256), g=random.nextInt(256), b=random.nextInt(256);
						chooseButtons[pos].setBackground(new Color(r,g,b));
						panel.setGridColor(pos, chooseButtons[pos].getBackground());
						panel.setColor();
						panel.repaint();
					}
				}
			});
			changeContainer.add(chooseButtons[4]);
			chooseButtons[4].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					for(int pos = 0; pos<3; pos++){
						panel.setGridColor(pos, chooseButtons[pos].getBackground());
						panel.setColor();
						panel.repaint();
						changeColor.dispose();
					}
				}
			});
			changeContainer.add(chooseButtons[5]);
			chooseButtons[5].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					chooseButtons[0].setBackground(Color.BLUE);
					chooseButtons[1].setBackground(Color.WHITE);
					chooseButtons[2].setBackground(Color.RED);
					for(int pos = 0; pos<3; pos++){
						panel.setGridColor(pos, chooseButtons[pos].getBackground());
						panel.setColor();
						panel.repaint();
					}
				}
			});
		}
		
		changeColor.setVisible(true);
		
		}
	
}
