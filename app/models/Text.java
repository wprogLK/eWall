package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Text extends Media
{
	
	private String title;
	
	public Text(String title)
	{
		this.title = title;
	}
	
	public String getTitle()
	{
		return this.title;
	}
	
	@Override
	public void saveAll() 
	{
		// TODO Auto-generated method stub
		
	}

}
