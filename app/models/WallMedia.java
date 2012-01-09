package models;

import java.util.ArrayList;
import java.util.List;

import interfaces.IWallModel;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import play.db.jpa.Model;

@Entity
public abstract class WallMedia extends Model implements IWallModel
{
	
	public WallMedia()
	{

	}
}
