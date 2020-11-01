import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EventListener;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class ThetrisEngine extends TetrisEngine implements Runnable{

	tetrisBlock[][] gameEnv;
	TetrisPiece currentPiece;
	boolean gameOver = true;

	static Queue<GamePiece> gameQueue;
	static boolean pause = false;
	
	public ThetrisEngine(tetrisBlock[][] gameEnv){
		this.gameEnv = gameEnv;
		gameQueue = new LinkedList<GamePiece>();
	}


	
	public void play() {

		if(currentPiece == null){
			clearClearables();
			currentPiece = gameQueue.remove();
			gameQueue.add(getNextPiece(GameOptions.getAllPieces()));
			gameOver = ArrayManipulation.putTopMiddle(gameEnv, currentPiece);
		}
		else
		{
			moveMovingPieces();
		} 
	}

	public void run() {
		gameQueue.add(getNextPiece(GameOptions.getAllPieces()));
		
		while(gameOver == true){
			if(!pause){
			play();
			try {
				Thread.sleep(GameOptions.getGameSpeed());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
		if(gameOver== false){
			gameOverPop overGame = new gameOverPop(GameOptions.getScore());
			overGame.show();
			overGame.requestFocus();
		}

	}

	public tetrisBlock[][] gameState(){
		return gameEnv;
	}

	private GamePiece getNextPiece(List<GamePiece> list){
		Random rng = new Random();

		int piecePool = list.size();
		int randomNum = rng.nextInt(piecePool);

		GamePiece piece = new GamePiece(list.get(randomNum).getBlockInfo(),list.get(randomNum).getMainPiece());

		return piece;

	}

	public void goRight(){
		for(int i = 0;i<gameEnv.length;i++){
			for(int j = gameEnv[0].length-1;j>=0;j--){
				if(gameEnv[i][j] != null && gameEnv[i][j].isRunning()){
					if(j >= gameEnv[0].length-1 || gameEnv[i][j+1]!=null && !gameEnv[i][j+1].isRunning())
						return;
				}
			}
		}

		for(int i = 0;i<gameEnv.length;i++){
			for(int j = gameEnv[0].length-1;j>=0;j--){
				if(gameEnv[i][j] != null && gameEnv[i][j].isRunning()){
					tetrisBlock tempBlock = gameEnv[i][j];
					gameEnv[i][j] = null;
					gameEnv[i][j+1] = tempBlock;
				}
			}
		}


	}

	public void goLeft(){
		for(int i = 0;i<gameEnv.length;i++){
			for(int j =0;j< gameEnv[0].length;j++){
				if(gameEnv[i][j] != null && gameEnv[i][j].isRunning()){
					if(j <= 0 || gameEnv[i][j-1]!=null && !gameEnv[i][j-1].isRunning())
						return;
				}
			}
		}

		for(int i = 0;i<gameEnv.length;i++){
			for(int j =0;j< gameEnv[0].length;j++){
				if(gameEnv[i][j] != null && gameEnv[i][j].isRunning()){
					tetrisBlock tempBlock = gameEnv[i][j];
					gameEnv[i][j] = null;
					gameEnv[i][j-1] = tempBlock;
				}
			}
		}
	}

	public void moveMovingPieces(){
		if(canMove()){
			for(int i=gameEnv.length-1; i>=0; i--){
				for(int j=gameEnv[0].length-1; j>=0; j--){
					if(gameEnv[i][j] != null && gameEnv[i][j].isRunning()){
						tetrisBlock tempBlock = gameEnv[i][j];
						gameEnv[i][j] = null ;
						gameEnv[i+1][j]=tempBlock ;
					}
				}	
			}		
		} else {
			stopMoving();	
			currentPiece=null;	
		}

	}

	private void clearClearables(){
		List<position> toClear = clearables();
		if(!toClear.isEmpty()){
			for(int i=0; i<toClear.size();i++){
				clear(toClear.get(i).x);
				moveUpperToDown(toClear.get(i).x);
			}		
		}
		
		double od = toClear.size();
		GameOptions.calcScore(od);
		GameOptions.calcLevel();	
	}



	private List<position> clearables(){
		List<position> canClear = new LinkedList<position>();
		for(int i = 0; i<gameEnv.length; i++){
			boolean clear = true;
			for(int j = 0; j<gameEnv[0].length;j++){
				if(gameEnv[i][j] == null || gameEnv[i][j].isRunning()){
					clear = false;
				}
			}
			if(clear) canClear.add(new position(i,-1));
		}
		return canClear;
	}

	private boolean canMove(){
		boolean pass = true;
		for(int i=0; i<gameEnv.length;i++){
			for(int j=0; j<gameEnv[0].length;j++){
				if(gameEnv[i][j] != null && gameEnv[i][j].isRunning()){
					if(i+1==gameEnv.length||gameEnv[i+1][j] != null && !gameEnv[i+1][j].isRunning() && gameEnv[i+1][j]!=null){
						return false;	
					}
				}
			}	
		}
		return pass;
	}

	private void stopMoving(){
		for(int i=0; i<gameEnv.length;i++){
			for(int j=0; j<gameEnv[0].length;j++){
				if(gameEnv[i][j] != null && gameEnv[i][j].isRunning()){
					gameEnv[i][j].setRunning(false);
					gameEnv[i][j].setAsNormalPiece();
				}
			}	
		}
	}

	public void turnClockwise(){
		position mainPos=getCurrentmain();
		GamePiece playOn = currentPiece.generateNew();
		playOn.flipClockwise();

		LinkedList<position> positions = new LinkedList<position>();
		LinkedList<tetrisBlock> blocks = new LinkedList<tetrisBlock>();

		for(int i=0; i<gameEnv.length;i++){
			for(int j=0; j<gameEnv[0].length;j++){
				if(gameEnv[i][j] != null && gameEnv[i][j].isRunning()){
					positions.add(new position(i,j));
					blocks.add(gameEnv[i][j]);
					gameEnv[i][j]=null;	
				}
			}	
		}

		if(ArrayManipulation.putable(gameEnv,playOn,mainPos)) {
			currentPiece = playOn;
			ArrayManipulation.put(gameEnv,playOn,mainPos);
			return;}
		
		if(ArrayManipulation.putable(gameEnv,playOn,new position(mainPos.x,mainPos.y-1))) {
			currentPiece = playOn;
			ArrayManipulation.put(gameEnv,playOn,new position(mainPos.x,mainPos.y-1));
			return;}
					
		if(ArrayManipulation.putable(gameEnv,playOn,new position(mainPos.x,mainPos.y+1))) {
			currentPiece = playOn;
			ArrayManipulation.put(gameEnv,playOn,new position(mainPos.x,mainPos.y+1));
			return;}
		
		
			for(int i=0;i<positions.size();i++){
				gameEnv[positions.get(i).x][positions.get(i).y] = blocks.get(i);
			}

	}

	public void turnCounterClockwise(){
		TetrisPiece piece = currentPiece.flipClockwise();
		position mainPos=getCurrentmain();
		currentPiece.flipCounterClockwise();

		if(ArrayManipulation.putable(gameEnv,currentPiece,mainPos)){
			for(int i=0; i==gameEnv.length; i++){
				for(int j=0; j==gameEnv[0].length; j++){
					if(gameEnv[i][j].isRunning()){
						gameEnv[i][j]=null;
					}
				}	
			}
			ArrayManipulation.put(gameEnv,currentPiece,mainPos);
		}
	}

	public void getToDown(){
		while(canMove()){
			moveMovingPieces();
		}
	}
	
	public TetrisPiece newPiece() {
		// TODO Auto-generated method stub
		return null;
	}

	private position getCurrentmain(){
		for(int i=0; i<gameEnv.length;i++){
			for(int j=0; j<gameEnv[0].length;j++){
				if(gameEnv[i][j] != null && gameEnv[i][j].isRunning() && gameEnv[i][j].getMasterStatus()){
					return new position(i,j);
				}
			}	
		}
		return null;
	}

	private void moveUpperToDown(int x){
		for(int i=x; i>=0;i--){
			for(int j=0;j<gameEnv[0].length;j++)
				if(gameEnv[i][j] != null && !gameEnv[i][j].isRunning()){
					tetrisBlock temp = gameEnv[i][j];
					gameEnv[i][j]=null;
					gameEnv[i+1][j]= temp;
				}
		}
	}

	public void clear(int x){
		for (int j=0; j<gameEnv[x].length;j++){
			gameEnv[x][j]=null;
		}
	}
		
		

}

