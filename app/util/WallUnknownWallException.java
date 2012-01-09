/**
 * 
 */
package util;

/**
 * @author lukas
 *
 */
public abstract class WallUnknownWallException extends WallAbstractEWallEception
{
	public WallUnknownWallException(String wallName)
	{
		super("A wall with the name " + wallName + "doesn't exist!");
	}
}
