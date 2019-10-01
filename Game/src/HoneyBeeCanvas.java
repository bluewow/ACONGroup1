import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class HoneyBeeCanvas extends Canvas {
	
	private BackGround bg;
	private GameTimer t;
	private Score s;
	private Bee bee;
	private TimeBee tbee;
	private Bottle bottle;

	private Honey[][] honey;
	private Bar[] bar;
	private Butterfly bf;
	private Flower fw;
	private int xPos;
	private int yPos;
	private int posCnt;
	
	public HoneyBeeCanvas() {
		bg = new BackGround();
		bottle = new Bottle();
		fw = new Flower();
		t = new GameTimer();
		s = new Score();
		tbee = new TimeBee();
		honey = new Honey[10][10];

		bar = new Bar[2];
		bar[0] = new Bar(150, 700, true, true);
		bar[1] = new Bar(70, 330, false, false);
		bee = new Bee();
		bf = new Butterfly();
		posCnt=0;
		
		bee.move(340, 0);
		
		bee.addBeeListener(new BeeListener() {
			
			@Override
			public void arrived() {
				Point p = new Point();
				p.x = 9;
				System.out.println("TEST");
			}
		});
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_SPACE:
					
					if(posCnt==0) { 
					bar[0].setActivation(false);
					xPos = bar[0].getPos();
					bar[1].setActivation(true);
					posCnt++;
					} else {
					bar[1].setActivation(false);
					yPos = bar[1].getPos();
//					bar[0].setActivation(true);
					posCnt--;
					
					}
					
					
				}
			}
		});
	}
	
	
	@Override
	public void update(Graphics g) {
		paint(g);
	}

	@Override
	public void paint(Graphics g) {
		Image bufImage = createImage(this.getWidth(), this.getHeight());
		Graphics g2 = bufImage.getGraphics();
		bg.draw(g2, this);
		
		bottle.draw(g2, this);
		
		fw.draw(g2, this);
		
		for(Bar b : bar)
			b.draw(g2,this);
		
		bee.draw(g2, this);
		
		bf.draw(g2, this);
		
		t.draw(g2,this);
		
		s.draw(g2, this);
		
		tbee.draw(g2, this);
		
		g.drawImage(bufImage, 0, 0, this);
	}
	
	public void start() {
		new Thread(() ->  {
			while (true) {
				try {
					tbee.update();
					bee.update();
					end();
					
					for(Bar b : bar)
                		b.update();
					
					Thread.sleep(17);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
				repaint();
			}
		}).start();
		
	}
	
	public void end() {
		GameFrame.getInstance().endChange();
	}
}
