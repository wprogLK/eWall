import static org.junit.Assert.*;

import models.User;
import models.Wall;

import org.junit.Before;
import org.junit.Test;

import play.test.Fixtures;
import play.test.UnitTest;


public class UserTest extends UnitTest{

	@Before
	public void setUp()
	{
		  Fixtures.deleteDatabase();
	}
	
	@Test
	public void simpleUserTest() 
	{
		User directUser = new User("tester","123");
		User indirectUser = User.find("byUsername", "tester").first();
		
		assertEquals(directUser,indirectUser);
	}
	
	@Test
	public void simpleCreateNewWall() 
	{
		User user = new User("tester","123");
		
		Wall wallDirect = user.createWall("MyWall");
		Wall wallIndirect = Wall.find("byName", "MyWall").first();
		
		assertEquals(wallDirect,wallIndirect);
		assertEquals(wallIndirect.getOwner(), user);
		assertEquals(wallIndirect.getName(),"MyWall");
	}

}
