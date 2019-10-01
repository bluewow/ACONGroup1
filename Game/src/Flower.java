

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Flower {
	
	
	private int x;
	private int y;
	private int w;
	private int h;
	private int hx;
	private int hy;
	Image img;
	Honey[][] honeies; 
	Honey honey1;

	public Flower() {
		x =150;
		y =330;
		w =260;
		h =380;
		hx = 44;
		hy = 40;
		
		honeies = new Honey[10][10];
		for(int i = 0; i<10 ; i++) 
		{
		hy+=15;
			for( int j = 0 ; j<10; j++)
			{
				honeies[i][j] = new Honey(hx,hy);
				System.out.println(i+","+j);
				hx += 15;
			}
		hx =0;
	    }
//		honey1 = new Honey(hx,hy);
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/flowerAll(260X380).png");
	}


	public void draw(Graphics g2, HoneyBeeCanvas honeybeecanvas) {
		// TODO Auto-generated method stub
		g2.drawImage(img, x, y, x+w, y+h,
				0, 0, w*4, h*4,honeybeecanvas);
		
		for(int i = 0; i<10 ; i++) 		
			for( int j = 0 ; j<10; j++)
				honeies[i][j].draw(g2, honeybeecanvas);
			
	
	}
	

}
