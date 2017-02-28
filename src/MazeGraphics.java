import javax.swing.JComponent;
import java.awt.*;

public class MazeGraphics extends JComponent{
	private int x, y;
	public MazeGraphics (int x, int y) {
		super();
		this.x = x; this.y = y;
	}
	
	 @Override
     protected void paintComponent(Graphics g) {
		 super.paintComponent(g);		 
		 g.setColor(Color.BLACK);
		 g.fillRect(0, 0, x*32, y*32);
		 g.drawRect(0, 0, x*32, y*32);
		 g.setColor(Color.WHITE);

		 for (int i = 0; i < x; i++) {
			 for (int j = 0; j < y; j++) {
				 g.fillRect(i+4, j+4, 26, 26);
				 g.drawRect(i+4, j+4, 26, 26);
				 System.out.println(i  + " " + j);
			 }
		 }
		 
	 }
}
