

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Flower {
	
	
	private int x;
	private int y;
	private int w;
	private int h;
	Image img;
	

	public Flower() {
		x =260;
		y =380;
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/²É ÀüÃ¼(260X380).png");
	}

	public void draw(Graphics g2, HoneyBeeCanvas honeybeecanvas) {
		// TODO Auto-generated method stub
		g2.drawImage(img, 0, 0, x, y,
				0, 0, x*4, y*4,honeybeecanvas);
	}
}
