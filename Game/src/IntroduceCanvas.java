import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;


public class IntroduceCanvas extends Canvas{
   
   
      private static Color color = new Color(0f, 0f, 0f, 0.5f);
      private static Toolkit tk = Toolkit.getDefaultToolkit();
      private Image img = tk.getImage("res/introduce.png");
      
      private boolean introswitch;
      private boolean introducemode;
   
      
      public IntroduceCanvas() {
        introducemode = false;
       introswitch = true;
        
      }
      
       public void draw(Graphics g2, IntroCanvas introCanvas) {
      
      if(introducemode) {      
           g2.setColor(color);
            g2.drawImage(img,-10,-20,800,800,this);
          }
      
      }
             
      
   public boolean switchIntroduceCanvas() {
      if(introswitch) {   
          introducemode = true;
          System.out.println("a");
          introswitch = false;
      }
      else {
         introducemode = false;
         introswitch = true;
      }
      System.out.println(introducemode+","+introswitch);

       return introducemode;
   }
}