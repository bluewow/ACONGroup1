import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * 
 * Bee 요구사항
 * - 이동
 * - 꿀 채취
 * - 꿀병에 꿀 전달
 * 
 * 벌 이동
 * L1 - move(int, int)
 *   L2 - normalMove
 *   L2 - dynamicMove
 *  
 * 꿀 수집
 * L1 - catchHoney(Point[])
 *
 * 벌/꿀 그리기
 * L1 - draw(Graphics, HoneyBeeCanvas)
 * 
 * 벌/꿀 이동좌표 갱신
 * L1 - update();
 *   L2 - updateBeeImageIndex();
 *   L2 - updateDynamicMoveStatus ? yes or no
 *   L2 - updateHoneyPosition()
 *     L3 - checkBoxRange()
 *   L2 - arriveAtLocation
 *     L3 - refreshBeeInfo ? yes or no
 *     L3 - callHoneyBeeCanvas no
 *       L4 - setHoneyPosition(Point[])
 * 
 * bottle 에게 꿀 전달 및 honey/leg null 처리
 * L3 - refreshBeeInfo
 * 
 * Canvas 에게 벌이 도착했을때 callback 호출
 * L1 - addBeeListener(BeeListener) 
 * 
 * TODO
 * dynamic move
 * 벌 수집시 약간의 delay  
 * 		 
 */
public class Bee {
	private int offsetX;
	private int offsetY;
	private double xPos;
	private double yPos;
	private double dx;
	private double dy;
	private double rdx;
	private double rdy;
	private double vx;
	private double vy;
	private int w;
	private int h;
	private int imageIndex;
	private int imageDelay;
	private int dynamicMoveCnt;
	private static final int MARGIN_W = 78;
	private static final int MARGIN_H = 76;
	private static final int HONEY_MARGIN_WH = 7;
	private static final int NORMAL_MOVING = 1;
	private static final int RANDOM_MOVING = 0;
	
	Random random = new Random();
	
	Point[] leg;
	Honey[] honeies;

	private Image img;
	private BeeListener listener;

	public interface BeeListener {
		void arrived(Point[] honey);
		void deliveryHoney(int honey);
	}

	public void addBeeListener(BeeListener listener) {
		this.listener = listener;
	}

	public Bee(int x, int y) {
		offsetX = x;
		offsetY = y;
		xPos = x;
		yPos = y;
		w = 176;
		h = 136;
		imageIndex = 0;
		imageDelay = 0;
		dynamicMoveCnt = 0;
		
		leg = new Point[6];
		honeies = new Honey[6];
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/bee(176X136).png");

	}

	public void move(int x, int y) {
		dx = x;
		dy = y;
		
		if(dx == xPos && dy == yPos)
			return;

		//check bottle position
		if(offsetX == dx && offsetY == dy)
			normalMove();
		else {
			dynamicMove();			
			dynamicMoveCnt = random.nextInt(3) + 1;
		}
	}
	
	private void normalMove() {
		double w = dx - xPos;
		double h = dy - yPos; 
		double d = (double) Math.sqrt(w * w + h * h);
		
		vx = (w / d) * 3;
		vy = (h / d) * 3;
	}
	
	private void dynamicMove() {
		rdx = random.nextInt(250) + 250 + random.nextInt(100);
	    rdy = random.nextInt(250) + 250 + random.nextInt(100);
	      
		double w = rdx - xPos;
		double h = rdy - yPos;
		double d = (double) Math.sqrt(w * w + h * h);

		vx = (w / d) * 5;
		vy = (h / d) * 5;
	}

	
	
	public void catchHoney(Point[] honey) {
		for (int i = 0; i < honey.length; i++) {
			if (honey[i].honey) {
				honeies[i] = new Honey(honey[i].x - HONEY_MARGIN_WH, honey[i].y - HONEY_MARGIN_WH);
			}
		}
	}

	public void draw(Graphics g2, HoneyBeeCanvas honeyBeeCanvas) {
		int sx = imageIndex * w;
		int xPos = (int)this.xPos;
		int yPos = (int)this.yPos;
		int w = (int)this.w;
		int h = (int)this.h;

		g2.drawImage(img, xPos - MARGIN_W, yPos - MARGIN_H, 
					      xPos + w - MARGIN_W, yPos + h - MARGIN_H,
					      sx, 0, sx + w, h, honeyBeeCanvas);

		for (int i = 0; i < honeies.length; i++) {
			if (honeies[i] != null) {
				honeies[i].draw(g2, honeyBeeCanvas);
			}
		}
	
//		Test Range
//		testObjectRange(g2);

	}

	public void update() {
		xPos += vx;
		yPos += vy;

		updateBeeImageIndex();
		if(updateDynamicMoveStatus() == RANDOM_MOVING)
			return;
		updateHoneyPosition();
		arriveAtLocation();
		
	}

	private boolean checkBoxRange() {
		if(((rdy - 3 < yPos) && (yPos < rdy + 3)) || 	
			((rdx - 3 < xPos) && (xPos < rdx + 3))) 
			return true;
		else
			return false;
	}
	
