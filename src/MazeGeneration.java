import java.util.Arrays;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class MazeGeneration {
	private int width , height;
	private int[][] maze;

	public MazeGeneration(int x, int y) {
		this.width = x;
		this.height = y;
		this.maze = new int[width][height];
		createMaze(0,0);
		drawMaze();
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
	
	private JFrame drawMaze() {
		JFrame frame = new JFrame();
		frame.setSize((width*32)+8, (height+1)*32);
		frame.setTitle("Maze");
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(new MazeGraphics(width, height, maze));
		return frame;
	}

	public static void main (String [] args) {
		new MazeGeneration(25,25);
	}
}
