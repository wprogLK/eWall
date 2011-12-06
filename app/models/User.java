package models;

import java.util.*;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class User extends Model
{
	private String username;
	private String password;
	private ArrayList<Wall> walls = new ArrayList<Wall>();
	
	public User(String username, String password)
	{
		this.username = username;
		this.password = password;
		
		//this.walls=new ArrayList<Wall>();
		
		Wall defaultWall=new Wall("Default");
	//	defaultWall.save();
		
		this.walls.add(defaultWall);
	}
	
	public Wall getWall()//(String name)
	{
		//TODO
		return walls.get(0);
	}
}
