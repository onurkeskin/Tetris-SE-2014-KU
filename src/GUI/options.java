import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Enumeration;
import java.util.LinkedList;

import javax.swing.AbstractAction;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;



public class options extends JPanel {
	JPanel panel;
	
	private JTextField rowField;
	private JTextField sizeField;
	private JTextField colField;
	private JLabel listenerStatus;
	
	private JButton btnGoDown;
	private JButton btnGoLeft;
	private JButton btnGoRight;
	private JButton btnRotate;
	private JButton btnGoDownmost;
	private JButton btnPause;
	
	private ActionEvent currentCaller;
	private ButtonGroup radioGroup ;
	private JRadioButton rdbtnCustom;
	private JRadioButton rdbtnTetris;
	private JRadioButton rdbtnTritris;
	private JRadioButton rdbtnBoth;
	private JCheckBox chckbxEnableNewPiece;
	
	/**
	 * Create the panel.
	 */
	public options() {
		setLayout(null);
		
		JLabel lblRows = new JLabel("Rows:");
		lblRows.setBounds(32, 35, 46, 25);
		add(lblRows);
		
		JLabel lblColumns = new JLabel("Columns:");
		lblColumns.setBounds(22, 73, 56, 16);
		add(lblColumns);
		
		rowField = new JTextField();
		rowField.setBounds(80, 29, 56, 30);
		add(rowField);
		rowField.setColumns(10);
		
		panel = new createABlock();
		panel.setBounds(12, 282, 526, 335);
		panel.setEnabled(false);
		add(panel);
		
		sizeField = new JTextField();
		sizeField.setBounds(80, 101, 56, 30);
		add(sizeField);
		sizeField.setColumns(10);
		
		JButton saveChangesButton = new JButton("Save Changes");
		saveChangesButton.addActionListener(new saveChanges("Action", KeyEvent.VK_A));
		saveChangesButton.setBounds(148, 22, 200, 50);
		add(saveChangesButton);
		
		colField = new JTextField();
		colField.setBounds(80, 66, 56, 30);
		add(colField);
		colField.setColumns(10);
		
		JLabel lblBlockSize = new JLabel("Block Size:");
		lblBlockSize.setBounds(12, 108, 67, 16);
		add(lblBlockSize);
		
		JButton btnReturnToGame = new JButton("Return To Game Menu");
		btnReturnToGame.addActionListener(new returnToMenu("Action", KeyEvent.VK_A));
		btnReturnToGame.setBounds(148, 91, 200, 50);
		add(btnReturnToGame);
		
		JButton btnPlay = new JButton("Play ->");
		btnPlay.addActionListener(new play("Action", KeyEvent.VK_A));
		btnPlay.setBounds(465, 44, 73, 118);
		add(btnPlay);
		
		JLabel lblGameDimensions = new JLabel("Game Dimensions");
		lblGameDimensions.setBounds(22, 13, 120, 16);
		add(lblGameDimensions);
		
		JLabel lblListeners = new JLabel("Listeners: Click to Reconfigure");
		lblListeners.setBounds(12, 181, 188, 16);
		add(lblListeners);
		
		listenerStatus = new JLabel("waiting...");
		listenerStatus.setBounds(200, 181, 238, 16);
		add(listenerStatus);
		
		btnGoLeft = new JButton("Go Left");
		btnGoLeft.addActionListener(new changeListemer("Action", KeyEvent.VK_A));
		btnGoLeft.setBounds(12, 229, 73, 25);
		add(btnGoLeft);
		
		btnGoDown = new JButton("Go Down");
		btnGoDown.addActionListener(new changeListemer("Action", KeyEvent.VK_A));
		btnGoDown.setBounds(84, 248, 89, 25);
		add(btnGoDown);
		
		btnGoRight = new JButton("Go Right");
		btnGoRight.addActionListener(new changeListemer("Action", KeyEvent.VK_A));
		btnGoRight.setBounds(169, 229, 81, 25);
		add(btnGoRight);
		
		btnRotate = new JButton("Rotate");
		btnRotate.addActionListener(new changeListemer("Action", KeyEvent.VK_A));
		btnRotate.setBounds(84, 210, 85, 25);
		add(btnRotate);
		
		btnGoDownmost = new JButton("Go Downmost");
		btnGoDownmost.addActionListener(new changeListemer("Action", KeyEvent.VK_A));
		btnGoDownmost.setBounds(262, 229, 120, 25);
		add(btnGoDownmost);
		
		btnPause = new JButton("Pause");
		btnPause.addActionListener(new changeListemer("Action", KeyEvent.VK_A));
		btnPause.setBounds(383, 229, 97, 25);
		add(btnPause);
		
		radioGroup = new ButtonGroup();
		
		rdbtnTetris = new JRadioButton("Tetris");
		rdbtnTetris.addActionListener(new changeGameMode("Action", KeyEvent.VK_A));
		rdbtnTetris.setBounds(375, 91, 82, 25);
		add(rdbtnTetris);
		
		rdbtnTritris = new JRadioButton("Tritris");
		rdbtnTritris.addActionListener(new changeGameMode("Action", KeyEvent.VK_A));
		rdbtnTritris.setBounds(375, 69, 73, 25);
		add(rdbtnTritris);
		
		rdbtnBoth = new JRadioButton("Both");
		rdbtnBoth.addActionListener(new changeGameMode("Action", KeyEvent.VK_A));
		rdbtnBoth.setSelected(true);
		rdbtnBoth.setBounds(375, 116, 67, 25);
		add(rdbtnBoth);
		
		rdbtnCustom = new JRadioButton("Custom");
		rdbtnCustom.addActionListener(new changeGameMode("Action", KeyEvent.VK_A));
		rdbtnCustom.setBounds(375, 138, 73, 25);
		add(rdbtnCustom);
		
		radioGroup.add(rdbtnTetris);
		radioGroup.add(rdbtnTritris);
		radioGroup.add(rdbtnBoth);
		radioGroup.add(rdbtnCustom);
		
		JLabel lblGameMode = new JLabel("Game Mode");
		lblGameMode.setBounds(360, 44, 75, 16);
		add(lblGameMode);
		
		chckbxEnableNewPiece = new JCheckBox("Enable New Piece Creation");
		chckbxEnableNewPiece.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxEnableNewPiece.isSelected()) enablePanel(panel); else disablePanel(panel) ;
			}
		});
		chckbxEnableNewPiece.setToolTipText("Checking will let user add new blocks to a specific game type rather than just Custom");
		chckbxEnableNewPiece.setBounds(273, 159, 200, 25);
		add(chckbxEnableNewPiece);

	}
	
	private JPanel getOuter(){ return this;}
	
	private class saveChanges extends AbstractAction{

		public saveChanges(String name, Integer mnemonic) {
	        super(name);
	        putValue(MNEMONIC_KEY, mnemonic);
	     }
		
		public void actionPerformed(ActionEvent arg0){
			if(!rowField.getText().equals("")){
		   	 if(!Helpers.isInteger(rowField.getText())){
		   		 JOptionPane.showMessageDialog(null, "Rows must be a number. Rows are not Saved", "Rows must be a number",JOptionPane.ERROR_MESSAGE);
		   		 }else{
			   		 GameOptions.setGameSize(new position (GameOptions.getGameSize().x,(Integer.parseInt(rowField.getText()))));
			   	 }
			}
		   	 
			if(!colField.getText().equals("")){
		   	 if(!Helpers.isInteger(colField.getText())) {
		   		JOptionPane.showMessageDialog(null, "Columns must be a number. Columns are not Saved", "Columns must be a number",JOptionPane.ERROR_MESSAGE);
		   	 }else{
		   		 GameOptions.setGameSize(new position (Integer.parseInt(colField.getText()),GameOptions.getGameSize().y));
		   	 }
			}
			
			if(!sizeField.getText().equals("")){
				if(!Helpers.isInteger(sizeField.getText())) {
			   		JOptionPane.showMessageDialog(null, "Block Size must be a number. Block Size is not saved.", "Block Size must be a number",JOptionPane.ERROR_MESSAGE);
			   	 }else{
			   		 GameOptions.blockSize=Integer.parseInt(sizeField.getText());
			   	 }
			}
			
			}
		}

	private class returnToMenu extends AbstractAction{
		
		public returnToMenu(String name, Integer mnemonic) {
	        super(name);
	        putValue(MNEMONIC_KEY, mnemonic);
	     }
		
		public void actionPerformed(ActionEvent arg0){
			runGame.frame.getContentPane().removeAll();
			runGame.frame.content();
			runGame.frame.setBounds(100, 100, 248, 432);
			runGame.frame.getContentPane().repaint();
		}
	}

	private class play extends AbstractAction{

		public play(String name, Integer mnemonic) {
	        super(name);
	        putValue(MNEMONIC_KEY, mnemonic);
	     }
		
		public void actionPerformed(ActionEvent arg0){
			if(GameOptions.getAllPieces().isEmpty()){
				JOptionPane.showMessageDialog(null, "There are no pieces to play with..", "There are no pieces to play with..",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			getOuter().removeAll();
			getOuter().repaint();
    	  
    	  ThetrisEngine engine = new ThetrisEngine(new tetrisBlock[GameOptions.getGameSize().x]
																  [GameOptions.getGameSize().y]);
    	  		
		
    	  ThetrisGraphics graphics = new ThetrisGraphics(runGame.frame.getOuter(),engine);	
    	
    	  
	  		KeyListener listener = new TetrisListener(engine);
			addKeyListener(listener);
			requestFocus();
    	  
    	  		Thread th1 = new Thread(engine);
    	  		Thread th2 = new Thread(graphics);

    	  		th1.start();
    	  		th2.start();

		}
	}

	private class changeListemer extends AbstractAction{
		keyAssigner listener;
		public changeListemer(String name, Integer mnemonic) {
	        super(name);
	        putValue(MNEMONIC_KEY, mnemonic);
	     }
		
		public void actionPerformed(ActionEvent arg0) {
			currentCaller = arg0;
			listenerStatus.setText( "Click On A key on your Keyboard");
			listener = new keyAssigner();
			runGame.frame.addKeyListener(listener);
			runGame.frame.requestFocus();
		}
			
		}
	
	
	private class keyAssigner implements KeyListener {
		
		public void keyTyped(KeyEvent e) {
			
		}
		
		public void keyReleased(KeyEvent e) {
			if(currentCaller!=null){
				if(currentCaller.getSource()==btnGoDown){
					GameOptions.goDown=e.getKeyCode();
					listenerStatus.setText( "waiting");}
				if(currentCaller.getSource()==btnGoLeft){
					GameOptions.goLeft=e.getKeyCode();
					listenerStatus.setText( "waiting");}
				if(currentCaller.getSource()==btnGoRight){
					GameOptions.goRight=e.getKeyCode();
					listenerStatus.setText( "waiting");}
				if(currentCaller.getSource()==btnRotate){
					GameOptions.rotate=e.getKeyCode();
					listenerStatus.setText( "waiting");}
				}
			runGame.frame.removeKeyListener(this);
			
		}
		
		public void keyPressed(KeyEvent e) {
			if(currentCaller!=null){
				if(currentCaller.getSource()==btnGoDown){
					GameOptions.goDown=e.getKeyCode();
					listenerStatus.setText( "waiting");}
				if(currentCaller.getSource()==btnGoLeft){
					GameOptions.goLeft=e.getKeyCode();
					listenerStatus.setText( "waiting");}
				if(currentCaller.getSource()==btnGoRight){
					GameOptions.goRight=e.getKeyCode();
					listenerStatus.setText( "waiting");}
				if(currentCaller.getSource()==btnRotate){
					GameOptions.rotate=e.getKeyCode();
					listenerStatus.setText( "waiting");}
				if(currentCaller.getSource()==btnGoDownmost){
					GameOptions.goMostDown=e.getKeyCode();
					listenerStatus.setText( "waiting");}
				if(currentCaller.getSource()==btnPause){
					GameOptions.pause=e.getKeyCode();
					listenerStatus.setText( "waiting");}
				}
			runGame.frame.removeKeyListener(this);
			
		}
			
		
		
	}


	private class changeGameMode extends AbstractAction{

		public changeGameMode(String name, Integer mnemonic) {
	        super(name);
	        putValue(MNEMONIC_KEY, mnemonic);
	     }
		
		public void actionPerformed(ActionEvent arg0) {
			Enumeration<AbstractButton> buttons = radioGroup.getElements();
			AbstractButton selected = null;
			while(buttons.hasMoreElements()){
				AbstractButton current = buttons.nextElement();
				if(current.isSelected()){selected = current; break;}
			}
			
			if(selected != null){
				chckbxEnableNewPiece.setSelected(false);
				if(selected == rdbtnBoth){
					GameOptions.setPieces(Helpers.getBoth());
					disablePanel(panel);
				}
				if(selected == rdbtnTetris){
					GameOptions.setPieces(Helpers.tetrisPieces());
					disablePanel(panel);
				}
				if(selected == rdbtnTritris){
					GameOptions.setPieces(Helpers.tritixPieces());
					disablePanel(panel);
				}
				if(selected == rdbtnCustom){
					enablePanel(panel);
					GameOptions.setPieces(new LinkedList<GamePiece>());
				}
			}
		}
	}
	
	private void enablePanel(JPanel panel){
		panel.setEnabled(true);
		Component[] componenets = panel.getComponents();
		for(int i = 0;i<componenets.length;i++){
		componenets[i].setEnabled(true);}
	}

	private void disablePanel(JPanel panel){
		panel.setEnabled(false);
		Component[] componenets = panel.getComponents();
		for(int i = 0;i<componenets.length;i++){
		componenets[i].setEnabled(false);}
	}
}
	

