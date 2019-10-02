
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Honey {

	private int w;
	private int h;
	private int x;
	private int y;

	private int sx;
	private int timer;
	private int imageIndex;
	private Image img;

	public Honey(int x, int y) {

		this.x = x;
		this.y = y;

		w = 15;
		h = 15;

		timer = 0;
		imageIndex = 4;

		sx = imageIndex * w;

		Toolkit tk = Toolkit.getDefaultToolkit();

		img = tk.getImage("res/honeyIndex(15X15).png");
	}

	public Honey() {
		this(45, 45);
	}

	public Point[] onHoney(Point[] point) {
		for (int z = 0; z < 6; z++) {
			if ((point[z].x > (x - 8)) &&
					(point[z].x < (x + 8)) && 
					(point[z].y > (y - 8)) && 
					(point[z].y < (y + 8))) {
				point[z].honey = true;
//			System.out.println(x + "," + y);
//			System.out.println(z);
//				System.out.println(point[z].honey);
			}
			System.out.println(point[z].honey);
		}

		return point;
	}

	public void draw(Graphics g, HoneyBeeCanvas honeyBeeCanvas) {

		int sx = imageIndex * w;
		g.drawImage(img, x, y, x + w, y + h, sx, 0, sx + w, h, honeyBeeCanvas);
	}

	public void update() {

		timer++;
		if (timer >= 300 /* 60*5초 */) {
			sx += w;
			if (sx == 75) {
				sx = 0;
			}
			timer = 0;
		}

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

}
