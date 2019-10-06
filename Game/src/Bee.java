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
 * L1 - move()
 *   L2 - normalMove()   
 *   L2 - dynamicMove()  
 *     L3 - calculateVector()
 *  
 * 꿀 수집
 * L1 - catchHoney(Point[])
 *
 * 벌/꿀 그리기
 * L1 - draw(Graphics, HoneyBeeCanvas)
 *   leg.draw
 * 
 * 벌/꿀 이동좌표 갱신
 * L1 - update();
 *   L2 - updateBeeImageIndex()
 *   L2 - collectStatus()
 *   L2 - updateDynamicMoveStatus()
 *   L2 - arriveAtLocation
 *     L3 - arriveBottle 
 *     L3 - arriveFlower 
 *       
 * 
 * Canvas 에게 벌이 도착했을때 callback 호출
 * L1 - addBeeListener(BeeListener) 
 * 
 * TODO
 * 다리 unit 교체 고민중
 * 		 
 */
public class Bee {
	private int offsetX, offsetY;
	private double xPos, yPos;
	private double dx, dy;
	private double rdx, rdy;
	private double vx, vy;
	private int w, h;
	private int imageIndex;
	private int imageDelay;
	private int captureDelay;
	private int dynamicMoveCnt;
	private static final int MARGIN_W = 78;
	private static final int MARGIN_H = 76;
	private static final int NORMAL_MOVING = 1;
	private static final int RANDOM_MOVING = 0;
	private Leg leg;
	private Random random = new Random();
	private BeeListener listener;
	private Image img;

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
		captureDelay = 0; 
		leg = new Leg();
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/bee(176X136).png");
	}

	public interface BeeListener {
		void arrivedInFlower(Point[] honey);
		void arrivedInBottle(int honey);
	}
	public void addBeeListener(BeeListener listener) {
		this.listener = listener;
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
	
	private void calculateVecotr(double x, double y, int speed) {
		double w = x - xPos;
		double h = y - yPos; 
		double d = (double) Math.sqrt(w * w + h * h);
		
		vx = (w / d) * speed;
		vy = (h / d) * speed;
	}
	
	private void normalMove() {
		calculateVecotr(dx, dy, 3);
	}
	
	private void dynamicMove() {
		rdx = random.nextInt(250) + 250 + random.nextInt(100);
	    rdy = random.nextInt(250) + 250 + random.nextInt(100);
	    
	    calculateVecotr(rdx, rdy, 5);
	}

	public void draw(Graphics g2, HoneyBeeCanvas honeyBeeCanvas) {
		g2.drawImage(img, (int)xPos - MARGIN_W,     (int)yPos - MARGIN_H, 
					      (int)xPos + w - MARGIN_W, (int)yPos + h - MARGIN_H,
					      imageIndex * w, 0, 
					      imageIndex * w + w, h, honeyBeeCanvas);
		
		leg.draw(g2, honeyBeeCanvas);
	}

	public void update() {
		updateBeeImageIndex();
		if(collectStatus())
			return;
		
		xPos += vx;
		yPos += vy;
		
		if(updateDynamicMoveStatus() == RANDOM_MOVING)
			return;
		
		leg.updateHoneyPosition((int)xPos- MARGIN_W, (int)yPos - MARGIN_W);
		arriveAtLocation();
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

	private boolean collectStatus() {
		if(--captureDelay > 0) {
			if(captureDelay % 20 == 0)
				BgMusic.Sound("res/BeePut.wav", "Play");
			
			return true;
		}
		captureDelay = 0;
		return false;
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
		else if(checkBoxScope(rdx, rdy)) {
			dynamicMoveCnt--;
			if(dynamicMoveCnt > 0) 
				dynamicMove();
			else 
				normalMove();
		}
		return RANDOM_MOVING;
	}

	private void arriveAtLocation() {
		if(checkBoxScope(dx, dy)) {
			vx = 0.0;
		    vy = 0.0;
		    
		    if(arriveBottle())
		    	return;
		    else
		    	arriveFlower();
		}		
	}

	private boolean arriveBottle() {
		if(checkBoxScope(offsetX, offsetY)) {
			imageIndex = 0;
			
			int honeyNum = 0;
			honeyNum = leg.refreshLegInfo();

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

	private void arriveFlower() {
		leg.initLeg((int)xPos- MARGIN_W, (int)yPos - MARGIN_W);

		if (listener != null) 
			listener.arrivedInFlower(leg.getLeg());
	}
		
	public void catchHoney(Point[] honey) {
		captureDelay = 100; //약 1.5초
		
		leg.catchHoney(honey);
		leg.updateHoneyPosition((int)xPos- MARGIN_W, (int)yPos - MARGIN_W);
	}
}
