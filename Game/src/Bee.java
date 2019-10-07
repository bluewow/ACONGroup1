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
 *  
 * 꿀 수집
 * L1 - catchHoney()
 *
 * 벌/꿀 그리기
 * L1 - draw(Graphics, HoneyBeeCanvas)
 * 
 * 벌/꿀 이동좌표 갱신
 * L1 - update();
 *   L2 - updateBeeImageIndex()
 *   L2 - checkCollectStatus()
 *   L2 - changeSpeed()
 *   L2 - checkDynamicMoveStatus()
 *   L2 - arriveAtLocation
 *     L3 - arriveBottle 
 *     L3 - arriveFlower 
 *       
 * Canvas 에게 벌이 도착했을때 callback 호출
 *	L1 - arrivedInFlower();
 *	L1 - arrivedInBottle();
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
	private ArriveListener listener;
	private Image img;
	private int[] xArrayLeg = { 56, 65, 98, 111, 142, 154 };
	private int[] yArrayLeg = { 128, 129, 132, 130, 121, 115 };
	
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
		leg = new Leg(6, xArrayLeg, yArrayLeg);
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/bee(176X136).png");
	}

	//callback to HoneyBeeCanvas
	public void addBeeListener(ArriveListener listener) {
		this.listener = listener;
	}
	
	//꽃으로부터 다리좌표의 honey 유/무를 돌려받는다
	public void catchHoney(Point[] honey) {
		captureDelay = 100; //약 1.5초
		
		leg.catchHoney(honey);
		leg.updateHoneyImage((int)xPos- MARGIN_W, (int)yPos - MARGIN_W);
	}
	
	//이동 
	//normal move -> bottle 또는 꽃으로 이동시
	//random move -> 꽃으로 이동시 중간에 random move 활성화된다.
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
	
	//속도 계산
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

	//벌과 다리의 꿀을 함께 그린다
	public void draw(Graphics g2, HoneyBeeCanvas honeyBeeCanvas) {
		g2.drawImage(img, (int)xPos - MARGIN_W,     (int)yPos - MARGIN_H, 
					      (int)xPos + w - MARGIN_W, (int)yPos + h - MARGIN_H,
					      imageIndex * w, 0, 
					      imageIndex * w + w, h, honeyBeeCanvas);
		
		leg.draw(g2, honeyBeeCanvas);
	}

	//1.벌의 상태 및 이미지 체크
	//2.이동 스피드를 업데이트
	//3.이동상태 체크(Random, Normal)
	//4.도착 처리(Flower, Bottle)
	public void update() {
		updateBeeImageIndex();
		if(checkCollectStatus())
			return;
		
		updateSpeed();
		if(checkDynamicMoveStatus() == RANDOM_MOVING)
			return;
		
		arriveAtLocation();
	}
	
	//1.벌의 상태 및 이미지 체크
	private void updateBeeImageIndex() {
		if(vx == 0 && vy == 0)
			return;
		
		leg.updateHoneyImage((int)xPos- MARGIN_W, (int)yPos - MARGIN_W);
		if (imageDelay++ % 30 == 0) {
			imageDelay = 0;
			if(dx == offsetX && dy == offsetY)
				imageIndex = (imageIndex == 2) ? 3 : 2;
			else
				imageIndex = (imageIndex == 1) ? 0 : 1;
		}
	}

	//1.벌의 상태 및 이미지 체크
	private boolean checkCollectStatus() {
		if(--captureDelay > 0) {
			if(captureDelay % 20 == 0)
				BgMusic.Sound("res/BeePut.wav", "Play");
			
			return true;
		}
		captureDelay = 0;
		return false;
	}

	//2.이동 스피드를 업데이트
	private void updateSpeed() {
		xPos += vx;
		yPos += vy;
	}
	
	//범위 체크
	private boolean checkBoxScope(double x, double y) {
		if((y - 3 < yPos) && (yPos < y + 3) &&   	
		   (x - 3 < xPos) && (xPos < x + 3)) 
			return true;
		else
			return false;
	}
	
	//3.이동상태 체크(Random, Normal)
	private int checkDynamicMoveStatus() {
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

	//4.도착 처리(Flower, Bottle)
	private void arriveAtLocation() {
		if(checkBoxScope(offsetX, offsetY)) {
			arriveBottle();
			return;
		}
		
		if(checkBoxScope(dx, dy)) 
		    arriveFlower();
	}

	//Bottle 도착시 다리의 정보와 꿀의 상태를 갱신한다
	private boolean arriveBottle() {
		vx = 0.0;
	    vy = 0.0;
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

	//Flower 도착시 다리 상태를 갱신하기 위해 callback 호출
	private void arriveFlower() {
		vx = 0.0;
	    vy = 0.0;
		leg.setLegPosition((int)xPos- MARGIN_W, (int)yPos - MARGIN_W);

		if (listener != null) 
			listener.arrivedInFlower(leg.getLeg());
	}
}
