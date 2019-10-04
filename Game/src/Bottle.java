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
	private int bpx;
	private int bpy;

	public Bottle() {
		x = 540;
		y = 340;
		w = 165;
		h = 380;
		sh = 422;
		bpx = x+80;
		bpy = y-20;
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/bottle(165X422).png");
		imgIndex = 0;
		honeyScore = 0;
	}

	public void draw(Graphics g2, HoneyBeeCanvas honeyBeeCanvas) {
		int sx = imgIndex * w;
		g2.drawImage(img, x, y, x + w, y + h, 0 + sx, 0, w + sx, sh, honeyBeeCanvas);
	}

	public int beePosX(int x) {
		x = bpx;
		return x;
	}

	public int beePosY(int y) {
		y = bpy;
		return y;
	}

	public int getHoney(int honey) {
		honeyScore = honeyScore + honey;
		int cnt;
		switch (cnt = honeyScore / 10) {
		case 1:
			imgIndex = 1;
			break;
		case 2:
			imgIndex = 2;
			break;
		case 3:
			imgIndex = 3;
			break;
		case 4:
			imgIndex = 4;
			break;
		case 5:
			imgIndex = 5;
			break;
		case 6:
			imgIndex = 6;
			break;
		case 7:
			imgIndex = 7;
			break;
		case 8:
			imgIndex = 8;
			break;
		case 9:
			imgIndex = 9;
			break;
		case 10:
			imgIndex = 10;
			break;
		}

//		System.out.println("total honey : "+honeyScore);
		return honeyScore;
	}

	public int getHoney() {
//		System.out.println("getHoney"+honeyScore);
		return honeyScore;
	}


}
