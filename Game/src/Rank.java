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
	private String sql = "select * from (select * from HONEYBEE order by SCORE desc, TIME asc) where rownum between 1 and 7";
	private String sql2 = "insert into HONEYBEE(NAME, SCORE, TIME) values(?, ?, ?)";
	private Connection connection;
	private Statement statement;
	private PreparedStatement pstatement;
	private ResultSet resultSet;
	
	private String inputName;
	private int inputScore;
	private int inputTime;
	
	private String[] name;
	private String[] score;
	private String[] time;
	private boolean once;
	
	public Rank() {
		name = new String[7];
		score = new String[7];
		time = new String[7];
		inputName = "";
		once = true;
		
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
		drawText(winWidth / 2 - 600 / 2 + 2, 160, "Rank", g, canvas);
		drawText(winWidth / 2 - 600 / 2 + 220, 160, "Name", g, canvas);
		drawText(winWidth / 2 - 600 / 2 + 425, 160, "Score", g, canvas);
		drawText(winWidth / 2 - 600 / 2 + 530, 160, "Time", g, canvas);
		
		// 랭킹
		for(int i = 0; i < name.length; i++) {
			if(name[i] != null)
				drawText(winWidth / 2 - 600 / 2 + 15, 215 + i * 52, Integer.toString(i + 1), g, canvas);
		}
		// 이름
		for(int i = 0; i < name.length; i++) {
			if(name[i] != null)
				drawText(winWidth / 2 - 600 / 2 + 90, 215 + i * 52, name[i], g, canvas);
		}
		// 점수
		for(int i = 0; i < score.length; i++) {
			if(name[i] != null)
				drawText(winWidth / 2 - 600 / 2 + 440, 215 + i * 52, score[i], g, canvas);
		}
		// 시간
		for(int i = 0; i < time.length; i++) {
			if(name[i] != null)
				drawText(winWidth / 2 - 600 / 2 + 540, 215 + i * 52, time[i], g, canvas);
		}
	}
	
	public void drawName(Graphics g, HoneyBeeCanvas canvas) {
		int winWidth = canvas.getWidth();
		int winHeight = canvas.getHeight();
		
		int x = winWidth / 2 - 400 / 2 - 35;
		int y = 415;
		drawText(x, y, inputName, g, canvas);
	}
	
	public void bringRank() {
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			int i = 0;
			while(resultSet.next()) {
				name[i] = resultSet.getString("NAME");
				score[i] = resultSet.getString("SCORE");
				time[i] = resultSet.getString("TIME");
				i++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void storeRank() {
		try {
			pstatement = connection.prepareStatement(sql2);
			pstatement.setString(1, inputName);
			pstatement.setInt(2, inputScore);
			pstatement.setInt(3, inputTime);
			pstatement.executeUpdate();
			once = false;
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
		else if (backInput >= 65 && backInput <= 90 || backInput >= 48 && backInput <= 57) {
			if(inputName.length()<=15)
				inputName += input;
		}
	}
	
	public String getInputName() {
		return inputName;
	}
	
	public void setInputScore(int score) {
		inputScore = score;
	}

	public void setInputTime(int time) {
		inputTime = time;
	}
}
