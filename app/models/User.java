package models;

import interfaces.IModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import controllers.Secure;
import controllers.Secure.Security;

import play.data.validation.Check;
import play.data.validation.CheckWith;
import play.data.validation.Required;
import play.data.validation.Validation;
import play.db.jpa.Model;
import util.UnknownWallException;

@Entity
public class User extends Model implements IModel
{
	
	//USER
	private String username;
	private String password;
	
	private int indexOfCurrentWall = -1;
	
	//SETTINGS
	private boolean fixHeader;
	private boolean fixFooter;
	
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	private List<Wall> walls;
	
	public User(String username, String password)
	{
		this.username = username;
		this.password = password;
		
		this.walls = new ArrayList<Wall>();
		
		Wall wall = new Wall("defaultWall",this);
		this.walls.add(wall);
		
		this.saveAll();
	}
	
	public String getUsername()
	{
		return this.username;
	}
	
	public Wall nextWall()
	{
		this.nextIndexOfWall();
		return this.walls.get(this.indexOfCurrentWall);
	}
	
	public Wall getWall(int index)
	{
		return this.walls.get(index);
	}
	
	public Wall getWall(String wallName) throws UnknownWallException
	{
		Wall wallToFind = null;
		
		for(Wall wall:this.walls)
		{
			if(wall.getName().equals(wallName))
			{
				wallToFind = wall;
				
				break;
			}
		}
		
		return wallToFind;
		
	}
	
	public void deleteWall(String wallName) throws UnknownWallException
	{
		Wall wallToDelete = this.getWall(wallName);
		
		this.walls.remove(wallToDelete);
		wallToDelete.delete();
		
		this.saveAll();
	}
	
	public boolean hasNext()
	{
		return (this.walls.size()-1==this.indexOfCurrentWall);
	}
	
	public List<Wall> getAll()
	{
		return this.walls;
	}
	

	private void nextIndexOfWall()
	{
		if(this.hasNext())
		{
			this.indexOfCurrentWall = 0;
			//TODO Maybe throw a "message"
		}
		else
		{
			this.indexOfCurrentWall++;
		}
	}
	
	public void addWall(Wall wallToAdd)
	{
		this.walls.add(wallToAdd);
		this.saveAll();
	}
	
	@Override
	public void saveAll()
	{
		this.save();
		
		for(Wall w: this.walls)
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
	
	public List<Wall> getWalls()
	{
		return this.walls;
	}
}
