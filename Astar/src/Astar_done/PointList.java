package Astar_done;
import java.util.Collections;
import java.util.LinkedList;

public class PointList extends LinkedList<Point> {
	
	public PointList fSort() {
		Collections.sort(this, (o1, o2)-> o1.getF()-o2.getF());
		return this;
	}
	
	public PointList gSort() {
		Collections.sort(this, (o1, o2)-> o1.getG()-o2.getG());
		return this;
	}
	
	public PointList hSort() {
		Collections.sort(this, (o1, o2)-> o1.getH()-o2.getH());
		return this;
	}
	
}
