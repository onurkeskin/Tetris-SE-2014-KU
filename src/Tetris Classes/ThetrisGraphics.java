import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;

import javax.swing.*;


public class ThetrisGraphics extends JPanel implements Runnable {

	private ThetrisEngine engine;
	private JFrame frame;


	public ThetrisGraphics(JFrame frame,ThetrisEngine engine){
		this.engine = engine;
		this.frame = frame;

	}


	public void makeCanvas(){

		int xDim = GameOptions.getGameSize().x * GameOptions.blockSize +50;
		int yDim = GameOptions.getGameSize().y * GameOptions.blockSize * 2;
		
		this.setBounds(0, 0, yDim,xDim);
		frame.setBounds(0, 0, yDim,xDim);
		frame.add(this);
		
		JButton mainMenuButton = new JButton("Main Menu");
		mainMenuButton.setBounds(xDim-30,yDim-30,30,30);
		this.add(mainMenuButton);
	}


	public void paint(Graphics g){
		tetrisBlock[][] currentGame = engine.gameState();

		super.paintComponent(g);


		GamePiece nextImage = ThetrisEngine.gameQueue.peek();
		int offSet = 10;
		
		int GameCanvasX = GameOptions.getGameSize().y* GameOptions.blockSize;
		int GameCanvasY = GameOptions.getGameSize().x * GameOptions.blockSize;
		
		int gameFontSize = GameCanvasX/10;
		
		String ScoreText = "Score: ";
		double Score = GameOptions.getScore();
		
		String LinesText = "Lines: ";
		double Lines = GameOptions.getLines();
		
		String LevelText = "Levels: ";
		double Level = GameOptions.getLevel();
		
		/*score*/
		
		int ScoreTextPosX = GameOptions.getGameSize().y+offSet+offSet* GameOptions.blockSize+GameOptions.getGameSize().y/5;
		int ScoreTextPosY = (GameOptions.getGameSize().x * GameOptions.blockSize+ GameOptions.blockSize)/2;
		int ScorePosX = ScoreTextPosX+4*gameFontSize;
		int ScorePosY = ScoreTextPosY;
		
		/*score ends*/
		
		/*lines*/
		
		int LinesTextPosX = ScoreTextPosX;
		int LinesTextPosY= ScoreTextPosY-50;
		int LinesPosX = LinesTextPosX+4*gameFontSize;
		int LinesPosY = LinesTextPosY;
		
		/*lines ends*/
		
		/*level*/
		
		int LevelTextPosX = ScoreTextPosX;
		int LevelTextPosY= ScoreTextPosY-100;
		int LevelPosX = LevelTextPosX+4*gameFontSize;
		int LevelPosY = LevelTextPosY;
		
		/*level ends*/
		

	
		
		Font gameFont = new Font("Georgia", Font.BOLD, gameFontSize);
		Font pauseFont = new Font("Pacifico", Font.BOLD, GameCanvasX/5);
		

		g.setFont(gameFont);

		g.drawString(ScoreText, ScoreTextPosX,  ScoreTextPosY);
		g.drawString(Double.toString(Score), ScorePosX, ScorePosY );

		g.drawString(LinesText,LinesTextPosX, LinesTextPosY);
		g.drawString(Double.toString(Lines), LinesPosX , LinesPosY);
		
		g.drawString(LevelText,LevelTextPosX, LevelTextPosY);
		g.drawString(Double.toString(Level), LevelPosX , LevelPosY);

		g.setColor(Color.BLACK);
		g.fillRect(0, 0,GameCanvasX+20, GameCanvasY+20);
		g.setColor(getForeground());
		g.fillRect(offSet, offSet,GameCanvasX, GameCanvasY);
		
		if(nextImage.getBlockInfo()!=null){
		for (int i = 0; i<currentGame.length; i++){
			for(int j= 0; j< currentGame[0].length; j++){
				if(currentGame[i][j]!=null){					
					g.setColor(currentGame[i][j].getColor());
					g.fillRect(GameOptions.blockSize*j+offSet,
							GameOptions.blockSize*i+offSet,
							GameOptions.blockSize
							,GameOptions.blockSize);
					g.setColor(Color.BLACK);
					g.drawRect(GameOptions.blockSize*j+offSet,
							GameOptions.blockSize*i+offSet,
							GameOptions.blockSize
							,GameOptions.blockSize);

				}
			}
		}
		if(nextImage!= null){
		for(int i=0;i<nextImage.getBlockInfo().length;i++){
			for(int j=0;j<nextImage.getBlockInfo()[0].length;j++){
				if( nextImage.getBlockInfo()[i][j]!=null&& nextImage.getBlockInfo() != null){
				g.setColor(nextImage.getBlockInfo()[i][j].getColor());
				g.fillRect(GameOptions.blockSize*j+GameOptions.getGameSize().y* GameOptions.blockSize+50, 
						GameOptions.blockSize*i, 
						GameOptions.blockSize, 
						GameOptions.blockSize);
			}
				g.setColor(Color.black);
				g.drawRect(GameOptions.blockSize*j+GameOptions.getGameSize().y* GameOptions.blockSize+50, 
						GameOptions.blockSize*i, 
						GameOptions.blockSize, 
						GameOptions.blockSize);
				}
			}
			}
		}
		if(engine.pause){
			g.setFont(pauseFont);
			g.drawString("PAUSED", GameOptions.getGameSize().y* GameOptions.blockSize/2-GameCanvasX/5, GameOptions.getGameSize().x* GameOptions.blockSize/2);
		}
		
		
	}

	public void run() {

		makeCanvas();

		while(engine.gameOver){
			repaint(0,0,this.getWidth()-30,
					this.getHeight());}
		try {
			Thread.sleep(GameOptions.getGameSpeed());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
