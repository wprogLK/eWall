package controllers;

import java.util.List;

import com.sun.net.httpserver.Authenticator.Success;

import models.WallUser;
import models.WallWall;
import play.data.validation.Validation;
import play.mvc.Controller;
import play.mvc.With;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.User;
import util.WallUnknownWallException;

@With(Secure.class)
public class WallUserController extends Controller
{
	public static void profile()
	{
		String username = Secure.Security.connected();
    	WallUser currentUser = WallUser.find("byUsername", username).first();
		render(currentUser);
	}
	
	public static void settings()
	{
		String username = Secure.Security.connected();
    	WallUser currentUser = WallUser.find("byUsername", username).first();
    	List<WallWall> walls = currentUser.getWalls();
    	
		render(currentUser, walls);
	}
	
	public static void changeSettings(boolean headerFix, boolean footerFix)
	{
		String username = Secure.Security.connected();
    	WallUser currentUser = WallUser.find("byUsername", username).first();
    	
    	currentUser.setFixFooter(footerFix);
    	currentUser.setFixHeader(headerFix);
    	
		settings();
	}
	
	public static void createNewWall(String wallName)
	{
		String username = Secure.Security.connected();
		WallUser currentUser = WallUser.find("byUsername", username).first();
		
		WallWall wall = new WallWall(wallName,currentUser);
		
		settings();
	}
	
	public static void deleteWall(String wallToDelete)
	{
		String username = Secure.Security.connected();
		WallUser currentUser = WallUser.find("byUsername", username).first();
		
		try 
		{
			currentUser.deleteWall(wallToDelete);
		} 
		catch (WallUnknownWallException e) 
		{
			Validation.addError("unknownUser", "user.unknownWallToDelete");
		}
	
		
		settings();
	}
	
	public static void myWalls()
	{
		String username = Secure.Security.connected();
		WallUser currentUser = WallUser.find("byUsername", username).first();
		
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
		WallUser currentUser = WallUser.find("byUsername", username).first();
		
		currentUser.nextWall();
		
		myWalls();
	}
	
	public static void createATweet(String message)
	{
		String username = Secure.Security.connected();
		WallUser currentUser = WallUser.find("byUsername", username).first();
		
		currentUser.createTweet(message);
		
		myWalls();
	}
	
	public static void getHomeTimeline() throws TwitterException
	{
		String username = Secure.Security.connected();
		WallUser currentUser = WallUser.find("byUsername", username).first();
		
		
		//currentUser.getHomeTimline();
		
//			ResponseList<Status> homeLine = currentUser.getHomeLine();
		
		//myWalls();
		
//		String username = Secure.Security.connected();
//		WallUser currentUser = WallUser.find("byUsername", username).first();
//		
//		try {
//			List<Status> statusList = currentUser.getHomeLine();
//			
//			int indexOfWall = currentUser.getIndex();
//			
//			if(validation.hasErrors())
//			{
//				params.flash(); 
//		        validation.keep();
//			}
//			
//			render(currentUser, indexOfWall, statusList);
//		} catch (TwitterException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		Twitter twitter = currentUser.getTwitter();
		
		List<Status> statusList = twitter.getHomeTimeline();
		statusList.get(0).getAccessLevel();
				statusList.get(0).getAnnotations();
				statusList.get(0).getContributors();
				statusList.get(0).getCreatedAt();
				statusList.get(0).getGeoLocation();
				statusList.get(0).getHashtagEntities();
				statusList.get(0).getId();
				statusList.get(0).getInReplyToScreenName();
				statusList.get(0).getInReplyToStatusId();
				statusList.get(0).getInReplyToUserId();
				statusList.get(0).getMediaEntities();
				statusList.get(0).getPlace();
				statusList.get(0).getRateLimitStatus();
				statusList.get(0).getRetweetCount();
				statusList.get(0).getRetweetedStatus();
				statusList.get(0).getSource();
				statusList.get(0).getURLEntities();
				statusList.get(0).getUser().getProfileImageURL();
				statusList.get(0).getUserMentionEntities();
				statusList.get(0).isFavorited();
				statusList.get(0).isRetweet();
				statusList.get(0).isRetweetedByMe();
				statusList.get(0).isTruncated();
			
		int indexOfWall = currentUser.getIndex();
		if(validation.hasErrors())
		{
			params.flash(); 
	        validation.keep();
		}
		
		render(currentUser, indexOfWall, statusList);
	}
	
	public static void bigProfile(long twitterUserID) throws TwitterException
	{
		System.out.println("ID is: " + twitterUserID);
		String username = Secure.Security.connected();
		WallUser currentUser = WallUser.find("byUsername", username).first();
		Twitter twitter = currentUser.getTwitter();
		
		User twitterUser = twitter.showUser(twitterUserID);
		
		
		render(twitterUser);
	}
}
