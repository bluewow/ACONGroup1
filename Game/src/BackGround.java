import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class BackGround {
	private int x;
	private int y;// 캔버스 좌표값
	private int sx;
	private int sy;// 이미지 소스 좌표값
	private int w;
	private int h;

	private int xTimeForReady;
	private int yTimeForReady;


	private Image img;

	public BackGround() {
		x = 0;
		y = 0;
		w = 800;
		h = 800;
		sx = 0;
		sy = 0;

		xTimeForReady = 0;
		yTimeForReady = 0;

		Toolkit tk = Toolkit.getDefaultToolkit(); // 이미지 그리는 api
		img = tk.getImage("res/backTemplet.png"); //

	}

	public void update() {
		if (xTimeForReady < 2)
			xTimeForReady++;
		else if (xTimeForReady == 2) {
			sx++;
			xTimeForReady = 0;
		} // 배경 X의 속도

		if (yTimeForReady < 200)
			yTimeForReady++;
		
		if (yTimeForReady <= 100 && yTimeForReady % 10 == 0 )
			sy++;
		else if (yTimeForReady>100 && yTimeForReady<=200 && yTimeForReady % 10 == 0)
			sy--;	
	
		if (yTimeForReady == 200)
			yTimeForReady = 0;
		// 배경 Y의 속도

	}

	public void draw(Graphics g, HoneyBeeCanvas honeyBeeCanvas) {
		g.drawImage(img, x, y, w, h, sx, sy, w + sx, sy + h, honeyBeeCanvas);

	}
	

}
