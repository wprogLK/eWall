import static org.junit.Assert.*;

import models.WallUser;
import models.WallWall;

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
		WallUser directUser = new WallUser("tester","123");
		WallUser indirectUser = WallUser.find("byUsername", "tester").first();
		
		assertEquals(directUser,indirectUser);
	}
	
	@Test
	public void simpleCreateNewWall() 
	{
		WallUser user = new WallUser("tester","123");
		
		WallWall wallDirect = new WallWall("MyWall",user);
		WallWall wallIndirect = WallWall.find("byName", "MyWall").first();
		
		assertEquals(wallDirect,wallIndirect);
		assertEquals(wallIndirect.getOwner(), user);
		assertEquals(wallIndirect.getName(),"MyWall");
	}

}
