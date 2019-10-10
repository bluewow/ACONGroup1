import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Rank {
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image img = tk.getImage("res/Font.png");
	private String url = "jdbc:oracle:thin:@192.168.0.3:1521/xepdb1";
//	private String url = "jdbc:oracle:thin:@localhost:1521/orcl";
	private String sql = "select * from HONEYBEE order by SCORE, TIME desc";
	private String sql2 = "insert into HONEYBEE(NAME, SCORE, TIME) values(?, ?, ?)";
	private Connection connection;
	private Statement statement;
	private PreparedStatement pstatement;
	private ResultSet resultSet;
	
	private String inputName;
	private String inputScore;
	private String inputTime;
	
	private String[] name;
	private String[] score;
	private String[] time;
	
	public Rank() {
		name = new String[5];
		score = new String[5];
		time = new String[5];
		inputName = "";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			connection = DriverManager.getConnection(url, "ACORN","newlec"); 
//			connection = DriverManager.getConnection(url, "SCOTT","tiger"); 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public void drawRank(Graphics g, HoneyBeeCanvas canvas) {
		int winWidth = canvas.getWidth();
		int winHeight = canvas.getHeight();
		
		// 이름, 점수, 시간 출력
		drawText(winWidth / 2 - 600 / 2 + 10, 150, "Rank", g, canvas);
		drawText(winWidth / 2 - 600 / 2 + 200, 150, "Name", g, canvas);
		drawText(winWidth / 2 - 600 / 2 + 420, 150, "Score", g, canvas);
		drawText(winWidth / 2 - 600 / 2 + 520, 150, "Time", g, canvas);
				
		for(int i = 0; i < name.length; i++) {
			if(name[i] != null)
				drawText(winWidth / 2 - 600 / 2 + 30, 200 + i * 50, Integer.toString(i + 1), g, canvas);
		}
		
		for(int i = 0; i < name.length; i++) {
			if(name[i] != null)
				drawText(winWidth / 2 - 600 / 2 + 100, 200 + i * 50, name[i], g, canvas);
		}

		for(int i = 0; i < score.length; i++) {
			if(name[i] != null)
				drawText(winWidth / 2 - 600 / 2 + 435, 200 + i * 50, score[i], g, canvas);
		}
		
		for(int i = 0; i < time.length; i++) {
			if(name[i] != null)
				drawText(winWidth / 2 - 600 / 2 + 535, 200 + i * 50, time[i], g, canvas);
		}
	}
	
	public void drawName(Graphics g, HoneyBeeCanvas canvas) {
		int winWidth = canvas.getWidth();
		int winHeight = canvas.getHeight();
		
		int x = winWidth / 2 - 300 / 2 - 35;
		int y = 415;
		drawText(x, y, inputName, g, canvas);
	}
	
	public void bringRank() {
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			for(int i = 0; resultSet.next() && i <= 5; i++) {
				name[i] = resultSet.getString("NAME");
				score[i] = resultSet.getString("SCORE");
				time[i] = resultSet.getString("TIME");
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void storeRank() {
		try {
			pstatement = connection.prepareStatement(sql2);
			pstatement.setString(1, inputName);
			pstatement.setInt(2, Integer.parseInt(inputScore));
			pstatement.setInt(3, Integer.parseInt(inputTime));
			pstatement.executeUpdate(sql2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void drawText(int tx, int ty, String text, Graphics g, HoneyBeeCanvas canvas) {
		int charW = img.getWidth(canvas) / 26;
		int charH = img.getHeight(canvas) / 3;
		
		for(int i = 0; i < text.length(); i++) {
			int index;
			char[] ch = text.toCharArray();
			index = ch[i];
			
			if(index >= 48 && index <= 57) {
				index += 4;
			} else if(index >= 65 && index <= 90) {
				index -= 65;					
			}
			else if(index >= 97 && index <= 122) {
				index -= 71;
			}

			g.drawImage(img, 
					tx + i * (charW * 1/2), ty,
					tx + charW * 1 / 2+ i * (charW* 1/2), ty + (charH * 1 / 2),
					charW * (index % 26), charH * (index / 26),
					charW + charW * (index % 26), charH + charH * (index / 26), canvas);
		}
		
	}
	public void setInputName(int backInput, char input) {
		// 백스페이스
		if(backInput == 8 && inputName.length() != 0)
			inputName = inputName.substring(0, inputName.length() - 1);
		else {
			if(inputName.length()<=15)
				inputName += input;
		}
	}
}
