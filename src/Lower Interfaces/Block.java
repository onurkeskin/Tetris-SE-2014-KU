import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;


public abstract class  Block {

	public abstract Block changeColor(Color color);
	
	public abstract Color getColor();
	public abstract boolean getMasterStatus();
	public abstract boolean isRunning();
	
	
}