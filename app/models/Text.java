package models;

import javax.persistence.Entity;


@Entity
public class Text<T> extends Media
{
	public String text;
	
	public Text(String name, String text) 
	{
		super(name, MediaType.TEXT);
		this.text = text;
	}
	
	public String getText()
	{
		return this.text;
	}

	@Override
	public  Text<T> toT() 
	{
		System.out.println("RETURN Text");
		return this;
	}
	
}
