import java.awt.Color;

public class BoardColor{
	
	final private Color start = Color.green;
	final private Color end = Color.red;
	final private Color wall = Color.black;
	final private Color road = Color.white;
	final private Color search = Color.yellow;
	final private Color path = Color.blue;	
	
	public BoardColor() {
		
	}
	public Color getStart() {
		return start;
	}
	public Color getEnd() {
		return end;
	}
	public Color getWall() {
		return wall;
	}
	public Color getRoad() {
		return road;
	}
	public Color getSearch() {
		return search;
	}
	public Color getPath() {
		return path;
	}
	
}
