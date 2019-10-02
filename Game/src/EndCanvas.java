import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class EndCanvas extends Canvas{
	
	@Override
	public void paint(Graphics g) {
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image img = tk.getImage("res/retry.png");
		
		g.drawImage(img,300, 400, 156, 46,this);
		
	}
	

}
