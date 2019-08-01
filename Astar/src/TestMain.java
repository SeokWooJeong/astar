import java.util.LinkedList;

public class TestMain {

	public static void main(String[] args) {

		PointList list = new PointList();
		PointList list2 = new PointList();
		Point n3 = new Point();
		n3.setF(3);
		list.add(n3);
		Point n2 = new Point();
		n2.setF(2);
		list.add(n2);
		Point n1 = new Point();
		n1.setF(1);
		list.add(n1);
		list.gSort();
		System.out.println(list);
		list2.add(list.getFirst());
		System.out.println(list2);
		list.remove(0);
		System.out.println(list2);
		Map map = new Map();
		Astar astar = new Astar(map);
		astar.getFindPath();
		System.out.println(astar.getFindPath());
		System.out.println(astar.getCloseList());
		astar.getCloseList();
		
	}

}
