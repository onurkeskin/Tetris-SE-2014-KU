
public class position {

	public int x;
	public int y;
	
	public position(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	public boolean equals(position pos){
		if(this.x == pos.x && this.y==pos.y){
			return true;
		}else return false;
	}
}
