import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.Vector;

import javax.swing.AbstractAction;
import javax.swing.JColorChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.table.DefaultTableModel;

import org.omg.CORBA.Environment;








import javax.swing.SwingConstants;
import javax.swing.JTextArea;


public class createABlock extends JPanel {
	private JTable table;
	private JTextField textSizefield;
	LinkedList<position> selectedCells = new LinkedList<position>();
	LinkedList<Color> selectedColors = new LinkedList<Color>();
	
	private Color currentColor;
	private JTextArea currColor;
	private JTextField textX;
	private JTextField textY;
	/**
	 * Create the panel.
	 */
	public createABlock() {
		setLayout(null);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
				{null, null, null},
				{null, null, null},
			},
			new String[] {
				"Columns", "Columns", "Columns"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class
			};
			public Class getColumnClass() {
				return columnTypes[1];
			}
		});
		table.getColumnModel().getColumn(0).setMinWidth(28);
		table.setToolTipText("Click On a Cell to color it");
		table.setCellSelectionEnabled(true);
		table.setColumnSelectionAllowed(true);
		table.setBounds(12, 128, 263, 179);
		table.setRowHeight(table.getHeight()/table.getColumnCount());
		add(table);
		
		JLabel lblNewPieceRow = new JLabel("New Piece Size Number:");
		lblNewPieceRow.setBounds(12, 61, 148, 16);
		add(lblNewPieceRow);
		
		textSizefield = new JTextField();
		textSizefield.setBounds(159, 58, 116, 22);
		add(textSizefield);
		textSizefield.setColumns(10);
		
		JButton btnCreateTable = new JButton("Create Table");
		btnCreateTable.addActionListener(new createTable("Action", KeyEvent.VK_A));
		btnCreateTable.setBounds(159, 93, 125, 22);
		add(btnCreateTable);
		
		JButton btnAddPiece = new JButton("add Piece");
		btnAddPiece.addActionListener(new addPiece("Action", KeyEvent.VK_A));
		btnAddPiece.setBounds(320, 275, 109, 32);
		add(btnAddPiece);
		
		JButton btnNewButton = new JButton("Choose Color");
		btnNewButton.addActionListener(new pickAColor("Action", KeyEvent.VK_A));
		
		btnNewButton.setBounds(295, 178, 148, 41);
		add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Current Choosen Color:");
		lblNewLabel.setBounds(284, 140, 148, 16);
		add(lblNewLabel);
		
		currColor = new JTextArea();
		currColor.setEditable(false);
		currColor.setText("null");
		currColor.setBounds(424, 124, 55, 53);
		add(currColor);
		
		JLabel lblMainPiecePosition = new JLabel("Main Piece Position:");
		lblMainPiecePosition.setBounds(281, 232, 116, 16);
		add(lblMainPiecePosition);
		
		JLabel lblX = new JLabel("x:");
		lblX.setBounds(398, 232, 20, 16);
		add(lblX);
		
		JLabel lblY = new JLabel("y:");
		lblY.setBounds(436, 232, 20, 16);
		add(lblY);
		
		textX = new JTextField();
		textX.setBounds(409, 229, 26, 22);
		add(textX);
		textX.setColumns(10);
		
		textY = new JTextField();
		textY.setColumns(10);
		textY.setBounds(453, 229, 26, 22);
		add(textY);
		
		
		

		
	 MouseListener tableMouseListener = new MouseAdapter() {
	      public void mouseClicked(MouseEvent e) {
	            int row = table.rowAtPoint(e.getPoint());//get mouse-selected row
	            int col = table.columnAtPoint(e.getPoint());//get mouse-selected col
	            position newEntry = new position(row,col);//{row,col}=selected cell
	            if(row != -1 && col != -1){
	            
	            int checker = -1;
	            for(int i= 0;i<selectedCells.size();i++){
	            	if(newEntry.equals(selectedCells.get(i))){
	            		checker = i;
	            	}
	            }
	            if(checker != -1){
	               //cell was already selected, deselect it
	               table.setValueAt("",row, col);
	               selectedCells.remove(checker);
	               selectedColors.remove(checker);
	            }else{
	               //cell was not selected
	            	if(currentColor != null){
	               table.setValueAt("r:"+currentColor.getAlpha()+
	            		            "g:"+currentColor.getGreen()+
	            		            "b:"+currentColor.getBlue(),row, col);
	               selectedCells.add(newEntry);
	               selectedColors.add(currentColor);}
	            }
	         }
	      }
	   };
	   table.addMouseListener(tableMouseListener);
	   
	}

	
	
private class pickAColor extends AbstractAction {
		
		public pickAColor(String name, Integer mnemonic) {
	         super(name);
	         putValue(MNEMONIC_KEY, mnemonic);
	      }

