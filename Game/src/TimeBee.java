
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class TimeBee {
	private int x;
	private int y;
	private int w;
	private int h;
	private Image img;
	private int sc;
	private int timer;
	
	public TimeBee() {
		x = 490;
		y = 90;
		w = 40;
		h = 27;
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/timerBee.png");
		sc = (int) System.currentTimeMillis();
		timer = 0;
	}

	public int getX() {
		return x;
	}

	public void draw(Graphics g, HoneyBeeCanvas canvas) {
		g.drawImage(img, x, y, w, h, canvas);
	}

	public void update() {
		timer = (((int) System.currentTimeMillis() - sc) / 1000);
		if(timer <= 60) {
			x = 470 - timer * 7;
			
			if(timer == 60)
				x = 50;
		}
	}

	public int getTime() {
		return timer;
	}
}
