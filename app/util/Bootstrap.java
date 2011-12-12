package util;

import play.jobs.Job;
import play.jobs.OnApplicationStart;
import models.*;

@OnApplicationStart
public class Bootstrap extends Job
{
	public void doJob()
	{
		User user = new User("bob","bob");
		User user2 = new User("glob","glob");
		Text text = new Text("Hello wall(1)!");
		Text text2 = new Text("Hello wall(2)!");
		Wall wall = user.getWall(0);
		wall.addMedia(text);
		wall.addMedia(text2);
		user.createWall("MyWall");
	}
}
