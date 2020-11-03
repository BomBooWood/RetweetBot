import java.util.Timer;
import java.util.TimerTask;

import twitter4j.TwitterException;

public class PeriodicExecution {

	public static void main(String[] args) {

		Timer timer = new Timer();

		TimerTask task = new TimerTask() {
		int count = 0;
			public void run()  {
				try {
					Main.main(args);
				} catch (TwitterException e) {
					e.printStackTrace();
				}
				count++;
		        System.out.println(count + "回目のタスクが実行されました。");
			}
		};

		//3時間間隔で実行
		timer.scheduleAtFixedRate(task, 1000, 10800000);

	}

}
