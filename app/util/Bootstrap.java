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
		Text text = new Text("Hello wall!");
		Wall wall = user.getWall(0);
		wall.addMedia(text);
		user.createWall("MyWall");
	}
}
