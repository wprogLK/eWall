package models;

import interfaces.IModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class User extends Model implements IModel
{
	private String username;
	private String password;
	
	private int indexOfCurrentWall = -1;
	
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
	
	public Wall getWall(int i)
	{
		return this.walls.get(i);
	}

	private void nextIndexOfWall()
	{
		if(this.walls.size()-1 == this.indexOfCurrentWall)
		{
			this.indexOfCurrentWall = 0;
			//TODO Maybem throw a "message"
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
	
	public Wall createWall(String wallName)
	{
		Wall wall = new Wall(wallName,this);
		this.addWall(wall);
		return wall;
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
}
