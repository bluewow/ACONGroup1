

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
	
	public TimeBee() {
		x = 490;
		y = 90;
		w = 40;
		h = 27;
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/Å¸ÀÌ¸Ó¿ë¹ú.png");
		sc = (int)System.currentTimeMillis();
		
	}

	public void draw(Graphics g, HoneyBeeCanvas canvas) {
		g.drawImage(img, x,y,w,h,canvas);
		
	}

	public void update() {
		System.out.println(((int)System.currentTimeMillis()-sc)/1000);
		x = 470- (((int)System.currentTimeMillis()-sc)/1000)*7;
		if(((int)System.currentTimeMillis()-sc)/1000>=60)
			x = 50;
		
		
		
	}
	

}
