package controllers;

import play.*;
import play.data.validation.Error;
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
			 validation.required("username",username).message("validation.unknownUser");
		     validation.required("password",password).message("validation.wrongPassword");
		     validation.required("userTo",userToLogin).message("validation.unknownUser");
		     
		   Error error =   validation.error("field");
		   
		     if(validation.hasErrors()) {
		    	 params.flash(); // add http parameters to the flash scope
		         validation.keep(); // keep the errors for the next request
		         login();
		      }
			
		
			System.out.println("ERROR: " + flash.toString());
			login();
		}
		else if(userToLogin.checkPassword(password))
		{
			try 
			{
				Secure.authenticate(username, password, remember);
				Secure.login();
			} 
			catch (Throwable e) 
			{
				flash.error("Opps, something goes wrong. Sorry for that. <p> The error was: " + e.getMessage() + " </p>");		//TODO
				params.flash();
			}
		}
		else
		{
			flash.error("Wrong password!");
			params.flash();
		}
		
		Application.index();
	}


}