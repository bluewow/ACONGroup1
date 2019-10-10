import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// 1. HoneyBeeCanvas의 TimeBee의 위치가 50이 되면 실행.
// 2. 마우스 위치가 버튼의 위로 올라가면 이미지가 변한다.
// 3. 마우스가 해당 버튼을 누르면 해당 명령이 실행된다.
//  --> 캔버스의 x,y 위치 받아오기
//  -->
public class GameOver {
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image imgBtn = tk.getImage("res/GameOverBtnTemplet(195X65).png");
	private static Image imgTitle = tk.getImage("res/TitleTemplet(690X200).png");
	private static int btnX = 145;
	private static int btnY = 420;
	private static int btnW = 195;
	private static int btnH = 65;
	private static int btnSX = 0;
	private static int btnSY = 0;
	private static int titleX = 50;
	private static int titleY = 200;
	private static int titleW = 690;
	private static int titleH = 200;
	private static int titleSX = titleW * 2;
	private static int titleSY = 0;

	private int winWidth;
	private int winHeight;
	private int getX;
	private int getY;
	private boolean gameOverMode;
	private boolean stopButton;
	private boolean replayButton;

	private static Color color = new Color(1f, 1f, 1f, 0.3f);

	public GameOver() {
		gameOverMode = false;
		stopButton = false;
		replayButton = false;
	}

	public boolean getgameOverMode() {
		return gameOverMode;
	}

	public void draw(Graphics g, HoneyBeeCanvas canvas) {
		gameOverMode = true;

		winWidth = canvas.getWidth();
		winHeight = canvas.getHeight();
		getX = canvas.getMouseX();
		getY = canvas.getMouseY();

		// 게임 오버가 실행될 시
		// 뒷배경 투명도 및 색 설정
		g.setColor(color);
		g.fillRect(0, 0, winWidth, winHeight);

		// 타이틀 그리기
		g.drawImage(imgTitle, titleX, titleY, titleX+titleW, titleY+titleH, titleSX, titleSY, titleSX + titleW, titleSY + titleH,
				canvas);

		// 마우스가 버튼 위에 올라올 때 이미지 변화
		// 다시하기
		if (getX >= btnX && getX <= btnX + btnW && getY >= btnY && getY <= btnY + btnH)
			g.drawImage(imgBtn, btnX, btnY, btnX + btnW, btnY + btnH, btnSX, btnSY + btnH, btnSX + btnW,
					btnSY + btnH * 2, canvas);
		else
			g.drawImage(imgBtn, btnX, btnY, btnX + btnW, btnY + btnH, btnSX, btnSY, btnSX + btnW, btnSY + btnH, canvas);
		// 타이틀로
		if (getX >= btnX + btnW + 90 && getX <= btnX + btnW * 2 + 90 && getY >= btnY && getY <= btnY + btnH)
			g.drawImage(imgBtn, btnX + btnW + 90, btnY, btnX + btnW * 2 + 90, btnY + btnH, btnSX + btnW, btnSY + btnH,
					btnSX + btnW * 2, btnSY + btnH * 2, canvas);
		else
			g.drawImage(imgBtn, btnX + btnW + 90, btnY, btnX + btnW * 2 + 90, btnY + btnH, btnSX + btnW, btnSY,
					btnSX + btnW * 2, btnSY + btnH, canvas);
	}

	public void clickButton(int getX, int getY) {
		// 다시하기
		if (getX >= btnX && getX <= btnX + btnW && getY >= btnY && getY <= btnY + btnH) {
			BgMusic.Sound("res/BtSelect.wav", "Play");
			replayButton = true;
		}
		// 타이틀로
		if (getX >= btnX + btnW + 90 && getX <= btnX + btnW * 2 + 90 && getY >= btnY && getY <= btnY + btnH) {
			BgMusic.Sound("res/BtSelect.wav", "Play");
			stopButton = true;
		}
	}

	public boolean getReplayButton() {
		return replayButton;
	}

	public boolean getStopButton() {
		return stopButton;
	}

}
