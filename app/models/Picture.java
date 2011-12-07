package models;

import javax.persistence.Entity;

public class Picture <T> extends Media
{

	public Picture(String name, String url, int height, int width) 
	{
		super(name, height, width, MediaType.PICTURE);
	}

	@Override
	public  Picture<T> toT() 
	{
		System.out.println("RETURN Picture");
		return this;
	}
	
}
