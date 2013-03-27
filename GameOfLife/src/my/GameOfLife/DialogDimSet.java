package my.GameOfLife;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;
/*
 * Si pensa di cambiarla in JOptionPane
 */
@SuppressWarnings("serial")
public class DialogDimSet extends javax.swing.JDialog implements ActionListener {

		JTextField tf1 = new JTextField(5);
		JButton btnOK = new JButton(" OK ");
		JButton btnCancel = new JButton("Cancella");
		String y_="";
		
		/**
		 * This method is used for creating the dimension frame. It creates a new frame and asks for square's length side.
		 * @param messaggio
		 * is used for setting the custom string
		 */
		public DialogDimSet(String messaggio){
			
		setTitle("Finestra JDialog personalizzata");
		setModal(true);
		
		getContentPane().setLayout(new BorderLayout());
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setLocation(400,300);
		getContentPane().add(new JLabel(messaggio),BorderLayout.NORTH);
		JPanel pannelloInterno = new JPanel();
		
		getContentPane().add(pannelloInterno);
		pannelloInterno.setLayout(new GridLayout(2,2));
		
		pannelloInterno.add(new JLabel("Insert square's length side:"));
		pannelloInterno.add(tf1);
		
		JPanel jp = new JPanel();
		btnOK.addActionListener(this);
		btnCancel.addActionListener(this);
		jp.add(btnOK);
		jp.add(btnCancel);
		getContentPane().add(jp,BorderLayout.SOUTH);
		
		jp.addKeyListener(new KeyListener(){
			@Override
			public void keyTyped(KeyEvent e){
				 if(e.getKeyCode() == KeyEvent.VK_ENTER){
						y_ = tf1.getText();
						dispose();
				 }
			}

			@Override
			public void keyPressed(KeyEvent e) {
				 if(e.getKeyCode() == KeyEvent.VK_ENTER && tf1.getText()!=null){
						y_ = tf1.getText();
						dispose();
				 }
			}

			@Override
			public void keyReleased(KeyEvent e) {
				 if(e.getKeyCode() == KeyEvent.VK_ENTER && tf1.getText()!=null){
						y_ = tf1.getText();
						dispose();
				 }
			}
		});
		pack();
		setVisible(true);
		
		}

		public void keyTyped(KeyEvent e) {}
	       public void keyPressed(KeyEvent e) {}

	    public void keyReleased(KeyEvent e) {}
	    
		public void actionPerformed(ActionEvent ae){
			if(ae.getSource() == btnOK){
				y_ = tf1.getText();
			}
			dispose();
		}
		/**
		 * This method is used for setting new grid dimension
		 * @return output:
		 * returns the dimension
		 */
		public int getData()
		{
			return Integer.parseInt(y_);
		}
	}
