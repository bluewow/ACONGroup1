import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

// @author : naram kim
public class Bar {
	private static Toolkit tk = Toolkit.getDefaultToolkit();;
	private Image img;			// Get Bee image file
	private BarBee bee;			// Get Bee instance
	private int x; 				// Position of Bar
	private int y;
	private int width;	 		// Size of Bar
	private int height;
	private boolean horizontal; // Bar Horizontal(default) and vertical

	public Bar() {
		this(100, 600, 1, true);
	}

	public Bar(int x, int y, boolean horizontal) {
		this(x, y, 1, horizontal);
	}

	public Bar(int x, int y, int vector, boolean horizontal) {
		this.x = x;
		this.y = y;
		this.horizontal = horizontal;

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

	public void update() {
		bee.move();
	}

	public void draw(Graphics g, BeeCanvas canvas) {
		g.drawImage(img, x, y, width, height, canvas);

		bee.draw(g, canvas);		
	}

	public void setVector(int vector) {
		
	}

	public int getPos() {
		if (horizontal)
			return bee.getX();
		else
			return bee.getY();
	}
}