package my.GameOfLife;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class ColorChanger extends JFrame{
	private JFrame changeColor = new JFrame("Choose your favorite color!");
	private Container changeContainer = changeColor.getContentPane();

	public ColorChanger(final Griglia panel){
		changeColor.setBounds(50, 50, 300, 200);
		changeColor.setDefaultCloseOperation(changeColor.DISPOSE_ON_CLOSE);
		changeContainer.setLayout(new GridLayout(3, 3));
		JLabel[] chooseLabels = {new JLabel("Life",JLabel.CENTER), new JLabel("No Life",JLabel.CENTER), new JLabel("Death",JLabel.CENTER)};
		final JButton[] chooseButtons = {new JButton(), new JButton(), new JButton(), new JButton("Random"), new JButton("Set")};
		for(int pos=0;pos<chooseLabels.length;pos++)
			changeContainer.add(chooseLabels[pos]);
		for(int pos=0;pos<3;pos++){
			changeColor.add(chooseButtons[pos]);
			final int pos3 = pos;
			chooseButtons[pos].setBackground(panel.getColor(pos));
			chooseButtons[pos].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Color selectedColor = JColorChooser.showDialog(changeColor, "Pick your favorite color", panel.getColor(0));
					if (selectedColor != null)
	                {
						chooseButtons[pos3].setBackground(selectedColor);
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
					}
				}
			});
			changeContainer.add(chooseButtons[4]);
			chooseButtons[4].addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					for(int pos = 0; pos<3; pos++){
						panel.setGridColor(pos, chooseButtons[pos].getBackground());
						panel.setVisible(false);
						panel.setVisible(true);
						panel.setColor();
						panel.repaint();
						changeColor.dispose();
					}
				}
			});
//			changeContainer.add(chooseButtons[pos]);
		}
		
		changeColor.setVisible(true);
		
	}
	
}