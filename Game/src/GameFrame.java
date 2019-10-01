import java.awt.Canvas;
import java.awt.Frame;

public class GameFrame extends Frame {

	private IntroCanvas introCanvas;
	private HoneyBeeCanvas canvas;
	
	public static GameFrame frame;
	public static GameFrame getInstance() {
		
		if (frame==null)
		frame = new GameFrame();
		return frame;
	}
	
	public void change() {
		add(canvas);
		canvas.setFocusable(true);
		canvas.requestFocus();
		remove(introCanvas);
		revalidate();
		
		canvas.start();
		
	}
	
	private GameFrame() {
		setSize(800,800);
		
		introCanvas = new IntroCanvas();
		canvas = new HoneyBeeCanvas();
		add(introCanvas);
		introCanvas.setFocusable(true);
		introCanvas.requestFocus();
		addWindowListener(new GameWindowListener());
		setVisible(true);
	}
}
