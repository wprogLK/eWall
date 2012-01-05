package util;

import play.i18n.Lang;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import models.*;

@OnApplicationStart
public class Bootstrap extends Job
{
	public void doJob()
	{
		User lukas = new User("lukas","lukas");
		
		lukas.setupTwitter("220058348-KPaT8hkUK2AEJu7uzhQQBGzSPJ8Avk9eN6LoW1Hd","O2tizT5EcXUH5zId5J8SPNPpqwKcYp7Jufhuy4puLoQ");
		
		User user = new User("bob","bob");
		User user2 = new User("glob","glob");
		Text text = new Text("Hello wall(1)!");
		Text text2 = new Text("Hello wall(2)!");
		Wall wall = user.getWall(0);
		wall.addMedia(text);
		wall.addMedia(text2);
		new Wall("MyWall",user);
	}
	
}
