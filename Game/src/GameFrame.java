import java.awt.Frame;

public class GameFrame extends Frame {
	private static GameFrame frame;
	private IntroCanvas introCanvas;
	private HoneyBeeCanvas honeyBeeCanvas;
	
	public static GameFrame getInstance() {
		if (frame == null)
			frame = new GameFrame();
		return frame;
	}

	private GameFrame() {
		setSize(800, 800);

		introCanvas = new IntroCanvas();
		add(introCanvas);
		introCanvas.setFocusable(true);
		introCanvas.requestFocus();

		addWindowListener(new GameWindowListener());

		setVisible(true);
	}

	public void introChange() {
		introCanvas = new IntroCanvas();
		add(introCanvas);

		if (honeyBeeCanvas.isValid()) {
			remove(honeyBeeCanvas);
			honeyBeeCanvas.stop();
			honeyBeeCanvas = null;
		}
//		} else if (endCanvas.isValid())
//			remove(endCanvas);

		introCanvas.setFocusable(true);
		introCanvas.requestFocus();
		revalidate();
	}

	public void honeyBeeChange() {
		honeyBeeCanvas = new HoneyBeeCanvas();

		add(honeyBeeCanvas);
		
		if (introCanvas.isValid())
			remove(introCanvas);

		honeyBeeCanvas.setFocusable(true);
		honeyBeeCanvas.requestFocus();
		revalidate();

		honeyBeeCanvas.start();
	}
	
	public void honeyBeeReChange() {
		if (honeyBeeCanvas.isValid()) {
			remove(honeyBeeCanvas);
			honeyBeeCanvas.stop();
			honeyBeeCanvas = null;
		}
		honeyBeeChange();
	}

//	public void endChange() {
//		add(endCanvas);
//		remove(honeyBeeCanvas);
//
//		endCanvas.setFocusable(true);
//		endCanvas.requestFocus();
//		honeyBeeCanvas.stop();
//		revalidate();
//	}

}
