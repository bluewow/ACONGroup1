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
	private int x;
	private int y;
	private int w;
	private int h;
	private int sx;
	private int sy;
	private int winWidth;
	private int winHeight;
	private int getX;
	private int getY;
	private boolean gameOverMode;
	private boolean stopButton;
	private boolean replayButton;

	private static Color color = new Color(1f, 1f, 1f, 0.3f);

	private Image img;

	public GameOver() {
		x = 145;
		y = 400;
		w = 195;
		h = 65;
		sx = 0;
		sy = 0;
		gameOverMode = false;
		stopButton = false;
		replayButton = false;

		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/GameOverBtnTemplet(195X65).png");
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

		// 마우스가 버튼 위에 올라올 때 이미지 변화
		// 다시하기
		if (getX >= x && getX <= x + w && getY >= y && getY <= y + h)
			g.drawImage(img, x, y, x + w, y + h, sx, sy + h, sx + w, sy + h * 2, canvas);
		else
			g.drawImage(img, x, y, x + w, y + h, sx, sy, sx + w, sy + h, canvas);
		// 타이틀로
		if (getX >= x + w + 90 && getX <= x + w * 2 + 90 && getY >= y && getY <= y + h)
			g.drawImage(img, x + w + 90, y, x + w * 2 + 90, y + h, sx + w, sy + h, sx + w * 2, sy + h * 2, canvas);
		else
			g.drawImage(img, x + w + 90, y, x + w * 2 + 90, y + h, sx + w, sy, sx + w * 2, sy + h, canvas);
	}

	public void clickButton(int getX, int getY) {
		// 다시하기
		if (getX >= x && getX <= x + w && getY >= y && getY <= y + h)
			replayButton = true;
		// 타이틀로
		if (getX >= x + w + 90 && getX <= x + w * 2 + 90 && getY >= y && getY <= y + h)
			stopButton = true;
	}

	public boolean getReplayButton() {
		return replayButton;
	}

	public boolean getStopButton() {
		return stopButton;
	}

}
