import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Victory {
	private int x;
	private int y;
	private int w;
	private int h;
	
	private Image img;
	
	public Victory() {
		x=200;
		y=400;
		w=255;
		h=85;
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/toTitleButton(255X85).png");
	}
	
	public void draw(Graphics g, HoneyBeeCanvas canvas) {
		g.drawImage(img, x,y,w,h, canvas);

	}

}
