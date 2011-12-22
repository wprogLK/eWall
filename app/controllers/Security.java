package controllers;

import play.*;
import play.data.validation.Error;
import play.data.validation.Validation;
import play.mvc.*;

import models.*;
public class Security extends Controller
{
	public static void login()
	{
		render();
	}
	
	public static void logout()
	{
		try
		{
			Secure.logout();
		} 
		catch (Throwable e) 
		{
			flash.error("Opps, something goes wrong. Sorry for that. <p> The error was: " + e.getMessage()+ " </p>");		//TODO
			params.flash();
		}
	
		Application.index();
	}
	
	public static void authenticate(String username, String password, boolean remember)
	{
		User userToLogin  = User.find("byUsername", username).first();
		
		if(userToLogin == null)
		{
			Validation.addError("unknownUser", "validation.unknownUser");
			
			if(validation.hasErrors()) 
			{
				params.flash(); // add http parameters to the flash scope
		        validation.keep(); // keep the errors for the next request
		    }
			
			login();
		}
		else if(userToLogin.checkPassword(password))
		{
			try 
			{
				Secure.authenticate(username, password, remember);
				Secure.login();
				Application.index();
			} 
			catch (Throwable e) 
			{
				flash.error("Opps, something goes wrong. Sorry for that. <p> The error was: " + e.getMessage() + " </p>");		//TODO
				params.flash();
			}
		}
		else	//wrong password
		{
		     if(validation.hasErrors()) 
		     {
		    	 params.flash(); 
		         validation.keep();
		         login();
		      }
		}
	}


}