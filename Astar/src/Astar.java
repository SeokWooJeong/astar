import java.util.LinkedList;

public class Astar {

	static LinkedList<Dot> blockList = new LinkedList<>();
	static int mapSize;// 체크
	static Dot startDot, endDot;
	static PointList openList = new PointList();
	static PointList closeList = new PointList();
	static Dot blockDot;
	static PointList findPath = new PointList();
	Astar(Map m) {
		int tempH;
		int tempG;
		int tempF;
		mapSize = 32;//맵의 사이즈 받아와서 처리하는걸로 바꾸기, ex m.getSize
		// 미로 정보 수집
		for (int i = 0; i < mapSize; i++) {
			for (int j = 0; j < mapSize; j++) {
				if (m.getMap(i, j) == 0) {
					continue;
				} else if (m.getMap(i, j) == 1) {
					blockDot = new Dot(j, i);
					blockList.add(blockDot);
				} else if (m.getMap(i, j) == 2) {
					// start 정보 추가
					startDot = new Dot(j, i);
//					blockList.add(startDot);
				} else if (m.getMap(i, j) == 3) {
					// end 정보 추가
					endDot = new Dot(j, i);
				}
			}
		}//for(i)
		
		// 클로즈리스트에 시작지점 추가
		closeList.add(new Point(0,0,0,startDot,startDot));
		// 첫 g h f 는 0으로 스타트
	
		while(true) {
			// 클로즈리스트의 가장 마지막 친구기준으로 이동경로 탐색, 이동 경로 탐색시 closeNode의 마지막 노드로 이동을 피해야 한다.
			Point point = closeList.get(closeList.size()-1);
			LinkedList<Dot> tempDotList = findWay(point.getCurDot());
			// 탐색된 이동경로의 h와 g 그리고 f=g+h를 연산하여 오픈 리스트 삽입
			for(Dot tempDot : tempDotList) {
				//h구하기
//				tempH = getH(endDot, new Dot(tempDot.getX(),tempDot.getY()));
				tempH = getH(endDot, tempDot);
				tempG=point.getG()+1;
				tempF=tempH+tempG;
				openList.add(new Point(tempG, tempH,tempF,new Dot(tempDot.getX(),tempDot.getY()), new Dot(point.getCurDot().getX(),point.getCurDot().getY())));
			}
			// 오픈리스트 f가 적은 비용 순서대로 sort
			openList.gSort();
			openList.fSort();
			
			// 오픈리스트의 가장 앞쪽에 위치한 포인트 빼서 closelist에 삽입
			closeList.addLast(openList.getFirst());
			openList.remove(0);
			// 현재 위치가 목적지인지 판단
			if(isEqualDot(closeList.getLast().getCurDot(), endDot)) {
				//목적지이면 closeList에서 findPath 생성하여 gSort후 data 저장
				//closeList의 가장 마지막 노드와 그 노드의 부모 노드 findPath에 넣는다 부모 노드가 null 일때까지
				findPath.addLast(closeList.getLast());
				
				while(findPath.getLast().getCurDot()!=startDot) {
					Dot tempDot = findPath.getLast().getParDot();
					for(int i=0;i<closeList.size();i++) {
						if(isEqualDot(tempDot, closeList.get(i).getCurDot())) {
							findPath.add(closeList.get(i));
							break;
						}
					}
				}//while(findPath.getLast().getCurDot()!=startDot)
				
				findPath.gSort();
				break;
			}
			
		}// 연산반복
		
	}
	
	public PointList getFindPath() {
		return findPath;
	}
	
	public PointList getCloseList() {
		return closeList;
	}
	
	public static boolean isEqualDot(Dot dot1, Dot dot2) {
		if(dot1.getX() == dot2.getX() && dot1.getY() == dot2.getY()) return true; 
		else return false;
		
	}

	public static int getH(Dot endDot, Dot curDot) {
		int h=0;
		h += Math.abs(endDot.getX() - curDot.getX());
		h += Math.abs(endDot.getY() - curDot.getY());
		return h;
	}

	public static LinkedList<Dot> findWay(Dot curDot) {
		LinkedList<Dot> tempList = new LinkedList<>();

		Dot tempDot = new Dot(curDot.getX() - 1, curDot.getY());
		Dot lastCloseDot = closeList.get(closeList.size()-1).getParDot();
		if (tempDot.getX() >= 0 && !isBlock(tempDot) && !isEqualDot(tempDot, lastCloseDot)) {
			tempList.add(new Dot(tempDot.getX(), tempDot.getY()));
		}
		tempDot = new Dot(curDot.getX() + 1, curDot.getY());
		if (tempDot.getX() < mapSize && !isBlock(tempDot) && !isEqualDot(tempDot, lastCloseDot)) {
			tempList.add(new Dot(tempDot.getX(), tempDot.getY()));
		}
		tempDot = new Dot(curDot.getX(), curDot.getY() - 1);
		if (tempDot.getY() >= 0 && !isBlock(tempDot) && !isEqualDot(tempDot, lastCloseDot)) {
			tempList.add(new Dot(tempDot.getX(), tempDot.getY()));
		}
		tempDot = new Dot(curDot.getX(), curDot.getY() + 1);
		if (tempDot.getY() < mapSize && !isBlock(tempDot) && !isEqualDot(tempDot, lastCloseDot)) {
			tempList.add(new Dot(tempDot.getX(), tempDot.getY()));
		}

		return tempList;
	}

	public static boolean isBlock(Dot dot) {
 		for (Dot item : blockList) {
 			if (item.getX() == dot.getX() && item.getY() == dot.getY()) {
				return true;
			}
		}
		return false;
	}
}
