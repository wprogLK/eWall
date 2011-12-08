package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Wall extends Media
{
	private String name;
	@ManyToOne
	private User owner;

	public Wall(String name, User owner) 
	{
		super();
		this.name = name;
		this.owner = owner;
	}
}
