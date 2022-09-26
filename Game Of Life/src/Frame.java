import javax.swing.JFrame;

public class Frame extends JFrame {
	//Ignore this
	private static final long serialVersionUID = 1L;

	//Initializes the JFrame
	Frame() {		
		this.add(new Panel());
		this.setTitle("Game of Life");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);		
	}
}
