import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HoneyBeeCanvas extends Canvas {
	private BackGround bg;
	private GameTimer timer;
	private Score score;
	private Bee bee;
	private TimeBee timeBee;
	private Bottle bottle;
	private Honey[][] honey;
	private Butterfly butterfly;
	private Flower flower;
	private Pause pause;
	public static Bar[] bar;
	public static int spaceHitCnt;
	private int xBarBee;
	private int yBarBee;
	private int honeyScore;
	private int xBeeStartPos;
	private int yBeeStartPos;
	private boolean running;

	public HoneyBeeCanvas() {
		bg = new BackGround();
		bottle = new Bottle();
		flower = new Flower();
		timer = new GameTimer();
		score = new Score();
		timeBee = new TimeBee();
		honey = new Honey[10][10];
		bar = new Bar[2];
		bar[0] = new Bar(150, 700, true, true);
		bar[1] = new Bar(70, 330, false, false);
		pause = new Pause();
		butterfly = new Butterfly();
		xBeeStartPos = bottle.beePosX(xBeeStartPos);
		yBeeStartPos = bottle.beePosY(yBeeStartPos);
		bee = new Bee(xBeeStartPos, yBeeStartPos);
		honeyScore = 0;
		spaceHitCnt = 0;

		running = false;

		bee.addBeeListener(new Bee.BeeListener() {

			@Override
			public void arrivedInFlower(Point[] leg) {
				leg = flower.putHoney(leg);
				bee.catchHoney(leg);
				bee.move(xBeeStartPos, yBeeStartPos);
			}

			@Override
			public void arrivedInBottle(int honeyNum) {
				System.out.println("toBottle : " + honeyNum);
				bottle.getHoney(honeyNum);
			}
		});
		// 스페이스키가 눌렸을 때 이벤트 발생
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (!pause.getPauseMode()) {
					switch (e.getKeyCode()) {
					case KeyEvent.VK_SPACE:
						if (spaceHitCnt == 0) {
							bar[0].setActivation(false);
							bar[1].setActivation(true);
							xBarBee = bar[0].getPos();
							spaceHitCnt++;
						} else if (spaceHitCnt == 1) {
							bar[1].setActivation(false);
							yBarBee = bar[1].getPos();
							spaceHitCnt++;
							bee.move(xBarBee, yBarBee);
							BgMusic.Sound("res/BeeFly.wav", "Play");
						} else
							spaceHitCnt++;
					}
				}
			}
		});

		// 마우스가 눌렀을 때 이벤트 발생
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				// 일시정지 버튼이 눌렸는지 체크
				pause.btnPauseClicked(e.getX(), e.getY());

				// 일시정지 모드일 때, 어떤 버튼을 눌렀는지
				if (pause.getPauseMode())
					pause.clickButton(e.getX(), e.getY());
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
		flower.draw(g2, this);
		timer.draw(g2, this);
		score.draw(g2, this);
		timeBee.draw(g2, this);
		butterfly.draw(g2, this);
		bee.draw(g2, this);
		for (Bar b : bar) b.draw(g2, this);

		// 벌의 목적지를 빨간 점으로 표시
		// 2번 스페이스바를 누를 때마다 나타난다. 시작 시 나타나지않는다.
		if (spaceHitCnt == 0 && xBarBee != 0) {
			g2.setColor(Color.RED);
			g2.fillOval(xBarBee - 10 / 2, yBarBee - 10 / 2, 10, 10);
		}
		
		pause.draw(g2, this);
		g.drawImage(bufImage, 0, 0, this);
	}

	public void start() {
		running = true;

		BgMusic.Sound("res/MainBgm.wav", "Loop");
		new Thread(() -> {
			while (running) {
				if (!pause.getPauseMode()) {
					try {
						bg.update();
						flower.flowerUpdate();
						butterfly.update();
						timeBee.update();
						bee.update();
						score.update(bottle);

						if (timeBee.getX() == 50) end();

						for (Bar b : bar) b.update();

						Thread.sleep(16);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if (pause.getReplay()) replay();

				repaint();
			}
		}).start();
	}

	public void stop() {
		running = false;
	}

	public void replay() {
		GameFrame.getInstance().introChange();
	}

	public void end() {
		GameFrame.getInstance().endChange();
	}

}
