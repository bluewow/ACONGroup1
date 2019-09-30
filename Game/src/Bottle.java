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
	//인트수에 따라 인덱스 번호가 바뀌어.10점에 인덱스 +1
	
//	int h = sendHoney();
	
	public Bottle() {
		x=480;
		y=240;
		w=165;
		h=422;
		
		Toolkit tk = Toolkit.getDefaultToolkit(); //이미지 그리는 api
		img = tk.getImage("res/꿑통 템플릿(165X422).png"); // 
		imgIndex = 0;
	}

	public void draw(Graphics g2, HoneyBeeCanvas honeyBeeCanvas) {
		int sx = imgIndex;
		g2.drawImage(img, x, y, x+w, y+h, 0, 0, w*4, h*4, honeyBeeCanvas);
	}
	
//	public void 
	
}
