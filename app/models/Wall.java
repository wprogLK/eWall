package models;

import java.util.ArrayList;
import java.util.Iterator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Wall<T> extends Media
{
	@ManyToOne( cascade = CascadeType.ALL,targetEntity=User.class )
	public User owner;

	public ArrayList<Media> medias = new ArrayList<Media>();
//	private ArrayList<Text> listText=new ArrayList<Text>();
//	private ArrayList<Picture> listPicture=new ArrayList<Picture>();
//	private ArrayList<Wall> listWall=new ArrayList<Wall>();
	
	public String wallName;
	
	public Wall(String name, User owner)
	{
		super(name, MediaType.WALL);
		this.wallName = name;
		this.owner=owner;
	}
	
	public void add(Media media)
	{
		medias.add(media);
	}
	
	public Iterator<Media> getIterator()
	{
		return this.medias.iterator();
	}

	@Override
	public  Wall<T> toT() 
	{
		System.out.println("RETURN WALL");
		return this;
	}
	
	

}
