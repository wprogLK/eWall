package controllers;

import models.User;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class UserController extends Controller
{
	public static void profile()
	{
		String username = Secure.Security.connected();
    	User currentUser = User.find("byUsername", username).first();
		render(currentUser);
	}
	
	public static void settings()
	{
		String username = Secure.Security.connected();
    	User currentUser = User.find("byUsername", username).first();
    	
		render(currentUser);
	}
	
	public static void changeSettings(boolean headerFix, boolean footerFix)
	{
		String username = Secure.Security.connected();
    	User currentUser = User.find("byUsername", username).first();
    	
    	currentUser.setFixFooter(footerFix);
    	currentUser.setFixHeader(headerFix);
    	
		settings();
	}
}
