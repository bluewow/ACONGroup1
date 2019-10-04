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

	private int xtimeForReady;
	private int y1timeForReady;
	private int y2timeForReady;

	private Image img;

	public BackGround() {
		x = 0;
		y = 0;
		w = 800;
		h = 800;
		sx = 0;
		sy = 0;

		xtimeForReady = 0;
		y1timeForReady = 0;
		y2timeForReady = 0;

		Toolkit tk = Toolkit.getDefaultToolkit(); // 이미지 그리는 api
		img = tk.getImage("res/backTemplet.png"); //

	}

	public void update() {
		if (xtimeForReady < 2)
			xtimeForReady++;
		else if (xtimeForReady == 2) {
			sx++;
			xtimeForReady = 0;
		} // 배경 X의 속도

		if (y1timeForReady < 200)
			y1timeForReady++;

		if (y1timeForReady <= 100 && y1timeForReady % 10 == 0 )
			sy++;
		else if (y1timeForReady>100 && y1timeForReady<=200 && y1timeForReady % 10 == 0)
			sy--;	
	
		if (y1timeForReady == 200)
			y1timeForReady = 0;

		// }

//
//		if (y2timeForReady < 30)
//			y2timeForReady++;
//		else if (y2timeForReady-y1timeForReady%5==0) 
//			sy--;	
//		else if(y2timeForReady==30)
//			y2timeForReady = 0;			
//		System.out.printf("x: %d\n", sx);

		// 배경 Y의 속도

	}

	public void draw(Graphics g, HoneyBeeCanvas honeyBeeCanvas) {
		g.drawImage(img, x, y, w, h, sx, sy, w + sx, sy + h, honeyBeeCanvas);

	}
}
