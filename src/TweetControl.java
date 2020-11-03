import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class TweetControl {

	public static void allUnRetweet(Twitter twitter) throws TwitterException {
		// 全リツイート解除
		ResponseList<Status> slc = twitter.getUserTimeline();
		for(Status s : slc) {
			twitter.unRetweetStatus(s.getId());
			System.out.println(s.getId());
		}
	    System.exit(0);
	}
}
