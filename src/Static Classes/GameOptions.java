import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.util.List;


public class GameOptions {
 
	public int currentLevel = 1;
	private static List<GamePiece> Pieces = Helpers.getBoth();
	private static long GameSpeed=400;
	private static position GameSize=new position(22,10);
	private static int score = 0;
	private static int lines = 0;
	private static int level = 1;
	 
	/* Listener needs */
	public static int goRight = KeyEvent.VK_RIGHT;
	public static int goLeft = KeyEvent.VK_LEFT;
	public static int rotate = KeyEvent.VK_UP;
	public static int goDown = KeyEvent.VK_DOWN; 
	public static int goMostDown = KeyEvent.VK_SPACE; 
	public static int pause = KeyEvent.VK_P;
	/* Listener need ends */
	
	
	
	
	/*Graphical needs*/
	
	public static int blockSize = 30;
	
	
	/* Graphical need ends*/
	
	public GameOptions(List<GamePiece> pieces,position gameSize, int gameSpeed){
		Pieces = pieces;
		this.GameSpeed = gameSpeed;
		this.GameSize = gameSize;
	}
	
	public static position getGameSize() {
		return GameSize;
	}

	public static void setGameSize(position gameSize) {
		GameSize = gameSize;
	}

	public static void addToPieces(GamePiece piece){
		Pieces.add(piece);
	}
	
	public static List<GamePiece> getAllPieces(){
		return Pieces;		
	}
	
	public static Boolean isPieceExist(GamePiece piece){
		for(int i = 0;i<Pieces.size();i++){
			GamePiece iPiece = Pieces.get(i);
			if(iPiece.getBlockInfo().length == piece.getBlockInfo().length){
				tetrisBlock[][] arr1= iPiece.getBlockInfo();
				tetrisBlock[][] arr2 = piece.getBlockInfo();
				boolean flag = true;
				for(int a=0;a<arr1.length;a++){
					if (!flag) break;
					for(int b=0;b<arr1[0].length;b++){
						if(arr1[a][b] == null){ if(arr2[a][b]!=null) flag=false;}else {
							if(arr2[a][b]==null){ flag = false;}else{
						if(!arr1[a][b].equals(arr2[a][b])){
							flag = false;}}
						}
					}
				}
				if (flag) return true;
			}
		}
		return false;
	}

	public static long getGameSpeed() {
		return GameSpeed;
	}
	
	public static double getGameSpeedinSec() {
		double asda = GameSpeed;
		return asda/1000;
	}
	
	public static void setGameSpeed(int gameSpeed) {
		GameSpeed = gameSpeed;
	}
	
	public static List<GamePiece> getPieces() {
		return Pieces;
	}

	public static void setPieces(List<GamePiece> pieces) {
		Pieces = pieces;
	}

	public static int getScore() {
		return score;
	}

	public static void setScore(int score) {
		GameOptions.score = score;
	}

	public static int getLines() {
		return lines;
	}

	public static void setLines(int lines) {
		GameOptions.lines = lines;
	}

	public static void calcLevel() {
		if(score>=0 && score<=15){
			level = 1;
			GameOptions.setGameSpeed(800);
			return;
		}
		if(score>=16 && score<=30){
			level = 2;
			GameOptions.setGameSpeed(800);
			return;
		}
		if(score>=31 && score<=60){
			level = 3;
			GameOptions.setGameSpeed(600);
			return;
		}
		if(score>=61 && score<=90){
			level = 4;
			GameOptions.setGameSpeed(400);
			return;
		}
		if(score>=91 && score<=120){
			level = 5;
			GameOptions.setGameSpeed(200);
			return;
		}
	}

	public static void setLevel(int level) {
		GameOptions.level = level;
	}

	public static void calcScore(double linesDestroyed){	
		double speedVar = 1/GameOptions.getGameSpeedinSec();
		
		if(linesDestroyed == 1){
			score+= speedVar * linesDestroyed;
			lines+= linesDestroyed;
			return;
		}
		if(linesDestroyed == 2){
			score+= speedVar * linesDestroyed*1.5;
			lines+= linesDestroyed;
			return;
		}
		if(linesDestroyed == 3){
			score+= speedVar * linesDestroyed*2;
			lines+= linesDestroyed;
			return;
			}
		if(linesDestroyed >= 4){
			score+= speedVar * linesDestroyed*2.5;
			
			return;
			}
	}

	public static int getLevel(){
		return level;
	}
}
