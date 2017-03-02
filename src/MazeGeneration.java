import java.util.Arrays;
import java.util.Collections;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class MazeGeneration {
	private int width , height;
	private int[][] maze;
	private int[][] map;

	public MazeGeneration(int x, int y) {
		this.width = x;
		this.height = y;
		this.maze = new int[width][height];
		createMaze(0,0);
	}
	
	private void createMaze(int cWidth, int cHeight) {
		Compass[] direction = Compass.values();
		Collections.shuffle(Arrays.asList(direction));
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

	private boolean arrayBounds (int index, int max) {
		return (index >= 0 && index < max );
	}
	
	private boolean mapBounds(int i, int j) {
		return i/2 < height && j/2 < width;
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
	
	public JFrame drawMaze() {
		JFrame frame = new JFrame();
		frame.setSize((width+1)*32, (height+1)*32);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(new MazeGraphics(width, height, maze));
		frame.setTitle("Maze");
		frame.setResizable(false);
		frame.setVisible(true);
		return frame;
	}
	
	public int[][] generateMap() {
		map = new int [width*2+1][height*2+1];
		for (int i = 0; i < height*2+1; i++) {
			for (int j = 0; j < width*2+1; j++) {
				if (i % 2 == 0 || j % 2 == 0){
					map[j][i] = 1;
				}
				if (j % 2 != 0 && mapBounds(i,j) && (maze[j/2][i/2] & 1) != 0 )
					map[j][i] = 0;
				if (i % 2 != 0 && mapBounds(i,j) && (maze[j/2][i/2] & 8) != 0)
					map[j][i] = 0;
			}
		}
		return map;
	}
	
	public static void main (String [] args) {
		MazeGeneration maze = new MazeGeneration(8,10);
		maze.generateMap();
	}
}
