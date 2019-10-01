

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Honey {

	private int x;
	private int y;
	private int w ;
	private int h ;
	
	int imagex;
	int xWidth;
	
	int timer;
	private int imgIndex;
	
	private Image img;
	
	public Honey() {
		
		x =45;
		y =45;
		w =15;
		h =15;
		
		timer = 0;

		imagex = 480;
		xWidth = 120;
		
		
		Toolkit tk = Toolkit.getDefaultToolkit();

		img = tk.getImage("res/HoneyIndex(30X30).png");

		
	}
	
	public void draw(Graphics g, HoneyBeeCanvas honeyBeeCanvas) {
				g.drawImage(img, 

						x, y, x+w, y+h, 
						imagex, 0, imagex + xWidth, 120, 

					   honeyBeeCanvas);
		
		
	}
	
	public void update() {

		timer++;
		if (timer >= 300 /*  60*5초  */) {
			imagex += xWidth;
			if (imagex == 600) {
				imagex = 0;
			}
			timer = 0;
		}



	}
	
	
	
}
