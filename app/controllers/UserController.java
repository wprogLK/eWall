package controllers;

import models.User;
import play.mvc.Controller;
import play.mvc.With;

@With(Secure.class)
public class UserController extends Controller
{
	public static void home()
	{
		String username = Secure.Security.connected();
    	User currentUser = User.find("byUsername", username).first();
		render(currentUser);
	}
}
