import java.awt.Canvas;
import java.awt.Event;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class IntroCanvas extends Canvas implements KeyListener, MouseListener{
	private int x;
	private int y;
	private int sx;
	private int sy;
	private int w;
	private int h;
	
	private Image img;
	
	
	public IntroCanvas() {
		x=250;
		y=400;
		sx=0;
		sy=0;
		w=255;
		h=85;
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/IntroBeforeBtnTemplet(255X85).png");
		
		addKeyListener(this);
		addMouseListener(this);
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(img, x, y,x+w,y+h,sx,sy,w,h,this);
		g.drawImage(img, x, y+100,x+w,y+100+h,sx+w,sy,sx+w*2,h,this);
		g.drawImage(img, x, y+200,x+w,y+200+h,sx+w*2,sy,sx+w*3,h,this);	
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getX()>=x&&e.getX()<=x+w && e.getY()>=y&&e.getY()<=y+h)
			GameFrame.getInstance().change();
		else if (e.getX()>=x&&e.getX()<=x+w && e.getY()>=y+200&&e.getY()<=y+200+h)
			System.exit(0);
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean mouseMove(Event evt, int x, int y) {
		// TODO Auto-generated method stub
		return super.mouseMove(evt, x, y);
	}
	@Override
	public void mouseEntered(MouseEvent e) {
	
		
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
	public void keyPressed(KeyEvent e) {
		GameFrame.getInstance().change();
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	


}
