package controllers;

import play.*;
import play.mvc.*;

import java.util.*;

import models.*;

public class Application extends Controller 
{
    public static void index() 
    {
        render();
    }
    
    public static void showWall(String wallName)
    {
    	//Wall wall = Wall.find("byWallName", wallName).first();
    	
    	//Iterator<Media> iterator=wall.getIterator();
//    	Wall w=wall.toT();

    	
    }

}