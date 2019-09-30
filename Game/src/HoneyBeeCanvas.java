import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Time;
import java.util.Timer;

public class HoneyBeeCanvas extends Canvas implements KeyListener{
	
	private BackGround bg;
	private GameTimer t;
	private Score s;
	private Bee bee;
	private TimeBee tbee;
	private Bottle bottle;
//	private Bar bar;
	private Honey honey;
	private Butterfly bf;
	private Flower fw;
	
	public HoneyBeeCanvas() {
		bg = new BackGround();
		bottle = new Bottle();
		fw = new Flower();
		t = new GameTimer();
		s = new Score();
		tbee = new TimeBee();
//		bar = new Bar();
		honey = new Honey();
		bee = new Bee();
		bf = new Butterfly();
		
		addKeyListener(this);
		
		new Thread(() ->  {
			while (true) {
				try {
					tbee.update();

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
		bg.draw(g2, this);
		bottle.draw(g2, this);
		fw.draw(g2, this);
		honey.draw(g2, this);
		bee.draw(g2, this);
		bf.draw(g2, this);
		t.draw(g2,this);
		s.draw(g2, this);
		tbee.draw(g2, this);
		g.drawImage(bufImage, 0, 0, this);
	}
	
	@Override
	public void update(Graphics g) {
		paint(g);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_SPACE:
			//여기에 스페이스 누르면 명령 입력
		}	
	}

	@Override
	public void keyReleased(KeyEvent e) { }

	@Override
	public void keyTyped(KeyEvent e) { }
}
