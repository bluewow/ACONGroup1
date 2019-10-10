import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Victory {
	private static Color color = new Color(1f, 1f, 1f, 0.3f);
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image imgBtn = tk.getImage("res/VictoryBtnTemplet(195X65).png");
	private static Image imgTitle = tk.getImage("res/TitleTemplet(690X200).png");
	private static Image imgRank = tk.getImage("res/InputTemplet(500X100).png");	
	private Rank rank;
	private static int btnX = 145;
	private static int btnY = 600;
	private static int btnW = 195;
	private static int btnH = 65;
	private static int btnSX = 0;
	private static int btnSY = 0;
	private static int titleX = 50;
	private static int titleY = 100;
	private static int titleW = 690;
	private static int titleH = 200;
	private static int titleSX = titleW*1;
	private static int titleSY = 0;
	private int winWidth;
	private int winHeight;
	private int getX;
	private int getY;
	private boolean victoryMode;
	private boolean stopButton;
	private boolean viewButton;
	
	public Victory() {
		rank = new Rank();
		
		victoryMode = false;
		stopButton = false;
		viewButton = false;
	}

	public void draw(Graphics g, HoneyBeeCanvas canvas) {
		victoryMode = true;

		winWidth = canvas.getWidth();
		winHeight = canvas.getHeight();
		getX = canvas.getMouseX();
		getY = canvas.getMouseY();

		// 게임 오버가 실행될 시
		// 뒷배경 투명도 및 색 설정
		g.setColor(color);
		g.fillRect(0, 0, winWidth, winHeight);

		// 타이틀 그리기
		g.drawImage(imgTitle, titleX, titleY, titleW, titleH, titleSX, titleSY, titleSX+titleW, titleSY+titleH, canvas);
		
		if(!viewButton) {
			// 이름 텍스트 박스
			g.drawImage(imgRank, winWidth / 2 - 300 / 2 - 50, 400, winWidth / 2 - 300 / 2 + 300 - 50, 400 + 50, 
					0, 0, 300, 50, canvas);
			
			rank.drawName(g, canvas);
			
			// 이름 입력 버튼
			if (getX >= winWidth / 2 + 300 / 2 - 50 && getX <= winWidth / 2 + 300 / 2 + 100 - 50
					&& getY >= 400 && getY <= 400 + 50)
				g.drawImage(imgRank, winWidth / 2 + 300 / 2 - 50, 400, winWidth / 2 + 300 / 2 + 100 - 50, 400 + 50, 
						400, 0, 500, 50, canvas);
			else
				g.drawImage(imgRank, winWidth / 2 + 300 / 2 - 50, 400, winWidth / 2 + 300 / 2 + 100 - 50, 400 + 50, 
						300, 0, 400, 50, canvas);
		} else {
			g.setColor(Color.white);
			g.fillRect(winWidth / 2 - 600 / 2, 150, 600, 400);
			g.setColor(Color.black);
			g.drawRect(winWidth / 2 - 600 / 2, 150, 600, 400);

			// rank 출력
			rank.drawRank(g, canvas);
		}
		
		// 마우스가 버튼 위에 올라올 때 이미지 변화
		// 다시하기
		if (getX >= btnX && getX <= btnX + btnW && getY >= btnY && getY <= btnY + btnH)
			g.drawImage(imgBtn, btnX, btnY, btnX + btnW, btnY + btnH, btnSX, btnSY + btnH, btnSX + btnW, btnSY + btnH * 2, canvas);
		else
			g.drawImage(imgBtn, btnX, btnY, btnX + btnW, btnY + btnH, btnSX, btnSY, btnSX + btnW, btnSY + btnH, canvas);
		// 타이틀로
		if (getX >= btnX + btnW + 90 && getX <= btnX + btnW * 2 + 90 && getY >= btnY && getY <= btnY + btnH)
			g.drawImage(imgBtn, btnX + btnW + 90, btnY, btnX + btnW * 2 + 90, btnY + btnH,
					btnSX + btnW, btnSY + btnH, btnSX + btnW * 2, btnSY + btnH * 2, canvas);
		else
			g.drawImage(imgBtn, btnX + btnW + 90, btnY, btnX + btnW * 2 + 90, btnY + btnH,
					btnSX + btnW, btnSY, btnSX + btnW * 2, btnSY + btnH, canvas);
		
	}

	public boolean getVictoryMode() {
		return victoryMode;
	}

	public void clickButton(int getX, int getY) {
		// 다시하기
		if (getX >= btnX && getX <= btnX + btnW && getY >= btnY && getY <= btnY + btnH) {
			viewButton = true;
			rank.bringRank();
		}
		// 타이틀로
		if (getX >= btnX + btnW + 90 && getX <= btnX + btnW * 2 + 90 && getY >= btnY && getY <= btnY + btnH)
			stopButton = true;
		
		// 랭킹입력
		if  (getX >= winWidth / 2 + 300 / 2 + 20 && getX <= winWidth / 2 + 300 / 2 + 20 + 100 
				&& getY >= 400 && getY <= 400 + 50)
			System.out.println("ddd");
	}

	public boolean getViewButton() {
		return viewButton;
	}

	public boolean getStopButton() {
		return stopButton;
	}
	
	public void inputName(int backInput, char input) {
		rank.setInputName(backInput, input);
	}
}
