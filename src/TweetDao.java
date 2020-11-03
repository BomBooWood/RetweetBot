import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import twitter4j.Status;

public class TweetDao {

	public static Connection con = null;
	public static PreparedStatement pstmt = null;
	public static ResultSet rs = null;

	public TweetDao() throws SQLException {
		con = DriverManager.getConnection(
				"jdbc:mysql://localhost/skbot",
				"root",
				"");
	}

	public List<TweetDto> findAll() throws SQLException {
		List<TweetDto> li = new ArrayList<>();
		pstmt  = con.prepareStatement("select * from tweets");
		rs = pstmt.executeQuery();

		while (rs.next()) {
			TweetDto tweet = new TweetDto(rs.getLong("id"),
									rs.getString("username"),
									rs.getString("text"));
			li.add(tweet);
	    }

		return li;
	}

	public void insertTweetList(Status tweet) throws SQLException {
		// エスケープ処理
		String text = doEscaping(tweet.getText());

		pstmt  = con.prepareStatement("insert into tweets (id, username, text) values (" +
									   tweet.getId() + ", '" +
									   tweet.getUser().getScreenName() + "', '" +
									   text + "')");

		int num = pstmt.executeUpdate();
	}

	private String doEscaping(String text) { return text.replaceAll("'", "''"); }

}
