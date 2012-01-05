package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Wall extends Media
{
	private String name;
	private int indexOfCurrentMedia = -1;

	@ManyToOne
	private User owner;

	@ManyToMany(cascade=CascadeType.ALL)
	private List<Media> medias;
	
	public Wall(String name, User owner) 
	{
		super();
		
		this.name = name;
		this.owner = owner;
		this.owner.addWall(this);
		
		this.medias = new ArrayList<Media>();
	}

	@Override
	public void saveAll() 
	{
		this.save();
		this.owner.save();
		
		for(Media m:this.medias)
		{
			m.save();
		}
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public User getOwner()
	{
		return this.owner;
	}
	
	public Media nextMedia()
	{
		this.nextIndexOfMedia();
		return this.medias.get(this.indexOfCurrentMedia);
	}

	public Media getMedia(int index)
	{
		return this.medias.get(index);
	}
	
	public boolean hasNext()
	{
		return (this.medias.size()-1==this.indexOfCurrentMedia);
	}
	
	private void nextIndexOfMedia()
	{
		if(this.hasNext())
		{
			this.indexOfCurrentMedia = 0;
			//TODO Maybem throw a "message"
		}
		else
		{
			this.indexOfCurrentMedia++;
		}
	}
	
	public List<Media> getAll()
	{
		return this.medias;
	}
	
	public void addMedia(Media mediaToAdd)
	{
		this.medias.add(mediaToAdd);
		this.saveAll();
	}
	
	public Media getMedia()
	{
		//TODO
		return this.medias.get(0);
	}
	
	public Media getCurrentMedia()	//TODO Test this!
	{
		if(indexOfCurrentMedia<0 || indexOfCurrentMedia>this.medias.size())
		{
			return this.nextMedia();
		}
		else
		{
			return this.getMedia(indexOfCurrentMedia);
		}
	}
	
}
