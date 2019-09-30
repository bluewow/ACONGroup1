import java.awt.Image;
import java.awt.Toolkit;

public class Bee {
	private int xPos;
	private int yPos;
	private Image img;
	
	
	public Bee() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		img = tk.getImage("res/moveFighter.png");
	}
	
	public void getHoney() {
		
	}
	
	public int sendHoney() {

		return 0;
	}
}
