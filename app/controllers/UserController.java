package controllers;

import java.util.List;

import com.sun.net.httpserver.Authenticator.Success;

import models.User;
import models.Wall;
import play.data.validation.Validation;
import play.mvc.Controller;
import play.mvc.With;
import util.UnknownWallException;

@With(Secure.class)
public class UserController extends Controller
{
	public static void profile()
	{
		String username = Secure.Security.connected();
    	User currentUser = User.find("byUsername", username).first();
		render(currentUser);
	}
	
	public static void settings()
	{
		String username = Secure.Security.connected();
    	User currentUser = User.find("byUsername", username).first();
    	List<Wall> walls = currentUser.getWalls();
    	
		render(currentUser, walls);
	}
	
	public static void changeSettings(boolean headerFix, boolean footerFix)
	{
		String username = Secure.Security.connected();
    	User currentUser = User.find("byUsername", username).first();
    	
    	currentUser.setFixFooter(footerFix);
    	currentUser.setFixHeader(headerFix);
    	
		settings();
	}
	
	public static void createNewWall(String wallName)
	{
		String username = Secure.Security.connected();
		User currentUser = User.find("byUsername", username).first();
		
		Wall wall = new Wall(wallName,currentUser);
		
		settings();
	}
	
	public static void deleteWall(String wallToDelete)
	{
		String username = Secure.Security.connected();
		User currentUser = User.find("byUsername", username).first();
		
		try 
		{
			currentUser.deleteWall(wallToDelete);
		} 
		catch (UnknownWallException e) 
		{
			Validation.addError("unknownUser", "user.unknownWallToDelete");
		}
	
		
		settings();
	}
	
	public static void myWalls()
	{
		String username = Secure.Security.connected();
		User currentUser = User.find("byUsername", username).first();
		
		int indexOfWall = currentUser.getIndex();
		
		if(validation.hasErrors())
		{
			params.flash(); 
	        validation.keep();
		}
		
		render(currentUser, indexOfWall);
	}
	
	public static void getNextWall()
	{
		String username = Secure.Security.connected();
		User currentUser = User.find("byUsername", username).first();
		
		currentUser.nextWall();
		
		myWalls();
	}
	
	public static void createATweet(String message)
	{
		String username = Secure.Security.connected();
		User currentUser = User.find("byUsername", username).first();
		
		currentUser.createTweet(message);
		
		myWalls();
	}
	
	public static void getHomeTimeline()
	{
		String username = Secure.Security.connected();
		User currentUser = User.find("byUsername", username).first();
		
		
		currentUser.getHomeTimline();
		
		myWalls();
	}
}
