import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Honey {
	private int w;
	private int h;
	private int x;
	private int y;
	private int sx;
	private int endHoneyImage;
	private int timer;
	private static Image img;
	private static Toolkit tk;
	private boolean isEmpty;
	private boolean sxChecker;

	static {
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/honeyIndex(15X15).png");
	}

	public Honey() {
		this(45, 45);
	}

	public Honey(int x, int y) {
		this.x = x;
		this.y = y;
		w = 15;
		h = 15;
		isEmpty = false;
		timer = 0;
		sx = 4 * w;
		sxChecker = true;
		endHoneyImage = 60;
	}

	public void update() {
		if (isEmpty) {
			timer++;
			if (timer >= 120) {
				sx += w;
				if (sxChecker) {
					sx = 0;
					sxChecker = false;
				}
				timer = 0; //
				if (sx == endHoneyImage) {
					isEmpty = false;
					sxChecker = true;
				}
			}
		}

	}

	public void draw(Graphics g, HoneyBeeCanvas honeyBeeCanvas) {

		g.drawImage(img, x, y, x + w, y + h, sx, 0, sx + w, h, honeyBeeCanvas);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void onHoney(Point[] point) {
		for (int z = 0; z < point.length; z++) {
			if ((point[z].x > (x - 8)) && 
					(point[z].x < (x + 8)) && 
					(point[z].y > (y - 8)) && 
					(point[z].y < (y + 8))) {
				if (isEmpty)
					isEmpty = false;
				else
					point[z].honey = true;
				    isEmpty = true;
			}
		}
	}

}