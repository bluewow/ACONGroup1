

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class GameTimer {
	
	private int x;
	private int y;
	private int w;
	private int h;
	private Image img;
	
	
	public GameTimer() {
		x = 45;
		y = 85;
		w = 486;
		h = 36;
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/Timer 02.png");
	}

	public void draw(Graphics g, HoneyBeeCanvas canvas) {
		g.drawImage(img, x, y,w,h, canvas);
		
	}
	
	public void update() {
		
	}

}
