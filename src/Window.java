import java.awt.*;
import javax.swing.*;

public class Window {
	private int row, column;
	
	public Window(int x, int y) {
		this.row = x;
		this.column = y;
		createFrame();
	} 
	

	
	private JFrame createFrame() {
		JFrame frame = new JFrame();
		frame.setSize(row*32, column*32);
		frame.setTitle("Maze");
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.add(new MazeGraphics(row, column));
		return frame;
	}

}
