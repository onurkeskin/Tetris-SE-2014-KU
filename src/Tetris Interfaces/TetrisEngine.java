import java.awt.Graphics;


public abstract class TetrisEngine{

	public abstract void play();
	
	public abstract TetrisPiece newPiece();
	
	public abstract void turnCounterClockwise();
	
	abstract void clear(int x);
}
