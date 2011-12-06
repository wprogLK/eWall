package models;

import play.db.jpa.Model;

public abstract class Media extends Model
{
	private final int DEFAULT_HEIGHT = 500;
	private final int DEFAULT_WIDTH = 500;
	
	private final int HEIGHT_PER_CLICK = 100;
	private final int WIDTH_PER_CLICK = 100;
	
	private final int MAX_HEIGHT=2000;
	private final int MAX_WIDTH=2000;
	
	private final int DEFAULT_CLICK_COUNTER=0;
	
	private int height = DEFAULT_HEIGHT;
	private int width = DEFAULT_WIDTH;
	
	private String name;
	private MediaType type;
	
	
//	protected boolean isPicture=false;
//	protected boolean isText=false;
//	protected boolean isWall=false;
	
	private int clickCounter=DEFAULT_CLICK_COUNTER;
	
	public Media(String name, MediaType type)
	{
		this.name = name;
		this.type=type;
		
		this.height = DEFAULT_HEIGHT;
		this.width = DEFAULT_WIDTH;
	}
	
	public Media(String name, int height, int width, MediaType type)
	{
		this.name = name;
		this.type=type;
		
		this.height = height;
		this.width = width;
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
