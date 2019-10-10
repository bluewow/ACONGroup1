import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HoneyBeeCanvas extends Canvas {
	private Victory victory;
	private GameOver gameOver;
	private Pause pause;
	private boolean running;
	private Score score;

	private int mouseX;
	private int mouseY;
	private int spaceHitCnt;
	
	private GameTimer timer;
	private TimeBee timeBee;
	private BackGround bg;
	private Bee bee;
	private int xBeeStartPos;
	private int yBeeStartPos;
	private Bottle bottle;
	private Butterfly butterfly;
	private Flower flower;
	private Bar barX;
	private Bar barY;

	public HoneyBeeCanvas() {
		bg = new BackGround();
		bottle = new Bottle();
		flower = new Flower();
		timer = new GameTimer();
		score = new Score();
		timeBee = new TimeBee();
		barX = new Bar(150, 700, true, true);
		barY = new Bar(70, 330, false, false);
		pause = new Pause();
		victory = new Victory();
		gameOver = new GameOver();
		butterfly = new Butterfly();
		xBeeStartPos = bottle.beePosX(xBeeStartPos);
		yBeeStartPos = bottle.beePosY(yBeeStartPos);
		bee = new Bee(xBeeStartPos, yBeeStartPos);
		spaceHitCnt = 0;

		running = false;

		bee.addBeeListener(new ArriveListener() {
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
				barX.setActivation(true);
				spaceHitCnt = 0;
				BgmBeeFly.Stop();
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
							barX.setActivation(false);
							barY.setActivation(true);
							spaceHitCnt++;
							BgMusic.Sound("res/BtBar.wav", "Play");
						} else if (spaceHitCnt == 1) {
							barY.setActivation(false);
							spaceHitCnt++;
							bee.move(barX.getPos(), barY.getPos());
							BgMusic.Sound("res/BtBar.wav", "Play");
							BgmBeeFly.Play();
						} else
							spaceHitCnt++;
					}
				}
				
				if(victory.getVictoryMode() && !victory.getViewButton())
					victory.inputName(e.getKeyCode(), e.getKeyChar());
			}
		});

		// 마우스가 눌렀을 때 이벤트 발생
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 일시정지 버튼이 눌렸는지 체크
				if(!gameOver.getgameOverMode() && !victory.getVictoryMode())
					pause.btnPauseClicked(e.getX(), e.getY());

				// 일시정지 모드일 때, 어떤 버튼을 눌렀는지
				if (pause.getPauseMode())
					pause.clickButton();
				
				// 게임 오버 모드일 때, 어떤 버튼이냐에 따른 행동
				if (gameOver.getgameOverMode()) {
					gameOver.clickButton(e.getX(),e.getY());
					if (gameOver.getStopButton())
						stopGame();
					if (gameOver.getReplayButton())
						replayGame();
				}
				
				// 게임에서 이겼을 때, 어떤 버튼이냐에 따른 행동
				if (victory.getVictoryMode()) {
					victory.clickButton(e.getX(), e.getY());
					
					if (victory.getStopButton()) {
						stopGame();	
					}
				}
			}			
		});
		
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
	}
	
	// 마우스  X 위치 리턴
	public int getMouseX() {
		return mouseX;
	}

	// 마우스  Y 위치 리턴
	public int getMouseY() {
		return mouseY;
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
		barX.draw(g2, this);
		barY.draw(g2, this);

		// 벌의 목적지를 빨간 점으로 표시
		// 2번 스페이스바를 누를 때마다 나타난다. 시작 시 나타나지않는다.
		if (spaceHitCnt == 2) {
			g2.setColor(Color.RED);
			g2.fillOval(barX.getPos() - 10 / 2, barY.getPos() - 10 / 2, 10, 10);
		}
		
		bee.draw(g2, this);

		// 일시정지시 나타나는 패널
		pause.draw(g2, this);

		// 100점 달성 시 나타나는 패널
		if (score.getGetScore() >= 100) {
			victory.draw(g2, this);
			stop();
			repaint();
		}

		// 시간 초과시 나타나는 패널
		if (timeBee.getX() == 50) {
			gameOver.draw(g2, this);
			stop();
			repaint();
		}

		g.drawImage(bufImage, 0, 0, this);
	}

	public void start() {
		running = true;

		BgmMain.Play("Loop");

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
						barX.update();
						barY.update();

						Thread.sleep(16);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				if (pause.getStopGame())
					stopGame();
				if (pause.getReplayGame())
					replayGame();
				repaint();
			}
		}).start();
	}

	public void stop() {
		running = false;
	}

	public void stopGame() {
		BgmMain.Stop();
		GameFrame.getInstance().introChange();
	}

	public void replayGame() {
		BgmMain.Stop();
		GameFrame.getInstance().honeyBeeReChange();
	}
}
