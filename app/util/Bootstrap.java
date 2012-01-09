package util;

import models.*;
import play.i18n.Lang;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


@OnApplicationStart
public class Bootstrap extends Job
{
	public void doJob()
	{
		WallUser lukas = new WallUser("lukas","lukas");
		
		lukas.setupTwitter("220058348-KPaT8hkUK2AEJu7uzhQQBGzSPJ8Avk9eN6LoW1Hd","O2tizT5EcXUH5zId5J8SPNPpqwKcYp7Jufhuy4puLoQ");
		
		WallUser user = new WallUser("bob","bob");
		WallUser user2 = new WallUser("glob","glob");
		WallText text = new WallText("Hello wall(1)!");
		WallText text2 = new WallText("Hello wall(2)!");
		WallWall wall = user.getWall(0);
		wall.addMedia(text);
		wall.addMedia(text2);
		new WallWall("MyWall",user);
	}
	
}
