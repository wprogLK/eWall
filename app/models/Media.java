package models;

import java.util.ArrayList;
import java.util.List;

import interfaces.IModel;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import play.db.jpa.Model;

@Entity
public abstract class Media extends Model implements IModel
{
	
	public Media()
	{

	}
}
