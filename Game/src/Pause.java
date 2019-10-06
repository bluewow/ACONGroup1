import java.awt.Color;
import java.awt.Graphics;

public class Pause {
	private int x;				// 일시정지 버튼 위치
	private int y;
	private int width;			// 일시정지 버튼 크기
	private int height;
	private int winWidth;		// 윈도우 창 크기
	private int winHeight;
	private boolean pauseMode; 	// 일시정지 상태인지 아닌지
	private static Color color = new Color(0f, 0f, 0f, 0.5f);
	private boolean[] buttons;	// 계속하기, 그만하기, 다시하기 버튼

	public Pause() {
		this(720, 20);
	}

	public Pause(int x, int y) {
		this.x = x;
		this.y = y;
		width = 40;
		height = 40;
		pauseMode = false;
		buttons = new boolean [2];
	}

	public void btnPauseClicked(int mx, int my) {
		// 일시정지 버튼을 누를 때, 일시정지 기능 활성화
		if (mx >= x && mx <= x + width && my >= y && my <= y + height) {
			pauseMode = true;
		}
	}
	
	public void clickButton(int mx, int my) {
		// 일시정지 상태일 때, 3개의 버튼 활성화
		if(pauseMode) {
			// 계속하기 버튼을 누를 때
			if (mx >= winWidth / 2 - 100 && mx <= winWidth / 2 + 100 && my >= 350 - 75 /2 && my <= 350 + 75 / 2)
				pauseMode = false;
			// 그만하기 버튼을 누를 때, 타이틀로 돌아간다.
			else if (mx >= winWidth / 2 - 100 && mx <= winWidth / 2 + 100 && my >= 450 - 75 /2 && my <= 450 + 75 / 2)
				buttons[0] = true;
			// 다시하기 버튼을 누를 때, 타이틀로 돌아가지않고 게임 다시
			else if (mx >= winWidth / 2 - 100 && mx <= winWidth / 2 + 100 && my >= 550 - 75 /2 && my <= 550 + 75 / 2)
				buttons[1] = true;
		}
	}

	public void draw(Graphics g2, HoneyBeeCanvas honeyBeeCanvas) {
		winWidth = honeyBeeCanvas.getWidth();
		winHeight = honeyBeeCanvas.getWidth();
		
		// 일시정지 버튼 그리기
		g2.drawRect(x, y, width, height);

		if (pauseMode) {
			// 화면 어두워지기
			g2.setColor(color);
			g2.fillRect(0, 0, winWidth, winHeight);
			
			g2.setColor(new Color(255, 255, 255));
			
			// 계속하기, 그만하기, 다시하기 버튼
			g2.drawRect(winWidth / 2 - 100, 350 - 75 / 2, 200, 75);
			g2.drawRect(winWidth / 2 - 100, 450 - 75 / 2, 200, 75);
			g2.drawRect(winWidth / 2 - 100, 550 - 75 / 2, 200, 75);
		}
	}

	public boolean getPauseMode() {
		return pauseMode;
	}

	public boolean getStopGame() {
		return buttons[0];
	}

	public boolean getReplayGame() {
		return buttons[1];
	}
}
