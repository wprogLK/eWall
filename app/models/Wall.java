package models;

import java.util.ArrayList;
import java.util.Iterator;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public class Wall<T> extends Media implements IMedia
{
	private ArrayList<Media> medias = new ArrayList<Media>();
//	private ArrayList<Text> listText=new ArrayList<Text>();
//	private ArrayList<Picture> listPicture=new ArrayList<Picture>();
//	private ArrayList<Wall> listWall=new ArrayList<Wall>();
	
	private String wallName;
	
	public Wall(String name)
	{
		super(name, MediaType.WALL);
		this.wallName = name;
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
