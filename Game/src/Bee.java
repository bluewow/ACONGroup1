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
 * 벌 수집시 약간의 delay
 * 벌 효과음
 *  - 병에 넣기 clear
 *  - 병에 넣을때 실패음
 *  - 꽃에서 꿀따기
 * 벌과 다리 분리
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
	private int[] xArrayLeg = {56, 65, 100, 111, 142, 154 };
	private int[] yArrayLeg = {128, 129, 129, 131, 119, 115 };
	
	Random random = new Random();
	private Point[] leg;
	private Honey[] honeies;

	private Image img;
	private BeeListener listener;

	public interface BeeListener {
		void arrivedInFlower(Point[] honey);
		void arrivedInBottle(int honey);
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

	private boolean checkBoxScope(double x, double y) {
		if((y - 3 < yPos) && (yPos < y + 3) &&   	
		   (x - 3 < xPos) && (xPos < x + 3)) 
			return true;
		else
			return false;
	}
	
	private int updateDynamicMoveStatus() {
		if(dynamicMoveCnt == 0)
			return NORMAL_MOVING;
		
		if(checkBoxScope(rdx, rdy) && dynamicMoveCnt != 0) {
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
		if(checkBoxScope(dx, dy)) {
			vx = 0.0;
		    vy = 0.0;
		    
		    if(refreshBeeInfo())
		    	return;
		    
		    callHoneyBeeCanvas();
		}		
	}

	private boolean refreshBeeInfo() {
		if(checkBoxScope(offsetX, offsetY)) {
			imageIndex = 0;
			
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
				listener.arrivedInBottle(honeyNum);
				if(honeyNum > 0)
					BgMusic.Sound("res/BeePut.wav", "Play");
				else 
					BgMusic.Sound("res/Empty.wav", "Play");
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
			listener.arrivedInFlower(leg);
		}			 
	}

	private void updateBeeImageIndex() {
		if(vx == 0 && vy == 0)
			return;
		
		if (imageDelay++ % 30 == 0) {
			imageDelay = 0;
			if(dx == offsetX && dy == offsetY)
				imageIndex = (imageIndex == 2) ? 3 : 2;
			else
				imageIndex = (imageIndex == 1) ? 0 : 1;
		}
	}

	private void updateHoneyPosition() {
		for(int i = 0; i < honeies.length; i++) {
			if (honeies[i] != null) {
				honeies[i].setX((int)xPos - MARGIN_W + xArrayLeg[i]);
				honeies[i].setY((int)yPos - MARGIN_H + yArrayLeg[i]);
			}
		}
	}

	private void setHoneyPosition(Point[] honey) {
		for(int i = 0; i < honey.length; i++) {
			honey[i].x = (int)xPos - MARGIN_W + xArrayLeg[i];
			honey[i].y = (int)yPos - MARGIN_H + yArrayLeg[i];
		}
	}
	
	private void testObjectRange(Graphics g2) {
		int xPos = (int)this.xPos;
		int yPos = (int)this.yPos;
		int w = (int)this.w;
		int h = (int)this.h;
		
		g2.drawRect(250, 250, 350, 350);
		g2.drawRect(xPos, yPos, 3, 3);
		g2.drawRect(xPos - MARGIN_W, yPos - MARGIN_H, 176, 136);
//		g2.drawRect(xPos - MARGIN_W + 16, yPos - MARGIN_H + 118, 3, 3);
//		g2.drawRect(xPos - MARGIN_W + 27, yPos - MARGIN_H + 121, 3, 3);
//		g2.drawRect(xPos - MARGIN_W + 59, yPos - MARGIN_H + 133, 3, 3);
//		g2.drawRect(xPos - MARGIN_W + 71, yPos - MARGIN_H + 131, 3, 3);
//		g2.drawRect(xPos - MARGIN_W + 107, yPos - MARGIN_H + 130, 3, 3);
//		g2.drawRect(xPos - MARGIN_W + 114, yPos - MARGIN_H + 129, 3, 3);
		g2.drawRect(xPos - MARGIN_W + 59, yPos - MARGIN_H + 129, 3, 3);
		g2.drawRect(xPos - MARGIN_W + 67, yPos - MARGIN_H + 130, 3, 3);
		g2.drawRect(xPos - MARGIN_W + 102, yPos - MARGIN_H + 130, 3, 3);
		g2.drawRect(xPos - MARGIN_W + 113, yPos - MARGIN_H + 132, 3, 3);
		g2.drawRect(xPos - MARGIN_W + 146, yPos - MARGIN_H + 122, 3, 3);
		g2.drawRect(xPos - MARGIN_W + 157, yPos - MARGIN_H + 118, 3, 3);
	}
}
