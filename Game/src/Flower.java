
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Flower {

	private int x;
	private int y;
	private int emptyhoney;
	private int w;
	private int h;
	private int hx;
	private int hy;
	private int imageindex;
	private int imagedelay;

	private Image img;
	private Honey[][] honeies;
//	Honey[] honeies2;

	public Flower() {
		emptyhoney = 0;
		x = 150;
		y = 330;
		w = 260;
		h = 380;
		hx = x + 45;
		hy = h - 20;
		imageindex = 0;
		imagedelay = 1;
		honeies = new Honey[10][10];
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/flowerFullIndex.png");
		honeyPosition();
	}

	public void honeyPosition() {
		for (int i = 0; i < 5; i++) {
			emptyhoney = 2 - i;
			honeymake(i);
		}
		for (int i = 5; i < 10; i++) {
			emptyhoney = i - 7;
			honeymake(i);
		}
	}
	
	public void honeymake(int i) {
		if (emptyhoney < 0)
			emptyhoney = 0;
		hy += 15;
		for (int j = 0 + emptyhoney; j < 10 - emptyhoney; j++) {
			honeies[i][j] = new Honey(hx + emptyhoney * 15, hy);
			hx += 15;
		}
		hx = x + 45;
	}
	
	

	public Point[] putHoney(Point[] point) {
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++) {
				if (honeies[i][j] != null) {
				   honeies[i][j].onHoney(point);
				}
 
			}
		return point;
	}
 
	public void flowerUpdate() {
		
		for (int i = 0; i < honeies.length; i++) {
			for (int j = 0; j < honeies[i].length; j++) {
				if (honeies[i][j] != null)
					honeies[i][j].update();
			}
		}
			
		if (imagedelay++ % 30 == 0) {
			if (imageindex < 0) {
				imageindex++;
			} else {
				imageindex--;
			}
		}
	}
	
	public void update() {
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++) {
				honeies[i][j].getX();
				honeies[i][j].getY();
			}
	}

	public void draw(Graphics g2, HoneyBeeCanvas honeybeecanvas) {
		g2.drawImage(img, x, y, x + w, y + h, 0 - w * imageindex, 0, w - w * imageindex, h, honeybeecanvas);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				if (honeies[i][j] != null)
					honeies[i][j].draw(g2, honeybeecanvas);
	}

}