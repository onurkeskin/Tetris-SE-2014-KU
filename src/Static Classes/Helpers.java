import java.awt.Color;
import java.util.LinkedList;
import java.util.List;

import com.sun.org.apache.xml.internal.serialize.OutputFormat.Defaults;


public class Helpers {

	public static List<GamePiece> tetrisPieces(){
		LinkedList<GamePiece> defaults = new LinkedList<GamePiece>(); 
		
		tetrisBlock[][] L = new tetrisBlock[3][3];
		L[0][0] = new tetrisBlock(Color.BLACK);
		L[0][1] = new tetrisBlock(Color.BLACK);
		L[0][2] = new tetrisBlock(Color.BLACK);
		L[1][2] = new tetrisBlock(Color.BLACK);
		GamePiece LPiece = new GamePiece(L,new position(0,2));
		
		tetrisBlock[][] O = new tetrisBlock[3][3];
		O[0][0] = new tetrisBlock(Color.GREEN);
		O[0][1] = new tetrisBlock(Color.GREEN);
		O[1][0] = new tetrisBlock(Color.GREEN);
		O[1][1] = new tetrisBlock(Color.GREEN);
		GamePiece OPiece = new GamePiece(O,new position(0,0));
		
		tetrisBlock[][] T = new tetrisBlock[3][3];
		T[0][1] = new tetrisBlock(Color.yellow);
		T[1][0] = new tetrisBlock(Color.yellow);
		T[1][1] = new tetrisBlock(Color.yellow);
		T[1][2] = new tetrisBlock(Color.yellow);

		GamePiece TPiece = new GamePiece(T,new position(1,1));
		
		tetrisBlock[][] I = new tetrisBlock[4][4];
		I[0][0] = new tetrisBlock(Color.BLUE);
		I[1][0] = new tetrisBlock(Color.BLUE);
		I[2][0] = new tetrisBlock(Color.BLUE);
		I[3][0]= new tetrisBlock(Color.BLUE);
		GamePiece IPiece = new GamePiece(I,new position(2,0));
		
		tetrisBlock[][] Z = new tetrisBlock[3][3];
		Z[0][0] = new tetrisBlock(Color.magenta);
		Z[0][1] = new tetrisBlock(Color.magenta);
		Z[1][1] = new tetrisBlock(Color.magenta);
		Z[1][2]= new tetrisBlock(Color.magenta);
		GamePiece ZPiece = new GamePiece(Z,new position(1,1));
		
		tetrisBlock[][] S = new tetrisBlock[3][3];
		S[0][1] = new tetrisBlock(new Color(255,4,4));
		S[1][0] = new tetrisBlock(new Color(255,4,4));
		S[1][1] = new tetrisBlock(new Color(255,4,4));
		S[0][2]= new tetrisBlock(new Color(255,4,4));
		GamePiece SPiece = new GamePiece(S,new position(1,1));
		
		tetrisBlock[][] J = new tetrisBlock[3][3];
		J[0][0] = new tetrisBlock(new Color(130,80,67));
		J[0][1] = new tetrisBlock(new Color(130,80,67));
		J[1][0] = new tetrisBlock(new Color(130,80,67));
		J[0][2]= new tetrisBlock(new Color(130,80,67));
		GamePiece JPiece = new GamePiece(J,new position(0,0));
		
		defaults.add(SPiece);
		defaults.add(LPiece);
		defaults.add(OPiece);
		defaults.add(TPiece);
		defaults.add(IPiece);
		defaults.add(ZPiece);
		defaults.add(JPiece);
		return defaults;
	}
	
	public static List<GamePiece> tritixPieces(){
		LinkedList<GamePiece> defaults = new LinkedList<GamePiece>(); 
		
		tetrisBlock[][] i = new tetrisBlock[3][3];
		i[0][0] = new tetrisBlock(new Color(240,77,77));
		i[0][1] = new tetrisBlock(new Color(240,77,77));
		i[0][2]= new tetrisBlock(new Color(240,77,77));
		GamePiece iPiece = new GamePiece(i,new position(0,1));
	
		tetrisBlock[][] r = new tetrisBlock[2][2];
		r[0][0] = new tetrisBlock(new Color(23,100,100));
		r[1][0] = new tetrisBlock(new Color(23,100,100));
		r[1][1]= new tetrisBlock(new Color(23,100,100));
		GamePiece rPiece = new GamePiece(r,new position(1,0));
		
		tetrisBlock[][] j = new tetrisBlock[2][2];
		j[0][1] = new tetrisBlock(new Color(0,40,100));
		j[1][0] = new tetrisBlock(new Color(0,40,100));
		j[1][1]= new tetrisBlock(new Color(0,40,100));
		GamePiece jPiece = new GamePiece(j,new position(0,1));
	
	defaults.add(jPiece);
	defaults.add(rPiece);
	defaults.add(iPiece);
	return defaults;
	}
	
	public static List<GamePiece> getBoth(){
		
		LinkedList<GamePiece> defaults = new LinkedList<GamePiece>(); 
		defaults.addAll(tetrisPieces());
		defaults.addAll(tritixPieces());
		return defaults;
	}
	
	public static boolean isInteger(String s) {
	    try { 
	        Integer.parseInt(s); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    // only got here if we didn't return false
	    return true;
	}
	
}
