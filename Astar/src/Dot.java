
public class Dot {
	private int x;
	private int y; 
	Dot(){}
	Dot(int x, int y){
		this.x=x;
		this.y=y;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Dot [x=").append(x).append(", y=").append(y).append("]");
		return builder.toString();
	}
	
}
