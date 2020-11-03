import java.util.ArrayList;
import java.util.List;

public class TweetDto {

	private long id;
	private String username;
	private String text;

	public TweetDto(long id, String username, String text) {
		this.id = id;
		this.username = username;
		this.text = text;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getId() {
		return this.id;
	}

	public void setName(String name) {
		this.username = name;
	}

	public String getUserName() {
		return this.username;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getText() {
		return this.text;
	}

	public static List<Long> createIdList(List<TweetDto> li){
		List<Long> IdList = new ArrayList<Long>();
		for(TweetDto tweet : li) {
			IdList.add(tweet.getId());
		}
		return IdList;
	}

}
