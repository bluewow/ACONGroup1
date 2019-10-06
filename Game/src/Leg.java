import java.awt.Graphics;
import java.awt.Image;

public class Leg {
	private Point[] leg;
	private Honey[] honeies;
	private int[] xArrayLeg = { 56, 65, 98, 111, 142, 154 };
	private int[] yArrayLeg = { 128, 129, 130, 131, 119, 115 };
	private Image img;
	private static final int HONEY_MARGIN_WH = 7;
	
	public Leg() {
		leg = new Point[6];
		honeies = new Honey[6];
	}

	public void draw(Graphics g2, HoneyBeeCanvas honeyBeeCanvas) {
		for (int i = 0; i < honeies.length; i++) {
			if (honeies[i] != null) 
				honeies[i].draw(g2, honeyBeeCanvas);
		}
	}
		
	public void catchHoney(Point[] honey) {
		for (int i = 0; i < honey.length; i++) {
			if (honey[i].honey) {
				honeies[i] = new Honey(honey[i].x - HONEY_MARGIN_WH, honey[i].y - HONEY_MARGIN_WH);
			}
		}
	}
	
	public void initLeg(int xPos, int yPos) {
		for (int i = 0; i < leg.length; i++) 
			leg[i] = new Point();
		
		for(int i = 0; i < leg.length; i++) {
			leg[i].x = xPos + xArrayLeg[i];
			leg[i].y = yPos + yArrayLeg[i];
		}
	}
	
	public void updateHoneyPosition(int xPos, int yPos) {
		for(int i = 0; i < honeies.length; i++) {
			if (honeies[i] != null) {
				honeies[i].setX(xPos + xArrayLeg[i]);
				honeies[i].setY(yPos + yArrayLeg[i]);
			}
		}
	}
	
	public int refreshLegInfo() {
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
		
		return honeyNum;
	}
	
	public Point[] getLeg() {
		return leg;
	}

}

