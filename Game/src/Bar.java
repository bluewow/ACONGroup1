import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/* 
 * @author : naram kim
 */
public class Bar {
	private static Toolkit tk = Toolkit.getDefaultToolkit();;
	private Image img; // Get Bee image file
	private BarBee bee; // Get Bee instance
	private int x; // Position of Bar
	private int y;
	private int width; // Size of Bar
	private int height;
	private boolean horizontal; // Choose between horizontal(default) and vertical bars
	private boolean activation; // Whether the bar is active

	// Bar default Constructor
	public Bar() {
		this(100, 600, true, true, 3);
	}

	// Constructor with x, y position, direction, activation of bar
	public Bar(int x, int y, boolean horizontal, boolean activation) {
		this(x, y, horizontal, activation, 3);
	}

	// Constructor with x, y position of bar, horizontal and vertical, activation
	// direction and velocity of bee
	public Bar(int x, int y, boolean horizontal, boolean activation, int vector) {
		this.x = x;
		this.y = y;
		this.horizontal = horizontal;
		this.activation = activation;

		// If you choose horizontal
		if (horizontal) {
			img = tk.getImage("res/XBar(266X46).png");
			width = 266;
			height = 46;
			bee = new BarBee("res/XBee(27X40).png", x, y, vector, width, horizontal);
		} else {
			img = tk.getImage("res/YBar(46X386).png");
			width = 46;
			height = 386;
			bee = new BarBee("res/YBee(40X27).png", x, y, vector, height, horizontal);
		}
	}

	// Update the status of the bar
	public void update() {
		if (activation)
			bee.move();
	}

	// Draw Bar
	public void draw(Graphics g, HoneyBeeCanvas canvas) {
		g.drawImage(img, x, y, width, height, canvas);

		bee.draw(g, canvas);
	}

	// Set vector of the bee
	public void setVector(int vector) {
		bee.setVector(vector);
	}

	// Set activation of the bar
	public void setActivation(boolean activation) {
		this.activation = activation;
	}

	// Get position of the bee
	public int getPos() {
		if (horizontal)
			return bee.getX();
		else
			return bee.getY();
	}
}