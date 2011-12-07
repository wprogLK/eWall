package bootstrap;


import models.*;
import play.jobs.Job;
import play.jobs.OnApplicationStart;

@OnApplicationStart
public class Bootstrap extends Job
{
	public void doJob()
	{
		User user = new User("Bob","bob");
		user.save();
		
		User defaultUser = new User("Default","Default"); 	//The default user (if no user is logged in)
		Wall defaultWall=defaultUser.getWall();
		
		Text text=new Text("Test","Hello world! \n new line");
		
		defaultWall.add(text);
		Media m=(Media) defaultWall.getIterator().next();
//		try
//		{
//			Text t=m.toT();
//		}
//		catch(ClassCastException e)
//		{
//			System.out.println("FEHLER");
//		}
		
	}
}
