import javax.swing.JComponent;
import java.awt.*;

public class MazeGraphics extends JComponent{
	private static final long serialVersionUID = 1L;
	private int x, y;
	private int[][] maze;
	public MazeGraphics (int x, int y, int[][] maze) {
		super();
		this.maze = maze;
		this.x = x; this.y = y; 
	}
	
	 @Override
     protected void paintComponent(Graphics g) {
		 super.paintComponent(g);		 
		 g.setColor(Color.BLACK);
		 g.fillRect(0, 0, (x+1)*32, (y+1)*32);
		 g.setColor(Color.WHITE);

		 for (int i = 0; i < x; i++) {
			 for (int j = 0; j < y; j++) {
				 g.fillRect((i*32)+4, (j*32)+4, 28, 28);
				 if ((maze[i][j] & 1) != 0) {
					 g.fillRect((i*32)+4, (j*32), 28, 4);
				 }
				 if ((maze[i][j] & 8) != 0) {
					 g.fillRect((i*32), (j*32)+4, 4, 28);
				 }
			 }
		 }
		 
	 }
}
