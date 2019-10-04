
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

	Image img;
	Honey[][] honeies;
	Honey honey1;

	public Flower() {

		z = 3;
		x = 150;
		y = 330;
		w = 260;
		h = 380;
		hx = x + 47;
		hy = h - 20;

		honeies = new Honey[10][10];
		for (int i = 0; i < 5; i++) {
			z = 2 - i;
			if (z < 0)
				z = 0;
			hy += 15;
			for (int j = 0 + z; j < 10 - z; j++) {
				honeies[i][j] = new Honey(hx + z * 15, hy);
				System.out.printf("%d,%d\n", i, j);
				hx += 15;
			}
			hx = x + 47;
		}
		for (int i = 5; i < 10; i++) {
			z = i - 7;
			if (z < 0)
				z = 0;
			hy += 15;
			for (int j = 0 + z; j < 10 - z; j++) {
				honeies[i][j] = new Honey(hx + z * 15, hy);
//			    System.out.printf("%d,%d\n",i,j);
				hx += 15;
			}
			hx = x + 47;
		}
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/flowerAll(260X380).png");
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
		// TODO Auto-generated method stub
		g2.drawImage(img, x, y, x + w, y + h, 0, 0, w * 4, h * 4, honeybeecanvas);

		for (int i = 0; i < 10; i++)
			for (int j = 0; j < 10; j++)
				if (honeies[i][j] != null)
					honeies[i][j].draw(g2, honeybeecanvas);
	}
}