import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
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
    private boolean running;
    private int xPos;
    private int yPos;
    private int posCnt;
    private int honeyScore;
    private int bpx;
    private int bpy;
	
    public HoneyBeeCanvas() {
		bg = new BackGround();
		bottle = new Bottle();
		bpx = bottle.beePosX(bpx);
		bpy = bottle.beePosY(bpy);
		fw = new Flower();
		t = new GameTimer();
		s = new Score();
		tbee = new TimeBee();
		honey = new Honey[10][10];
		honeyScore = 0;
		posCnt = 0;
		bar = new Bar[2];

		bar[0] = new Bar(150, 700, true, true);
		bar[1] = new Bar(70, 330, false, false);
		bee = new Bee(bpx, bpy);
		bf = new Butterfly();

		running = false;
		
		bee.addBeeListener(new Bee.BeeListener() {
			
			@Override
			public void arrived(Point[] leg) {
			    leg = fw.putHoney(leg);
				bee.catchHoney(leg);
				bee.move(bpx, bpy);
			}

			@Override
			public void deliveryHoney(int honey) {
				System.out.println(honey);
			}
		}); 
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_SPACE:
					// 여기에 스페이스 누르면 명령 입력
					if(posCnt==0) {
						bar[0].setActivation(false);
						xPos = bar[0].getPos();
						bar[1].setActivation(true);
						posCnt++;
					} else {
						bar[1].setActivation(false);
						yPos = bar[1].getPos();
						bar[0].setActivation(true);
						posCnt--;
//						System.out.println(xPos);
//						System.out.println(yPos);
//						System.out.println("move");
						bee.move(xPos, yPos);
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

		for (Bar b : bar)
			b.draw(g2, this);

		bee.draw(g2, this);

		// 2번 스페이스바를 누를 때마다 나타난다. 시작 시 나타나지않는다.
		if(posCnt == 0 && xPos != 0) {
			g2.setColor(Color.RED);
			g2.fillOval(xPos - 10 / 2, yPos - 10 / 2, 10, 10);
		}
		
		bf.draw(g2, this);

		t.draw(g2, this);

		s.draw(g2, this);

		tbee.draw(g2, this);

		g.drawImage(bufImage, 0, 0, this);
	}

	public void start() {
		running = true;
		
		new Thread(() -> {
			while (running) {
				try {
					bg.update();
					fw.flowerUpdate();
					bf.update();

					if (tbee.getX() == 50)
						end();	
					for (Bar b : bar)
						b.update();
					tbee.update();
					bee.update();

					bottle.update(bee);
					s.update(bottle);

					Thread.sleep(16);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				repaint();
			}
		}).start();
	}
	
	public void stop() {
		running = false;
	}

	public void end() {
		GameFrame.getInstance().endChange();
	}

}
