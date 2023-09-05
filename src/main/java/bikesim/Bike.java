package bikesim;

public class Bike {
	
	static final int NORTH=0, EAST=1, SOUTH=2, WEST=3;
	static final String[] DIRS = {"NORTH", "EAST", "SOUTH", "WEST"};

	static final int X=0, Y=1, MIN_COORD=0, MAX_COORD=6;
	static final int[][] COORD_DELTAS = {
		{0,1},	// NORTH	  	  
		{1,0},	// EAST  
		{0,-1},	// SOUTH  
		{-1,0}	// WEST
	}; 

	int curCoord[] = {0,0}; // curCoor[0] = x coord, curCoord[1] = y coord
	int curDir = NORTH;
	
	boolean isPlaced = false;

	/***************************************************/
	
	boolean place(int x, int y, int newDir) {
		
		boolean ret = checkCoord(x, y);
		
		if(ret) {
			curCoord[X] = x;
			curCoord[Y] = y;
			curDir = newDir;
			isPlaced = true;
		}
		
		return ret;
		
	}
	
	/***************************************************/
	
	boolean forward() {

		boolean ret = false;
		
		if(isPlaced) {
			
			int[] newCoord = {curCoord[X] + COORD_DELTAS[curDir][X], curCoord[Y] + COORD_DELTAS[curDir][Y]};
			
			ret = checkCoord(newCoord[X], newCoord[Y]);
			
			if(ret) {
				curCoord = newCoord;
			}

		}
		
		return ret;
		
	}

	/***************************************************/
	
	boolean turn(int deltaDir) {

		boolean ret = false;
		
		if(isPlaced) {

			curDir += deltaDir;
			
			if(curDir < NORTH) {
				curDir = WEST;
			} else if(curDir > WEST) {
				curDir = NORTH;
			}  
			
			ret = true;
			
		}
		
		return ret;
		
	}
	
	/***************************************************/
	
	boolean checkCoord(int x, int y) {
		
		boolean ret = true;

		if(x < MIN_COORD || x > MAX_COORD || y < MIN_COORD || y > MAX_COORD) {
			ret = false;
		}
		
		return ret;
		
	}

	/***************************************************/
	
	public String getGPSReport() {
		return "(" + curCoord[X] + "," + curCoord[Y] + "), " + DIRS[curDir];
	}
	
}
