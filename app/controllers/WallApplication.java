package controllers;

import models.WallUser;
import play.mvc.Controller;
public class WallApplication extends Controller {

    public static void index() 
    {
    	String username = Secure.Security.connected();
    	WallUser currentUser = WallUser.find("byUsername", username).first();
    	render(currentUser);
    }           
    
 
    
}