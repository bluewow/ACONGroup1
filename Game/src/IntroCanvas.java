import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;

public class IntroCanvas extends Canvas {
	@Override
	public void paint(Graphics g) {
		g.drawString("게임 시작", 200, 300);
		
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				GameFrame.getInstance().honeyBeeChange();				
			}
		});
		
		addMouseListener(new MouseAdapter() {
			
		});
	}
}
