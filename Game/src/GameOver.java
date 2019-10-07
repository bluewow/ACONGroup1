import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class GameOver {
	private int x;
	private int y;
	private int w;
	private int h;
	private int sx;
	private int sy;
	
	private Image img;

	public GameOver() {
		x=200;
		y=400;
		w=255;
		h=85;
		sx=0;
		sy=0;
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("GameOverBtnTemplet(255X85).png");

	}

	public void draw(Graphics g, HoneyBeeCanvas canvas) {
		System.out.println("게임오버");
		g.drawImage(img, x, y, x+w,y+h,sx,sy,sx+w,sy+h, canvas);
		//g.drawImage(img, x, y+100, x+w,y+h+100,sx+w, sy,sx+w*2,sy+h, canvas);
	}

}
