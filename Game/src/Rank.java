import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Rank {
	private String url = "jdbc:oracle:thin:@192.168.0.3:1521/xepdb1";
//	private String url = "jdbc:oracle:thin:@localhost:1521/orcl";
	private String sql = "select * from HONEYBEE order by SCORE, TIME desc";
	private String sql2 = "insert into HONEYBEE(NAME, SCORE, TIME) values(?, ?, ?)";
	private Connection connection;
	private Statement statement;
	private PreparedStatement pstatement;
	private ResultSet resultSet;
	
	private String name;
	private String score;
	private String time;
	
	public Rank() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection connection = DriverManager.getConnection(url, "ACORN","newlec"); 
//			connection = DriverManager.getConnection(url, "SCOTT","tiger"); 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	public void showRank() {
		try {
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);
			
			for(int i = 0; resultSet.next() && i < 5; i++) {
				name = resultSet.getString("NAME");
				score = resultSet.getString("SCORE");
				time = resultSet.getString("TIME");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void storeRank() {
		try {
			pstatement = connection.prepareStatement(sql2);
			pstatement.setString(1, name);
			pstatement.setInt(2, Integer.parseInt(score));
			pstatement.setInt(3, Integer.parseInt(time));
			pstatement.executeUpdate(sql2);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
