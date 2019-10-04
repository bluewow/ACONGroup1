import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class IntroCanvas extends Canvas {
	private int x;
	private int y;
	private int sx;
	private int sy;
	private int w;
	private int h;
	private Image img;

	public IntroCanvas() {
		x = 250;
		y = 400;
		sx = 0;
		sy = 0;
		w = 255;
		h = 85;

		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/IntroBeforeBtnTemplet(255X85).png");

		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				GameFrame.getInstance().honeyBeeChange();
			}
		});

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getX() >= x && e.getX() <= x + w && e.getY() >= y && e.getY() <= y + h)
					GameFrame.getInstance().honeyBeeChange();
				else if (e.getX() >= x && e.getX() <= x + w && e.getY() >= y + 200 && e.getY() <= y + 200 + h)
					System.exit(0);
			}

			@Override
			public void mouseMoved(MouseEvent e) {

			}
		});
	}

	@Override
	public void paint(Graphics g) {
		g.drawImage(img, x, y, x + w, y + h, sx, sy, w, h, this);
		g.drawImage(img, x, y + 100, x + w, y + 100 + h, sx + w, sy, sx + w * 2, h, this);
		g.drawImage(img, x, y + 200, x + w, y + 200 + h, sx + w * 2, sy, sx + w * 3, h, this);
	}
}
