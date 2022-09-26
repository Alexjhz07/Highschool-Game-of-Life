import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import javax.swing.JPanel;

public class Panel extends JPanel {
	//Ignore this
	private static final long serialVersionUID = 1L;
	
	//Game display variables
	private static final int unitSize = 25;
	private static final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
	private static final int marginUnits = 5;
	private static final int width = (int)(screen.getWidth() - screen.getWidth()%unitSize - marginUnits*unitSize);
	private static final int height = (int)(screen.getHeight() - screen.getHeight()%unitSize - marginUnits*unitSize);
	
	//2D array to store coordinates
	private int coords[][] = new int[width/unitSize][height/unitSize];
	
	//Game variables
	private int tick = 500;
	private boolean running = false;
	private Timer timer;
	
	//Initialize panel
	Panel() {
		this.setPreferredSize(new Dimension(width, height));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addMouseListener(new MouseListener());
		this.addKeyListener(new KeyListener());
		System.out.println("Current Tick Speed: " + tick + "\nGame Running Status: " + running + "\n\n\n\n");
		reset();
	}
	
	//Resets the game
	public void reset() {
		for (int a = 0; a < width/unitSize; a++) {
			for (int b = 0; b < height/unitSize; b++) {
				coords[a][b] = 0;
			}
		}
		repaint();
	}
	
	//Adds component to panel
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	//Graphics
    public void paint(Graphics g) {
		g.setColor(Color.yellow);
		for (int i = 0; i < width/unitSize; i++) {
			for (int j = 0; j < height/unitSize; j++) {
				if (coords[i][j] == 1) {
					g.fillRect(i*unitSize, j*unitSize, unitSize, unitSize);
				} else {
					g.setColor(Color.gray);
					g.fillRect(i*unitSize, j*unitSize, unitSize, unitSize);
					g.setColor(Color.yellow);
				}
			}
		}
		
		//Draws grid lines, can be disabled if wanted
		g.setColor(Color.black);
		for (int i = 0; i < width/unitSize; i++) {
			g.drawLine(i*unitSize, 0, i*unitSize, height);
			
		}
		for (int j = 0; j < height/unitSize; j++) {
			g.drawLine(0, j*unitSize, width, j*unitSize);
		}
    }
    
    //Progresses game by one generation
    public void nextGeneration() {
    	int[][] storage = new int[width/unitSize][height/unitSize];
    	
		for (int i = 0; i < width/unitSize; i++) {
			for (int j = 0; j < height/unitSize; j++) {
				int neighbours = 0;
				for (int k = Math.max(i - 1, 0); k < Math.min(i + 2, width/unitSize); k++) {
					for (int l = Math.max(j - 1, 0); l < Math.min(j + 2, height/unitSize); l++) {
						if (coords[k][l] == 1) {
							neighbours++;
						}
					}
				}
				if (coords[i][j] == 1 && neighbours > 2 && neighbours < 5) {
					storage[i][j] = 1;
				} else if (coords[i][j] == 0 && neighbours == 3) {
					storage[i][j] = 1;
				} else {
					storage[i][j] = 0;
				}
			}
		}
		coords = storage.clone();
		repaint();
    }
    
    //Starts the automatic progression
    public void setTimer(int interval) {
    	timer = new Timer();
    	timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				nextGeneration();
			}
    	}, 0, interval);
    }
    
    //Stops the automatic progression
    public void stopTimer() {
    	timer.cancel();
    }
    
    //For mouse interactions
    public class MouseListener extends MouseAdapter {
    	@Override
    	public void mousePressed(MouseEvent e) {
    		if (coords[(e.getX() - e.getX()%unitSize)/unitSize][(e.getY() - e.getY()%unitSize)/unitSize] == 1) {
    			coords[(e.getX() - e.getX()%unitSize)/unitSize][(e.getY() - e.getY()%unitSize)/unitSize] = 0;
    		} else {
    			coords[(e.getX() - e.getX()%unitSize)/unitSize][(e.getY() - e.getY()%unitSize)/unitSize] = 1;
    		}
    		repaint();
    	}
    }
    
    //For keyboard commands
    public class KeyListener extends KeyAdapter {
    	@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_SPACE:
				if (!running) {
					running = true;
					setTimer(tick);
				} else {
					running = false;
					stopTimer();
				}
				System.out.println("Current Tick Speed: " + tick + "\nGame Running Status: " + running + "\n\n\n\n");
				break;
			case KeyEvent.VK_LEFT:
				if (tick <= 4950) {
					tick += 50;
					System.out.println("Current Tick Speed: " + tick + "\nGame Running Status: " + running + "\n\n\n\n");
				}
				if (running) {
					stopTimer();
					setTimer(tick);
				}
				break;
			case KeyEvent.VK_RIGHT:
				if (tick >= 100) {
					tick -= 50;
					System.out.println("Current Tick Speed: " + tick + "\nGame Running Status: " + running + "\n\n\n\n");
				}
				if (running) {
					stopTimer();
					setTimer(tick);
				}
				break;
			case KeyEvent.VK_DOWN:
				reset();
				if (running) {
					running = false;
					stopTimer();
					System.out.println("Current Tick Speed: " + tick + "\nGame Running Status: " + running + "\n\n\n\n");
				}
				break;
			case KeyEvent.VK_PERIOD:
				nextGeneration();
				break;
			}
		}
    }
}
