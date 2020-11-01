import java.awt.EventQueue;

import javax.swing.JFrame;


public class runGame {

	public static TetrisEngine logicEngine;
	public static GraphicsEngine graphicEngine;
	public static GameOptions options;
	public static startScreen frame;
	protected static String startUpOption;
	
	public static void main(String args[]){
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new startScreen();
					frame.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}
	
	
	
}
