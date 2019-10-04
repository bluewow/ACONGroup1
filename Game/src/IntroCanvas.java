import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class IntroCanvas extends Canvas implements MouseListener, MouseMotionListener {
	private int x;
	private int y;
	private int sx;
	private int sy;
	private int w;
	private int h;
	private int hGap;
	private int getX;
	private int getY;

	private Image imgBefore;
	private Image imgAfter;

	public IntroCanvas() {
		x = 250;
		y = 400;
		sx = 0;
		sy = 0;
		w = 255;
		h = 85;
		hGap = 100;
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		imgBefore = tk.getImage("res/IntroBeforeBtnTemplet(255X85).png");
		imgAfter = tk.getImage("res/IntroAfterBtnTemplet(255X85).png");

		addMouseMotionListener(this);
		addMouseListener(this);
	}

	@Override
	public void update(Graphics g) {
		paint(g);
	}
	
	@Override
	public void paint(Graphics g) {
		if (getX >= x && getX <= x + w && getY >= y && getY <= y + h)
			g.drawImage(imgAfter, x, y,x+w,y+h,sx,sy,w,h,this);
		else 
			g.drawImage(imgBefore, x, y, x + w, y + h, sx, sy, w, h, this);
		if (getX >= x && getX <= x + w && getY >= y + hGap && getY <= y + h + hGap)
			g.drawImage(imgAfter, x, y + hGap, x + w, y + h + hGap, sx + w, sy, sx + w * 2, h, this);
		else
			g.drawImage(imgBefore, x, y + hGap, x + w, y + h + hGap, sx + w, sy, sx + w * 2, h, this);
		if (getX >= x && getX <= x + w && getY >= y + hGap * 2 && getY <= y + h + hGap * 2)
			g.drawImage(imgAfter, x, y + hGap * 2, x + w, y + h + hGap * 2, sx + w * 2, sy, sx + w * 3, h, this);
		else
			g.drawImage(imgBefore, x, y + hGap * 2, x + w, y + h + hGap * 2, sx + w * 2, sy, sx + w * 3, h, this);
		
		repaint();
	}

	@Override
	// 메뉴 클릭할 때 메뉴마다 실행할 행동.
	public void mouseClicked(MouseEvent e) {
		if (e.getX() >= x && e.getX() <= x + w && e.getY() >= y && e.getY() <= y + h)
			// Audio 넣어야 함
			GameFrame.getInstance().change();
		else if (e.getX() >= x && e.getX() <= x + w && e.getY() >= y + 200 && e.getY() <= y + 200 + h)
			System.exit(0);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		getX = e.getX();
		getY = e.getY();
	}
}
