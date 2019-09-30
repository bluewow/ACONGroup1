import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Time;

public class HoneyBeeCanvas extends Canvas implements KeyListener{
	
//	private BackGround bg;
//	private Time t;
//	private Score s;
	private Bee bee;
//	private Bottle bottle;
//	private Bar bar;
//	private Honey honey;
//	private ButterFly bf;
//	private Flower fw;
	
	
	
	
	public HoneyBeeCanvas() {
//		bg = new BackGround();
//		t = new Time();
//		s = new Score();
		bee = new Bee();
//		bottle = new Bottle();
//		bar = new Bar();
//		honey = new Honey();
//		bf = new ButterFly();
//		fw = new Flower();
		
		addKeyListener(this);
		
		new Thread(() ->  {
			while (true) {
				try {
//					bg.update();
					
					Thread.sleep(17);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
				repaint();
			}
		}).start();
	}

	@Override
	public void paint(Graphics g) {
		Image bufImage = createImage(this.getWidth(), this.getHeight());
		Graphics g2 = bufImage.getGraphics();
//		bg.draw(g2, this);

		g.drawImage(bufImage, 0, 0, this);
	}
	
	@Override
	public void update(Graphics g) {
		paint(g);
	}


	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
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
