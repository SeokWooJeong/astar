package Astar_done;
public class Point {
	private int g;//이때가지 이동한 거리 누적
	private int h;//현재 노드에서 도착지점까지 예상 이동거리
	private int f;//g+h
	private Dot curDot;
	private Dot parDot;
	
	Point(){}
	
	Point(int g, int h, int f,Dot curDot, Dot parDot){
		this.g=g;
		this.h=h;
		this.f=f;
		this.curDot = curDot;
		this.parDot = parDot;
	}
	
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Point [g=").append(g).append(", h=").append(h).append(", f=").append(f).append(", curDot=")
				.append(curDot).append(", parDot=").append(parDot).append("]");
		return builder.toString();
	}

	public Dot getCurDot() {
		return curDot;
	}

	public void setCurDot(Dot curDot) {
		this.curDot = curDot;
	}

	public Dot getParDot() {
		return parDot;
	}

	public void setParDot(Dot parDot) {
		this.parDot = parDot;
	}

	public int getF() {
		return f;
	}
	public void setF(int f) {
		this.f = f;
	}
	public int getG() {
		return g;
	}
	public void setG(int g) {
		this.g = g;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}

	
}
