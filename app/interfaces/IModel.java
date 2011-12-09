package interfaces;

public interface IModel 
{
	/**
	 * Call the .save() method of every object which is in a relationship with the class 
	 */
	public void saveAll();
}
