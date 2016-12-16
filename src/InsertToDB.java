import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class InsertToDB extends Thread {
	private PrepareKeys prepare = null;
	private int num;
	private int range;

	public InsertToDB(PrepareKeys prep, int num, int range) {
		this.prepare = prep;
		this.range = range;
		this.num = num * range;
	}

	public void run() {
		PreparedStatement pstmt = null;
		DatabaseConnection db = new DatabaseConnection();
		String sql = "INSERT INTO test VALUES(?,?,?,?,?)";

		db.getConnection();
		try {
			pstmt = db.conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (int i = num; i < num + range; i++) {
			db.updateQuery(pstmt, prepare.getPid(i), prepare.getSid(), prepare.getDate(), prepare.getSummary(), prepare.getDetail());
		}
		db.closeConnection();
	}

	public static void main(String[] args) {
		ArrayList<Thread> threads = new ArrayList<>();
		PrepareKeys prep = new PrepareKeys();
		int threadNum = 10;
		int range = 10000000 / threadNum;

		for (int i = 0; i < threadNum; i++) {
			Thread t = new InsertToDB(prep, i, range);
			t.start();
			threads.add(t);
		}

		for (int i = 0; i < threads.size(); i++) {
			Thread t = threads.get(i);
			try {
				t.join();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
