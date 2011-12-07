package controllers;

import play.*;
import play.mvc.*;

import java.net.SecureCacheResponse;
import java.security.Security;
import java.util.*;

import models.*;
@With(Secure.class)
public class Application extends Controller 
{
    public static void index() 
    {
        render();
    }
    
    public static void showWall(String wallName)
    {
//    	Wall wall = Wall.find("byWallName", wallName).first();
//    	
//    	Iterator<Media> iterator=wall.getIterator();
//    	Wall w=wall.toT();
    	String userName=Secure.Security.connected();
    	System.out.println("USERNAME: " + userName);
    	
    }

}