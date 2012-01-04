/**
 * 
 */
package util;

/**
 * @author lukas
 *
 */
public abstract class UnknownWallException extends AbstractEWallEception
{
	public UnknownWallException(String wallName)
	{
		super("A wall with the name " + wallName + "doesn't exist!");
	}
}
