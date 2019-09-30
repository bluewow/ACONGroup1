import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class BackGround {
	private Image img;
	
	public BackGround() {
		Toolkit tk = Toolkit.getDefaultToolkit(); //이미지 그리는 api
		img = tk.getImage("res/배경 낮.png"); // 
		
	}
	public void draw(Graphics g, HoneyBeeCanvas honeyBeeCanvas) {
		g.drawImage(img, 0,0,800,800,0,0,873,853,honeyBeeCanvas);
		
	}
}
