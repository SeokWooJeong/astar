/*{0,1,0,0,0,1,3,0},
		{0,1,0,1,0,0,1,0},
		{0,1,0,1,1,0,1,0},
		{0,0,0,2,1,0,1,0},
		{0,1,0,1,1,1,1,0},
		{0,1,0,0,0,0,1,0},
		{0,1,1,1,1,1,1,0},
		{0,0,0,0,0,0,0,0}*/
public class Map {
	private int[][] map ={
		{2,0,0,0,0,1,0,0},
		{0,0,0,0,0,1,1,0},
		{0,0,0,0,0,1,1,0},
		{0,0,0,0,0,1,1,0},
		{0,0,0,0,3,1,1,0},
		{1,1,1,1,1,1,1,0},
		{0,0,0,0,1,1,1,0},
		{0,0,0,0,0,0,0,0}
	};
	private int count = 0;
	
	public int getMap(int i, int j) {
		return map[i][j];
	}
	public void reset() {
		switch(count++) {
		case 0:
			map[3][3] = 0;
			map[0][7] = 0;
			map[0][7] = 2;
			map[5][5] = 3;
			break;
		case 1:
			map[0][7] = 0;
			map[5][5] = 0;
			map[1][2] = 2;
			map[3][5] = 3;
			break;
		case 2:
			map[1][2] = 0;
			map[3][5] = 0;
			map[3][2] = 2;
			map[3][5] = 3;
			break;
		case 3:
			map[3][2] = 0;
			map[3][5] = 0;
			map[5][7] = 2;
			map[2][5] = 3;
			break;
		case 4:
			map[5][7] = 0;
			map[2][5] = 0;
			map[5][5] = 2;
			map[4][0] = 3;
			break;
		case 5:
			map[5][5] = 0;
			map[4][0] = 0;
			map[7][1] = 2;
			map[4][2] = 3;
			break;
		case 6:
			map[7][1] = 0;
			map[4][2] = 0;
			map[5][4] = 2;
			map[0][0] = 3;
			break;
		case 7:
			map[5][4] = 0;
			map[0][0] = 0;
			map[3][3] = 2;
			map[0][7] = 3;
			break;
		}
		count %= 8;
	}
}
