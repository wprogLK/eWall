package models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class WallText extends WallMedia
{
	
	private String title;
	
	public WallText(String title)
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
