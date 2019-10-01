import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Bottle {

	private Image img;
	private int imgIndex;
	private int x;
	private int y;
	private int w;
	private int h;
	private int sh;
	private int honeyScore;

	public Bottle() {
		x = 540;
		y = 340;
		w = 165;
		h = 380;
		sh = 422;

		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/bottle(165X422).png");
		imgIndex = 0;
		honeyScore = 0;
	}

	public void draw(Graphics g2, HoneyBeeCanvas honeyBeeCanvas) {
		int sx = imgIndex * w;
		g2.drawImage(img, x, y, x + w, y + h, 0 + sx, 0, w + sx, sh, honeyBeeCanvas);
	}

	public int getHoney() {

		honeyScore =+ this.getHoney();
		if(honeyScore>=10) {
			imgIndex = 1;
		} else if(honeyScore>=20) {
			imgIndex = 2;
		}  else if(honeyScore>=20) {
			imgIndex = 2;
		}  else if(honeyScore>=30) {
			imgIndex = 3;
		}  else if(honeyScore>=40) {
			imgIndex = 4;
		}  else if(honeyScore>=50) {
			imgIndex = 5;
		}  else if(honeyScore>=60) {
			imgIndex = 6;
		}  else if(honeyScore>=70) {
			imgIndex = 7;
		}  else if(honeyScore>=80) {
			imgIndex = 8;
		}  else if(honeyScore>=90) {
			imgIndex = 9;
		}  else if(honeyScore>=100) {
			imgIndex = 10;
			honeyScore = 100;
		}
		
		return honeyScore;
	}

}
