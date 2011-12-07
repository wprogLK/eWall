package models;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import play.db.jpa.Model;

@Entity
public class User extends Model
{
	public String username;
	public String password;
	@OneToMany( mappedBy="owner", cascade = CascadeType.ALL )
	public List<Wall> walls = new ArrayList<Wall>();
	
	public User(String username, String password)
	{
		this.username = username;
		this.password = password;
		
		//this.walls=new ArrayList<Wall>();
		
		Wall defaultWall=new Wall("Default",this);
		defaultWall.save();
		
		this.walls.add(defaultWall);
	}
	
	public Wall getWall()//(String name)
	{
		//TODO
		return walls.get(0);
	}
}
