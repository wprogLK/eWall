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
public class WallWall extends WallMedia
{
	private String name;
	private int indexOfCurrentMedia = -1;

	@ManyToOne
	private WallUser owner;

	@ManyToMany(cascade=CascadeType.ALL)
	private List<WallMedia> medias;
	
	public WallWall(String name, WallUser owner) 
	{
		super();
		
		this.name = name;
		this.owner = owner;
		this.owner.addWall(this);
		
		this.medias = new ArrayList<WallMedia>();
	}

	@Override
	public void saveAll() 
	{
		this.save();
		this.owner.save();
		
		for(WallMedia m:this.medias)
		{
			m.save();
		}
	}
	
	public String getName()
	{
		return this.name;
	}
	
	public WallUser getOwner()
	{
		return this.owner;
	}
	
	public WallMedia nextMedia()
	{
		this.nextIndexOfMedia();
		return this.medias.get(this.indexOfCurrentMedia);
	}

	public WallMedia getMedia(int index)
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
	
	public List<WallMedia> getAll()
	{
		return this.medias;
	}
	
	public void addMedia(WallMedia mediaToAdd)
	{
		this.medias.add(mediaToAdd);
		this.saveAll();
	}
	
	public WallMedia getMedia()
	{
		//TODO
		return this.medias.get(0);
	}
	
	public WallMedia getCurrentMedia()	//TODO Test this!
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
