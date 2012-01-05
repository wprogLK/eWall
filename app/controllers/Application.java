package controllers;

import controllers.Secure.Security;
import play.*;
import play.mvc.*;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import models.*;
public class Application extends Controller {

    public static void index() 
    {
    
    	 
    	
    	
    	String username = Secure.Security.connected();
    	User currentUser = User.find("byUsername", username).first();
    	render(currentUser);
    }           
    
 
    
}