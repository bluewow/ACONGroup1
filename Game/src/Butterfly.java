

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Butterfly {
	private int x;
	private int y;
	private int vx;
	private int vy;

	private int w;
	private int h;
	private int speed;

	private Image img;

	public Butterfly() {
		speed = 3;
		x = 150;
		y = 250;
		w = 122;
		h = 96;

		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/나비(122X96).png");

	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public void update() {

	}

	public void draw(Graphics g2, HoneyBeeCanvas honeyBeeCanvas) {
		g2.drawImage(img, x, y, x+w, y+h, 0, 0, w*4, h*4, honeyBeeCanvas);
		

	}

	public void move(int x, int y) {
	

	}

}
