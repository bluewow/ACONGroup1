

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Score {

	private int x;
	private int y;
	private int w;
	private int h;
	private int score;
	private Image img;

	public Score() {
		x = 590;
		y = 80;
		w = 156;
		h = 46;
		score = 0;
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/Score100.png");
	}

	public void draw(Graphics g, HoneyBeeCanvas canvas) {
		g.drawImage(img, x, y,w,h, canvas);
		

	}

	public void update() {

	}

}
