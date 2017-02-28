import java.awt.*;
import javax.swing.*;

public class Window extends JFrame {

	public Window(int x, int y) {
		this.setSize(x, y);
		this.setTitle("Maze");
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

}
