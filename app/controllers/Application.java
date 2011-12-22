package controllers;

import controllers.Secure.Security;
import play.*;
import play.mvc.*;

import models.*;
public class Application extends Controller {

    public static void index() 
    {
    	String username = Secure.Security.connected();
    	User user = User.find("byUsername", username).first();
    	boolean isLoggedIn = Security.isConnected();
    	
    	render(user, isLoggedIn);
    }
   
    
    

}