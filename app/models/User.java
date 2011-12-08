package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import play.db.jpa.Model;

@Entity
public class User extends Model
{
	private String username;
	private String password;
	
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
	private List<Wall> walls = new ArrayList<Wall>();
	
	public User(String username, String password)
	{
		this.username = username;
		this.password = password;
		
		//this.walls=
		
		this.save();
	}
}
