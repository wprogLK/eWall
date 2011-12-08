package models;

import interfaces.IModel;

import javax.persistence.Entity;

import play.db.jpa.Model;

@Entity
public abstract class Media extends Model implements IModel
{
	public Media()
	{
		
	}
}
