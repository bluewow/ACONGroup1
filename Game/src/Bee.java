import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Bee {
	private int xPos;
	private int yPos;
	private int w;
	private int h;
	private Image img;
	
	
	public Bee() {
		xPos = 400;
		yPos = 400;
		w = 176;
		h = 136;
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/벌(176X136).png");
	}
	
	public void getHoney() {
		
	}
	
	public int sendHoney() {

		return 0;
	}

	public void draw(Graphics g2, HoneyBeeCanvas honeyBeeCanvas) {
		g2.drawImage(img, xPos, yPos, xPos + w, yPos+h, 
					0, 0, w * 4, h * 4, honeyBeeCanvas);
	}
}
