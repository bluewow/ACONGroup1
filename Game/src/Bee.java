import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Bee {
	private int xPos;
	private int yPos;
	private int dx;
	private int dy;
//	private int[] xLeg = { 17, 28, 60, 71, 107, 115};
//	private int[] yLeg = { 119, 122, 134, 132, 131, 130};
	private int vx;
	private int vy;
	private int w;
	private int h;
	private static final int MARGIN_W = 88;
	private static final int MARGIN_H = 68;
	
	private Image img;
	private List<Honey> honey = new ArrayList<>();
	private BeeListener listener;
	
	public void addBeeListener(BeeListener listener) {
		this.listener = listener;
	}
	
	public Bee() {
		xPos = 540;
		yPos = 240;
		w = 176;
		h = 136;
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/bee(176X136).png");
	}

	// 1 목적지까지
	// 2 채취
	// 3.돌아오기
	public void move(int x, int y) {

		dx = x;
		dy = y;
		
		float w = dx - this.xPos;
		float h = dy - this.yPos;
		float d = (float)Math.sqrt(w * w + h * h);
		vx = (int) ((w / d) * 3);
		if(vx > 0)
			vx = 1;
		else 
			vx = -1;
		
		vy = (int) ((h / d) * 3);
		if(vy > 0)
			vy = 1;
		else
			vy = -1;
	}
	
	public int sendHoney() {

		return 0;
	}

	public void draw(Graphics g2, HoneyBeeCanvas honeyBeeCanvas) {
		g2.drawImage(img, xPos - MARGIN_W, yPos - MARGIN_H, xPos + w - MARGIN_W, yPos+h - MARGIN_H, 
					0, 0, w, h, honeyBeeCanvas);
		
		//for check
//		g2.drawRect(xPos - MARGIN_W, yPos - MARGIN_H, 176, 136);
//		g2.drawRect(xPos - MARGIN_W + 16, yPos - MARGIN_H + 118, 3, 3);
//		g2.drawRect(xPos - MARGIN_W + 27, yPos - MARGIN_H + 121, 3, 3);
//		g2.drawRect(xPos - MARGIN_W + 59, yPos - MARGIN_H + 133, 3, 3);
//		g2.drawRect(xPos - MARGIN_W + 71, yPos - MARGIN_H + 131, 3, 3);
//		g2.drawRect(xPos - MARGIN_W + 107, yPos - MARGIN_H + 130, 3, 3);
//		g2.drawRect(xPos - MARGIN_W + 114, yPos - MARGIN_H + 129, 3, 3);
		
	}

	public void update() {
		xPos += vx;
		yPos += vy;
		
		if(yPos == dy)
			vy = 0;
		if(xPos == dx)
			vx = 0;

		if(dx == xPos && dy == yPos) {
			if(listener != null )
				listener.arrived();
			dx = 0;
			dy = 0;
		}
	}
}
