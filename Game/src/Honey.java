

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Honey {

	int x;
	int y;
	
	private int imgIndex;
	
	private Image img;
	
	public Honey(int x, int y) {
		
		this.x = x;
		this.y = y;
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/�� �ε���.png");
		
	}
	
	public void draw(Graphics g, HoneyBeeCanvas honeyBeeCanvas) {
		g.drawImage(img, 
						x, y, 30, 30, 
					   480, 0, 600, 120, 
					   honeyBeeCanvas);
		
		
	}
	
	
}
