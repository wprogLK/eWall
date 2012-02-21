package models;

import interfaces.IWallModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import util.WallTwitterUtil;
import util.WallUnknownWallException;


@Entity
public class WallUser extends Model implements IWallModel
{
	
	//USER
	private String username;
	private String password;
	
	private int indexOfCurrentWall;
	
	//TWITTER
	private String oAuthToken;
	private String oAuthTokenSecret;
	
	//SETTINGS
	private boolean fixHeader;
	private boolean fixFooter;
	
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	private List<WallWall> walls;
	
	public WallUser(String username, String password)
	{
		this.username = username;
		this.password = password;
		
		this.oAuthToken = "";
		this.oAuthTokenSecret = "";
		
		this.walls = new ArrayList<WallWall>();
		
		WallWall wall = new WallWall("defaultWall",this);
		this.walls.add(wall);
		
		this.indexOfCurrentWall = 0;
		
		this.saveAll();
	}
	
	public String getUsername()
	{
		return this.username;
	}
	
	public WallWall nextWall()
	{
		this.nextIndexOfWall();
		this.saveAll();
		
		return this.walls.get(this.indexOfCurrentWall);
		
	}
	
	public WallWall getWall(int index)
	{
		try
		{
			return this.walls.get(index);
		}
		catch(IndexOutOfBoundsException e)
		{
//			if(this.walls.isEmpty())
//			{
//				Validation.addError("noWalls","user.noWalls");
//			}
		
			return null;
		}
		
		
	}
	
	public WallWall getWall(String wallName) throws WallUnknownWallException
	{
		WallWall wallToFind = null;
		
		for(WallWall wall:this.walls)
		{
			if(wall.getName().equals(wallName))
			{
				wallToFind = wall;
				
				break;
			}
		}
		
		return wallToFind;
	}
	
	public void deleteWall(String wallName) throws WallUnknownWallException
	{
		WallWall wallToDelete = this.getWall(wallName);
		
		this.walls.remove(wallToDelete);
		wallToDelete.delete();
		
		this.saveAll();
	}
	
	public boolean hasNext()
	{
		return ((this.walls.size()-1)!=this.indexOfCurrentWall);
	}
	
	public List<WallWall> getAll()
	{
		return this.walls;
	}
	

	private void nextIndexOfWall()
	{		
		if(this.hasNext())
		{
			this.indexOfCurrentWall++;
		}
		else
		{
			this.indexOfCurrentWall = 0;
		}
	}
	
	public void addWall(WallWall wallToAdd)
	{
		this.walls.add(wallToAdd);
		this.saveAll();
	}
	
	@Override
	public void saveAll()
	{
		this.save();
		
		for(WallWall w: this.walls)
		{
			w.save();
		}
	}

	public String getPassword() 
	{
		return this.password;
	}
	
	//SETTINGS:
	public boolean getFixHeader()
	{
		return fixHeader;
	}
	
	public boolean getFixFooter()
	{
		return fixFooter;
	}
	
	public void setFixHeader(boolean value)
	{
		this.fixHeader = value;
		this.saveAll();
	}
	
	public void setFixFooter(boolean value)
	{
		this.fixFooter = value;
		this.saveAll();
	}
	
	public List<WallWall> getWalls()
	{
		return this.walls;
	}
	
	public WallWall getCurrentWall()	
	{
		if(this.indexOfCurrentWall > this.walls.size())
		{
			indexOfCurrentWall = 0;
			this.saveAll();
		}
		
		return this.getWall(indexOfCurrentWall);
	}
	
	public int getIndex()
	{
		return this.indexOfCurrentWall;
	}

	public void setupTwitter(String oAuthToken, String oAuthTokenSecret) 
	{
		this.oAuthToken = oAuthToken;
		this.oAuthTokenSecret = oAuthTokenSecret;
		
		this.saveAll();
	}
	

	public void createTweet(String message)
	{
		Twitter twitter = WallTwitterUtil.setupTwitter(this.oAuthToken, this.oAuthTokenSecret);
		
		Status status = null;
		try {
			status = twitter.updateStatus("Test");
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println("Successfully updated the status to [" + status.getText() + "].");
	}
	
//	public void getHomeTimline()
//	{
//		Twitter twitter = WallTwitterUtil.setupTwitter(this.oAuthToken, this.oAuthTokenSecret);
//		
//		try {
//			ResponseList<Status> list = twitter.getUserTimeline(); //getHomeTimeline();
//			
//			System.out.println("STATUS'S:");
//			for(Status status:list)
//			{
//				System.out.println(status.getText());
//			}
//		} catch (TwitterException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	public ResponseList<Status> getHomeLine() throws TwitterException
//	{
//		Twitter twitter = WallTwitterUtil.setupTwitter(this.oAuthToken, this.oAuthTokenSecret);
//		
//		return twitter.getUserTimeline(); //getHomeTimeline();
//	}
	
	public Twitter getTwitter()
	{
		return WallTwitterUtil.setupTwitter(this.oAuthToken, this.oAuthTokenSecret);
	}
	
	
	
	}

