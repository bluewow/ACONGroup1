import java.awt.Canvas;
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
	private int w;
	private int h;
	
	private Image img;
	
	
	public IntroCanvas() {
		x=200;
		y=300;
		Toolkit tk = Toolkit.getDefaultToolkit();
		//img = tk.getImage("res/IntroBeforeBtnTemplet(255X85).png");
	}
	
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		g.drawImage(img, 200, 300,255,85,this);
=======
//		g.drawImage(img, 200, 300,this);
>>>>>>> d4bc7f74bd0cc4ec501ca9825cc511820e10b48e
		addKeyListener(this);
		addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
