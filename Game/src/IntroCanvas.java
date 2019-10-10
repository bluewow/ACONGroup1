import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IntroCanvas extends Canvas {
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private Image imgBack = tk.getImage("res/introBack.png");
	private Image imgTitle = tk.getImage("res/TitleTemplet(690X200).png");
	private Image imgBefore = tk.getImage("res/IntroBeforeBtnTemplet(255X85).png");
	private Image imgAfter = tk.getImage("res/IntroAfterBtnTemplet(255X85).png");
	
	private static int titleX = 50;
	private static int titleY = 200;
	private static int titleW = 690;
	private static int titleH = 200;
	private static int btnX = (800-255)/2;
	private static int btnY = 400;
	private static int btnW = 255;
	private static int btnH = 85;
	private static int btnHGap = 100;
	private static int sX = 0;
	private static int sY = 0;

	private int getX;
	private int getY;


	public IntroCanvas() {
		BgMusic.Sound("res/Title.wav", "Play");
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getX() >= btnX && e.getX() <= btnX + btnW && e.getY() >= btnY && e.getY() <= btnY + btnH)
				{	BgMusic.Sound("res/BtSelect.wav", "Play");
					GameFrame.getInstance().honeyBeeChange();}
				else if (e.getX() >= btnX && e.getX() <= btnX + btnW && e.getY() >= btnY + 200 && e.getY() <= btnY + 200 + btnH)
				System.exit(0);
			}
		});
		
		addMouseMotionListener(new MouseAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				getX = e.getX();
				getY = e.getY();
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
		// 배경 그리기
		g2.drawImage(imgBack, 0, 0, this);
		
		// 타이틀 그리기
		g2.drawImage(imgTitle, titleX, titleY, titleX+titleW,titleY+titleH,sX,sY,sX+titleW,sY+titleH, this);
		
		// 버튼 그리기 (마우스 갖다 댔을 때 이미지 변화 포함)
		if (getX >= btnX && getX <= btnX + btnW && getY >= btnY && getY <= btnY + btnH)
			g2.drawImage(imgAfter, btnX, btnY,btnX+btnW,btnY+btnH,sX,sY,btnW,btnH,this);
		else 
			g2.drawImage(imgBefore, btnX, btnY, btnX + btnW, btnY + btnH, sX, sY, btnW, btnH, this);
		if (getX >= btnX && getX <= btnX + btnW && getY >= btnY + btnHGap && getY <= btnY + btnH + btnHGap)
			g2.drawImage(imgAfter, btnX, btnY + btnHGap, btnX + btnW, btnY + btnH + btnHGap, sX + btnW, sY, sX + btnW * 2, btnH, this);
		else
			g2.drawImage(imgBefore, btnX, btnY + btnHGap, btnX + btnW, btnY + btnH + btnHGap, sX + btnW, sY, sX + btnW * 2, btnH, this);
		if (getX >= btnX && getX <= btnX + btnW && getY >= btnY + btnHGap * 2 && getY <= btnY + btnH + btnHGap * 2)
			g2.drawImage(imgAfter, btnX, btnY + btnHGap * 2, btnX + btnW, btnY + btnH + btnHGap * 2, sX + btnW * 2, sY, sX + btnW * 3, btnH, this);
		else
			g2.drawImage(imgBefore, btnX, btnY + btnHGap * 2, btnX + btnW, btnY + btnH + btnHGap * 2, sX + btnW * 2, sY, sX + btnW * 3, btnH, this);
		
		repaint();
		
		g.drawImage(bufImage, 0, 0, this);
	}

}
