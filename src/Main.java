import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;

public class Main {

	public static void main(String[] args) throws TwitterException {

		Twitter twitter = TwitterFactory.getSingleton();

		Query query = new Query();

		// TwitterAPIクエリ情報
		query.setQuery("skateboarding min_faves:100");
		query.setResultType(Query.MIXED);
		query.setCount(50);

		QueryResult result = null;

		try {
			while (true) {

				// ツイートを検索
				result = twitter.search(query);

				// ツイート取得 & 重複削除
				List<Status> li = result.getTweets();
				List<Status> tweets = new ArrayList<Status>(new HashSet<>(li));

				// DB接続しリツイート済みのツイートを取得
				TweetDao dao = new TweetDao();
				List<TweetDto> retweetedTweets = dao.findAll();
				// ツイート固有のIDリストを作成
				List<Long> IdList = TweetDto.createIdList(retweetedTweets);

				// 重複帽子処理 & リツイート
				for (Status tweet : tweets) {
					if (!(IdList.contains(tweet.getId()))) {
						// リツイート
						twitter.retweetStatus(tweet.getId());
						// ツイートをDBに保存
						dao.insertTweetList(tweet);
					}
				}

				if (!result.hasNext())
					break;
				query = result.nextQuery();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				TweetDao.con.close();
				TweetDao.pstmt.close();
				TweetDao.rs.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}

		}
	}
}
