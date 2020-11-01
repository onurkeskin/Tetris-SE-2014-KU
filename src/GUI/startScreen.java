import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.Font;
import java.awt.Color;

import javax.swing.SwingConstants;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;

import java.awt.GridLayout;
import java.awt.CardLayout;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import net.miginfocom.swing.MigLayout;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTable;

import com.sun.xml.internal.ws.api.server.Invoker;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class startScreen extends JFrame {
	
	private JPanel contentPane;
	
	public startScreen() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 248, 432);
		contentPane = new JPanel();
		content();
	}
	
	public void content(){
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnPlay = new JButton("PLAY");
		btnPlay.addActionListener(new playAction("Action", KeyEvent.VK_A));
		
		btnPlay.setBounds(44, 32, 148, 65);
		btnPlay.setBackground(Color.LIGHT_GRAY);
		btnPlay.setFont(new Font("Calibri", Font.BOLD, 16));
		contentPane.add(btnPlay);
		
		JButton btnOptons = new JButton("OPTIONS");
		btnOptons.addActionListener(new optionsAction("Action", KeyEvent.VK_A));
		
		btnOptons.setBounds(44, 104, 148, 65);
		btnOptons.setBackground(Color.LIGHT_GRAY);
		btnOptons.setFont(new Font("Calibri", Font.BOLD, 16));
		contentPane.add(btnOptons);
		
		JButton btnScores = new JButton("HIGH SCORES");
		btnScores.addActionListener(new highScoresAction("Action", KeyEvent.VK_A));
		btnScores.setBounds(46, 182, 146, 65);
		btnScores.setBackground(Color.LIGHT_GRAY);
		btnScores.setFont(new Font("Calibri", Font.BOLD, 16));
		contentPane.add(btnScores);
		
		
		JButton btnQuit = new JButton("QUIT");
		btnQuit.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				runGame.frame.dispose();
			}
		});
		btnQuit.setBounds(44, 260, 148, 65);
		btnQuit.setBackground(Color.LIGHT_GRAY);
		btnQuit.setFont(new Font("Calibri", Font.BOLD, 16));
		contentPane.add(btnQuit);
	}
	
	public JPanel getPanel(){return contentPane;}
	
	public JFrame getOuter() {
	    return this;
	}
	
	
	private class optionsAction extends AbstractAction{

		public optionsAction(String name, Integer mnemonic) {
	         super(name);
	         putValue(MNEMONIC_KEY, mnemonic);
	      }
		
		public void actionPerformed(ActionEvent arg0) {
			getContentPane().removeAll();
			getContentPane().repaint();
			options gameOptions = new options();
			gameOptions.setBounds(0, 0, 550,700);
			getOuter().setBounds(0, 0, 550,700);
			getOuter().add(gameOptions);
		}
	}
	
	
	
	private class playAction extends AbstractAction {
		
		
		public playAction(String name, Integer mnemonic) {
	         super(name);
	         putValue(MNEMONIC_KEY, mnemonic);
	      }

	      public void actionPerformed(ActionEvent e) {
				if(GameOptions.getAllPieces().isEmpty()){
					JOptionPane.showMessageDialog(null, "There are no pieces to play with..", "There are no pieces to play with..",JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				getContentPane().removeAll();
				getContentPane().repaint();
	    	  
	    	  ThetrisEngine engine = new ThetrisEngine(new tetrisBlock[GameOptions.getGameSize().x]
																	  [GameOptions.getGameSize().y]);
	    	  		
			
	    	  ThetrisGraphics graphics = new ThetrisGraphics(getOuter(),engine);	
	    	
	    	  
		  		KeyListener listener = new TetrisListener(engine);
				addKeyListener(listener);
				requestFocus();
	    	  
	    	  		Thread th1 = new Thread(engine);
	    	  		Thread th2 = new Thread(graphics);

	    	  		th1.start();
	    	  		th2.start();

	      }
	   }

	private class highScoresAction extends AbstractAction {
		
		public highScoresAction (String name, Integer mnemonic) {
	         super(name);
	         putValue(MNEMONIC_KEY, mnemonic);
	      }

	      public void actionPerformed(ActionEvent e){
	    	  getContentPane().removeAll();
	    	  getContentPane().repaint();
	    	  highScores score = new highScores("scores.txt");
	    	  score.setBounds(0, 0, 400,406);
	    	  getOuter().setBounds(0, 0, 400,406);
	    	  getOuter().add(score);
    	 
	      }
	}
	
}
