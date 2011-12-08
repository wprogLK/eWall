import static org.junit.Assert.*;

import models.User;

import org.junit.Before;
import org.junit.Test;

import play.test.UnitTest;


public class UserTest extends UnitTest{

	@Before
	public void setUp()
	{
		
	}
	
	@Test
	public void simpleUserTest() 
	{
		User directUser = new User("tester","123");
		User indirectUser = User.find("byUsername", "tester").first();
		
		assertEquals(directUser,indirectUser);
	}

}
