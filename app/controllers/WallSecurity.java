package controllers;

import play.*;
import play.data.validation.Error;
import play.data.validation.Validation;
import play.mvc.*;

import models.*;
public class WallSecurity extends Controller
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
	
		WallApplication.index();
	}
	
	public static void authenticate(String username, String password, boolean remember)
	{
		WallUser userToLogin  = WallUser.find("byUsername", username).first();
		
		if(userToLogin == null)
		{
			Validation.addError("unknownUser", "security.authenticate.unknownUser");
			
			if(validation.hasErrors()) 
			{
				params.flash(); // add http parameters to the flash scope
		        validation.keep(); // keep the errors for the next request
		    }
			
			login();
		}
		else if(checkPassword(password, userToLogin.getPassword()))
		{
			try 
			{
				Secure.authenticate(username, password, remember);
				Secure.login();
				WallApplication.index();
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

	public static void register()
	{
		render();
	}
	
	public static void doRegister(String username, String password, String confirmPassword, boolean remember)
	{
		if(equalsPassword(password, confirmPassword) && usernameAllowed(username) && usernameNotAlreadyExist(username))
		{
			new WallUser(username, password);
			authenticate(username, password, remember);
		}
		else
		{
			register();
		}
	}
	
	/**
	 * for registration of a new user
	 * @param password
	 * @param confirmPassword
	 * @return
	 * @see #checkPassword
	 */
	private static boolean equalsPassword(String password, String confirmPassword)
	{
		if(!password.equals(confirmPassword))
		{
			Validation.addError("notMatchPassword", "security.register.notMatchPassword");
			Validation.keep();	
		}
		
		return password.equals(confirmPassword);
	}
	
	/**
	 * for login a user
	 * @param passwordInput
	 * @param passwordUser
	 * @return
	 * @see #equalsPassword
	 */
	private static boolean checkPassword(String passwordInput, String passwordUser)
	{
		if(!passwordInput.equals(passwordUser))
		{
			Validation.addError("wrongPassword", "security.authenticate.wrongPassword");
			Validation.keep();	
		}
		
		return passwordInput.equals(passwordUser);
	}
	
	private static boolean usernameNotAlreadyExist(String username)
	{
		WallUser user = WallUser.find("byUsername", username).first();
		
		if(user!=null)
		{
			Validation.addError("usernameAlreadyExist", "security.register.usernameAlreadyExist");
			Validation.keep();	
		}
		
		return user==null;
	}
	
	private static boolean usernameAllowed(String username)
	{
		//TODO: do not allowed spaces and usernames with special characters and usernames like admin, Admin, wprog, wprogLK
		//TODO: implement it
		return true;
	}
}