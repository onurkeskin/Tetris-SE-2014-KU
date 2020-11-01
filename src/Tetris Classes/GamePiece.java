import java.util.List;


public class GamePiece extends TetrisPiece {

	private tetrisBlock[][] blocks;
	private position mainPosition;
	
	public GamePiece(tetrisBlock[][] blocks, position baseblock){
		if(blocks != null && blocks[0].length == blocks.length){
			this.blocks = blocks;
			this.mainPosition = baseblock;
			this.blocks[baseblock.x][baseblock.y].setAsMasterPiece();
		}else {
			//throw new Exception("wrong block");	
		}
	}
	
	public GamePiece generateNew(){
		tetrisBlock[][] newArray= new tetrisBlock[blocks.length][blocks[0].length];
		for(int i = 0; i< blocks.length;i++){
			for(int j = 0; j< blocks[0].length;j++){
				if(blocks[i][j] != null){
				newArray[i][j] = new tetrisBlock(blocks[i][j].getColor());
				if(blocks[i][j].getMasterStatus()){
					newArray[i][j].setAsMasterPiece();}
				}
			}
		}
		
		
		GamePiece newPiece = new GamePiece(newArray,mainPosition);
		return newPiece;
	}
	
	public TetrisPiece flipClockwise() {
        transpose(blocks);

        //  swap rows
        for (int  i = 0; i < blocks.length/2; i++) {
            for (int j = 0; j < blocks[0].length; j++) {
                tetrisBlock x = blocks[i][j];
                blocks[i][j] = blocks[blocks.length -1 -i][j]; 
                blocks[blocks.length -1 -i][j] = x;
            }
        }
        
		for(int a=0;a<blocks.length;a++){
			for(int b=0;b<blocks.length;b++){
				if(blocks[a][b]!=null && blocks[a][b].getMasterStatus()){
					this.mainPosition = new position(a,b);
				}
			}
		}
		return this;
	}

	public TetrisPiece flipCounterClockwise() {
        transpose(blocks);

        // swap columns
        for (int  j = 0; j < blocks[0].length/2; j++) {
            for (int i = 0; i < blocks.length; i++) {
                tetrisBlock x = blocks[i][j];
                blocks[i][j] = blocks[i][blocks[0].length -1 -j]; 
                blocks[i][blocks[0].length -1 -j] = x;
            }
        }
        
		for(int a=0;a<blocks.length;a++){
			for(int b=0;b<blocks.length;b++){
				if(blocks[a][b]!=null && blocks[a][b].getMasterStatus()){
					this.mainPosition = new position(a,b);
				}
			}
		}
		return this;
	}

	public tetrisBlock[][] getBlockInfo() {
		// TODO Auto-generated method stub
		return blocks;
	}

    private static void transpose(tetrisBlock[][] m) {

        for (int i = 0; i < m.length; i++) {
            for (int j = i; j < m[0].length; j++) {
            	tetrisBlock x = m[i][j];
                m[i][j] = m[j][i];
                m[j][i] = x;
            }
        }
    }

	public position getMainPiece() {
		return mainPosition;
	}
	
	
}
