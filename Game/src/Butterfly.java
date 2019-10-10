import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.Random;

public class Butterfly {
   private double x;
   private double y;// 현재위치
   private double dx;
   private double dy;// 가야할위치
   private double vx;
   private double vy;// 방향속도
   private int timeCount;

   private double w;
   private double h;// 이미지 크기

   private Image img;

   int imgIndex;
   int imageDelay;

   public Butterfly() {
      imgIndex = 0;
      imageDelay = 0;

      x = 150.0;
      y = 250.0;// 현재위치

      w = 122.0;
      h = 96.0;// 이미지크기
      move();

      Toolkit tk = Toolkit.getDefaultToolkit();
      img = tk.getImage("res/butterfly.png");
   }

   public boolean update(Point point) {
      if (imageDelay++ % 120 == 0) {
         imageDelay = 0;
         imgIndex = (imgIndex == 1) ? 0 : 1;
      }

    
      x += vx;
      y += vy;

      if (((dy - 2 < y) && (y < dy + 2)) || ((dx - 2 < x) && (x < dx + 2))) {
         vy = 0;
         vx = 0;

         move();
      }

      timeCount++;
      if(timeCount>150){
         timeCount=0;
         point.x = (int)(x+w/2);
         point.y = (int)(y+h/2);
         return true;
      }
      
      return false;

   }

   public void draw(Graphics g2, HoneyBeeCanvas honeyBeeCanvas) {
      int x = (int) this.x;
      int y = (int) this.y;
      int w = (int) this.w;
      int h = (int) this.h;

      g2.drawImage(img, x, y, x + w, y + h, w * imgIndex, 0, (w * imgIndex) + w, 96, honeyBeeCanvas);
   }

   public void move() {
      Random random = new Random();
      dx = random.nextInt(280) + 110;
      dy = random.nextInt(200) + 330;

      double w = dx - x;
      double h = dy - y;
      double d = (double) Math.sqrt(w * w + h * h);
      vx = (w / d) * 2;
      vy = (h / d) * 2;

   }

}