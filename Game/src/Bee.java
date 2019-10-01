import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Bee {
	private int xPos;
	private int yPos;
	private int xPosDest;
	private int yposDest;
	private int[] xLeg = { 17, 28, 60, 71, 107, 115};
	private int[] yLeg = { 119, 122, 134, 132, 131, 130};
	private float speed;
	private int w;
	private int h;
	private static final int MARGIN_W = 88;
	private static final int MARGIN_H = 68;
	private List<Honey> honey = new ArrayList<>();
	
	private Image img;
	
	
	
	public Bee() {
		xPos = 540;
		yPos = 240;
		w = 176;
		h = 136;
		speed = 1.0f;
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/bee(176X136).png");
	}
	
	public void move(int x, int y) {
		//1 목적지까지
		xPosDest = x;
		yposDest = y;
		
		//2 채취
		
		
		//3.돌아오기
	}
	
	public void getHoney() {
		for(int i = 0; i < xLeg.length; i++) {
			if(dummy(xLeg[i], yLeg[i]))
				honey.add(new Honey());
		}
	}
	
	public int sendHoney() {

		return 0;
	}

	public void draw(Graphics g2, HoneyBeeCanvas honeyBeeCanvas) {
		g2.drawImage(img, xPos - MARGIN_W, yPos - MARGIN_H, xPos + w - MARGIN_W, yPos+h - MARGIN_H, 
					0, 0, w, h, honeyBeeCanvas);
		
		g2.drawRect(xPos - MARGIN_W, yPos - MARGIN_H, 176, 136);
		g2.drawRect(xPos, yPos, 3, 3);
	}

	public void update() {
		xPos -= speed;
		yPos += speed;

		if(xPos == xPosDest) {
			speed = 0;
//			if(ok)
			getHoney();
			
			xPosDest = 0;
			
		}
		
		
	}
	
	
}
