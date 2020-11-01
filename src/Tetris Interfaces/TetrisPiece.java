
public abstract class TetrisPiece {

	public abstract TetrisPiece flipClockwise();
	public abstract TetrisPiece flipCounterClockwise();
	public abstract Block[][] getBlockInfo(); 
	public abstract position getMainPiece();
	public abstract GamePiece generateNew();
	
}
