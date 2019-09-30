import java.awt.Canvas;
import java.awt.Frame;

public class GameFrame extends Frame {

	private Canvas canvas;
	
	public GameFrame() {
		setSize(800,800);
		
		canvas = new HoneyBeeCanvas();
		this.add(canvas);
		canvas.setFocusable(true);
		canvas.requestFocus();
		addWindowListener(new GameWindowListener());
		setVisible(true);
	}
}
