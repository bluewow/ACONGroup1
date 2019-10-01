import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

// @author : naram kim
public class BarBee {
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private Image img; // Get Bee image file
	private int x; // Bee position
	private int y;
	private int bx; // The beginning of the range of the bar
	private int by;
	private int width; // Size of Bee
	private int height;
	private int range; // Range of Bar
	private int vector; // Bee`s speed and direction
	private boolean horizontal; // Bar Horizontal(default) and vertical

	public BarBee(String imgRes, int bx, int by, int vector, int range, boolean horizontal) {
		img = tk.getImage(imgRes);
		this.bx = bx;
		this.by = by;
		this.vector = vector;
		this.range = range;
		this.horizontal = horizontal;

		if (horizontal) {
			x = bx + range / 2;
			width = 27;
			height = 40;
		} else {
			y = by + range / 2;
			width = 40;
			height = 27;
		}
	}

	protected void draw(Graphics g, HoneyBeeCanvas canvas) {
		if (horizontal)
			g.drawImage(img, x, by, width, height, canvas);
		else
			g.drawImage(img, bx, y, width, height, canvas);
	}

	protected void move() {
		if (horizontal) {
			x += vector;
			if (bx + range - width <= x || bx + 0 >= x)
				vector *= -1;
		} else {
			y += vector;
			if (by + range - height <= y || by + 0 >= y)
				vector *= -1;
		}
	}

	protected void stop() {
		vector = 0;
	}

	protected int getX() {
		return x;
	}

	protected int getY() {
		return y;
	}
}
