

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Butterfly {
	private int x;
	private int y;
	private int vx;
	private int vy;
//kkkk
	private int w;
	private int h;
	private int speed;

	private Image img;

	public Butterfly() {
		speed = 3;
		x = 150;
		y = 150;
		w = 80;
		h = 80;

		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/nabi.png");

	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void update() {

	}

	public void draw(Graphics g, Butterfly bf) {
		// g.drawImage(img, imglndex, vy, observer);
		

	}

	public void move(int x, int y) {
	

	}

}
