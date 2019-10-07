
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Flower {

	private int x;
	private int y;
	private int z;
	private int w;
	private int h;
	private int hx;
	private int hy;
	private int setx;
	private int sety;
	private int imageindex;
	private int imagedelay;

	Image img;
	Honey[][] honeies;
	Honey honey1;
//	Honey[] honeies2;

	public Flower() {

		z = 0;
		x = 150;
		y = 330;
		w = 260;
		h = 380;
		hx = x + 45;
		hy = h - 20;
		imageindex = 0;
		imagedelay = 1;

		honeies = new Honey[10][10];
//		honeies2 = new Honey[100]; // honeies2[0]

		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/flowerFullIndex.png");
		honeyPosition();
	}

	public void honeyPosition() {
		for (int i = 0; i < 5; i++) {
			z = 2 - i;
			if (z < 0)
				z = 0;
			hy += 15;
			for (int j = 0 + z; j < 10 - z; j++) {
				honeies[i][j] = new Honey(hx + z * 15, hy);
				hx += 15;
			}
			hx = x + 45;
		}
		
		for (int i = 5; i < 10; i++) {
			z = i - 7;
			noneymake(i);
		}
	}
	
	public void noneymake(int i) {
		if (z < 0)
			z = 0;
		hy += 15;
		for (int j = 0 + z; j < 10 - z; j++) {
			honeies[i][j] = new Honey(hx + z * 15, hy);
//		    System.out.printf("%d,%d\n",i,j);
			hx += 15;
		}
		hx = x + 45;
		
	}
	
	

	public Point[] putHoney(Point[] point) {
	
		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++) {
				if (honeies[i][j] != null) {
				   honeies[i][j].onHoney(point,honeies,i,j);
				}
		}
		return point;
	}
 
	public void flowerUpdate() {
		if (imagedelay++ % 30 == 0) {
			if (imageindex < 0) {
				imageindex++;
			} else {
				imageindex--;
			}
		}
	}

	public void setX(int setx) {
		this.setx = setx;
	}

	public void setY(int sety) {
		this.sety = sety;
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
//		g2.drawRect(15,15,15,15);
	}

}