	private int updateDynamicMoveStatus() {
		if(dynamicMoveCnt == 0)
			return NORMAL_MOVING;
		
		if(checkBoxRange() && dynamicMoveCnt != 0) {
			dynamicMoveCnt--;
			if(dynamicMoveCnt == 0) {
				normalMove();
				return NORMAL_MOVING;
			} 
			else {
				dynamicMove();
				return RANDOM_MOVING;
			}
		}

		return RANDOM_MOVING;
	}

	private void arriveAtLocation() {
		if (((dy - 3 < yPos) && (yPos < dy + 3)) || 
			((dx - 3 < xPos) && (xPos < dx + 3))) {
			
			vx = 0.0;
		    vy = 0.0;
		    xPos = (int)dx;
		    yPos = (int)dy;
		    
		    if(refreshBeeInfo())
		    	return;
		    
		    callHoneyBeeCanvas();
		}		
	}

	private boolean refreshBeeInfo() {
		if(xPos == offsetX && yPos == offsetY) {
			int honeyNum = 0;
			for (int i = 0; i < leg.length; i++) {
				if(leg[i] != null && leg[i].honey == true)
					honeyNum++;
				if(leg[i] == null)
					honeyNum = -1;
				
				leg[i] = null;
			}
			
			for(int i = 0; i < honeies.length; i++) 
				honeies[i] = null;

			if(listener != null && honeyNum >= 0) {
				listener.deliveryHoney(honeyNum);
			}

			return true;
		}
		return false;
	}

	private void callHoneyBeeCanvas() {
		if (listener != null) {
			for (int i = 0; i < leg.length; i++) 
				leg[i] = new Point();

			setHoneyPosition(leg);
			listener.arrived(leg);
		}			 
	}

	private void updateBeeImageIndex() {
		if(vx == 0 && vy == 0)
			return;
		
		if (imageDelay++ % 30 == 0) {
			imageDelay = 0;
			imageIndex = (imageIndex == 1) ? 0 : 1;
		}
	}

	private void updateHoneyPosition() {
		if (honeies[0] != null) {
			honeies[0].setX((int)xPos - MARGIN_W + 16);
			honeies[0].setY((int)yPos - MARGIN_H + 118);
		}

		if (honeies[1] != null) {
			honeies[1].setX((int)xPos - MARGIN_W + 27);
			honeies[1].setY((int)yPos - MARGIN_H + 121);
		}

		if (honeies[2] != null) {
			honeies[2].setX((int)xPos - MARGIN_W + 59);
			honeies[2].setY((int)yPos - MARGIN_H + 133);
		}

		if (honeies[3] != null) {
			honeies[3].setX((int)xPos - MARGIN_W + 71);
			honeies[3].setY((int)yPos - MARGIN_H + 131);
		}

		if (honeies[4] != null) {
			honeies[4].setX((int)xPos - MARGIN_W + 107);
			honeies[4].setY((int)yPos - MARGIN_H + 130);
		}

		if (honeies[5] != null) {
			honeies[5].setX((int)xPos - MARGIN_W + 114);
			honeies[5].setY((int)yPos - MARGIN_H + 129);
		}

	}

	private void setHoneyPosition(Point[] honey) {
		honey[0].x = (int)xPos - MARGIN_W + 16;
		honey[0].y = (int)yPos - MARGIN_H + 118;

		honey[1].x = (int)xPos - MARGIN_W + 27;
		honey[1].y = (int)yPos - MARGIN_H + 121;

		honey[2].x = (int)xPos - MARGIN_W + 59;
		honey[2].y = (int)yPos - MARGIN_H + 133;

		honey[3].x = (int)xPos - MARGIN_W + 71;
		honey[3].y = (int)yPos - MARGIN_H + 131;

		honey[4].x = (int)xPos - MARGIN_W + 107;
		honey[4].y = (int)yPos - MARGIN_H + 130;

		honey[5].x = (int)xPos - MARGIN_W + 114;
		honey[5].y = (int)yPos - MARGIN_H + 129;

	}
	
	private void testObjectRange(Graphics g2) {
		int xPos = (int)this.xPos;
		int yPos = (int)this.yPos;
		int w = (int)this.w;
		int h = (int)this.h;
		
		g2.drawRect(250, 250, 350, 350);
		g2.drawRect(xPos, yPos, 3, 3);
		g2.drawRect(xPos - MARGIN_W, yPos - MARGIN_H, 176, 136);
		g2.drawRect(xPos - MARGIN_W + 16, yPos - MARGIN_H + 118, 3, 3);
		g2.drawRect(xPos - MARGIN_W + 27, yPos - MARGIN_H + 121, 3, 3);
		g2.drawRect(xPos - MARGIN_W + 59, yPos - MARGIN_H + 133, 3, 3);
		g2.drawRect(xPos - MARGIN_W + 71, yPos - MARGIN_H + 131, 3, 3);
		g2.drawRect(xPos - MARGIN_W + 107, yPos - MARGIN_H + 130, 3, 3);
		g2.drawRect(xPos - MARGIN_W + 114, yPos - MARGIN_H + 129, 3, 3);
	}
}
