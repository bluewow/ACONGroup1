import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Victory {
	private static Color color = new Color(1f, 1f, 1f, 0.3f);
	private static Toolkit tk = Toolkit.getDefaultToolkit();;
	private Image img;
	private Image font;
	private Rank rank;
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
	private boolean victoryMode;
	private boolean stopButton;
	private boolean viewButton;
	
	public Victory() {
		img = tk.getImage("res/VictoryBtnTemplet(195X65).png");
		font = tk.getImage("res/Font.png");
		rank = new Rank();
		
		x = 145;
		y = 600;
		w = 195;
		h = 65;
		sx = 0;
		sy = 0;
		
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

		if(!viewButton) {
			// 이름 텍스트 박스
			g.setColor(Color.white);
			g.fillRect(winWidth / 2 - 300 / 2, 400, 300, 50);
		
			g.setColor(Color.black);
			g.drawRect(winWidth / 2 - 300 / 2, 400, 300, 50);
		} else {
			g.setColor(Color.white);
			g.fillRect(winWidth / 2 - 600 / 2, 150, 600, 400);
			g.setColor(Color.black);
			g.drawRect(winWidth / 2 - 600 / 2, 150, 600, 400);
			
			// 이름, 점수, 시간 출력
			drawText(winWidth / 2 - 600 / 2, 150, "Name", g, canvas);
			drawText(winWidth / 2 - 600 / 2 + 250, 150, "Score", g, canvas);
			drawText(winWidth / 2 - 600 / 2 + 480, 150, "Time", g, canvas);
		}
		
		// 마우스가 버튼 위에 올라올 때 이미지 변화
		// 다시하기
		if (getX >= x && getX <= x + w && getY >= y && getY <= y + h)
			g.drawImage(img, x, y, x + w, y + h, sx, sy + h, sx + w, sy + h * 2, canvas);
		else
			g.drawImage(img, x, y, x + w, y + h, sx, sy, sx + w, sy + h, canvas);
		// 타이틀로
		if (getX >= x + w + 90 && getX <= x + w * 2 + 90 && getY >= y && getY <= y + h)
			g.drawImage(img, x + w + 90, y, x + w * 2 + 90, y + h,
					sx + w, sy + h, sx + w * 2, sy + h * 2, canvas);
		else
			g.drawImage(img, x + w + 90, y, x + w * 2 + 90, y + h,
					sx + w, sy, sx + w * 2, sy + h, canvas);
		
	}

	public boolean getVictoryMode() {
		return victoryMode;
	}

	public void clickButton(int getX, int getY) {
		// 다시하기
		if (getX >= x && getX <= x + w && getY >= y && getY <= y + h) {
			viewButton = true;
			rank.showRank();
		}
		// 타이틀로
		if (getX >= x + w + 90 && getX <= x + w * 2 + 90 && getY >= y && getY <= y + h)
			stopButton = true;
	}

	public boolean getViewButton() {
		return viewButton;
	}

	public boolean getStopButton() {
		return stopButton;
	}
	
	private void drawText(int tx, int ty, String text, Graphics g, HoneyBeeCanvas canvas) {
		int charW = font.getWidth(canvas) / 26;
		int charH = font.getHeight(canvas) / 3;
		
		for(int i = 0; i < text.length(); i++) {
			int index;
			char[] ch = text.toCharArray();
			index = ch[i];
			
			if(index >= 48 && index <= 57) {
				index += 4;
			} else if(index >= 65 && index <= 90) {
				index -= 65;					
			}
			else if(index >= 97 && index <= 122) {
				index -= 71;
			}

			g.drawImage(font, 
					tx + i * charW, ty,
					tx + charW + i * charW, ty + charH,
					charW * (index % 26), charH * (index / 26),
					charW + charW * (index % 26), charH + charH * (index / 26), canvas);
		}
	}
}
