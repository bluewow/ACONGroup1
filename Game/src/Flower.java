

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
		x =150;
		y =330;
		w =260;
		h =380;
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/꽃 전체(260X380).png");
	}

	public void draw(Graphics g2, HoneyBeeCanvas honeybeecanvas) {
		// TODO Auto-generated method stub
		g2.drawImage(img, x, y, x+w, y+h,
				0, 0, w*4, h*4,honeybeecanvas);
	}
}
