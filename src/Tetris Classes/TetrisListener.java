import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class TetrisListener implements KeyListener {
	ThetrisEngine engine;
	
	public TetrisListener(ThetrisEngine engine){
		this.engine=engine;
	}
	
	public void keyPressed(KeyEvent e) {
	if(engine.currentPiece!=null){
		if (e.getKeyCode() == GameOptions.goRight){
			engine.goRight();
		}
		
		if (e.getKeyCode()  == GameOptions.goLeft){
			engine.goLeft();
		}
		
		if (e.getKeyCode()  == GameOptions.rotate){
			engine.turnClockwise();
		}
		if(e.getKeyCode() == GameOptions.goDown){
			engine.moveMovingPieces();
		}
		
		if(e.getKeyCode() == GameOptions.goMostDown){
			engine.getToDown();
		}
	}
	if(e.getKeyCode() == GameOptions.pause){
		if(engine.pause)
		engine.pause = false;
		else engine.pause = true;
	}
	}


	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}


	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
