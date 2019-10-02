import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

/*
 * Bee 행동패턴
 * 
 * - 꽃으로 이동
 * - 꿀 채취
 * - 꿀통이동 
 * 
 */
public class Bee {
	private int xPos;
	private int yPos;
	private int dx;
	private int dy;
	private int vx;
	private int vy;
	private int w;
	private int h;
	private int imageIndex;
	private int imageDelay;
	Point[] leg;
	private static final int MARGIN_W = 78;
	private static final int MARGIN_H = 76;
	
	private Image img;
	private BeeListener listener;
	
	public interface BeeListener {
		void arrived(Point[] honey);
	}

	public void addBeeListener(BeeListener listener) {
		this.listener = listener;
	}
	
	public Bee() {
		xPos = 620;
		yPos = 320;
		w = 176;
		h = 136;
		imageIndex = 0;
		imageDelay = 0;
		
		leg = new Point[6];
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/bee(176X136).png");
	}

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
	
	public void sendToBottole(Point[] honey) {
		
	}
	
	
	public void draw(Graphics g2, HoneyBeeCanvas honeyBeeCanvas) {
		int sx = imageIndex * w;
		g2.drawImage(img, xPos - MARGIN_W, yPos - MARGIN_H, xPos + w - MARGIN_W, yPos+h - MARGIN_H, 
					sx, 0, sx + w, h, honeyBeeCanvas);
		
		//for check
		g2.drawRect(dx, dy, 3, 3);
		g2.drawRect(xPos, yPos, 3, 3);
//		g2.drawRect(xPos - MARGIN_W, yPos - MARGIN_H, 176, 136);
		g2.drawRect(xPos - MARGIN_W + 16, yPos - MARGIN_H + 118, 3, 3);
		g2.drawRect(xPos - MARGIN_W + 27, yPos - MARGIN_H + 121, 3, 3);
		g2.drawRect(xPos - MARGIN_W + 59, yPos - MARGIN_H + 133, 3, 3);
		g2.drawRect(xPos - MARGIN_W + 71, yPos - MARGIN_H + 131, 3, 3);
		g2.drawRect(xPos - MARGIN_W + 107, yPos - MARGIN_H + 130, 3, 3);
		g2.drawRect(xPos - MARGIN_W + 114, yPos - MARGIN_H + 129, 3, 3);
		
	}

	public void update() {
		xPos += vx;
		yPos += vy;
		
		if(imageDelay++ % 30 == 0) {
			imageDelay = 0;
			imageIndex = (imageIndex == 1)? 0:1;
		}
			
		if(yPos == dy)
			vy = 0;
		if(xPos == dx)
			vx = 0;

		if(dx == xPos && dy == yPos) {
			if(listener != null ) {
				for(int i = 0; i < leg.length; i++)
					leg[i] = new Point();
				
				passBeeLegPoint(leg);
				listener.arrived(leg);
			}
			
			dx = 0;
			dy = 0;
		}
	}

	private void passBeeLegPoint(Point[] honey) {
		honey[0].x = xPos - MARGIN_W + 16;
		honey[0].y = yPos - MARGIN_H + 118;
		
		honey[1].x = xPos - MARGIN_W + 27;
		honey[1].y = yPos - MARGIN_H + 121;
		
		honey[2].x = xPos - MARGIN_W + 59;
		honey[2].y = yPos - MARGIN_H + 133;
		
		honey[3].x = xPos - MARGIN_W + 71;
		honey[3].y = yPos - MARGIN_H + 131;
		
		honey[4].x = xPos - MARGIN_W + 107;
		honey[4].y = yPos - MARGIN_H + 130;
		
		honey[5].x = xPos - MARGIN_W + 114;
		honey[5].y = yPos - MARGIN_H + 129;
		
	}
}
