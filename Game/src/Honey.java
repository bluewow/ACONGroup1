

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Honey {

	private int x;
	private int y;
	private int w ;
	private int h ;
	
	
	private int imgIndex;
	
	private Image img;
	
	public Honey() {
		
		x =45;
		y =45;
		w =15;
		h =15;
		
		Toolkit tk = Toolkit.getDefaultToolkit();

		img = tk.getImage("res/HoneyIndex(30X30).png");

		
	}
	
	public void draw(Graphics g, HoneyBeeCanvas honeyBeeCanvas) {
				g.drawImage(img, 

						x, y, x+w, y+h, //ȭ����ǥ
					   480, 0, 600, 120, // �̹�����ǥ

					   honeyBeeCanvas);
		
		
	}
	
	
}
