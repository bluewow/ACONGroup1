
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Score {

	private int x;
	private int y;
	private int w;
	private int h;
	private int pos2;
	private int pos1;
	private int getScore;
	private Image plate;
	private Image number1;
	private Image number2;
	private Image score100;

	public Score() {
		x = 590;
		y = 80;
		w = 156;
		h = 46;
		getScore = 100;

		Toolkit tk = Toolkit.getDefaultToolkit();
		plate = tk.getImage("res/Scoreplate(156X46).png");
		number1 = tk.getImage("res/number_1Templet(156X64).png");
		number2 = tk.getImage("res/number_2Templet(156X64).png");
		score100 = tk.getImage("res/Score100(156X46).png");

	}

	public void update(Bottle bottle) {
		this.getScore = bottle.getHoney();
	}

	public void draw(Graphics g, HoneyBeeCanvas canvas) {

		pos2 = getScore / 10;
		pos1 = getScore % 10;

		g.drawImage(plate, x, y, w, h, canvas);
		
	

		if (pos1 > 0 && pos1 < 10)
			g.drawImage(number1, x, y, x+w, y+h, w * pos1, 0, w * (pos1 + 1), h, canvas);
		else if (pos1 == 0 || pos1 == 10)
			g.drawImage(number1, x, y, x+w, y+h, 0, 0, w, h, canvas);

		if (pos2 == 10)
			g.drawImage(score100, x, y, w, h, canvas);
		else if (pos2 > 0 && pos2 <= 9)
			g.drawImage(number2, x, y, x+w, y+h, w * pos2, 0, w * (pos2 + 1), h, canvas);
		else
			g.drawImage(number2, x, y, x+w, y+h, 0, 0, w, h, canvas);

	}

}
