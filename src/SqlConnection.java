import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlConnection {

	public static void doConnect() {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			// 接続情報
			con =DriverManager.getConnection(
					"jdbc:mysql://localhost/skbot",
					"root",
					"");

			pstmt = con.prepareStatement("select * from tweets");

			rs = pstmt.executeQuery();

			while (rs.next()) {
		        System.out.println(rs.getString("id"));
		        System.out.println(rs.getInt("username"));
		        System.out.println(rs.getInt("text"));
		    }

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {

			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (pstmt != null) {
		        try {
		          pstmt.close();
		        } catch (SQLException e) {
		          e.printStackTrace();
		        }
		     }
		    if (con != null) {
		        try {
		          con.close();
		        } catch (SQLException e) {
		          e.printStackTrace();
		        }
		    }
		}
	}
}
