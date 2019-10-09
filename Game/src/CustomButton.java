import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class CustomButton {
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private Image img;
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean isHovered;
	
	public CustomButton(String fileName, int x, int y, int width,int height) {
		img = tk.getImage(fileName);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}
	
	public void update() {
		
	}
	
	public void draw(Graphics g, Canvas canvas) {
		g.drawImage(img, x, y, width, height, canvas);
	}
	
	public void click() {
		
	}
	
	public void hovered() {
		
	}
}
