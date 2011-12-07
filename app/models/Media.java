package models;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public abstract class Media extends Model
{
	public final int DEFAULT_HEIGHT = 500;
	public final int DEFAULT_WIDTH = 500;
	
	public final int HEIGHT_PER_CLICK = 100;
	public final int WIDTH_PER_CLICK = 100;
	
	public final int MAX_HEIGHT=2000;
	public final int MAX_WIDTH=2000;
	
	
	public int height = DEFAULT_HEIGHT;
	public int width = DEFAULT_WIDTH;
	
	public String name;
	public MediaType type;
	
	
//	protected boolean isPicture=false;
//	protected boolean isText=false;
//	protected boolean isWall=false;
	
	public int clickCounter;//=DEFAULT_CLICK_COUNTER;
	
	public Media(String name, MediaType type)
	{
		this.name = name;
		this.type=type;
		
		this.height = DEFAULT_HEIGHT;
		this.width = DEFAULT_WIDTH;
		this.clickCounter=0;
	}
	
	public Media(String name, int height, int width, MediaType type)
	{
		this.name = name;
		this.type=type;
		
		this.height = height;
		this.width = width;
		
		this.clickCounter=0;
	}

		
	public void click()
	{
		this.clickCounter++;
		this.height+=HEIGHT_PER_CLICK;
		this.width+=WIDTH_PER_CLICK;
	}
	
	public MediaType getType()
	{
		return this.type;
	}
	
	public abstract <T> T toT() throws ClassCastException;
	
	
	
	
}
