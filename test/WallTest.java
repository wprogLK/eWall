import static org.junit.Assert.*;

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

}
