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
	private GameTimer t;
	private Score s;
	private Bee bee;
	private TimeBee tbee;
	private Bottle bottle;
	private Honey[][] honey;
	public static Bar[] bar;
	private Butterfly bf;
	private Flower fw;
	private Pause pause;
	private boolean running;
	private int xPos;
	private int yPos;
	public static int posCnt;
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
		bar = new Bar[2];
		bar[0] = new Bar(150, 700, true, true);
		bar[1] = new Bar(70, 330, false, false);
		pause = new Pause();

		honeyScore = 0;
		posCnt = 0;
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
			public void deliveryHoney(int honeyNum) {
				System.out.println("toBottle : " + honeyNum);
				bottle.getHoney(honeyNum);
			}
		});

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (!pause.getPauseMode()) {
					switch (e.getKeyCode()) {
					case KeyEvent.VK_SPACE:
						// 여기에 스페이스 누르면 명령 입력
						if (posCnt == 0) {
							bar[0].setActivation(false);
							bar[1].setActivation(true);
							xPos = bar[0].getPos();
							posCnt++;
						} else if(posCnt==1){
							bar[1].setActivation(false);
							yPos = bar[1].getPos();
							posCnt++;
							bee.move(xPos, yPos);
						} else posCnt++;
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

		fw.draw(g2, this);

		for (Bar b : bar)
			b.draw(g2, this);

		bee.draw(g2, this);

		// 벌의 목적지를 빨간 점으로 표시
		// 2번 스페이스바를 누를 때마다 나타난다. 시작 시 나타나지않는다.
		if (posCnt == 0 && xPos != 0) {
			g2.setColor(Color.RED);
			g2.fillOval(xPos - 10 / 2, yPos - 10 / 2, 10, 10);
		}

		bf.draw(g2, this);

		t.draw(g2, this);

		s.draw(g2, this);

		tbee.draw(g2, this);

		pause.draw(g2, this);

		g.drawImage(bufImage, 0, 0, this);
	}

	public void start() {
		running = true;

		BgMusic.Sound("res/MainBgm.wav", true);
		new Thread(() -> {
			while (running) {
				if (!pause.getPauseMode()) {
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
						s.update(bottle);

						Thread.sleep(16);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if(pause.getReplay())
					replay();
				
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
