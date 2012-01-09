import static org.junit.Assert.*;

import models.WallMedia;
import models.WallText;
import models.WallUser;
import models.WallWall;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;


public class WallTest extends UnitTest{

	@Before
	public void setUp()
	{
		Fixtures.deleteDatabase();
		new WallUser("tester","123");
	}
	
	@Test
	public void simpleWallTest() 
	{	
		WallUser user = WallUser.find("byUsername", "tester").first();
		WallWall wall = user.getWall(0);
		assertEquals(wall.getOwner(),user);
		assertEquals(wall.getName(),"defaultWall");
	}
	
	@Test
	public void addMediaToWall() 
	{	
		WallUser user = WallUser.find("byUsername", "tester").first();
		WallWall wall = user.getWall(0);
		
		WallText text = new WallText("TestText");
		
		wall.addMedia(text);
		
		WallMedia media = wall.getMedia();
		WallText text2 = WallText.find("byTitle", "TestText").first();
		
		assertEquals(media,text2);
		
		assertEquals(text2.getTitle(),"TestText");
	}

}
