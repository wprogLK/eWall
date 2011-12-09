import static org.junit.Assert.*;

import models.Media;
import models.Text;
import models.User;
import models.Wall;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;


public class WallTest extends UnitTest{

	@Before
	public void setUp()
	{
		Fixtures.deleteDatabase();
		new User("tester","123");
	}
	
	@Test
	public void simpleWallTest() 
	{	
		User user = User.find("byUsername", "tester").first();
		Wall wall = user.getWall();
		assertEquals(wall.getOwner(),user);
		assertEquals(wall.getName(),"defaultWall");
	}
	
	@Test
	public void addMediaToWall() 
	{	
		User user = User.find("byUsername", "tester").first();
		Wall wall = user.getWall();
		
		Text text = new Text("TestText");
		
		wall.addMedia(text);
		
		Media media = wall.getMedia();
		Text text2 = Text.find("byTitle", "TestText").first();
		
		assertEquals(media,text2);
		
		assertEquals(text2.getTitle(),"TestText");
	}

}
