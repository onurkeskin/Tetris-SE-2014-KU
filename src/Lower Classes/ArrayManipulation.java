import java.util.ArrayList;
import java.util.List;


public class ArrayManipulation {

	public ArrayManipulation() {
	}
	
	public static boolean putTopMiddle(Object[][] array1, TetrisPiece array2){

		boolean canPut = putable(array1,array2,new position(0,array1[0].length/2));
		int i = 0;
		while(!canPut && i<array2.getMainPiece().x){
			i = i+1;
			canPut = putable(array1,array2,new position(i,array1[0].length/2));
		}
		if(canPut){
		put(array1,array2,new position(i,array1[0].length/2));
		}
		return canPut;
	}

	
	
	public static boolean putable(Object[][] array1, TetrisPiece array2, position pos1){
		
		List<position> positions = filledPositions(array2);
		
		for(int i = 0; i<positions.size(); i++){
			position mapos = array2.getMainPiece();
		position relativePosition=new position(pos1.x-mapos.x+positions.get(i).x  
											  ,pos1.y-mapos.y+positions.get(i).y );
		
		if(legitPos(array1,relativePosition)){
			}else {return false;}
		}
		return true;
	}

	public static void put(Object[][] array1, TetrisPiece array2, position pos1){
		List<position> positions = filledPositions(array2);
	for(int i = 0; i<positions.size(); i++){
		position relativePosition=new position(pos1.x-array2.getMainPiece().x+positions.get(i).x  
				  ,pos1.y-array2.getMainPiece().y+positions.get(i).y );
	position nonRelative = positions.get(i);
	
	tetrisBlock newBlock  = new tetrisBlock(array2.getBlockInfo()[nonRelative.x][nonRelative.y].getColor());
	
	if(array2.getBlockInfo()[nonRelative.x][nonRelative.y].getMasterStatus()){
		newBlock.setAsMasterPiece();}
			
	array1[relativePosition.x][relativePosition.y]=newBlock;
		
	}
}
	
	private static position getMain(TetrisPiece piece){
		for(int i=0; i<piece.getBlockInfo().length;i++){
			for(int j=0; j<piece.getBlockInfo()[0].length;j++){
				if(piece.getBlockInfo()[i][j].isRunning() && piece.getBlockInfo()[i][j].getMasterStatus()){
					return new position(i,j);
				}
			}	
		}
		return null;
	}
	

	private static boolean legitPos(Object[][] array,position pos){
		if(pos.x>=0&&pos.y>=0&&pos.x<array.length&&pos.y<array[0].length) {
			if(array[pos.x][pos.y]==null)return true;
			else return false;	
		}
		else return false;
	}

	public static List<position> filledPositions(TetrisPiece array){
		List<position> positions = new ArrayList<position>();
		for(int i = 0; i< array.getBlockInfo().length; i++){
			for(int j = 0; j< array.getBlockInfo()[0].length; j++){
			if(array.getBlockInfo()[i][j] != null){
				positions.add(new position(i,j));
				}			
			}
		}
		 return positions;                                     
	}
}