	      public void actionPerformed(ActionEvent e) {
	    	 Color newColor = JColorChooser.showDialog(
	                     createABlock.this,"Choose A Color For Block",Color.black);
	        
	    	 if(newColor != null){
	        	 currentColor = newColor;
	        	 currColor.setText("r:"+currentColor.getAlpha()+
		            "\ng:"+currentColor.getGreen()+
		            "\nb:"+currentColor.getBlue());
	        	 
	         }
	      }
	   }

private class addPiece extends AbstractAction{
	public addPiece(String name, Integer mnemonic) {
        super(name);
        putValue(MNEMONIC_KEY, mnemonic);
     }

     public void actionPerformed(ActionEvent e) {
   	 if(!Helpers.isInteger(textX.getText())){
   		 JOptionPane.showMessageDialog(null, "X must be a number", "X must be a number",JOptionPane.ERROR_MESSAGE);
   		 return;
   		 }
   	 
   	 if(!Helpers.isInteger(textY.getText())) {
   		JOptionPane.showMessageDialog(null, "Y must be a number", "Y must be a number",JOptionPane.ERROR_MESSAGE);
   		return;
   	 }
   			 
   	if(selectedCells.isEmpty()){
   		JOptionPane.showMessageDialog(null, "Manipulate the table first", "Manipulate the table first",JOptionPane.ERROR_MESSAGE);
   		return;
   	}
   		 
   		 boolean continueAdd = false;
   		 position mainPos = new position(Integer.parseInt(textX.getText()),Integer.parseInt(textY.getText()));
   	  for(int i= 0;i<selectedCells.size();i++){
      	if(mainPos.equals(selectedCells.get(i))){
      		continueAdd = true;
      	}
      }
   	  
   	  if(!continueAdd){
   		JOptionPane.showMessageDialog(null, "Wrong main position info", "Wrong main position info",JOptionPane.ERROR_MESSAGE);
   		return;
   	  }
   		 
        	 int rowNum=0;
        	 int colNum=0;;
        	 for(int i = 0;i<selectedCells.size();i++){
        		 int curRow = selectedCells.get(i).x;
        		 int curCol = selectedCells.get(i).y;
        		 if(curRow>rowNum) rowNum=curRow;
        		 if(curCol>colNum) colNum=curCol;
        	 }
        	 
        	 int num;
    		  if (rowNum>colNum) num = rowNum+1; else num = colNum+1;
    		 
    		  tetrisBlock[][] blocks = new tetrisBlock[num][num];
    		  for(int i = 0;i<selectedCells.size();i++){
    			  position currPos = selectedCells.get(i);
    			  Color currColor = selectedColors.get(i);
    			  blocks[currPos.x][currPos.y]= new tetrisBlock(currColor);
    		  }
    		  
    		 GamePiece newPiece = new GamePiece(blocks,mainPos);
    		 if(!GameOptions.isPieceExist(newPiece)){
    		 GameOptions.addToPieces(newPiece); 
    		 }else
    		 {
    			JOptionPane.showMessageDialog(null, "Piece Already Exists", "Piece Already Exists",JOptionPane.ERROR_MESSAGE);
    		   	return;
    		 }		 
    	 
        }
     }

private class createTable extends AbstractAction{

	public createTable(String name, Integer mnemonic) {
        super(name);
        putValue(MNEMONIC_KEY, mnemonic);
     }
	
	public void actionPerformed(ActionEvent arg0) {
	   	 if(!Helpers.isInteger(textSizefield.getText())){
	   		 JOptionPane.showMessageDialog(null, "Wrong Number Of Rows", "Wrong Number Of Rows",JOptionPane.ERROR_MESSAGE);
	   		 return;
	   		 }
	   	 
	   	int curNum = Integer.parseInt(textSizefield.getText());
	   	 if(curNum>table.getRowCount())
	   	 {
	   		 DefaultTableModel model = (DefaultTableModel) table.getModel();
	   		 int now = table.getRowCount();
	   		 while(curNum>now){
	   		 model.addColumn("Column");
	   		Object[] row = new Object[now];
	   		for(int i =0;i<row.length;i++){
	   			row[i]="";
	   		}
	   		 model.addRow(row);
	   		 now++;
	   		 }
	   	 }
	   	 else
	   	 {
	   		 DefaultTableModel model = (DefaultTableModel) table.getModel();
	   		 int now=table.getColumnCount()-1;
	   		 while(now>=curNum){
	   		    model.setColumnCount(now);
		   		model.removeRow(now);
	   		    now--;
	   		 } 
	   	 }
	   	 table.setRowHeight(table.getHeight()/table.getColumnCount());
	   	 table.repaint();
	}
	
}
}
