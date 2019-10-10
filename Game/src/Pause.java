import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Pause {
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image pauseImg = tk.getImage("res/PauseBtn(40X40).png");
	private static Image btnImg = tk.getImage("res/PauseBtnTemplet(585X130).png");
	private static Image pauseTitle = tk.getImage("res/TitleTemplet(690X200).png");
	private static Color color = new Color(0f, 0f, 0f, 0.5f);
	private int x;				// 일시정지 버튼 위치
	private int y;
	private int width;			// 일시정지 버튼 크기
	private int height;
	private int winWidth;		// 윈도우 창 크기
	private int winHeight;
	private int mx;
	private int my;
	private boolean pauseMode; 	// 일시정지 상태인지 아닌지
	private boolean stop;		// 타이틀로 돌아기기
	private boolean replay;		// 다시하기
	
	public Pause() {
		this(720, 20);
	}

	public Pause(int x, int y) {
		this.x = x;
		this.y = y;
		width = 40;
		height = 40;
		pauseMode = false;
		stop = false;
		replay = false;
	}

	public void btnPauseClicked(int mx, int my) {
		// 일시정지 버튼을 누를 때, 일시정지 기능 활성화
		if (mx >= x && mx <= x + width && my >= y && my <= y + height) {
			pauseMode = true;
		}
	}
	
	public void clickButton() {
		// 일시정지 상태일 때, 3개의 버튼 활성화
		if(pauseMode) {
			// 이어하기 버튼을 누를 때
			if (mx >= winWidth / 2 - 100 && mx <= winWidth / 2 + 100 
					&& my >= 350 - 75 /2 && my <= 350 + 75 / 2) {
				pauseMode = false;
				BgMusic.Sound("res/BtSelect.wav", "Play");
			}
			// 새로하기 버튼을 누를 때, 타이틀로 돌아가지않고 게임 새로 시작
			else if (mx >= winWidth / 2 - 100 && mx <= winWidth / 2 + 100 
					&& my >= 450 - 75 /2 && my <= 450 + 75 / 2) {
				replay = true;
				BgMusic.Sound("res/BtSelect.wav", "Play");
			}
			// 타이틀로 버튼을 누를 때, 타이틀로 돌아가기
			else if (mx >= winWidth / 2 - 100 && mx <= winWidth / 2 + 100 
					&& my >= 550 - 75 /2 && my <= 550 + 75 / 2) {
				stop = true;
				BgMusic.Sound("res/BtSelect.wav", "Play");
			}
		}
	}

	public void draw(Graphics g2, HoneyBeeCanvas honeyBeeCanvas) {
		// Canvas 크기 구하기
		winWidth = honeyBeeCanvas.getWidth();
		winHeight = honeyBeeCanvas.getWidth();
		
		// 마우스의 위치 구하기
		mx = honeyBeeCanvas.getMouseX();
		my = honeyBeeCanvas.getMouseY();
		
		// 일시정지 버튼 그리기
		g2.drawImage(pauseImg, x, y, honeyBeeCanvas);

		if (pauseMode) {
			// 화면 어두워지기
			g2.setColor(color);
			g2.fillRect(0, 0, winWidth, winHeight);
			g2.drawImage(pauseTitle,50,400,200+690,400+200,honeyBeeCanvas);
			
			// 버튼 1개의 이미지 크기
			int imgW = btnImg.getWidth(honeyBeeCanvas) / 3;
			int imgH = btnImg.getHeight(honeyBeeCanvas) / 2;
			
			// 이어하기
			if (mx >=  winWidth / 2 - imgW / 2 && mx <=  winWidth / 2 + imgW / 2 
					&& my >= 350 - imgH / 2 && my <= 350 + imgH / 2)
				g2.drawImage(btnImg, 
						winWidth / 2 - imgW / 2, 350 - imgH / 2,
						winWidth / 2 + imgW / 2, 350 + imgH / 2,
						0, imgH, imgW, imgH * 2, honeyBeeCanvas);
			else
				g2.drawImage(btnImg, 
						winWidth / 2 - imgW / 2, 350 - imgH / 2,
						winWidth / 2 + imgW / 2, 350 + imgH / 2,
					0, 0, imgW, imgH, honeyBeeCanvas);
			
			// 새로하기
			if (mx >=  winWidth / 2 - imgW / 2 && mx <=  winWidth / 2 + imgW / 2 
					&& my >= 450 - imgH / 2 && my <= 450 + imgH / 2)
				g2.drawImage(btnImg,
						winWidth / 2 - imgW / 2, 450 - imgH / 2,
						winWidth / 2 + imgW / 2, 450 + imgH / 2,
						imgW, imgH, imgW * 2, imgH * 2, honeyBeeCanvas);
			else
				g2.drawImage(btnImg,
						winWidth / 2 - imgW / 2, 450 - imgH / 2,
						winWidth / 2 + imgW / 2, 450 + imgH / 2,
						imgW, 0, imgW * 2, imgH, honeyBeeCanvas);
			
			// 타이틀로
			if (mx >=  winWidth / 2 - imgW / 2 && mx <=  winWidth / 2 + imgW / 2 
					&& my >= 550 - imgH / 2 && my <= 550 + imgH / 2)
				g2.drawImage(btnImg, 
						winWidth / 2 - imgW / 2, 550 - imgH / 2,
						winWidth / 2 + imgW / 2, 550 + imgH / 2,
						imgW * 2, imgH, imgW * 3, imgH * 2, honeyBeeCanvas);
			else
				g2.drawImage(btnImg, 
						winWidth / 2 - imgW / 2, 550 - imgH / 2,
						winWidth / 2 + imgW / 2, 550 + imgH / 2,
						imgW * 2, 0, imgW * 3, imgH, honeyBeeCanvas);
		}
	}

	public boolean getPauseMode() {
		return pauseMode;
	}

	public boolean getStopGame() {
		return stop;
	}

	public boolean getReplayGame() {
		return replay;
	}
}
