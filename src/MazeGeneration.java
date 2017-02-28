import java.util.Arrays;
import java.util.Collections;

public class MazeGeneration {
	private int width , height;
	private int[][] maze;

	public MazeGeneration(int x, int y) {
		this.width = x;
		this.height = y;
		this.maze = new int[width][height];
		createMaze(0,0);
		System.out.println(Arrays.deepToString(maze));
		display();
		draw();
	}
	private void createMaze(int cWidth, int cHeight) {
		Compass[] direction = Compass.values();
		Collections.shuffle(Arrays.asList(direction));
		System.out.println(Arrays.toString(direction));
		for (Compass pointing: direction) {
			int nextW = cWidth + pointing.compassX;
			int nextH = cHeight + pointing.compassY;
			if (arrayBounds(nextW, width) && arrayBounds (nextH, height) && maze[nextW][nextH] == 0) {
				maze[cWidth][cHeight] |= pointing.value;
				maze[nextW][nextH] |= pointing.opposite.value;
				createMaze(nextW, nextH);
			}
			
		}
	}

	public int[][] getMaze () {
		return maze;
	}
	
	private boolean arrayBounds (int index, int max) {
		return (index >= 0 && index < max );
	}
	
	private enum Compass {
		N(1, 0, -1),S(2, 0, 1),W(8, -1, 0),E(4, 1, 0);
		private int value, compassX, compassY;
		private Compass opposite;
		
		static {
			N.opposite = S;	S.opposite = N;
			W.opposite = E; E.opposite = W;
		}
		
		private Compass(int value, int compassX, int compassY) {
			this.value = value; this.compassX = compassX; this.compassY = compassY;
		}
	}
	
	public void display() {
		for (int i = 0; i < height; i++) {
			// draw the north edge
			for (int j = 0; j < width; j++) {
				System.out.print((maze[j][i] & 1) == 0 ? "+---" : "+   ");
			}
			System.out.println("+");
			// draw the west edge
			for (int j = 0; j < width; j++) {
				System.out.print((maze[j][i] & 8) == 0 ? "|   " : "    ");
			}
			System.out.println("|");
		}
		// draw the bottom line
		for (int j = 0; j < width; j++) {
			System.out.print("+---");
		}
		System.out.println("+");
	}
	
	public void draw() {
		new Window(width, height);
	}
	
	public static void main (String [] args) {
		new MazeGeneration(15,15);
	}
}
