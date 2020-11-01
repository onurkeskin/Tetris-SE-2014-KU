import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;


public class tetrisBlock extends Block{

	private Color blockColor;
	private boolean runningPiece;
	private boolean masterPiece;

	
	public tetrisBlock(Color color){
		blockColor = color;
		runningPiece = true;
		masterPiece = false;
	}
	public boolean isRunning(){
		return runningPiece;	
	}
	
	public void setRunning(boolean run){
		runningPiece=run;	
	}
	
	public void setAsMasterPiece(){
		masterPiece = true;
	}
	
	public void setAsNormalPiece(){
		masterPiece = false;
	}

	public boolean getMasterStatus(){
		return masterPiece;
	}
	
	public Block changeColor(Color color) {
		blockColor = color;
		return this;
	}
	public Color getColor() {
		return blockColor;
	}

	public boolean equals(tetrisBlock x){
		if(this != null && x != null){
		if(this.blockColor == x.blockColor && 
				this.masterPiece == x.masterPiece && 
				this.runningPiece == x.runningPiece) return true; else return false;
		}
		if(this == null && x == null) return true; else return false;
	}
}
