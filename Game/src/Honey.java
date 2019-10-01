

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Honey {


	private int w ;
	private int h ;
	private int x;
	private int y;
	
	

	int timer;
	private int imageIndex;	
	private Image img;
	
	public Honey(int x, int y) {

		this.x = x;
		this.y = y;
		w =15;
		h =15;

		timer = 0;
		imageIndex = 4;
		
		Toolkit tk = Toolkit.getDefaultToolkit();

		img = tk.getImage("res/honeyIndex(15X15).png");
	}
	
	public Honey() {
		this(45,45);

	}
	
	public void draw(Graphics g, HoneyBeeCanvas honeyBeeCanvas) {
		
		int sx = imageIndex*w;
		g.drawImage(img, 
				x, y, x+w, y+h, 
				sx, 0, sx + w, h, 
			   honeyBeeCanvas);	
	}
		

//	public void update() {
//		timer++;
//		if (timer >= 300 /*  60*5초  */) {
//			imagex += xWidth;
//			if (imagex == 600) {
//				imagex = 0;
//			}
//			timer = 0;
//		}
//	}

}
