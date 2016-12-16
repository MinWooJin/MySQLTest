import java.sql.*;

public class DatabaseConnection {
	public Connection conn;
	public Statement stmt = null;

	public String removeWARN = "?autoReconnect=true&useSSL=false";
	public String url = "jdbc:mysql://1.255.51.181:3306/mwjin_test" + removeWARN;
	public String id = "alsdn653";
	public String pw = "Jin842862@";

	public DatabaseConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void getConnection() {
		try {
			conn = DriverManager.getConnection(url, id, pw);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			if (stmt != null) {
				stmt.close();
			}
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateQuery(String qr) {
		try {
			stmt = conn.createStatement();
			stmt.executeUpdate(qr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void updateQuery(PreparedStatement pstmt, String pid, String sid, String date, String prod_sum, String prod_det) {
		try {
			pstmt.setString(1, pid);
			pstmt.setString(2, sid);
			pstmt.setDate(3, java.sql.Date.valueOf(date));
			pstmt.setString(4, prod_sum);
			pstmt.setString(5, prod_det);

			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*public ResultSet getQuery(String qr) {
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(qr);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rs;
	}*/
}
